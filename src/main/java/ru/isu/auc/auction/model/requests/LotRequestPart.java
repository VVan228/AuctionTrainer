package ru.isu.auc.auction.model.requests;

import lombok.Data;
import ru.isu.auc.auction.model.room.BetParams;

import java.util.List;

@Data
public class LotRequestPart {
    String name;
    String description;
    BetParams betParams;
    Long duration;
    Long pauseAfter;

}
