package ru.isu.auc.auction.api.entities;

import ru.isu.auc.auction.model.room.Lot;
import ru.isu.auc.auction.model.room.Round;

public interface RoundService {
    void save(Round round);
}
