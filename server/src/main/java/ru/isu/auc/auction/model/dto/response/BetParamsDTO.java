package ru.isu.auc.auction.model.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.isu.auc.auction.model.room.BetParams;

@Data
@NoArgsConstructor
public class BetParamsDTO {
    Integer startSum;
    Integer limitSum;
    Integer minBetStep;

    public BetParamsDTO(BetParams bp) {
        this.startSum = bp.getStartSum();
        this.limitSum = bp.getLimitSum();
        this.minBetStep = bp.getMinBetStep();
    }

    public BetParamsDTO setStartSum(Integer startSum) {
        this.startSum = startSum;
        return this;
    }

    public BetParamsDTO setLimitSum(Integer limitSum) {
        this.limitSum = limitSum;
        return this;
    }

    public BetParamsDTO setMinBetStep(Integer minBetStep) {
        this.minBetStep = minBetStep;
        return this;
    }
}
