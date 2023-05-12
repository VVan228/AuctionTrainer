package ru.isu.auc.auction.model.room;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import ru.isu.auc.auction.model.BaseEntity;

import java.util.UUID;

@Entity
@Data
public class Round extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    UUID roomUid;
    Boolean ascending;

    public Round setId(Long id) {
        this.id = id;
        return this;
    }

    public Round setRoomUid(UUID roomUid) {
        this.roomUid = roomUid;
        return this;
    }

    public Round setAscending(Boolean ascending) {
        this.ascending = ascending;
        return this;
    }
}
