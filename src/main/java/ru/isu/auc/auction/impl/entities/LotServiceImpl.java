package ru.isu.auc.auction.impl.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.isu.auc.auction.api.entities.LotService;
import ru.isu.auc.auction.model.room.Lot;
import ru.isu.auc.auction.repo.LotRepo;

import java.util.List;

@Service
public class LotServiceImpl implements LotService {
    @Autowired
    LotRepo lotRepo;

    @Override
    public Lot save(Lot lot) {
        return lotRepo.save(lot);
    }

    @Override
    public List<Lot> saveAll(Iterable<Lot> lots) {
        return lotRepo.saveAll(lots);
    }
}
