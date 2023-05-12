package ru.isu.auc.auction.api.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.isu.auc.auction.model.interval.Interval;
import ru.isu.auc.auction.model.room.Lot;

import java.util.List;

public interface IntervalService {
    void save(Interval interval);
    void saveAll(List<Interval> intervals);
}
