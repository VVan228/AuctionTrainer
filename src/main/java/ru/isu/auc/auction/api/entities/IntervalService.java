package ru.isu.auc.auction.api.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.isu.auc.auction.model.interval.Interval;
import ru.isu.auc.auction.model.room.Lot;
import ru.isu.auc.auction.model.types.Status;

import java.util.List;

public interface IntervalService {
    void save(Interval interval);
    List<Interval> saveAll(Iterable<Interval> intervals);
    Interval get(Long intervalId);
    Interval setStatus(Long intervalId, Status status);
}
