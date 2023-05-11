package ru.isu.auc.auction.service.factories;

import ru.isu.auc.auction.model.interval.Interval;
import ru.isu.auc.auction.model.requests.CreateDefaultRoomRequest;
import ru.isu.auc.auction.model.room.Room;
import ru.isu.auc.security.model.User;

public interface RoomFactory {
    Room createDefaultRoom(CreateDefaultRoomRequest request, User creator);
}
