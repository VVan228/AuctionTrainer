package ru.isu.auc.auction.model.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.isu.auc.auction.model.room.ParticipantBet;

@Data
@NoArgsConstructor
public class ParticipantBetDTO {
    String username;
    Long sum;

    public ParticipantBetDTO(ParticipantBet bet) {
        this.username = bet.getUser().getUsername();
        this.sum = bet.getSum();
    }
}
