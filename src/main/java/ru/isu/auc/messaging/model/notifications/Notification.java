package ru.isu.auc.messaging.model.notifications;

import ru.isu.auc.messaging.model.notifications.payload.IPayload;

public class Notification <P extends IPayload> {
    P payload;
    NotificationType type;
}
