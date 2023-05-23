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

    <R extends CreateRoomRequest> void createRoom(R request, User user);
    void joinRoom(User user, Long roomId) throws AbstractException;
    void leaveRoom(User user, Long roomId) throws AbstractException;
    <R extends RoomDTO> R getFullRoom(Long roomId) throws AbstractException;
    void handleBet(Long intervalId, User user, Long sum) throws AbstractException;
    ParticipantBet getLotResultByIntervalId(Long intervalId) throws AbstractException;
    ParticipantBet getLotResultByLotId(Long lotId) throws AbstractException;

}
