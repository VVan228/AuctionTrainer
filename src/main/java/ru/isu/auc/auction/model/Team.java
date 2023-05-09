package ru.isu.auc.auction.model;

import jakarta.persistence.*;
import lombok.Data;
import ru.isu.auc.security.model.User;

@Entity
@Data
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String name;
    String password;
    @ManyToOne
    User creator;
    @ManyToOne
    User captain;
}
