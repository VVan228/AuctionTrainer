package ru.isu.auc.auction.service.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.isu.auc.auction.model.interval.Interval;
import ru.isu.auc.auction.model.requests.CreateDefaultRoomRequest;
import ru.isu.auc.auction.model.requests.LotRequestPart;
import ru.isu.auc.auction.model.requests.RoundRequestPart;
import ru.isu.auc.auction.model.room.BetParams;
import ru.isu.auc.auction.model.room.Lot;
import ru.isu.auc.auction.model.room.Room;
import ru.isu.auc.auction.model.room.Round;
import ru.isu.auc.auction.service.entities.LotService;
import ru.isu.auc.auction.service.entities.RoomService;
import ru.isu.auc.auction.service.entities.RoundService;
import ru.isu.auc.security.model.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoomFactoryImpl implements RoomFactory {

    @Autowired
    LotService lotService;
    @Autowired
    RoomService roomService;
    @Autowired
    RoundService roundService;
    @Autowired
    IntervalFactory intervalFactory;

    //для стандартной комнаты only
    boolean isRoundAsc(int index){
        return index%2==0;
    }

    @Override
    public Room createDefaultRoom(CreateDefaultRoomRequest request, User creator) {
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
                betParams.setMinBetStep(getMinBetStep(request, round, lot));
                betParams.setStartSum(getStartSum(request, round, lot));
                betParams.setLimitSum(getLimitSum(request, round, lot));
                betParams.setEndOnAllAnswered(getEndOnAllAnswered(request, round, lot));

                Lot lotEntity = new Lot();
                lotEntity.setDescription(lot.getDescription());
                lotEntity.setName(lot.getName());
                lotEntity.setBetParams(betParams);
                lotService.save(lotEntity);

                Interval lotInterval = intervalFactory.createLot(
                    getLotDuration(request, round, lot),
                    lotEntity.getId()
                );

                lots.add(lotInterval);

                Long pauseDur = getLotPause(request, round, lot);
                if(j == lotLen-1 || pauseDur == 0L) {
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

            Long pauseDur = getRoundPause(request, round);
            if(i == roundLen-1 || pauseDur == 0L) {
                continue;
            }
            Interval roundPause = intervalFactory.createRoundPause(
                pauseDur
            );
            rounds.add(roundPause);

        }

        room.setIntervals(rounds);
        return room;
    }

    Integer getStartSum(
        CreateDefaultRoomRequest request,
        RoundRequestPart round,
        LotRequestPart lot){
        if(lot.getBetParams().getStartSum()!=null)
            return lot.getBetParams().getStartSum();
        else if(round.getDefaultBetParams().getStartSum()!=null)
            return round.getDefaultBetParams().getStartSum();
        else if(request.getDefaultBetParams().getStartSum()!=null)
            return request.getDefaultBetParams().getStartSum();
        else
            return null;
    }

    Integer getLimitSum(
        CreateDefaultRoomRequest request,
        RoundRequestPart round,
        LotRequestPart lot){
        if(lot.getBetParams().getLimitSum()!=null)
            return lot.getBetParams().getLimitSum();
        else if(round.getDefaultBetParams().getLimitSum()!=null)
            return round.getDefaultBetParams().getLimitSum();
        else if(request.getDefaultBetParams().getLimitSum()!=null)
            return request.getDefaultBetParams().getLimitSum();
        else
            return null;
    }

    Integer getMinBetStep(
        CreateDefaultRoomRequest request,
        RoundRequestPart round,
        LotRequestPart lot){
        if(lot.getBetParams().getMinBetStep()!=null)
            return lot.getBetParams().getMinBetStep();
        else if(round.getDefaultBetParams().getMinBetStep()!=null)
            return round.getDefaultBetParams().getMinBetStep();
        else if(request.getDefaultBetParams().getMinBetStep()!=null)
            return request.getDefaultBetParams().getMinBetStep();
        else
            return null;
    }

    Boolean getEndOnAllAnswered(
        CreateDefaultRoomRequest request,
        RoundRequestPart round,
        LotRequestPart lot){
        if(lot.getBetParams().getMinBetStep()!=null)
            return lot.getBetParams().getEndOnAllAnswered();
        else if(round.getDefaultBetParams().getEndOnAllAnswered()!=null)
            return round.getDefaultBetParams().getEndOnAllAnswered();
        else if(request.getDefaultBetParams().getEndOnAllAnswered()!=null)
            return request.getDefaultBetParams().getEndOnAllAnswered();
        else
            return null;
    }

    Long getLotPause(
        CreateDefaultRoomRequest request,
        RoundRequestPart round,
        LotRequestPart lot){
        if(lot.getPauseAfter()!=null)
            return lot.getPauseAfter();
        else if(round.getDefaultLotPause()!=null)
            return round.getDefaultLotPause();
        else if(request.getDefaultLotPause()!=null)
            return request.getDefaultLotPause();
        else
            return null;
    }

    Long getRoundPause(
        CreateDefaultRoomRequest request,
        RoundRequestPart round){
        if(round.getRoundPause()!=null)
            return round.getRoundPause();
        else if(request.getDefaultRoundPause()!=null)
            return request.getDefaultRoundPause();
        else
            return null;
    }

    Long getLotDuration(
        CreateDefaultRoomRequest request,
        RoundRequestPart round,
        LotRequestPart lot){
        if(lot.getDuration()!=null)
            return lot.getDuration();
        else if(round.getDefaultLotDuration()!=null)
            return round.getDefaultLotDuration();
        else if(request.getDefaultLotDuration()!=null)
            return request.getDefaultLotDuration();
        else
            return null;
    }

}
