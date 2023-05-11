package ru.isu.auc.auction.model.interval;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class IntervalPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    List<Long> intervalStartIds = new ArrayList<>();
    @ElementCollection
    List<Long> intervalEndIds = new ArrayList<>();
    Long queueId;
}
