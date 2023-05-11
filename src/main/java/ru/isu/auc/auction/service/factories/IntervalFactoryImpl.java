package ru.isu.auc.auction.service.factories;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.isu.auc.auction.model.interval.Interval;
import ru.isu.auc.auction.model.types.IntervalType;
import ru.isu.auc.auction.model.types.Status;

import java.util.List;

@Component
public class IntervalFactoryImpl implements IntervalFactory{

    @Value("${auction.defaults.autostart}")
    private boolean DEFAULT_AUTOSTART;
    @Value("${auction.defaults.end_on_all_answered}")
    private boolean DEFAULT_END_ON_ALL_ANSWERED;
    @Value("${auction.defaults.min_bet_step}")
    private Integer DEFAULT_MIN_BET_STEP;
    @Value("${auction.defaults.start_sum}")
    private Integer DEFAULT_START_SUM;
    @Value("${auction.defaults.limit_sum}")
    private Integer DEFAULT_LIMIT_SUM;
    @Value("${auction.defaults.round_pause}")
    private Long DEFAULT_ROUND_PAUSE;
    @Value("${auction.defaults.lot_pause}")
    private Long DEFAULT_LOT_PAUSE;
    @Value("${auction.defaults.lot_duration}")
    private Long DEFAULT_LOT_DURATION;


    @Override
    public Interval createLot(Long duration, Long entityId) {
        return createLot(duration, entityId, DEFAULT_AUTOSTART);
    }

    @Override
    public Interval createLotPause(Long duration) {
        return createLotPause(duration, DEFAULT_AUTOSTART);
    }

    @Override
    public Interval createRound(List<Interval> lots, Long entityId) {
        return createRound(lots, entityId, DEFAULT_AUTOSTART);
    }

    @Override
    public Interval createRoundPause(Long duration) {
        return createRoundPause(duration, DEFAULT_AUTOSTART);
    }


    @Override
    public Interval createLot(Long duration, Long entityId, Boolean autostart) {
        return new Interval()
            .setDuration(duration==null?DEFAULT_LOT_DURATION:duration)
            .setAutostart(autostart==null?DEFAULT_AUTOSTART:autostart)
            .setStatus(Status.SAVED)
            .setType(IntervalType.LOT)
            .setEntityId(entityId);
    }

    @Override
    public Interval createLotPause(Long duration, Boolean autostart) {
        return new Interval()
            .setDuration(duration==null?DEFAULT_LOT_DURATION:duration)
            .setAutostart(autostart==null?DEFAULT_AUTOSTART:autostart)
            .setStatus(Status.SAVED)
            .setType(IntervalType.LOT_PAUSE);
    }

    @Override
    public Interval createRound(List<Interval> lots, Long entityId, Boolean autostart) {
        return new Interval()
            .setDuration(
                lots.stream()
                    .map(Interval::getDuration)
                    .mapToLong(Long::longValue)
                    .sum()
            )
            .setAutostart(autostart==null?DEFAULT_AUTOSTART:autostart)
            .setStatus(Status.SAVED)
            .setEntityId(entityId)
            .setIntervals(lots)
            .setType(IntervalType.ROUND);
    }

    @Override
    public Interval createRoundPause(Long duration, Boolean autostart) {
        return new Interval()
            .setDuration(duration==null?DEFAULT_LOT_DURATION:duration)
            .setAutostart(autostart==null?DEFAULT_AUTOSTART:autostart)
            .setStatus(Status.SAVED)
            .setType(IntervalType.ROUND_PAUSE);
    }

}
