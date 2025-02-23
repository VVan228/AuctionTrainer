package ru.isu.auc.auction.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.isu.auc.auction.model.interval.IntervalPoint;

@Repository
public interface IntervalPointRepo extends JpaRepository<IntervalPoint, Long> {
}
