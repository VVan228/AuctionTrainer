package ru.isu.auc.templates.model.dto.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.isu.auc.auction.model.dto.mappers.BetParamsMapper;
import ru.isu.auc.auction.model.dto.response.BetParamsDTO;
import ru.isu.auc.auction.model.room.BetParams;
import ru.isu.auc.common.api.DTOMapper;
import ru.isu.auc.templates.model.TemplateData;
import ru.isu.auc.templates.model.dto.request.TemplateDataDTO;

@Component
public class TemplateDataMapper implements DTOMapper<TemplateDataDTO, TemplateData> {

    @Autowired
    DTOMapper<BetParamsDTO, BetParams> betParamsMapper;


    @Override
    public TemplateData mapFromDto(TemplateDataDTO templateDataDTO) {
        return new TemplateData()
            .setTemplateName(templateDataDTO.getTemplateName())
            .setLotNames(templateDataDTO.getLotNames())
            .setLotDescriptions(templateDataDTO.getLotDescriptions())
            .setRoundTypes(templateDataDTO.getRoundTypes())
            .setBetParams(betParamsMapper.mapFromDto(templateDataDTO.getBetParams()))
            .setLotDuration(templateDataDTO.getLotDuration())
            .setLotPauseDuration(templateDataDTO.getLotPauseDuration())
            .setRoundPauseDuration(templateDataDTO.getRoundPauseDuration());
    }

    @Override
    public TemplateDataDTO mapToDto(TemplateData templateData) {
        return new TemplateDataDTO()
            .setTemplateName(templateData.getTemplateName())
            .setLotNames(templateData.getLotNames())
            .setLotDescriptions(templateData.getLotDescriptions())
            .setRoundTypes(templateData.getRoundTypes())
            .setBetParams(betParamsMapper.mapToDto(templateData.getBetParams()))
            .setLotDuration(templateData.getLotDuration())
            .setLotPauseDuration(templateData.getLotPauseDuration())
            .setRoundPauseDuration(templateData.getRoundPauseDuration());
    }
}
