package ru.isu.auc.auction.impl.factories;

import org.javatuples.Triplet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.isu.auc.auction.api.factorties.IntervalFactory;
import ru.isu.auc.auction.api.factorties.RoomFactory;
import ru.isu.auc.auction.model.interval.Interval;
import ru.isu.auc.auction.model.dto.request.CreateDefaultRoomRequest;
import ru.isu.auc.auction.model.dto.request.LotRequestPart;
import ru.isu.auc.auction.model.dto.request.RoundRequestPart;
import ru.isu.auc.auction.model.room.BetParams;
import ru.isu.auc.auction.model.room.Lot;
import ru.isu.auc.auction.model.room.Room;
import ru.isu.auc.auction.model.room.Round;
import ru.isu.auc.auction.model.types.RoomType;
import ru.isu.auc.auction.model.types.Status;
import ru.isu.auc.common.api.SettingHandler;
import ru.isu.auc.security.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DefaultRoomFactory implements RoomFactory<CreateDefaultRoomRequest> {

    @Value("${auction.defaults.end_on_all_answered}")
    private boolean DEFAULT_END_ON_ALL_ANSWERED;
    @Value("${auction.defaults.min_bet_step}")
    private Integer DEFAULT_MIN_BET_STEP;
    @Value("${auction.defaults.start_sum}")
    private Integer DEFAULT_START_SUM;
    @Value("${auction.defaults.limit_sum}")
    private Integer DEFAULT_LIMIT_SUM;

    @Autowired
    public IntervalFactory intervalFactory;

    @Autowired
    public SettingHandler settingHandler;


    public DefaultRoomFactory(
        IntervalFactory intervalFactory,
        SettingHandler settingHandler) {
        this.intervalFactory = intervalFactory;
        this.settingHandler = settingHandler;
    }

    //для стандартной комнаты only
    boolean isRoundAsc(int index){
        return index%2==0;
    }

    @Override
    public Triplet<Room, List<Lot>, List<Round>> createRoom(CreateDefaultRoomRequest request, User creator) {
        Room room = new Room()
            .setCreator(creator)
            .setName(request.getName())
            .setStartTime(request.getStartTime())
            .setRoomType(RoomType.SINGLE_LAYER);
            //.setStatus(Status.SAVED);
        List<Lot> resLots = new ArrayList<>();
        List<Round> resRounds = new ArrayList<>();

        List<Interval> rounds = new ArrayList<>();
        int roundLen = request.getRounds().size();
        for(int i = 0; i<roundLen; i++) {
            RoundRequestPart round = request.getRounds().get(i);

            int lotLen = round.getLots().size();
            List<Interval> lots = new ArrayList<>();
            for(int j = 0; j<lotLen; j++){
                LotRequestPart lot = round.getLots().get(j);

                BetParams betParams = new BetParams();
                betParams.setMinBetStep(
                    settingHandler.resolveSetting(
                        "minBetStep",
                        DEFAULT_MIN_BET_STEP,
                        Arrays.asList(
                            lot.getBetParams(),
                            round.getDefaultBetParams(),
                            request.getDefaultBetParams())
                        )
                );
                betParams.setStartSum(
                    settingHandler.resolveSetting(
                        "startSum",
                        DEFAULT_START_SUM,
                        Arrays.asList(
                            lot.getBetParams(),
                            round.getDefaultBetParams(),
                            request.getDefaultBetParams())
                    )
                );
                betParams.setLimitSum(
                    settingHandler.resolveSetting(
                        "limitSum",
                        DEFAULT_LIMIT_SUM,
                        Arrays.asList(
                            lot.getBetParams(),
                            round.getDefaultBetParams(),
                            request.getDefaultBetParams())
                    )
                );
                betParams.setEndOnAllAnswered(
                    settingHandler.resolveSetting(
                        "endOnAllAnswered",
                        DEFAULT_END_ON_ALL_ANSWERED,
                        Arrays.asList(
                            lot.getBetParams(),
                            round.getDefaultBetParams(),
                            request.getDefaultBetParams())
                    )
                );

                Lot lotEntity = new Lot()
                    .setDescription(lot.getDescription())
                    .setName(lot.getName())
                    .setBetParams(betParams);
                resLots.add(lotEntity);

                Interval lotInterval = intervalFactory.createLot(
                    settingHandler.resolveSetting(
                        List.of("defaultLotDuration", "duration"),
                        Arrays.asList(lot, round, request)
                    ),
                    lotEntity.getUid(),
                    lot.getAutostart(),
                    lot.getAutoend()
                );

                lots.add(lotInterval);

                Long pauseDur = settingHandler.resolveSetting(
                    List.of("defaultLotPause", "pauseAfter"),
                    Arrays.asList(lot, round, request)
                );
                if(j == lotLen-1 || pauseDur == null || pauseDur == 0L) {
                    continue;
                }
                Interval lotPause = intervalFactory.createLotPause(
                    pauseDur
                );
                lots.add(lotPause);
            }

            Round roundEntity = new Round()
                .setRoomUid(room.getUid())
                .setAscending(isRoundAsc(i));
            resRounds.add(roundEntity);

            Interval roundInterval = intervalFactory.createRound(
                lots,
                roundEntity.getUid(),
                round.getAutostart(),
                round.getAutoend()
            );

            rounds.add(roundInterval);

            Long pauseDur = settingHandler.resolveSetting(
                List.of("defaultRoundPause","roundPause"),
                Arrays.asList(round, request)
            );

            if(i == roundLen-1 || pauseDur == null || pauseDur == 0L) {
                continue;
            }
            Interval roundPause = intervalFactory.createRoundPause(
                pauseDur
            );
            rounds.add(roundPause);

        }
        room.setIntervals(rounds);


        return new Triplet<>(room, resLots, resRounds);
    }
}
