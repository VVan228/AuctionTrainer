package ru.isu.auc.auction.model.room;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.isu.auc.auction.model.BaseEntity;
import ru.isu.auc.auction.model.types.Status;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Round extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    Boolean ascending;

    UUID roomUid;


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
