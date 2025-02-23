package ru.isu.auc.auction.impl.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.isu.auc.auction.api.entities.IntervalQueueService;
import ru.isu.auc.auction.model.interval.IntervalQueue;
import ru.isu.auc.auction.repo.IntervalPointRepo;
import ru.isu.auc.auction.repo.IntervalQueueRepo;

import java.util.List;
import java.util.UUID;

@Service
public class IntervalQueueServiceImpl implements IntervalQueueService {
    @Autowired
    IntervalQueueRepo repo;

    @Autowired
    IntervalPointRepo pointRepo;

    @Override
    public IntervalQueue get(Long queueId) {
        return repo.getReferenceById(queueId);
    }

    @Override
    public void setCurrentIndex(Long queueId, Long index) {
        repo.setCurrentIndex(queueId, index);
    }

    @Override
    public IntervalQueue save(IntervalQueue intervalQueue) {
        pointRepo.saveAll(intervalQueue.getIntervalPoints());
        return repo.save(intervalQueue);
    }

    @Override
    public List<IntervalQueue> saveAll(Iterable<IntervalQueue> intervalQueue) {
        return repo.saveAll(intervalQueue);
    }

    @Override
    public IntervalQueue getByRoomUid(UUID roomUid) {
        return repo.getByRoomUid(roomUid);
    }

    @Override
    public Long getIntervalQIdByRoomUid(UUID roomUid) {
        return repo.getIntervalQIdByRoomUid(roomUid);
    }
}
