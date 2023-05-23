package ru.isu.auc.auction.api.entities;

import ru.isu.auc.auction.model.room.Lot;
import ru.isu.auc.auction.model.room.Round;

import java.util.List;
import java.util.UUID;

public interface RoundService {
    Round save(Round round);
    List<Round> saveAll(Iterable<Round> rounds);
    Round findByUid(UUID uid);
    Round findByLotId(Long lotId);
}
