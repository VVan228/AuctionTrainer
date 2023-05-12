package ru.isu.auc.auction.api.factorties;

import ru.isu.auc.auction.model.interval.Interval;
import ru.isu.auc.auction.model.interval.IntervalQueue;

import java.util.List;

public interface IntervalQueueFactory {
    IntervalQueue createFromIntervals(List<Interval> intervals);
}
