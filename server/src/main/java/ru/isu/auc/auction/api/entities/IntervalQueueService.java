package ru.isu.auc.auction.api.entities;

import ru.isu.auc.auction.model.interval.IntervalQueue;

import java.util.List;
import java.util.UUID;

public interface IntervalQueueService {
    IntervalQueue get(Long queueId);
    IntervalQueue save(IntervalQueue intervalQueue);
    List<IntervalQueue> saveAll(Iterable<IntervalQueue> intervalQueue);

    IntervalQueue getByRoomUid(UUID roomUid);
    void setCurrentIndex(Long queueId, Long index);
    Long getIntervalQIdByRoomUid(UUID roomUid);
}
