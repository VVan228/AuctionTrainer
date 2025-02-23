package ru.isu.auc.auction.model.interval;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class ShortIntervalStart {
    Long intervalId;
    Boolean autostart;

    public ShortIntervalStart setIntervalId(Long intervalId) {
        this.intervalId = intervalId;
        return this;
    }

    public ShortIntervalStart setAutostart(Boolean autostart) {
        this.autostart = autostart;
        return this;
    }

    @Override
    public String toString() {
        String aS = autostart!=null&&!autostart?"|→":"";
        return intervalId + aS;
    }
}
