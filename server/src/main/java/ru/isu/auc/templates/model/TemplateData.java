package ru.isu.auc.templates.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.isu.auc.auction.model.room.BetParams;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class TemplateData {
    String templateName;
    @ElementCollection
    List<String> lotNames = new ArrayList<>();
    @ElementCollection
    List<String> lotDescriptions = new ArrayList<>();
    @ElementCollection
    List<RoundTemplate> rounds = new ArrayList<>();
    BetParams betParams;
    Long lotDuration;
    Long lotPauseDuration;
    Long roundPauseDuration;
    Boolean manualMode;

    public TemplateData setTemplateName(String templateName) {
        this.templateName = templateName;
        return this;
    }

    public TemplateData setLotNames(List<String> lotNames) {
        this.lotNames = lotNames;
        return this;
    }

    public TemplateData setLotDescriptions(List<String> lotDescriptions) {
        this.lotDescriptions = lotDescriptions;
        return this;
    }


    public TemplateData setBetParams(BetParams betParams) {
        this.betParams = betParams;
        return this;
    }

    public TemplateData setLotDuration(Long lotDuration) {
        this.lotDuration = lotDuration;
        return this;
    }

    public TemplateData setLotPauseDuration(Long lotPauseDuration) {
        this.lotPauseDuration = lotPauseDuration;
        return this;
    }

    public TemplateData setRoundPauseDuration(Long roundPauseDuration) {
        this.roundPauseDuration = roundPauseDuration;
        return this;
    }

    public TemplateData setManualMode(Boolean manualMode) {
        this.manualMode = manualMode;
        return this;
    }

    public TemplateData setRounds(List<RoundTemplate> rounds) {
        this.rounds = rounds;
        return this;
    }
}
