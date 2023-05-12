package ru.isu.auc.auction.impl.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.isu.auc.auction.api.factorties.IntervalFactory;
import ru.isu.auc.auction.api.factorties.RoomFactory;
import ru.isu.auc.auction.model.interval.Interval;
import ru.isu.auc.auction.model.requests.CreateDefaultRoomRequest;
import ru.isu.auc.auction.model.requests.LotRequestPart;
import ru.isu.auc.auction.model.requests.RoundRequestPart;
import ru.isu.auc.auction.model.room.BetParams;
import ru.isu.auc.auction.model.room.Lot;
import ru.isu.auc.auction.model.room.Room;
import ru.isu.auc.auction.model.room.Round;
import ru.isu.auc.auction.api.SettingHandler;
import ru.isu.auc.auction.api.entities.IntervalService;
import ru.isu.auc.auction.api.entities.LotService;
import ru.isu.auc.auction.api.entities.RoomService;
import ru.isu.auc.auction.api.entities.RoundService;
import ru.isu.auc.security.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class RoomFactoryImpl implements RoomFactory {

    @Value("${auction.defaults.end_on_all_answered}")
    private boolean DEFAULT_END_ON_ALL_ANSWERED;
    @Value("${auction.defaults.min_bet_step}")
    private Integer DEFAULT_MIN_BET_STEP;
    @Value("${auction.defaults.start_sum}")
    private Integer DEFAULT_START_SUM;
    @Value("${auction.defaults.limit_sum}")
    private Integer DEFAULT_LIMIT_SUM;

    @Autowired
    LotService lotService;
    @Autowired
    RoomService roomService;
    @Autowired
    RoundService roundService;
    @Autowired
    IntervalService intervalService;

    @Autowired
    IntervalFactory intervalFactory;

    @Autowired
    SettingHandler settingHandler;


    //для стандартной комнаты only
    boolean isRoundAsc(int index){
        return index%2==0;
    }

    @Override
    public Room createAndSaveDefaultRoom(CreateDefaultRoomRequest request, User creator) {
        Room room = new Room();
        room.setCreator(creator);
        room.setName(request.getName());
        room.setStartTime(request.getStartTime());
        roomService.save(room);

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

                Lot lotEntity = new Lot();
                lotEntity.setDescription(lot.getDescription());
                lotEntity.setName(lot.getName());
                lotEntity.setBetParams(betParams);
                lotService.save(lotEntity);

                Interval lotInterval = intervalFactory.createLot(
                    //getLotDuration(request, round, lot),
                    settingHandler.resolveSetting(
                        List.of("defaultLotDuration", "duration"),
                        Arrays.asList(lot, round, request)
                    ),
                    lotEntity.getId()
                );

                lots.add(lotInterval);

                //Long pauseDur = getLotPause(request, round, lot);
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

            Round roundEntity = new Round();
            roundEntity.setRoomId(room.getId());
            roundEntity.setAscending(isRoundAsc(i));
            roundService.save(roundEntity);

            Interval roundInterval = intervalFactory.createRound(
                lots,
                roundEntity.getId()
            );

            rounds.add(roundInterval);

            //Long pauseDur = getRoundPause(request, round);
            Long pauseDur = settingHandler.resolveSetting(
                List.of("defaultRoundPause","roundPause"),
                Arrays.asList(round, request)
            );
            if(i == roundLen-1 || pauseDur == 0L) {
                continue;
            }
            Interval roundPause = intervalFactory.createRoundPause(
                pauseDur
            );
            rounds.add(roundPause);

        }
        intervalService.saveAll(rounds);
        room.setIntervals(rounds);
        roomService.save(room);
        return room;
    }
}
