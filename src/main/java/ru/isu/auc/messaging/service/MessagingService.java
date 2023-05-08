package ru.isu.auc.messaging.service;

public interface MessagingService {
    void sendNotification(Object data, String channel);
}
