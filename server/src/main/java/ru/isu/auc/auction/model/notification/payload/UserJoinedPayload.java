package ru.isu.auc.auction.model.notification.payload;

import lombok.Data;

@Data
public class UserJoinedPayload implements IPayload{
    Long userId;
    String username;
    int currentAmount;

    public UserJoinedPayload setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public UserJoinedPayload setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserJoinedPayload setCurrentAmount(int currentAmount) {
        this.currentAmount = currentAmount;
        return this;
    }
}
