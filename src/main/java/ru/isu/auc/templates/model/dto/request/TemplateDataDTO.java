package ru.isu.auc.templates.model.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.isu.auc.auction.model.dto.response.BetParamsDTO;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class TemplateDataDTO {
    String templateName;
    List<String> lotNames = new ArrayList<>();
    List<String> lotDescriptions = new ArrayList<>();
    List<RoundTemplateDTO> rounds = new ArrayList<>();
    BetParamsDTO betParams;
    Long lotDuration;
    Long lotPauseDuration;
    Long roundPauseDuration;
    Boolean manualMode;


    public TemplateDataDTO setTemplateName(String templateName) {
        this.templateName = templateName;
        return this;
    }

    public TemplateDataDTO setLotNames(List<String> lotNames) {
        this.lotNames = lotNames;
        return this;
    }

    public TemplateDataDTO setLotDescriptions(List<String> lotDescriptions) {
        this.lotDescriptions = lotDescriptions;
        return this;
    }


    public TemplateDataDTO setBetParams(BetParamsDTO betParams) {
        this.betParams = betParams;
        return this;
    }

    public TemplateDataDTO setLotDuration(Long lotDuration) {
        this.lotDuration = lotDuration;
        return this;
    }

    public TemplateDataDTO setLotPauseDuration(Long lotPauseDuration) {
        this.lotPauseDuration = lotPauseDuration;
        return this;
    }

    public TemplateDataDTO setRoundPauseDuration(Long roundPauseDuration) {
        this.roundPauseDuration = roundPauseDuration;
        return this;
    }

    public TemplateDataDTO setManualMode(Boolean manualMode) {
        this.manualMode = manualMode;
        return this;
    }

    public TemplateDataDTO setRounds(List<RoundTemplateDTO> rounds) {
        this.rounds = rounds;
        return this;
    }
}
