package ru.isu.auc.auction.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.isu.auc.auction.model.room.Room;
import ru.isu.auc.auction.model.types.Status;

public interface RoomRepo extends JpaRepository<Room, Long> {
}
