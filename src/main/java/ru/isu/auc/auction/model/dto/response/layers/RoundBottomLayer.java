package ru.isu.auc.auction.model.dto.response.layers;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.isu.auc.auction.model.dto.response.LotDTO;
import ru.isu.auc.auction.model.dto.response.RoundDTO;
import ru.isu.auc.auction.model.interval.Interval;
import ru.isu.auc.auction.model.room.Lot;
import ru.isu.auc.auction.model.room.Round;
import ru.isu.auc.auction.model.types.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Data
@NoArgsConstructor
public class RoundBottomLayer extends RoundDTO {
    List<LotDTO> lots = new ArrayList<>();

    public RoundBottomLayer(Round round, Interval i) {
        super(round, i);
    }
}
