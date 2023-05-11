package ru.isu.auc.auction.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.isu.auc.auction.model.interval.IntervalPoint;

public interface IntervalPointRepo extends JpaRepository<IntervalPoint, Long> {
}
