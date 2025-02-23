package ru.isu.auc.auction.impl.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.isu.auc.auction.api.entities.RoundService;
import ru.isu.auc.auction.model.room.Round;
import ru.isu.auc.auction.repo.RoundRepo;

import java.util.List;
import java.util.UUID;

@Service
public class RoundServiceImpl implements RoundService {
    @Autowired
    RoundRepo roundRepo;
    @Override
    public Round save(Round round) {
        return roundRepo.save(round);
    }

    @Override
    public List<Round> saveAll(Iterable<Round> rounds) {
        return roundRepo.saveAll(rounds);
    }

    @Override
    public Round findByUid(UUID uid) {
        return roundRepo.findByUid(uid);
    }

    @Override
    public Round findByLotId(Long lotId) {
        return roundRepo.findByLotId(lotId);
    }
}
