package ru.isu.auc.auction.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.isu.auc.auction.api.IntervalEventHandler;
import ru.isu.auc.auction.api.entities.IntervalService;
import ru.isu.auc.auction.api.entities.LotService;
import ru.isu.auc.auction.api.entities.RoundService;
import ru.isu.auc.auction.model.EntityNotFoundException;
import ru.isu.auc.auction.model.interval.Interval;
import ru.isu.auc.auction.model.interval.IntervalPoint;
import ru.isu.auc.auction.model.interval.ShortIntervalEnd;
import ru.isu.auc.auction.model.interval.ShortIntervalStart;
import ru.isu.auc.auction.model.room.Lot;
import ru.isu.auc.auction.model.room.ParticipantBet;
import ru.isu.auc.auction.model.room.Round;
import ru.isu.auc.auction.model.types.IntervalType;
import ru.isu.auc.auction.model.types.Status;

import java.util.Comparator;
import java.util.UUID;

@Service
public class IntervalEventHandlerImpl implements IntervalEventHandler {

    @Autowired
    IntervalService intervalService;

    @Autowired
    LotService lotService;

    @Autowired
    RoundService roundService;

    @Override
    public void intervalEvent(IntervalPoint intervalPoint) {
        for(ShortIntervalEnd i: intervalPoint.getIntervalEndIds()) {
            Interval interval = intervalService.get(i.getIntervalId());
            if (interval.getType().equals(IntervalType.LOT)) {
                setLotWinner(interval.getEntityUid());
            }
            if (interval.getType().equals(IntervalType.ROUND)){
                setRoundResults(interval.getEntityUid());
            }
        }
    }

    private void setLotWinner(UUID lotUid) {
        Lot lot = lotService.findByUid(lotUid);
        Round r = roundService.findByLotId(lot.getId());
        ParticipantBet winner;
        if(r.getAscending()) {
            winner = lot.getBets().stream()
                .max(Comparator.comparing(ParticipantBet::getSum)).orElse(null);
        } else{
            winner = lot.getBets().stream()
                .min(Comparator.comparing(ParticipantBet::getSum)).orElse(null);
        }
        lot.setWinner(winner);
        lotService.save(lot);
    }

    private void setRoundResults(UUID roundUid) {
        //TODO:
    }
}
