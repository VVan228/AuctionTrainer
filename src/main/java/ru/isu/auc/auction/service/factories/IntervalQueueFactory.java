package ru.isu.auc.auction.service.factories;

import ru.isu.auc.auction.model.interval.Interval;
import ru.isu.auc.auction.model.interval.IntervalQueue;
import ru.isu.auc.auction.model.room.Room;

public interface IntervalQueueFactory {
    IntervalQueue createFromRoom(Room interval);
}
