package ru.isu.auc.auction.model.interval;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class IntervalQueue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    Long roomId;
    @OneToMany
    List<IntervalPoint> intervalPoints = new ArrayList<>();
    Long currentIndex;


    public IntervalQueue setId(Long id) {
        this.id = id;
        return this;

    }

    public IntervalQueue setRoomId(Long roomId) {
        this.roomId = roomId;
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
