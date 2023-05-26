package ru.isu.auc.templates.model.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.isu.auc.auction.model.dto.response.BetParamsDTO;
import ru.isu.auc.templates.model.TemplateData;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class TemplateDataDTO {
    String templateName;
    List<String> lotNames = new ArrayList<>();
    List<String> lotDescriptions = new ArrayList<>();
    List<Boolean> roundTypes = new ArrayList<>();
    BetParamsDTO betParams;
    Long lotDuration;
    Long lotPauseDuration;
    Long roundPauseDuration;

    public TemplateDataDTO(TemplateData templateData) {
        this.templateName = templateData.getTemplateName();
        this.lotNames = templateData.getLotNames();
        this.lotDescriptions = templateData.getLotDescriptions();
        this.betParams = new BetParamsDTO(templateData.getBetParams());
        this.lotDuration = templateData.getLotDuration();
        this.lotPauseDuration = templateData.getLotPauseDuration();
        this.roundPauseDuration = templateData.getRoundPauseDuration();
        this.roundTypes = templateData.getRoundTypes();
    }


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

    public TemplateDataDTO setRoundTypes(List<Boolean> roundTypes) {
        this.roundTypes = roundTypes;
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
}
