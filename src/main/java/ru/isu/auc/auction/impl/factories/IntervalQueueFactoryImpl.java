package ru.isu.auc.auction.impl.factories;

import org.springframework.stereotype.Component;
import ru.isu.auc.auction.api.factorties.IntervalQueueFactory;
import ru.isu.auc.auction.model.interval.*;
import ru.isu.auc.auction.model.room.Room;
import ru.isu.auc.common.impl.DefaultHashMap;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class IntervalQueueFactoryImpl implements IntervalQueueFactory {
    @Override
    public IntervalQueue createFromIntervals(
        Room room
    ) {

        Set<Long> timestamps = new TreeSet<>();
        Map<Long, IntervalPoint> points = new DefaultHashMap<>(
            IntervalPoint::new);

        Long totalDuration = 0L;
        for(Interval i: room.getIntervals()) {
            addTimestamps(
                points,
                timestamps,
                totalDuration,
                i);
            totalDuration += i.getDuration();
        }

        return new IntervalQueue()
            .setCurrentIndex(0L)
            .setRoomUid(room.getUid())
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

        IntervalPoint startPoint = points.get(startTime);
        IntervalPoint endPoint = points.get(endTime);

        startPoint.insertStartId(0,
            new ShortIntervalStart()
                .setIntervalId(interval.getId())
                .setAutostart(interval.getAutostart()));
        if(interval.getAutostart()!=null && !interval.getAutostart())
            startPoint.setAutostart(false);
        endPoint.insertEndId(0,
            new ShortIntervalEnd()
                .setIntervalId(interval.getId())
                .setAutoend(interval.getAutoend()));
        if(interval.getAutoend()!=null && !interval.getAutoend())
            endPoint.setAutoend(false);
        timestamps.add(endTime);
        timestamps.add(startTime);

//        if(startPoint.getAutostart() == null || startPoint.getAutostart())
//            startPoint.setAutostart(interval.getAutostart());
//        if(endPoint.getAutoend() == null || endPoint.getAutoend())
//            endPoint.setAutoend(interval.getAutoend());


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
