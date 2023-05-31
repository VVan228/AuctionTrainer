package ru.isu.auc.templates.model.dto.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.isu.auc.auction.model.dto.response.BetParamsDTO;
import ru.isu.auc.auction.model.room.BetParams;
import ru.isu.auc.common.api.DTOMapper;
import ru.isu.auc.templates.model.RoundTemplate;
import ru.isu.auc.templates.model.TemplateData;
import ru.isu.auc.templates.model.dto.request.RoundTemplateDTO;
import ru.isu.auc.templates.model.dto.request.TemplateDataDTO;

import java.util.stream.Collectors;

@Component
public class TemplateDataMapper implements DTOMapper<TemplateDataDTO, TemplateData> {

    @Autowired
    DTOMapper<BetParamsDTO, BetParams> betParamsMapper;

    @Autowired
    DTOMapper<RoundTemplateDTO, RoundTemplate> roundTemplateMapper;


    @Override
    public TemplateData mapFromDto(TemplateDataDTO templateDataDTO) {
        return new TemplateData()
            .setTemplateName(templateDataDTO.getTemplateName())
            .setLotNames(templateDataDTO.getLotNames())
            .setLotDescriptions(templateDataDTO.getLotDescriptions())
            .setRounds(templateDataDTO.getRounds().stream().map(r->roundTemplateMapper.mapFromDto(r)).collect(Collectors.toList()))
            .setBetParams(betParamsMapper.mapFromDto(templateDataDTO.getBetParams()))
            .setLotDuration(templateDataDTO.getLotDuration())
            .setLotPauseDuration(templateDataDTO.getLotPauseDuration())
            .setRoundPauseDuration(templateDataDTO.getRoundPauseDuration())
            .setManualMode(templateDataDTO.getManualMode());
    }

    @Override
    public TemplateDataDTO mapToDto(TemplateData templateData) {
        return new TemplateDataDTO()
            .setTemplateName(templateData.getTemplateName())
            .setLotNames(templateData.getLotNames())
            .setLotDescriptions(templateData.getLotDescriptions())
            .setRounds(templateData.getRounds().stream().map(r->roundTemplateMapper.mapToDto(r)).collect(Collectors.toList()))
            .setBetParams(betParamsMapper.mapToDto(templateData.getBetParams()))
            .setLotDuration(templateData.getLotDuration())
            .setLotPauseDuration(templateData.getLotPauseDuration())
            .setRoundPauseDuration(templateData.getRoundPauseDuration())
            .setManualMode(templateData.getManualMode());
    }
}
