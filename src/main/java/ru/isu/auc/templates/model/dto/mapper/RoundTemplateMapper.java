package ru.isu.auc.templates.model.dto.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.isu.auc.auction.model.dto.response.BetParamsDTO;
import ru.isu.auc.auction.model.room.BetParams;
import ru.isu.auc.common.api.DTOMapper;
import ru.isu.auc.templates.model.RoundTemplate;
import ru.isu.auc.templates.model.dto.request.RoundTemplateDTO;

@Component
public class RoundTemplateMapper  implements DTOMapper<RoundTemplateDTO, RoundTemplate> {

    @Autowired
    DTOMapper<BetParamsDTO, BetParams> betParamsMapper;

    @Override
    public RoundTemplate mapFromDto(RoundTemplateDTO roundTemplateDTO) {
        return new RoundTemplate()
            .setBetParams(betParamsMapper.mapFromDto(roundTemplateDTO.getBetParams()))
            .setLotDuration(roundTemplateDTO.getLotDuration())
            .setLotPauseDuration(roundTemplateDTO.getLotPauseDuration())
            .setRoundPauseDuration(roundTemplateDTO.getRoundPauseDuration())
            .setAscending(roundTemplateDTO.getAscending());
    }

    @Override
    public RoundTemplateDTO mapToDto(RoundTemplate roundTemplate) {
        return new RoundTemplateDTO()
            .setBetParams(betParamsMapper.mapToDto(roundTemplate.getBetParams()))
            .setLotDuration(roundTemplate.getLotDuration())
            .setLotPauseDuration(roundTemplate.getLotPauseDuration())
            .setRoundPauseDuration(roundTemplate.getRoundPauseDuration())
            .setAscending(roundTemplate.getAscending());
    }
}
