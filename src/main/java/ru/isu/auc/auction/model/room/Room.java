package ru.isu.auc.auction.model.room;

import jakarta.persistence.*;
import lombok.Data;
import ru.isu.auc.auction.model.BaseEntity;
import ru.isu.auc.auction.model.interval.Interval;
import ru.isu.auc.security.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Room extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    List<Interval> intervals = new ArrayList<>();
    LocalDateTime startTime;
    @ManyToOne
    User creator;
    String name;

    public Room setId(Long id) {
        this.id = id;
        return this;
    }

    public Room setIntervals(List<Interval> intervals) {
        this.intervals = intervals;
        return this;
    }

    public Room setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
        return this;
    }

    public Room setCreator(User creator) {
        this.creator = creator;
        return this;
    }

    public Room setName(String name) {
        this.name = name;
        return this;
    }
}
