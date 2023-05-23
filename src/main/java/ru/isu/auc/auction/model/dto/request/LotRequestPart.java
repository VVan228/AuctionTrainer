package ru.isu.auc.auction.model.dto.request;

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
    Boolean autostart;
    Boolean autoend;
}
