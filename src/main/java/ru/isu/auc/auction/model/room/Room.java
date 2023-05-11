package ru.isu.auc.auction.model.room;

import jakarta.persistence.*;
import lombok.Data;
import ru.isu.auc.auction.model.interval.Interval;
import ru.isu.auc.security.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    List<Interval> intervals = new ArrayList<>();
    LocalDateTime startTime;
    @ManyToOne
    User creator;
    String name;
}
