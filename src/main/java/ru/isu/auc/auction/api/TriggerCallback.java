package ru.isu.auc.auction.api;

public interface TriggerCallback {
    void triggerFired(Long queueId, Long index);
}
