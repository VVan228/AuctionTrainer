package ru.isu.auc.scheduling.api;

public interface SchedulerService {
    void startJob(Long queueId, Long index, Integer duration) throws SchedulerException;
}
