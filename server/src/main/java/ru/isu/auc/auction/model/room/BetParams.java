package ru.isu.auc.auction.model.room;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class BetParams {
    Integer startSum;
    Integer limitSum;
    Integer minBetStep;
    Boolean endOnAllAnswered;

    public BetParams setStartSum(Integer startSum) {
        this.startSum = startSum;
        return this;
    }

    public BetParams setLimitSum(Integer limitSum) {
        this.limitSum = limitSum;
        return this;
    }

    public BetParams setMinBetStep(Integer minBetStep) {
        this.minBetStep = minBetStep;
        return this;
    }

    public BetParams setEndOnAllAnswered(Boolean endOnAllAnswered) {
        this.endOnAllAnswered = endOnAllAnswered;
        return this;
    }
}
