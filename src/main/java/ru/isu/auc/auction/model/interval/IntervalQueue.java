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
}
