package ru.isu.auc.messaging.model;

import lombok.Data;

@Data
public class Params {
    String channel;
    Object data;
}
