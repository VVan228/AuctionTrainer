package ru.isu.auc.auction.model.requests;

import lombok.Data;
import ru.isu.auc.auction.model.room.BetParams;

import java.util.List;

@Data
public class RoundRequestPart {
    Boolean ascending;
    Long roundPause;
    Long defaultLotPause;
    Long defaultLotDuration;
    BetParams defaultBetParams;
    List<LotRequestPart> lots;
}
