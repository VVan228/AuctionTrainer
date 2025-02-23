package ru.isu.auc.auction.model.room;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import ru.isu.auc.security.model.User;

@Data
@Embeddable
public class ParticipantBet {
    @ManyToOne
    User user;
    Long sum;

    public ParticipantBet setUser(User user) {
        this.user = user;
        return this;
    }

    public ParticipantBet setSum(Long sum) {
        this.sum = sum;
        return this;
    }
}
