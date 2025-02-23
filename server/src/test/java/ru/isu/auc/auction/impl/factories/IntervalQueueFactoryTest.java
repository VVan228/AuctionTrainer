package ru.isu.auc.auction.impl.factories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.isu.auc.auction.api.factorties.IntervalQueueFactory;
import ru.isu.auc.auction.model.interval.Interval;
import ru.isu.auc.auction.model.interval.IntervalPoint;
import ru.isu.auc.auction.model.interval.IntervalQueue;
import ru.isu.auc.auction.model.room.Room;
import ru.isu.auc.auction.model.types.IntervalType;

import java.util.Arrays;
import java.util.stream.Collectors;

public class IntervalQueueFactoryTest {

    IntervalQueueFactory factory;

    @BeforeEach
    public void setUp() {
        factory = new IntervalQueueFactoryImpl();
    }

    @Test
    public void test1() {
        ///

        Interval i11 = new Interval().setId(1L).setDuration(40L)
            .setAutostart(true).setAutoend(true)
            .setType(IntervalType.ROUND);

        Interval i211 = new Interval().setId(2L).setDuration(20L)
            .setAutostart(true).setAutoend(true)
            .setType(IntervalType.LOT);
        Interval i212 = new Interval().setId(3L).setDuration(5L)
            .setAutostart(true).setAutoend(true)
            .setType(IntervalType.LOT_PAUSE);
        Interval i213 = new Interval().setId(4L).setDuration(15L)
            .setAutostart(true).setAutoend(false)
            .setType(IntervalType.LOT);

        i11.setIntervals(Arrays.asList(i211, i212, i213));

        Interval i214 = new Interval().setId(5L).setDuration(25L)
            .setAutostart(true).setAutoend(true)
            .setType(IntervalType.ROUND_PAUSE);

        ///

        Interval i12 = new Interval().setId(6L).setDuration(82L)
            .setAutostart(true).setAutoend(true)
            .setType(IntervalType.ROUND);

        Interval i221 = new Interval().setId(7L).setDuration(30L)
            .setAutostart(true).setAutoend(true)
            .setType(IntervalType.LOT);
        Interval i222 = new Interval().setId(8L).setDuration(12L)
            .setAutostart(true).setAutoend(true)
            .setType(IntervalType.LOT_PAUSE);
        Interval i223 = new Interval().setId(9L).setDuration(40L)
            .setAutostart(true).setAutoend(true)
            .setType(IntervalType.LOT);

        i12.setIntervals(Arrays.asList(i221, i222, i223));

        Interval i224 = new Interval().setId(10L).setDuration(10L)
            .setAutostart(true).setAutoend(true)
            .setType(IntervalType.ROUND_PAUSE);

        ///

        Interval i13 = new Interval().setId(11L).setDuration(50L)
            .setAutostart(true).setAutoend(true)
            .setType(IntervalType.ROUND);

        Interval i231 = new Interval().setId(12L).setDuration(20L)
            .setAutostart(true).setAutoend(true)
            .setType(IntervalType.LOT);
        Interval i232 = new Interval().setId(13L).setDuration(10L)
            .setAutostart(true).setAutoend(true)
            .setType(IntervalType.LOT_PAUSE);
        Interval i233 = new Interval().setId(14L).setDuration(20L)
            .setAutostart(true).setAutoend(true)
            .setType(IntervalType.LOT);

        i13.setIntervals(Arrays.asList(i231, i232, i233));

        IntervalQueue res = factory.createFromIntervals(
            new Room()
                .setIntervals(
                    Arrays.asList(i11, i214, i12, i224, i13)));
        System.out.println(res.getIntervalPoints());
        System.out.println(res.getIntervalPoints()
            .stream().map(IntervalPoint::getTimestamp)
            .collect(Collectors.toList()));
    }
}
