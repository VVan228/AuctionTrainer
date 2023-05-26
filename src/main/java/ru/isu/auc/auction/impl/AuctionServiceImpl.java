package ru.isu.auc.auction.impl;

import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
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
import ru.isu.auc.auction.model.room.Room;
import ru.isu.auc.auction.model.types.Status;
import ru.isu.auc.scheduling.api.SchedulerException;
import ru.isu.auc.scheduling.api.SchedulerService;
import ru.isu.auc.scheduling.impl.JobHelper;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class AuctionServiceImpl implements TriggerCallback, AuctionService {

    final IntervalQueueService intervalQueueService;
    final SchedulerService schedulerService;
    final IntervalService intervalService;
    final NotificationService notificationService;

    @Autowired
    JobHelper jobHelper;

    public AuctionServiceImpl(
        IntervalQueueService intervalQueueService,
        SchedulerService schedulerService,
        IntervalService intervalService,
        NotificationService notificationService
    ) {
        this.intervalQueueService = intervalQueueService;
        this.schedulerService = schedulerService;
        this.intervalService = intervalService;
        this.notificationService = notificationService;
    }

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
            //autoendNotification(curIp); (old)
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
                //autoendNotification(nextIp); (old)
                notificationService.sendAutoendNotifications(nextIp);
            }
            return;
        }

        //there are some autostarts, we send notification
        //autostartNotification(curIp); (old)
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


    @SneakyThrows
    @Override
    public void scheduleRoom(Room room) {
        if(room.getStartTime()==null) {
            return;
        }
        jobHelper.clearHistory();
        var time = new DateTime(
            room.getStartTime().atZone(ZoneId.systemDefault())
                .toInstant().toEpochMilli());
        System.out.println(DateTime.now().compareTo(time));
        schedulerService.startJob(
            0,
            intervalQueueService.getIntervalQIdByRoomUid(room.getUid()),
            0L,
            time
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

        if(room.getStatus().equals(Status.SAVED)){
            handleIntervalPoint(q, i);
            return;
        }

        if(
            (i==0 || ip.getAtLeastOneManualStart()) &&
            firstStartInterval.getStatus().equals(Status.SAVED)) {

            handleUserStartPoint(q, i);
            return;
        }
        if(
            ( i+1<q.getIntervalPoints().size() &&
            q.getIntervalPoints().get(i+1).getAtLeastOneManualEnd() ) &&
                (firstStartInterval.getStatus().equals(Status.ONGOING) ||
                firstStartInterval.getStatus().equals(Status.ENDED))) {

            handleUserEndPoint(q, i);
        }
    }

    void handleUserEndPoint(IntervalQueue q, int index)
    throws SchedulerException {

        //if room ended, send notifications
        if(index+1<q.getIntervalPoints().size() &&
            q.getIntervalPoints().get(index+1).getIntervalStartIds().isEmpty()) {

            //autostartNotification(q.getIntervalPoints().get(index+1)); (old)
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
        //if(curPi.getAtLeastOneManualStart())
        notificationService.sendManualStartNotifications(curPi);
            //manualStartNotification(curPi); (old)

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
