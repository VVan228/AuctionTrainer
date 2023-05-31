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

    public LotRequestPart setName(String name) {
        this.name = name;
        return this;
    }

    public LotRequestPart setDescription(String description) {
        this.description = description;
        return this;
    }

    public LotRequestPart setBetParams(BetParams betParams) {
        this.betParams = betParams;
        return this;
    }

    public LotRequestPart setDuration(Long duration) {
        this.duration = duration;
        return this;
    }

    public LotRequestPart setPauseAfter(Long pauseAfter) {
        this.pauseAfter = pauseAfter;
        return this;
    }

    public LotRequestPart setAutostart(Boolean autostart) {
        this.autostart = autostart;
        return this;
    }

    public LotRequestPart setAutoend(Boolean autoend) {
        this.autoend = autoend;
        return this;
    }
}
