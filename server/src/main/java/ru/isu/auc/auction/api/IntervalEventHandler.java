package ru.isu.auc.auction.api;

import ru.isu.auc.auction.model.interval.IntervalPoint;
import ru.isu.auc.auction.model.types.Status;

public interface IntervalEventHandler {
    void intervalEvent(IntervalPoint interval);
}
