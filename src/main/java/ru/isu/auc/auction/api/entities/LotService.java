package ru.isu.auc.auction.api.entities;

import ru.isu.auc.auction.model.room.Lot;

import java.util.List;
import java.util.UUID;

public interface LotService {
    Lot save(Lot lot);
    List<Lot> saveAll(Iterable<Lot> lots);
    Lot findByUid(UUID uid);
    Lot findById(Long id);
    Lot findByIntervalId(Long intervalId);
}
