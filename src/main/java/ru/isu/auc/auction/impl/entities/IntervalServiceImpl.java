package ru.isu.auc.auction.impl.entities;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.isu.auc.auction.api.entities.IntervalService;
import ru.isu.auc.auction.api.entities.LotService;
import ru.isu.auc.auction.model.EntityNotFoundException;
import ru.isu.auc.auction.model.interval.Interval;
import ru.isu.auc.auction.model.types.IntervalType;
import ru.isu.auc.auction.model.types.Status;
import ru.isu.auc.auction.repo.IntervalRepo;

import java.util.List;

@Component
public class IntervalServiceImpl implements IntervalService {
    @Autowired
    IntervalRepo intervalRepo;
    @Autowired
    LotService lotService;

    @Override
    public void save(Interval interval) {
        intervalRepo.save(interval);
    }

    @Override
    public List<Interval> saveAll(Iterable<Interval> intervals) {
        return intervalRepo.saveAll(intervals);
    }

    //TODO: cache put
    @Override
    @SneakyThrows
    public Interval get(Long intervalId) {
        return intervalRepo.findById(intervalId).orElseThrow(EntityNotFoundException::interval);
    }

    //TODO: cache update
    @Override
    @SneakyThrows
    public Interval setStatus(Long intervalId, Status status) {
        Interval i = intervalRepo.findById(intervalId).orElseThrow(
            EntityNotFoundException::interval);

        i.setStatus(status);

        if(status.equals(Status.ENDED) && i.getType().equals(IntervalType.LOT)) {
            lotService.setWinner(i.getEntityUid());
        }
        i = intervalRepo.save(i);
        return i;
    }

    @Override
    public Long getRoomIdByQueueId(Long queueId) {
        return intervalRepo.getRoomIdBuQueueId(queueId);
    }


    @Override
    public Interval getByLotId(Long lotId) {
        return intervalRepo.getByLotId(lotId);
    }
}
