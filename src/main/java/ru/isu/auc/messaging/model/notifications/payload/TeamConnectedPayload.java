package ru.isu.auc.messaging.model.notifications.payload;

import lombok.Data;

@Data
public class TeamConnectedPayload implements IPayload {
    Long teamId;
}
