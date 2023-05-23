package ru.isu.auc.auction.model.room;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import ru.isu.auc.auction.model.BaseEntity;
import ru.isu.auc.auction.model.types.Status;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Lot extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String name;
    String description;
    @Embedded
    BetParams betParams;

    @ElementCollection
    List<ParticipantBet> bets = new ArrayList<>();
    ParticipantBet winner;


    public Lot setId(Long id) {
        this.id = id;
        return this;
    }

    public Lot setName(String name) {
        this.name = name;
        return this;
    }

    public Lot setDescription(String description) {
        this.description = description;
        return this;
    }

    public Lot setBetParams(BetParams betParams) {
        this.betParams = betParams;
        return this;
    }

    public Lot addBet(ParticipantBet bet) {
        bets.add(bet);
        return this;
    }
}
