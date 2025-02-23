package ru.isu.auc.auction.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import ru.isu.auc.auction.api.CreateRoomRequest;
import ru.isu.auc.auction.model.room.BetParams;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreateDefaultRoomRequest implements CreateRoomRequest {
    String name;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    LocalDateTime startTime;
    BetParams defaultBetParams;

    Long defaultRoundPause;
    Long defaultLotPause;
    Long defaultLotDuration;

    List<RoundRequestPart> rounds;

    public CreateDefaultRoomRequest setName(String name) {
        this.name = name;
        return this;
    }

    public CreateDefaultRoomRequest setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
        return this;
    }

    public CreateDefaultRoomRequest setDefaultBetParams(BetParams defaultBetParams) {
        this.defaultBetParams = defaultBetParams;
        return this;
    }

    public CreateDefaultRoomRequest setDefaultRoundPause(Long defaultRoundPause) {
        this.defaultRoundPause = defaultRoundPause;
        return this;
    }

    public CreateDefaultRoomRequest setDefaultLotPause(Long defaultLotPause) {
        this.defaultLotPause = defaultLotPause;
        return this;
    }

    public CreateDefaultRoomRequest setDefaultLotDuration(Long defaultLotDuration) {
        this.defaultLotDuration = defaultLotDuration;
        return this;
    }

    public CreateDefaultRoomRequest setRounds(List<RoundRequestPart> rounds) {
        this.rounds = rounds;
        return this;
    }
}
