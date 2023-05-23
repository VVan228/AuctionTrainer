package ru.isu.auc.auction.api;

import ru.isu.auc.auction.model.notification.Notification;

public interface ChannelProvider {
    String getChannel(Long roomId, boolean isAdmin);
}
