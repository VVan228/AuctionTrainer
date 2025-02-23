package ru.isu.auc.auction.api.factorties;

import ru.isu.auc.auction.model.interval.Interval;
import ru.isu.auc.auction.model.types.IntervalType;

import java.util.List;
import java.util.UUID;

public interface IntervalFactory {
    Interval createLot(
        Long duration,
        UUID entityUid);
    Interval createLotPause(
        Long duration);
    Interval createRound(
        List<Interval> lots,
        UUID entityUid);
    Interval createRoundPause(
        Long duration);

    Interval createLot(
        Long duration,
        UUID entityUid,
        Boolean autostart,
        Boolean autoend);
    Interval createLotPause(
        Long duration,
        Boolean autostart,
        Boolean autoend);
    Interval createRound(
        List<Interval> lots,
        UUID entityUid,
        Boolean autostart,
        Boolean autoend);
    Interval createRoundPause(
        Long duration,
        Boolean autostart,
        Boolean autoend);
}
