package ru.isu.auc.messaging.model.notifications.payload;

import lombok.Data;

@Data
public class TeamCaptainChangedPayload {
    Long teamId;
    Long oldCaptainId;
    Long newCaptainId;
}
