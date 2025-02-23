package ru.isu.auc.auction.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.isu.auc.auction.model.interval.Interval;

@Repository
public interface IntervalRepo extends JpaRepository<Interval, Long> {


    @Query("select r.id from IntervalQueue q join Room r on q.roomUid=r.uid where q.id=:qid")
    Long getRoomIdBuQueueId(@Param("qid") Long queueId);

    @Query("select i from Lot l join interval i on i.entityUid=l.uid where l.id=:lotId")
    Interval getByLotId(@Param("lotId") Long lotId);
}
