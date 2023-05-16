package ru.isu.auc.auction.model.notification.payload;

import lombok.Data;

@Data
public class UserPayload implements IPayload {
    Long userId;

    public UserPayload setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
}
