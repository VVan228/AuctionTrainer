package ru.isu.auc.auction.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.isu.auc.auction.model.interval.IntervalQueue;

public interface IntervalQueueRepo extends JpaRepository<IntervalQueue, Long> {
}
