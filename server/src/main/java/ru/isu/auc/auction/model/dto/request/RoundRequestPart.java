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

    public RoundRequestPart setAscending(Boolean ascending) {
        this.ascending = ascending;
        return this;
    }

    public RoundRequestPart setRoundPause(Long roundPause) {
        this.roundPause = roundPause;
        return this;
    }

    public RoundRequestPart setDefaultLotPause(Long defaultLotPause) {
        this.defaultLotPause = defaultLotPause;
        return this;
    }

    public RoundRequestPart setDefaultLotDuration(Long defaultLotDuration) {
        this.defaultLotDuration = defaultLotDuration;
        return this;
    }

    public RoundRequestPart setAutostart(Boolean autostart) {
        this.autostart = autostart;
        return this;
    }

    public RoundRequestPart setAutoend(Boolean autoend) {
        this.autoend = autoend;
        return this;
    }

    public RoundRequestPart setDefaultBetParams(BetParams defaultBetParams) {
        this.defaultBetParams = defaultBetParams;
        return this;
    }

    public RoundRequestPart setLots(List<LotRequestPart> lots) {
        this.lots = lots;
        return this;
    }
}
