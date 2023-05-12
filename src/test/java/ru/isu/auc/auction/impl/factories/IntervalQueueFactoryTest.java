package ru.isu.auc.auction.impl.factories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.isu.auc.auction.api.factorties.IntervalQueueFactory;
import ru.isu.auc.auction.model.interval.Interval;
import ru.isu.auc.auction.model.interval.IntervalPoint;
import ru.isu.auc.auction.model.interval.IntervalQueue;

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

        Interval i11 = new Interval().setId(1L).setDuration(40L);

        Interval i211 = new Interval().setId(2L).setDuration(20L);
        Interval i212 = new Interval().setId(3L).setDuration(5L);
        Interval i213 = new Interval().setId(4L).setDuration(15L);

        i11.setIntervals(Arrays.asList(i211, i212, i213));

        Interval i214 = new Interval().setId(5L).setDuration(25L);

        ///

        Interval i12 = new Interval().setId(6L).setDuration(82L);

        Interval i221 = new Interval().setId(7L).setDuration(30L);
        Interval i222 = new Interval().setId(8L).setDuration(12L);
        Interval i223 = new Interval().setId(9L).setDuration(40L);

        i12.setIntervals(Arrays.asList(i221, i222, i223));

        Interval i224 = new Interval().setId(10L).setDuration(10L);

        ///

        Interval i13 = new Interval().setId(11L).setDuration(50L);

        Interval i231 = new Interval().setId(12L).setDuration(20L);
        Interval i232 = new Interval().setId(13L).setDuration(10L);
        Interval i233 = new Interval().setId(14L).setDuration(20L);

        i13.setIntervals(Arrays.asList(i231, i232, i233));

        IntervalQueue res = factory.createFromIntervals(
            Arrays.asList(i11, i214, i12, i224, i13));
        System.out.println(res.getIntervalPoints());
        System.out.println(res.getIntervalPoints()
            .stream().map(IntervalPoint::getTimestamp)
            .collect(Collectors.toList()));
    }
}
