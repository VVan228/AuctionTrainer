package ru.isu.auc.auction.model.interval;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class ShortIntervalEnd {
    Long intervalId;
    Boolean autoend;

    public ShortIntervalEnd setIntervalId(Long intervalId) {
        this.intervalId = intervalId;
        return this;
    }


    public ShortIntervalEnd setAutoend(Boolean autoend) {
        this.autoend = autoend;
        return this;
    }

    @Override
    public String toString() {
        String aE = autoend!=null&&!autoend?"←|":"";
        return intervalId+aE;
    }
}
