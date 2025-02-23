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
    List<ShortIntervalStart> intervalStartIds = new ArrayList<>();
    @ElementCollection
    List<ShortIntervalEnd> intervalEndIds = new ArrayList<>();
    @JsonIgnore
    Long queueId;
    Long timestamp;


    //TODO: resource
    Boolean atLeastOneAutoStart = false;
    Boolean atLeastOneAutoEnd = false;

    Boolean atLeastOneManualStart = false;
    Boolean atLeastOneManualEnd = false;


    public IntervalPoint setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public IntervalPoint setId(Long id) {
        this.id = id;
        return this;
    }

    public IntervalPoint setAtLeastOneAutoStart(Boolean atLeastOneAutoStart) {
        this.atLeastOneAutoStart = atLeastOneAutoStart;
        return this;
    }

    public IntervalPoint setAtLeastOneAutoEnd(Boolean atLeastOneAutoEnd) {
        this.atLeastOneAutoEnd = atLeastOneAutoEnd;
        return this;
    }

    public IntervalPoint setAtLeastOneManualStart(Boolean atLeastOneManualStart) {
        this.atLeastOneManualStart = atLeastOneManualStart;
        return this;
    }

    public IntervalPoint setAtLeastOneManualEnd(Boolean atLeastOneManualEnd) {
        this.atLeastOneManualEnd = atLeastOneManualEnd;
        return this;
    }

    public IntervalPoint setIntervalStartIds(List<ShortIntervalStart> intervalStartIds) {
        this.intervalStartIds = intervalStartIds;
        return this;
    }

    public IntervalPoint setIntervalEndIds(List<ShortIntervalEnd> intervalEndIds) {
        this.intervalEndIds = intervalEndIds;
        return this;
    }

    public IntervalPoint setQueueId(Long queueId) {
        this.queueId = queueId;
        return this;
    }

    public IntervalPoint addStartId(ShortIntervalStart i) {
        intervalStartIds.add(i);
        return this;
    }
    public IntervalPoint addEndId(ShortIntervalEnd i) {
        intervalEndIds.add(i);
        return this;
    }

    public IntervalPoint insertStartId(int pos, ShortIntervalStart i) {
        intervalStartIds.add(pos, i);
        return this;
    }
    public IntervalPoint insertEndId(int pos, ShortIntervalEnd i) {
        intervalEndIds.add(pos, i);
        return this;
    }

    @Override
    public String toString() {
        return
            " +" + intervalStartIds.toString() +
            "-" + intervalEndIds.toString();
    }
}
