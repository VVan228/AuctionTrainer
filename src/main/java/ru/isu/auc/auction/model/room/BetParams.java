package ru.isu.auc.auction.model.room;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class BetParams {
    Integer startSum;
    Integer limitSum;
    Integer minBetStep;
    Boolean endOnAllAnswered;
}
