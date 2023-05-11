package ru.isu.auc.auction.model.room;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Lot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String name;
    String description;
    @Embedded
    BetParams betParams;
}
