package ru.isu.auc.auction.api.factorties;

import ru.isu.auc.auction.model.interval.Interval;
import ru.isu.auc.auction.model.interval.IntervalQueue;
import ru.isu.auc.auction.model.room.Room;

public interface IntervalQueueFactory {
    IntervalQueue createFromRoom(Room interval);
}
