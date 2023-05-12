package ru.isu.auc.auction.impl.factories;

import ru.isu.auc.auction.api.factorties.IntervalQueueFactory;
import ru.isu.auc.auction.model.interval.Interval;
import ru.isu.auc.auction.model.interval.IntervalPoint;
import ru.isu.auc.auction.model.interval.IntervalQueue;
import ru.isu.auc.auction.model.room.Room;
import ru.isu.auc.common.api.AbstractFactory;
import ru.isu.auc.common.impl.DefaultHashMap;

import java.util.*;
import java.util.stream.Collectors;

public class IntervalQueueFactoryImpl implements IntervalQueueFactory {
    @Override
    public IntervalQueue createFromIntervals(
        List<Interval> intervals
    ) {

        Set<Long> timestamps = new TreeSet<>();
        Map<Long, IntervalPoint> points = new DefaultHashMap<>(
            IntervalPoint::new);

        Long totalDuration = 0L;
        for(Interval i: intervals) {
            addTimestamps(
                points,
                timestamps,
                totalDuration,
                i);
            totalDuration += i.getDuration();
        }

        return new IntervalQueue()
            .setCurrentIndex(0L)
            .setIntervalPoints(
                timestamps.stream()
                    .map(t->points.get(t).setTimestamp(t))
                    .collect(Collectors.toList())
            );
    }

    private void addTimestamps(
        Map<Long, IntervalPoint> points,
        Set<Long> timestamps,
        Long startTime,
        Interval interval
    ) {
        Long endTime = startTime+interval.getDuration();
        points.get(startTime).addStartId(interval.getId());
        points.get(endTime).addEndId(interval.getId());
        timestamps.add(endTime);
        timestamps.add(startTime);

        Long curTimestamp = startTime;
        for (Interval i: interval.getIntervals()) {
            addTimestamps(
                points,
                timestamps,
                curTimestamp,
                i);
            curTimestamp += i.getDuration();
        }
    }
}
