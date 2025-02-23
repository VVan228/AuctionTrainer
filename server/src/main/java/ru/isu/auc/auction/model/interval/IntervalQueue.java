package ru.isu.auc.auction.model.interval;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class IntervalQueue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    UUID roomUid;
    @OneToMany
    List<IntervalPoint> intervalPoints = new ArrayList<>();
    Long currentIndex;


    public IntervalQueue setId(Long id) {
        this.id = id;
        return this;

    }

    public IntervalQueue setRoomUid(UUID roomUid) {
        this.roomUid = roomUid;
        return this;
    }

    public IntervalQueue setIntervalPoints(List<IntervalPoint> intervalPoints) {
        this.intervalPoints = intervalPoints;
        return this;
    }

    public IntervalQueue setCurrentIndex(Long currentIndex) {
        this.currentIndex = currentIndex;
        return this;
    }

    @Override
    public String toString() {
        return intervalPoints.toString();
    }
}
