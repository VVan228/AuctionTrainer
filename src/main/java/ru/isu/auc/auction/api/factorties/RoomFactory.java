package ru.isu.auc.auction.api.factorties;

import ru.isu.auc.auction.model.requests.CreateDefaultRoomRequest;
import ru.isu.auc.auction.model.room.Room;
import ru.isu.auc.security.model.User;

public interface RoomFactory {
    Room createAndSaveDefaultRoom(CreateDefaultRoomRequest request, User creator);
}
