package ru.isu.auc.auction.model.notification.payload;

import jakarta.validation.Payload;
import lombok.Data;

@Data
public class TeamUserJoinedPayload implements Payload {
    Long teamId;
    Long userId;
}
