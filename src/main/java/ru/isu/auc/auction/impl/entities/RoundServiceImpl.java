package ru.isu.auc.auction.impl.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.isu.auc.auction.api.entities.RoundService;
import ru.isu.auc.auction.model.room.Round;
import ru.isu.auc.auction.repo.RoundRepo;

import java.util.List;

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
}
