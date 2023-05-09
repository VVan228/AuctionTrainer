package ru.isu.auc.auction.model.notification.payload;

import lombok.Data;

@Data
public class TeamCaptainChangedPayload implements IPayload {
    Long teamId;
    Long oldCaptainId;
    Long newCaptainId;
}
