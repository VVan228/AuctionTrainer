package ru.isu.auc.auction.api.entities;

import ru.isu.auc.auction.model.interval.IntervalQueue;

import java.util.List;
import java.util.UUID;

public interface IntervalQueueService {
    IntervalQueue get(Long queueId);
    void setCurrentIndex(Long queueId, Long index);

    IntervalQueue save(IntervalQueue intervalQueue);
    List<IntervalQueue> saveAll(Iterable<IntervalQueue> intervalQueue);

    IntervalQueue getByRoomUid(UUID roomUid);
    Long getIntervalQIdByRoomUid(UUID roomUid);
}
