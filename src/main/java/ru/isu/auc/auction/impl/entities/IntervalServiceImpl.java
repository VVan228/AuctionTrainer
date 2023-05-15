package ru.isu.auc.auction.impl.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.isu.auc.auction.api.entities.IntervalService;
import ru.isu.auc.auction.model.interval.Interval;
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
    public Interval get(Long intervalId) {
        return intervalRepo.getReferenceById(intervalId);
    }
}
