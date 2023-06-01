package ru.isu.auc.auction.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.isu.auc.auction.model.room.ConnectedUsers;

@Repository
public interface ConnectedUsersRepo extends JpaRepository<ConnectedUsers, Long> {

    @Query("select case when (count(ju) > 0)  then true else false end  \n" +
        "from ConnectedUsers cu join cu.joinedUsers ju where cu.roomId = :roomId and ju.id=:userId")
    boolean isInRoom(@Param("roomId") Long roomId, @Param("userId") Long userId);
}
