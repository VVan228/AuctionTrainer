package ru.isu.auc.auction.model.room;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.isu.auc.auction.model.BaseEntity;
import ru.isu.auc.auction.model.interval.Interval;
import ru.isu.auc.auction.model.types.RoomType;
import ru.isu.auc.auction.model.types.Status;
import ru.isu.auc.security.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    List<Interval> intervals = new ArrayList<>();
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    LocalDateTime startTime;
    @ManyToOne
    User creator;
    String name;
    RoomType roomType;


    public Room setId(Long id) {
        this.id = id;
        return this;
    }

    public Room setIntervals(List<Interval> intervals) {
        this.intervals = intervals;
        return this;
    }

    public Room setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
        return this;
    }

    public Room setCreator(User creator) {
        this.creator = creator;
        return this;
    }

    public Room setName(String name) {
        this.name = name;
        return this;
    }

    public Room setRoomType(RoomType roomType) {
        this.roomType = roomType;
        return this;
    }

    public Status getStatus() {
        Status roomStartStatus = getIntervals().get(0).getStatus();
        Status roomEndStatus = getIntervals()
            .get(getIntervals().size()-1).getStatus();

        if(roomEndStatus.equals(Status.ENDED)) {
            return Status.ENDED;
        }
        if(getStartTime()!=null && LocalDateTime.now().isAfter(getStartTime())) {
            return Status.ONGOING;
        }
        if(roomStartStatus.equals(Status.ONGOING) || roomStartStatus.equals(Status.ENDED)) {
            return Status.ONGOING;
        }
        return Status.SAVED;
    }
}
