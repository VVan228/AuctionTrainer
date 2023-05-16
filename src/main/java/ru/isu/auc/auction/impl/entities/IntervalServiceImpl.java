package ru.isu.auc.auction.impl.entities;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.isu.auc.auction.api.entities.IntervalService;
import ru.isu.auc.auction.model.EntityNotFoundException;
import ru.isu.auc.auction.model.interval.Interval;
import ru.isu.auc.auction.model.types.Status;
import ru.isu.auc.auction.repo.IntervalRepo;

import java.util.List;

@Component
public class IntervalServiceImpl implements IntervalService {
    @Autowired
    IntervalRepo intervalRepo;

    @Override
    public void save(Interval interval) {
        intervalRepo.save(interval);
    }

    @Override
    public List<Interval> saveAll(Iterable<Interval> intervals) {
        return intervalRepo.saveAll(intervals);
    }

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
        return i;
    }
}
