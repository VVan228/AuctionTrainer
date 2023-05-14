package ru.isu.auc.auction.api.factorties;

import org.javatuples.Triplet;
import ru.isu.auc.auction.model.requests.CreateDefaultRoomRequest;
import ru.isu.auc.auction.model.room.Lot;
import ru.isu.auc.auction.model.room.Room;
import ru.isu.auc.auction.model.room.Round;
import ru.isu.auc.security.model.User;

import java.util.List;

public interface RoomFactory {
    Triplet<Room, List<Lot>, List<Round>> createDefaultRoom(CreateDefaultRoomRequest request, User creator);
}
