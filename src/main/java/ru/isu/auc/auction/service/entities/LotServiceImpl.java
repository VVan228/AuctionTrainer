package ru.isu.auc.auction.service.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.isu.auc.auction.model.room.Lot;
import ru.isu.auc.auction.repo.LotRepo;

@Service
public class LotServiceImpl implements LotService {
    @Autowired
    LotRepo lotRepo;

    @Override
    public void save(Lot lot) {
        lotRepo.save(lot);
    }
}
