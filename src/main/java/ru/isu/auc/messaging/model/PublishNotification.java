package ru.isu.auc.messaging.model;

import lombok.Data;

@Data
public class PublishNotification {
    String method;
    Params params;
}
