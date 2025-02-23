package ru.isu.auc.auction.model.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.isu.auc.auction.model.room.Room;
import ru.isu.auc.auction.model.types.Status;
import ru.isu.auc.security.model.User;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class RoomDTO {

    Long id;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    LocalDateTime startTime;
    UserDTO creator;
    String name;
    Status status;

    public RoomDTO(Room r, Status status) {
        this.id = r.getId();
        this.startTime = r.getStartTime();
        this.creator = new UserDTO(r.getCreator());
        this.name = r.getName();
        this.status = status;
    }
}
