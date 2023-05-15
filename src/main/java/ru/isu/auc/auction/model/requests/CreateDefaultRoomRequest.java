package ru.isu.auc.auction.model.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import ru.isu.auc.auction.model.room.BetParams;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreateDefaultRoomRequest {
    String name;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    LocalDateTime startTime;
    BetParams defaultBetParams;

    Long defaultRoundPause;
    Long defaultLotPause;
    Long defaultLotDuration;

    List<RoundRequestPart> rounds;
}
