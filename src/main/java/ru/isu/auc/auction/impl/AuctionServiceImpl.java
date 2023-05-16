package ru.isu.auc.auction.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.aspectj.weaver.ast.Not;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.isu.auc.auction.api.AuctionService;
import ru.isu.auc.auction.api.NotificationService;
import ru.isu.auc.auction.api.TriggerCallback;
import ru.isu.auc.auction.api.entities.IntervalQueueService;
import ru.isu.auc.auction.api.entities.IntervalService;
import ru.isu.auc.auction.model.interval.Interval;
import ru.isu.auc.auction.model.interval.IntervalPoint;
import ru.isu.auc.auction.model.interval.IntervalQueue;
import ru.isu.auc.auction.model.interval.ShortIntervalStart;
import ru.isu.auc.auction.model.notification.Notification;
import ru.isu.auc.auction.model.notification.payload.RoomEventPayload;
import ru.isu.auc.auction.model.room.Room;
import ru.isu.auc.auction.model.types.Status;
import ru.isu.auc.messaging.service.MessagingService;
import ru.isu.auc.scheduling.api.SchedulerException;
import ru.isu.auc.scheduling.api.SchedulerService;
import ru.isu.auc.security.model.NotAllowedException;

import java.time.ZoneId;
import java.util.Date;

@Service
public class AuctionServiceImpl implements TriggerCallback, AuctionService {

    @Autowired
    MessagingService messagingService;

    @Autowired
    IntervalQueueService intervalQueueService;

    @Autowired
    SchedulerService schedulerService;

    @Autowired
    IntervalService intervalService;

    @Autowired
    NotificationService notificationService;

    @SneakyThrows
    @Transactional
    @Override
    public void triggerFired(Long queueId, Long index) {
        IntervalQueue queue = intervalQueueService.get(queueId);

        handleIntervalPoint(queue, Math.toIntExact(index));
    }

    private void handleIntervalPoint(IntervalQueue intervalQueue, int index) throws SchedulerException {
        IntervalPoint curIp = intervalQueue.getIntervalPoints()
            .get(index);

        if (curIp.getIntervalStartIds().isEmpty() &&
            index == intervalQueue.getIntervalPoints().size()-1) {
            //autoendNotification(curIp);
            notificationService.sendAutoendNotifications(curIp);
            return;
        }
        IntervalPoint nextIp = intervalQueue.getIntervalPoints()
            .get(index+1);

        //we are on second check in this interval
        //that means that its trigger for autoends
        if(
            intervalService.get(
            curIp.getIntervalStartIds()
                .get(0).getIntervalId())
            .getStatus().equals(Status.ONGOING)) {

            //if all are autoend, interval is over
            if (!nextIp.getAtLeastOneManualEnd()) {

                intervalQueue.setCurrentIndex((long) index + 1);
                intervalQueueService.setCurrentIndex(
                    intervalQueue.getId(), (long) index + 1);
                handleIntervalPoint(intervalQueue, index + 1);
            } else {
                //if there are manuals, we need to wait for them,
                //we send autoend notification
                //autoendNotification(nextIp);
                notificationService.sendAutoendNotifications(nextIp);
            }
            return;
        }

        //there are some autostarts, we send notification
        //autostartNotification(curIp);
        notificationService.sendIntervalPointNotifications(curIp);

        //first circle
        //all are manuals -> we wait
        if(!curIp.getAtLeastOneAutoStart()) return;

        //there are manuals starts, we need to wait for them
        if(curIp.getAtLeastOneManualStart()) return;


        //all are autostarts, next interval is all autoends,
        //we can schedule next interval
        if(!nextIp.getAtLeastOneManualEnd()) {
            intervalQueue.setCurrentIndex((long) index+1);
            intervalQueueService.setCurrentIndex(
                intervalQueue.getId(), (long) index+1);

            schedulerService.startJob(
                0,
                intervalQueue.getId(),
                (long) index+1,
                (int) (nextIp.getTimestamp() - curIp.getTimestamp())
            );
            return;
        }

        //there is autoend, we still need to schedule them
        if(nextIp.getAtLeastOneAutoEnd()) {
            schedulerService.startJob(
                1,
                intervalQueue.getId(),
                (long) index,
                (int) (nextIp.getTimestamp() - curIp.getTimestamp())
            );
        }

        //there are no autoends, we wait
    }

    void manualStartNotification(IntervalPoint intervalPoint) {
        RoomEventPayload payload = new RoomEventPayload();

        //we already sent notification (presumably))
        if(!intervalPoint.getAtLeastOneAutoStart())
            checkPrevEndNotification(intervalPoint, payload);

        //we add all manualstarts
        for (var intervalStart: intervalPoint.getIntervalStartIds()) {
            if(!intervalStart.getAutostart()) {
                payload.addIntervalStart(
                    intervalService.setStatus(
                        intervalStart.getIntervalId(),
                        Status.ONGOING));
            }
        }

        //we check room state
        payload.setRoomStatus(getRoomState(intervalPoint));
        messagingService.sendNotification(
            Notification.createFromPayload(payload),
            "test");
    }

    Status getRoomState(IntervalPoint intervalPoint) {
        if(intervalPoint.getIntervalStartIds().isEmpty()) {
            return Status.ENDED;
        }else {
            return  Status.ONGOING;
        }
    }

