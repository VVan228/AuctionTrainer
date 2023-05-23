package ru.isu.auc.service;

import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.isu.auc.auction.model.interval.Interval;
import ru.isu.auc.auction.model.dto.request.CreateDefaultRoomRequest;
import ru.isu.auc.auction.model.dto.request.RoundRequestPart;
import ru.isu.auc.auction.model.room.BetParams;
import ru.isu.auc.common.api.SettingHandler;
import ru.isu.auc.common.impl.SettingHandlerImpl;

import java.util.Arrays;
import java.util.List;

public class SettingHandlerTest {

    SettingHandler settingHandler;

    @BeforeEach
    public void load()
    {
        settingHandler = new SettingHandlerImpl();
    }

    @Test
    public void basic()
    {
        BetParams b = new BetParams()
            .setLimitSum(122)
            .setEndOnAllAnswered(true);
        Integer val = settingHandler.resolveSetting("limitSum", List.of(b));
        Assert.isTrue(val == 122);

        Boolean val2 = settingHandler.resolveSetting("endOnAllAnswered", List.of(b));
        Assert.isTrue(val2);
    }

    @Test
    public void advanced()
    {
        BetParams a = new BetParams()
            .setLimitSum(null)
            .setEndOnAllAnswered(null);
        BetParams b = new BetParams()
            .setLimitSum(122)
            .setEndOnAllAnswered(null);
        BetParams c = new BetParams()
            .setLimitSum(155)
            .setEndOnAllAnswered(true);
        Integer val = settingHandler.resolveSetting("limitSum", List.of(a, b, c));
        Assert.isTrue(val == 122);

        Boolean val2 = settingHandler.resolveSetting("endOnAllAnswered", List.of(a, b, c));
        Assert.isTrue(val2);
    }

    @Test
    public void insane()
    {
        Interval a = new Interval();
        BetParams b = new BetParams()
            .setLimitSum(120);
        BetParams c = new BetParams()
            .setLimitSum(155)
            .setEndOnAllAnswered(true);
        Integer val = settingHandler.resolveSetting("limitSum", List.of(a, b, c));
        Assert.isTrue(val == 120);

        Boolean val2 = settingHandler.resolveSetting("endOnAllAnswered", List.of(a, b, c));
        Assert.isTrue(val2);
    }

    @Test
    public void mastermind()
    {
        BetParams a = null;
        BetParams b = new BetParams()
            .setLimitSum(120);
        BetParams c = new BetParams()
            .setLimitSum(155)
            .setEndOnAllAnswered(true);
        Integer val = settingHandler.resolveSetting("limitSum", Arrays.asList(a, b, c));
        Assert.isTrue(val == 120);

        Boolean val2 = settingHandler.resolveSetting("endOnAllAnswered", Arrays.asList(a, b, c));
        Assert.isTrue(val2);
    }

    @Test
    public void defaultValue()
    {
        BetParams a = null;
        BetParams b = null;
        BetParams c = null;
        Integer val = settingHandler.resolveSetting(
            "limitSum",
            120,
            Arrays.asList(a, b, c));
        Assert.isTrue(val == 120);

        Boolean val2 = settingHandler.resolveSetting(
            "endOnAllAnswered",
            true,
            Arrays.asList(a, b, c));
        Assert.isTrue(val2);
    }

    @Test
    public void fieldNames1()
    {
        CreateDefaultRoomRequest a = new CreateDefaultRoomRequest();
        RoundRequestPart b = new RoundRequestPart();

        a.setDefaultRoundPause(null);
        b.setRoundPause(100L);

        Long val = settingHandler.resolveSetting(
            List.of("defaultRoundPause", "roundPause"),
            Arrays.asList(a, b));
        Assert.isTrue(val == 100L);
    }
    @Test
    public void fieldNames2()
    {
        CreateDefaultRoomRequest a = new CreateDefaultRoomRequest();
        RoundRequestPart b = new RoundRequestPart();

        Long val = settingHandler.resolveSetting(
            List.of("defaultRoundPause", "roundPause"),
            100L,
            Arrays.asList(a, b));
        Assert.isTrue(val == 100L);
    }
}
