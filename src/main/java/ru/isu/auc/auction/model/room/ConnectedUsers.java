package ru.isu.auc.auction.model.room;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ManyToAny;
import ru.isu.auc.auction.repo.ConnectedUsersRepo;
import ru.isu.auc.security.model.User;
import ru.isu.auc.security.model.UserShort;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class ConnectedUsers {
    @Id
    private Long roomId;

    @ManyToAny
    List<User> joinedUsers = new ArrayList<>();

    public ConnectedUsers setRoomId(Long roomId) {
        this.roomId = roomId;
        return this;
    }
}