    void autostartNotification(
        IntervalPoint intervalPoint) {

        RoomEventPayload payload = new RoomEventPayload();
        //first point in interval,
        //we need to check prev notifications
        checkPrevEndNotification(intervalPoint, payload);

        //we add all autostarts
        for (var intervalStart: intervalPoint.getIntervalStartIds()) {
            if(intervalStart.getAutostart()) {
                payload.addIntervalStart(
                    intervalService.setStatus(
                        intervalStart.getIntervalId(),
                        Status.ONGOING));
            }
        }

        //we check room state
        payload.setRoomStatus(getRoomState(intervalPoint));
        messagingService.sendNotification(
            Notification.createFromPayload(payload),
            "test");
    }

    void autoendNotification(IntervalPoint intervalPoint) {
        //if there are no autoend, no need for notification
        if(!intervalPoint.getAtLeastOneAutoEnd()) return;

        //we add all autoend intervals
        RoomEventPayload payload = new RoomEventPayload();
        intervalPoint.getIntervalEndIds().forEach(
            end->{
                if(end.getAutoend()) {
                    payload.addIntervalEnd(
                        intervalService.setStatus(
                            end.getIntervalId(),
                            Status.ENDED)
                    );
                }
            });
        //payload.setRoomStatus(getRoomState(intervalPoint));
        if(intervalPoint.getIntervalStartIds().isEmpty() &&
            !intervalPoint.getAtLeastOneManualEnd()) {

            payload.setRoomStatus(Status.ENDED);
        }else {
            payload.setRoomStatus(Status.ONGOING);
        }
        messagingService.sendNotification(
            Notification.createFromPayload(payload),
            "test");
    }

    void checkPrevEndNotification(
        IntervalPoint intervalPoint,
        RoomEventPayload payload) {

        //in prev interval manuals are latest,
        //we need to send notification
        intervalPoint.getIntervalEndIds().forEach(
            end->{
                if(!end.getAutoend()) {
                    payload.addIntervalEnd(
                        intervalService.setStatus(
                            end.getIntervalId(),
                            Status.ENDED)
                    );
                }
            });
        //if there are no manuals, all are autos,
        //we need to send them all
        if(payload.getIntervalsEnded().isEmpty()) {
            intervalPoint.getIntervalEndIds().forEach(
                end->{
                    if(end.getAutoend()) {
                        payload.addIntervalEnd(
                            intervalService.setStatus(
                                end.getIntervalId(),
                                Status.ENDED)
                        );
                    }
                });
        }
    }

    @SneakyThrows
    @Override
    public void scheduleRoom(Room room) {
        if(room.getStartTime()==null) {
            return;
        }
        var time = new DateTime(
            room.getStartTime().atZone(ZoneId.systemDefault())
                .toInstant().toEpochMilli());
        System.out.println(DateTime.now().compareTo(time));
        schedulerService.startJob(
            0,
            intervalQueueService.getIntervalQIdByRoomUid(room.getUid()),
            0L,
            //TODO: bring back
            1000//time
            );
    }


    @Override
    @Transactional
    @SneakyThrows
    public void handleUserAction(Room room) {
        IntervalQueue q = intervalQueueService.getByRoomUid(room.getUid());

        int i = Math.toIntExact(q.getCurrentIndex());
        IntervalPoint ip = q.getIntervalPoints().get(i);

        Interval firstStartInterval = intervalService.get(
            ip.getIntervalStartIds().get(0)
                .getIntervalId());
        if(firstStartInterval.getStatus().equals(Status.SAVED)) {

            handleUserStartPoint(q, i);
            return;
        }
        if(
            firstStartInterval.getStatus().equals(Status.ONGOING) ||
            firstStartInterval.getStatus().equals(Status.ENDED)) {

            handleUserEndPoint(q, i);
        }
    }

    void handleUserEndPoint(IntervalQueue q, int index)
    throws SchedulerException {

        //if room ended, send notifications
        if(index+1<q.getIntervalPoints().size() &&
            q.getIntervalPoints().get(index+1).getIntervalStartIds().isEmpty()) {

            //autostartNotification(q.getIntervalPoints().get(index+1));
            notificationService.sendIntervalPointNotifications(q.getIntervalPoints().get(index+1));
            return;
        }

        IntervalPoint nextIp = q.getIntervalPoints().get(index+1);
        if(!nextIp.getAtLeastOneAutoStart()) {

            q.setCurrentIndex((long) index + 1);
            intervalQueueService.setCurrentIndex(
                q.getId(), (long) index + 1);
            handleUserStartPoint(q, index+1);
            return;
        }

        //next point
        q.setCurrentIndex((long) index + 1);
        intervalQueueService.setCurrentIndex(
            q.getId(), (long) index + 1);
        handleIntervalPoint(q, index + 1);
    }

    void handleUserStartPoint(IntervalQueue q, int index)
    throws SchedulerException {

        var curPi = q.getIntervalPoints().get(index);
        var nextPi = q.getIntervalPoints().get(index+1);

        //if all are autostarts, we need to start room,
        //therefore no need to send empty notification
        if(curPi.getAtLeastOneManualStart())
            manualStartNotification(curPi);

        //if there are autoends, but alse manuals, we schedule same point
        if(nextPi.getAtLeastOneManualEnd() && nextPi.getAtLeastOneAutoEnd()) {
            schedulerService.startJob(
                1,
                q.getId(),
                (long) index,
                (int) (nextPi.getTimestamp() - curPi.getTimestamp())
            );
        }
        //all are autoends, we can schedule next point
        if(nextPi.getAtLeastOneAutoEnd() && !nextPi.getAtLeastOneManualEnd()) {
            q.setCurrentIndex((long) index+1);
            intervalQueueService.setCurrentIndex(
                q.getId(), (long) index+1);

            schedulerService.startJob(
                1,
                q.getId(),
                (long) index+1,
                (int) (nextPi.getTimestamp() - curPi.getTimestamp())
            );
        }
    }
}
