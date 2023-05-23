package ru.isu.auc.auction.model.notification.payload;

import lombok.Data;

@Data
public class UserLeftPayload implements IPayload{
    Long userId;
    String username;
    int currentAmount;

    public UserLeftPayload setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public UserLeftPayload setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserLeftPayload setCurrentAmount(int currentAmount) {
        this.currentAmount = currentAmount;
        return this;
    }
}
