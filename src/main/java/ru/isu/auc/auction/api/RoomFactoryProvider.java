package ru.isu.auc.auction.api;

import ru.isu.auc.auction.api.CreateRoomRequest;
import ru.isu.auc.auction.api.factorties.RoomFactory;

public interface RoomFactoryProvider {
    <R extends CreateRoomRequest> RoomFactory<R> getFactory(R request);
}
