package ru.isu.auc.auction.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.isu.auc.auction.model.room.Lot;

import java.util.UUID;

@Repository
public interface LotRepo extends JpaRepository<Lot, Long> {

    @Query("from Lot where uid=:uid")
    Lot findByUid(@Param("uid") UUID uid);

    @Query("from Lot l join interval i on l.uid=i.entityUid where i.id=:intervalId")
    Lot findByIntervalId(@Param("intervalId") Long intervalId);
}
