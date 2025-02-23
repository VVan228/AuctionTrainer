package ru.isu.auc.auction.api;

public interface ChannelProvider {
    String getChannel(Long roomId, boolean isAdmin);
}
