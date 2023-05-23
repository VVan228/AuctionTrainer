package ru.isu.auc.auction.model.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.isu.auc.auction.model.interval.Interval;
import ru.isu.auc.auction.model.room.Round;
import ru.isu.auc.auction.model.types.Status;

import java.util.UUID;

@Data
@NoArgsConstructor
public class RoundDTO {

    Long id;
    Boolean ascending;
    Status status;
    Long intervalId;

    public RoundDTO(Round r, Interval i) {
        this.id = r.getId();
        this.ascending = r.getAscending();

        this.status = i.getStatus();
        this.intervalId = i.getId();
    }
}
