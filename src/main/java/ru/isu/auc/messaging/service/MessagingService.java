package ru.isu.auc.messaging.service;

import ru.isu.auc.auction.model.notification.Notification;

public interface MessagingService {
    void sendNotification(Notification<?> data, String channel);
}
