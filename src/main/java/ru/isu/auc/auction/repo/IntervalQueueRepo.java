package ru.isu.auc.auction.repo;

import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import ru.isu.auc.auction.model.interval.IntervalQueue;

import java.util.UUID;

public interface IntervalQueueRepo extends JpaRepository<IntervalQueue, Long> {

    //@Lock(LockModeType.PESSIMISTIC_WRITE)
    //@QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value = "3000")})
    @Modifying(flushAutomatically = true)
    @Query("update IntervalQueue q set q.currentIndex = :index where q.id = :queueId")
    void setCurrentIndex(@Param("queueId") Long queueId, @Param("index") Long index);

    @Query("select q from Room r join IntervalQueue q on r.uid=q.roomUid where r.id=:roomId")
    IntervalQueue getByRoomId(@Param("roomId") Long roomId);

    @Query("select q from IntervalQueue q where q.roomUid=:roomUid")
    IntervalQueue getByRoomUid(@Param("roomUid") UUID roomUid);

    @Query("select q.id from IntervalQueue q where q.roomUid=:roomUid")
    Long getIntervalQIdByRoomUid(@Param("roomUid") UUID roomUid);
}
