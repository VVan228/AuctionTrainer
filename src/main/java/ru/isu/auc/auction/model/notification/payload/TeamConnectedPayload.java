package ru.isu.auc.auction.model.notification.payload;

import lombok.Data;

@Data
public class TeamConnectedPayload implements IPayload {
    Long teamId;
}
