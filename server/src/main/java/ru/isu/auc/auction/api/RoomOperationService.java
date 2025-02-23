package ru.isu.auc.auction.api;

import ru.isu.auc.auction.model.dto.response.RoomDTO;
import ru.isu.auc.auction.model.room.ParticipantBet;
import ru.isu.auc.exception.model.AbstractException;
import ru.isu.auc.security.model.User;

public interface RoomOperationService {
    <R extends CreateRoomRequest> Long createRoom(R request, User user) throws AbstractException;
    void joinRoom(User user, Long roomId) throws AbstractException;
    void leaveRoom(User user, Long roomId) throws AbstractException;
    <R extends RoomDTO> R getFullRoom(Long roomId) throws AbstractException;
    void handleBet(Long intervalId, User user, Long sum) throws AbstractException;
    ParticipantBet getLotResultByIntervalId(Long intervalId) throws AbstractException;
    ParticipantBet getLotResultByLotId(Long lotId) throws AbstractException;
}
