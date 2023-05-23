package ru.isu.auc.auction.model.dto.request;

import lombok.Data;
import ru.isu.auc.auction.model.room.BetParams;

import java.util.List;

@Data
public class RoundRequestPart {
    Boolean ascending;

    Long roundPause;
    Long defaultLotPause;
    Long defaultLotDuration;
    Boolean autostart;
    Boolean autoend;

    BetParams defaultBetParams;

    List<LotRequestPart> lots;
}
