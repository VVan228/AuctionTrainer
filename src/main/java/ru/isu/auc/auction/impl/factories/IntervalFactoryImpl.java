package ru.isu.auc.auction.impl.factories;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.isu.auc.auction.api.factorties.IntervalFactory;
import ru.isu.auc.auction.model.interval.Interval;
import ru.isu.auc.auction.model.types.IntervalType;
import ru.isu.auc.auction.model.types.Status;

import java.util.List;
import java.util.UUID;

@Service
public class IntervalFactoryImpl implements IntervalFactory {

    @Value("${auction.defaults.autostart}")
    private boolean DEFAULT_AUTOSTART = true;
    @Value("${auction.defaults.autoend}")
    private boolean DEFAULT_AUTOEND = true;
    @Value("${auction.defaults.round_pause}")
    private Long DEFAULT_ROUND_PAUSE;
    @Value("${auction.defaults.lot_pause}")
    private Long DEFAULT_LOT_PAUSE;
    @Value("${auction.defaults.lot_duration}")
    private Long DEFAULT_LOT_DURATION;


    @Override
    public Interval createLot(Long duration, UUID entityUid) {
        return createLot(duration, entityUid, DEFAULT_AUTOSTART, DEFAULT_AUTOEND);
    }

    @Override
    public Interval createLotPause(Long duration) {
        return createLotPause(duration, DEFAULT_AUTOSTART, DEFAULT_AUTOEND);
    }

    @Override
    public Interval createRound(List<Interval> lots, UUID entityUid) {
        return createRound(lots, entityUid, DEFAULT_AUTOSTART, DEFAULT_AUTOEND);
    }

    @Override
    public Interval createRoundPause(Long duration) {
        return createRoundPause(duration, DEFAULT_AUTOSTART, DEFAULT_AUTOEND);
    }


    @Override
    public Interval createLot(
        Long duration,
        UUID entityUid,
        Boolean autostart,
        Boolean autoend) {
        return new Interval()
            .setDuration(duration==null?DEFAULT_LOT_DURATION:duration)
            .setAutostart(autostart==null?DEFAULT_AUTOSTART:autostart)
            .setAutoend(autoend==null?DEFAULT_AUTOEND:autoend)
            .setStatus(Status.SAVED)
            .setType(IntervalType.LOT)
            .setEntityUid(entityUid);
    }

    @Override
    public Interval createLotPause(
        Long duration,
        Boolean autostart,
        Boolean autoend) {
        return new Interval()
            .setDuration(duration==null?DEFAULT_LOT_PAUSE:duration)
            .setAutostart(autostart==null?DEFAULT_AUTOSTART:autostart)
            .setAutoend(autoend==null?DEFAULT_AUTOEND:autoend)
            .setStatus(Status.SAVED)
            .setType(IntervalType.LOT_PAUSE);
    }

    @Override
    public Interval createRound(
        List<Interval> lots,
        UUID entityUid,
        Boolean autostart,
        Boolean autoend) {
        return new Interval()
            .setDuration(
                lots.stream()
                    .map(Interval::getDuration)
                    .mapToLong(Long::longValue)
                    .sum()
            )
            .setAutostart(autostart==null?DEFAULT_AUTOSTART:autostart)
            .setAutoend(autoend==null?DEFAULT_AUTOEND:autoend)
            .setStatus(Status.SAVED)
            .setEntityUid(entityUid)
            .setIntervals(lots)
            .setType(IntervalType.ROUND);
    }

    @Override
    public Interval createRoundPause(
        Long duration,
        Boolean autostart,
        Boolean autoend) {
        return new Interval()
            .setDuration(duration==null?DEFAULT_ROUND_PAUSE:duration)
            .setAutostart(autostart==null?DEFAULT_AUTOSTART:autostart)
            .setAutoend(autoend==null?DEFAULT_AUTOEND:autoend)
            .setStatus(Status.SAVED)
            .setType(IntervalType.ROUND_PAUSE);
    }

}
