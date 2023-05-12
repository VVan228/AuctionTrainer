package ru.isu.auc.auction.model.room;

import jakarta.persistence.*;
import lombok.Data;
import ru.isu.auc.auction.model.BaseEntity;

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
}
