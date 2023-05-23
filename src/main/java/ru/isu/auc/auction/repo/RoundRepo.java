package ru.isu.auc.auction.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.isu.auc.auction.model.room.Round;

import java.util.UUID;

public interface RoundRepo extends JpaRepository<Round, Long> {

    @Query("from Round where uid=:uid")
    Round findByUid(@Param("uid") UUID uid);

    @Query("select r from Round r " +
        "join interval i on r.uid=i.entityUid " +
        "join i.intervals ii " +
        "join Lot l on ii.entityUid=l.uid " +
        "where l.id=:lotId")
    Round findByLotId(@Param("lotId") Long lotId);
}
