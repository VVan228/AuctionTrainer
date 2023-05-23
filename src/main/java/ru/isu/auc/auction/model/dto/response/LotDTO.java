package ru.isu.auc.auction.model.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.isu.auc.auction.model.interval.Interval;
import ru.isu.auc.auction.model.room.BetParams;
import ru.isu.auc.auction.model.room.Lot;
import ru.isu.auc.auction.model.room.ParticipantBet;
import ru.isu.auc.auction.model.types.Status;

@Data
@NoArgsConstructor
public class LotDTO {

    Long id;

    String name;
    String description;
    ParticipantBetDTO winner;
    Status status;
    Long duration;
    Boolean autoend;
    Long intervalId;
    BetParamsDTO betParams;

    public LotDTO(Lot lot, Interval interval) {
        this.id = lot.getId();
        this.name = lot.getName();
        this.description = lot.getDescription();
        if(lot.getWinner()!=null)
            this.winner = new ParticipantBetDTO(lot.getWinner());
        this.betParams = new BetParamsDTO(lot.getBetParams());

        this.duration = interval.getDuration();
        this.autoend = interval.getAutoend();
        this.intervalId = interval.getId();
        this.status = interval.getStatus();
    }
}
