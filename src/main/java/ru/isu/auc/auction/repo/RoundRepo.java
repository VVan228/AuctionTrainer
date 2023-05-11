package ru.isu.auc.auction.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.isu.auc.auction.model.room.Round;

public interface RoundRepo extends JpaRepository<Round, Long> {
}
