package ru.isu.auc.auction.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.isu.auc.auction.model.room.Room;

public interface RoomRepo extends JpaRepository<Room, Long> {
}
