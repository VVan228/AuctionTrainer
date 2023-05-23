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
}
