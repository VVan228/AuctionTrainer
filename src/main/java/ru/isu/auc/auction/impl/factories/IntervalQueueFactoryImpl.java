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

    @Override
    public void saveIds(IntervalQueue q) {
        for(var ip: q.getIntervalPoints()){
            ip.setQueueId(q.getId());
        }
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

        if(startPoint.getAtLeastOneManualStart())
            interval.setAutostart(false);

        startPoint.insertStartId(0,
            new ShortIntervalStart()
                .setIntervalId(interval.getId())
                .setAutostart(interval.getAutostart()));

        if(interval.getAutostart())
            startPoint.setAtLeastOneAutoStart(true);
        if(!interval.getAutostart())
            startPoint.setAtLeastOneManualStart(true);

        //
        if(endPoint.getAtLeastOneAutoEnd())
            interval.setAutoend(true);

        endPoint.insertEndId(0,
            new ShortIntervalEnd()
                .setIntervalId(interval.getId())
                .setAutoend(interval.getAutoend()));

        if(interval.getAutoend())
            endPoint.setAtLeastOneAutoEnd(true);
        if(!interval.getAutoend())
            endPoint.setAtLeastOneManualEnd(true);

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
