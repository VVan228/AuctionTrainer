package ru.isu.auc.auction.model.notification.payload;

import lombok.Data;

@Data
public class UserPayload implements IPayload {
    Long userId;
}
