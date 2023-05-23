package ru.isu.auc.auction.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.isu.auc.auction.model.room.Room;
import ru.isu.auc.auction.model.types.Status;

public interface RoomRepo extends JpaRepository<Room, Long> {
    @Query("select r.id from Room r " +
        "join IntervalQueue q on r.uid=q.roomUid " +
        "join q.intervalPoints ip " +
        "join ip.intervalStartIds ii " +
        "join interval i on ii.intervalId=i.id " +
        "join Lot l on i.entityUid=l.uid where l.id=:lotId")
    Long getIdByLotId(@Param("lotId") Long lotId);
}
