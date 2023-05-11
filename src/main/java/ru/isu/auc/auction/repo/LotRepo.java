package ru.isu.auc.auction.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.isu.auc.auction.model.room.Lot;

public interface LotRepo extends JpaRepository<Lot, Long> {
}
