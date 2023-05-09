package ru.isu.auc.messaging.model.notifications.payload;

import jakarta.validation.Payload;
import lombok.Data;

@Data
public class TeamUserJoinedPayload implements Payload {
    Long teamId;
    Long userId;
}
