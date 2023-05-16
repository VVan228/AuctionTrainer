package ru.isu.auc.auction.api;

import ru.isu.auc.scheduling.api.SchedulerException;

public interface TriggerCallback {
    void triggerFired(Long queueId, Long index);
}
