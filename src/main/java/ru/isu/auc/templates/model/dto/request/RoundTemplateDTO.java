package ru.isu.auc.templates.model.dto.request;

import lombok.Data;
import ru.isu.auc.auction.model.dto.response.BetParamsDTO;

@Data
public class RoundTemplateDTO {
    BetParamsDTO betParams;
    Long lotDuration;
    Long lotPauseDuration;
    Long roundPauseDuration;
    Boolean ascending;

    public RoundTemplateDTO setBetParams(BetParamsDTO betParams) {
        this.betParams = betParams;
        return this;
    }

    public RoundTemplateDTO setLotDuration(Long lotDuration) {
        this.lotDuration = lotDuration;
        return this;
    }

    public RoundTemplateDTO setLotPauseDuration(Long lotPauseDuration) {
        this.lotPauseDuration = lotPauseDuration;
        return this;
    }

    public RoundTemplateDTO setRoundPauseDuration(Long roundPauseDuration) {
        this.roundPauseDuration = roundPauseDuration;
        return this;
    }

    public RoundTemplateDTO setAscending(Boolean ascending) {
        this.ascending = ascending;
        return this;
    }
}
