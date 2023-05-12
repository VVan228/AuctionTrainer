package ru.isu.auc.auction.api.factorties;

import ru.isu.auc.auction.model.interval.Interval;
import ru.isu.auc.auction.model.types.IntervalType;

import java.util.List;

public interface IntervalFactory {
    Interval createLot(
        Long duration,
        Long entityId);
    Interval createLotPause(
        Long duration);
    Interval createRound(
        List<Interval> lots,
        Long entityId);
    Interval createRoundPause(
        Long duration);

    Interval createLot(
        Long duration,
        Long entityId,
        Boolean autostart);
    Interval createLotPause(
        Long duration,
        Boolean autostart);
    Interval createRound(
        List<Interval> lots,
        Long entityId,
        Boolean autostart);
    Interval createRoundPause(
        Long duration,
        Boolean autostart);
}
