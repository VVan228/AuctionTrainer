package ru.isu.auc.auction.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import ru.isu.auc.auction.api.CreateRoomRequest;
import ru.isu.auc.auction.model.dto.response.LotDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class CreateRoomFromTemplateRequest  implements CreateRoomRequest {
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    LocalDateTime startTime;
    String name;

    Long templateId;
    List<List<LotRequestPart>> lots = new ArrayList<>();
}
