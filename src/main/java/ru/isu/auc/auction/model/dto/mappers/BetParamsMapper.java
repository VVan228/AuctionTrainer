package ru.isu.auc.auction.model.dto.mappers;

import org.springframework.stereotype.Component;
import ru.isu.auc.auction.model.dto.response.BetParamsDTO;
import ru.isu.auc.auction.model.room.BetParams;
import ru.isu.auc.common.api.DTOMapper;

@Component
public class BetParamsMapper implements DTOMapper<BetParamsDTO, BetParams> {
    @Override
    public BetParams mapFromDto(BetParamsDTO betParamsDTO) {
        return new BetParams()
            .setMinBetStep(betParamsDTO.getMinBetStep())
            .setLimitSum(betParamsDTO.getLimitSum())
            .setStartSum(betParamsDTO.getStartSum());
    }

    @Override
    public BetParamsDTO mapToDto(BetParams betParams) {
        return new BetParamsDTO()
            .setMinBetStep(betParams.getMinBetStep())
            .setLimitSum(betParams.getLimitSum())
            .setStartSum(betParams.getStartSum());
    }
}
