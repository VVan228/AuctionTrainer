package ru.isu.auc.auction.model.interval;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class IntervalPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    List<Long> intervalStartIds = new ArrayList<>();
    @ElementCollection
    List<Long> intervalEndIds = new ArrayList<>();
    @JsonIgnore
    Long queueId;
    Long timestamp;

    boolean autostart;
    boolean autoend;


    public IntervalPoint setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public IntervalPoint setId(Long id) {
        this.id = id;
        return this;
    }

    public IntervalPoint setIntervalStartIds(List<Long> intervalStartIds) {
        this.intervalStartIds = intervalStartIds;
        return this;
    }

    public IntervalPoint setIntervalEndIds(List<Long> intervalEndIds) {
        this.intervalEndIds = intervalEndIds;
        return this;
    }

    public IntervalPoint setQueueId(Long queueId) {
        this.queueId = queueId;
        return this;
    }

    public IntervalPoint setAutostart(boolean autostart) {
        this.autostart = autostart;
        return this;
    }

    public IntervalPoint setAutoend(boolean autoend) {
        this.autoend = autoend;
        return this;
    }

    public IntervalPoint addStartId(Long i) {
        intervalStartIds.add(i);
        return this;
    }
    public IntervalPoint addEndId(Long i) {
        intervalEndIds.add(i);
        return this;
    }

    @Override
    public String toString() {
        return
            " +" + intervalStartIds.toString() +
            "-" + intervalEndIds.toString();

    }
}
