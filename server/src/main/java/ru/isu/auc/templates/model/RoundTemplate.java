package ru.isu.auc.templates.model;

import jakarta.persistence.Embeddable;
import lombok.Data;
import ru.isu.auc.auction.model.room.BetParams;

@Embeddable
@Data
public class RoundTemplate {
    BetParams betParams;
    Long lotDuration;
    Long lotPauseDuration;
    Long roundPauseDuration;
    Boolean ascending;

    public RoundTemplate setBetParams(BetParams betParams) {
        this.betParams = betParams;
        return this;
    }

    public RoundTemplate setLotDuration(Long lotDuration) {
        this.lotDuration = lotDuration;
        return this;
    }

    public RoundTemplate setLotPauseDuration(Long lotPauseDuration) {
        this.lotPauseDuration = lotPauseDuration;
        return this;
    }

    public RoundTemplate setRoundPauseDuration(Long roundPauseDuration) {
        this.roundPauseDuration = roundPauseDuration;
        return this;
    }

    public RoundTemplate setAscending(Boolean ascending) {
        this.ascending = ascending;
        return this;
    }
}
