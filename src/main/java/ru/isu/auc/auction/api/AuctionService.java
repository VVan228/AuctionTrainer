package ru.isu.auc.auction.api;

import ru.isu.auc.auction.model.room.Room;

public interface AuctionService {
    void handleUserAction(Room room);
    void scheduleRoom(Room room);
}
