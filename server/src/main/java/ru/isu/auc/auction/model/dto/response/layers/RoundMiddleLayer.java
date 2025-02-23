package ru.isu.auc.auction.model.dto.response.layers;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.isu.auc.auction.model.dto.response.RoundDTO;
import ru.isu.auc.auction.model.room.Round;

import java.util.List;

@Data
@NoArgsConstructor
public class RoundMiddleLayer<T extends RoundDTO> extends RoundDTO{
    List<T> rounds;
}
