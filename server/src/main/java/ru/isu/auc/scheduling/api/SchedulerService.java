package ru.isu.auc.scheduling.api;

import org.joda.time.DateTime;
import ru.isu.auc.auction.model.interval.IntervalQueue;

public interface SchedulerService {
    void startJob(int cycle, Long queueId, Long index, Integer duration) throws SchedulerException;
    void startJob(int cycle, Long queueId, Long index, DateTime dateTime) throws SchedulerException;
    void stopJob( Long queueId) throws SchedulerException;
}
