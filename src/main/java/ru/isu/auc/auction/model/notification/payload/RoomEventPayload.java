package ru.isu.auc.auction.model.notification.payload;

import lombok.Data;
import ru.isu.auc.auction.model.interval.Interval;
import ru.isu.auc.auction.model.types.Status;

import java.util.ArrayList;
import java.util.List;

@Data
public class RoomEventPayload implements IPayload {
    List<Interval> intervalsStarted = new ArrayList<>();
    List<Interval> intervalsEnded = new ArrayList<>();
    Status roomStatus;

    public void addIntervalStart(Interval interval){
        intervalsStarted.add(0, interval);
    }
    public void addIntervalEnd(Interval interval){
        intervalsEnded.add(0, interval);
    }
}
