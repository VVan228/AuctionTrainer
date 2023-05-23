package ru.isu.auc.auction.impl.entities;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.isu.auc.auction.api.entities.LotService;
import ru.isu.auc.auction.api.entities.RoundService;
import ru.isu.auc.auction.model.room.Lot;
import ru.isu.auc.auction.model.room.ParticipantBet;
import ru.isu.auc.auction.model.room.Round;
import ru.isu.auc.auction.repo.LotRepo;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class LotServiceImpl implements LotService {
    @Autowired
    LotRepo lotRepo;
    @Autowired
    RoundService roundService;

    @Override
    public Lot save(Lot lot) {
        return lotRepo.save(lot);
    }

    @Override
    public List<Lot> saveAll(Iterable<Lot> lots) {
        return lotRepo.saveAll(lots);
    }

    @Override
    public Lot findByUid(UUID uid) {
        return lotRepo.findByUid(uid);
    }

    @Override
    public Lot findById(Long id) {
        return lotRepo.findById(id).get();
    }

    @Override
    public Lot findByIntervalId(Long intervalId) {
        return lotRepo.findByIntervalId(intervalId);
    }

    @Override
    @Transactional
    public void setWinner(UUID lotUid) {
        Lot lot = findByUid(lotUid);
        Round r = roundService.findByLotId(lot.getId());
        ParticipantBet winner = null;
        if(r.getAscending()) {
            winner = lot.getBets().stream()
                .max(Comparator.comparing(ParticipantBet::getSum)).orElseGet(()->null);
        } else{
            winner = lot.getBets().stream()
                .min(Comparator.comparing(ParticipantBet::getSum)).orElseGet(()->null);
        }
        lot.setWinner(winner);
        lotRepo.save(lot);
    }
}
