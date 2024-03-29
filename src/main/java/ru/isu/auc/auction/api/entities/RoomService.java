package ru.isu.auc.auction.api.entities;

import ru.isu.auc.auction.api.CreateRoomRequest;
import ru.isu.auc.auction.model.dto.response.RoomDTO;
import ru.isu.auc.auction.model.room.ParticipantBet;
import ru.isu.auc.auction.model.room.Room;
import ru.isu.auc.exception.model.AbstractException;
import ru.isu.auc.security.model.User;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    Room save(Room room);
    Optional<Room> get(Long roomId);
    List<Room> saveAll(Iterable<Room> rooms);
}
