package ru.isu.auc.auction.api.entities;

import ru.isu.auc.auction.model.room.Lot;

import java.util.List;

public interface LotService {
    Lot save(Lot lot);
    List<Lot> saveAll(Iterable<Lot> lots);
}
