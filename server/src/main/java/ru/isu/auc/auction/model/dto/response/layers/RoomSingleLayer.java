package ru.isu.auc.auction.model.dto.response.layers;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.isu.auc.auction.model.dto.response.RoomDTO;
import ru.isu.auc.auction.model.dto.response.UserDTO;
import ru.isu.auc.auction.model.room.Room;
import ru.isu.auc.auction.model.types.Status;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
public class RoomSingleLayer extends RoomDTO {
    List<UserDTO> connectedUsers = new ArrayList<>();
    List<RoundBottomLayer> rounds = new ArrayList<>();

    public RoomSingleLayer(
        Room room, Status s) {

        super(room, s);
    }
}
