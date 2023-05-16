package ru.isu.auc.auction.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.isu.auc.auction.api.NotificationService;
import ru.isu.auc.auction.api.entities.IntervalService;
import ru.isu.auc.auction.model.interval.IntervalPoint;
import ru.isu.auc.auction.model.notification.Notification;
import ru.isu.auc.auction.model.notification.payload.RoomEventPayload;
import ru.isu.auc.auction.model.types.Status;
import ru.isu.auc.messaging.service.MessagingService;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    IntervalService intervalService;
    @Autowired
    MessagingService messagingService;

    @Override
    public void sendIntervalPointNotifications(IntervalPoint intervalPoint) {
        RoomEventPayload payload = new RoomEventPayload();
        //first point in interval,
        //we need to check prev notifications
        checkPrevEndNotification(intervalPoint, payload, true);

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
        if(intervalPoint.getIntervalStartIds().isEmpty()) {
            payload.setRoomStatus(Status.ENDED);
        }else {
            payload.setRoomStatus(Status.ONGOING);
        }
        messagingService.sendNotification(
            Notification.createFromPayload(payload),
            "test");

    }

    @Override
    public void sendAutoendNotifications(IntervalPoint intervalPoint) {
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

    @Override
    public void sendManualStartNotifications(IntervalPoint intervalPoint) {
        RoomEventPayload payload = new RoomEventPayload();

        //we already sent notification (presumably))
        if(!intervalPoint.getAtLeastOneAutoStart())
            checkPrevEndNotification(intervalPoint, payload, false);

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
        if(intervalPoint.getIntervalStartIds().isEmpty()) {
            payload.setRoomStatus(Status.ENDED);
        }else {
            payload.setRoomStatus(Status.ONGOING);
        }
        messagingService.sendNotification(
            Notification.createFromPayload(payload),
            "test");
    }

    private void checkPrevEndNotification(
        IntervalPoint intervalPoint,
        RoomEventPayload payload,
        boolean checkAutoended) {

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
        if(!checkAutoended) return;
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
}
