package ru.isu.auc.auction.model.interval;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.isu.auc.auction.model.types.IntervalType;
import ru.isu.auc.auction.model.types.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "interval")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Interval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    Long duration;
    Status status;
    IntervalType type;
    UUID entityUid;

    Boolean autostart;
    Boolean autoend;

    @JsonIgnore
    @OneToMany(
        cascade=CascadeType.ALL,
        fetch = FetchType.LAZY)
    List<Interval> intervals = new ArrayList<>();

    public Interval setId(Long id) {
        this.id = id;
        return this;
    }

    public Interval setDuration(Long duration) {
        this.duration = duration;
        return this;
    }

    public Interval setStatus(Status status) {
        this.status = status;
        return this;
    }

    public Interval setType(IntervalType type) {
        this.type = type;
        return this;
    }

    public Interval setEntityUid(UUID entityUid) {
        this.entityUid = entityUid;
        return this;
    }

    public Interval setAutostart(Boolean autostart) {
        this.autostart = autostart;
        return this;
    }

    public Interval setIntervals(List<Interval> intervals) {
        this.intervals = intervals;
        return this;
    }

    public Interval setAutoend(Boolean autoend) {
        this.autoend = autoend;
        return this;
    }
}
