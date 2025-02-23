package ru.isu.auc.auction.model.dto.response.layers;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.isu.auc.auction.model.dto.response.RoomDTO;
import ru.isu.auc.auction.model.room.Room;
import ru.isu.auc.auction.model.types.Status;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class RoomMultiLayer extends RoomDTO {
    List<Long> connectedUsers = new ArrayList<>();
    List<RoundMiddleLayer<?>> rounds = new ArrayList<>();

    public RoomMultiLayer(Room r, Status status) {
        super(r, status);
    }
}
