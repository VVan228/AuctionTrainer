package ru.isu.auc.templates.model.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.isu.auc.auction.model.dto.response.BetParamsDTO;
import ru.isu.auc.auction.model.dto.response.UserDTO;
import ru.isu.auc.templates.model.Template;
import ru.isu.auc.templates.model.dto.request.TemplateDataDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class TemplateDTO {

    UserDTO creator;
    Integer approvesAmount;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    LocalDateTime creationTime;

    TemplateDataDTO data;

    public TemplateDTO(Template template) {
        this.creator = new UserDTO(template.getCreator());
        this.approvesAmount = template.getApprovesAmount();
        this.creationTime = template.getCreationTime();
        this.data = new TemplateDataDTO(template.getData());
    }

    public TemplateDTO setCreator(UserDTO creator) {
        this.creator = creator;
        return this;
    }

    public TemplateDTO setApprovesAmount(Integer approvesAmount) {
        this.approvesAmount = approvesAmount;
        return this;
    }

    public TemplateDTO setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
        return this;
    }

    public TemplateDTO setData(TemplateDataDTO data) {
        this.data = data;
        return this;
    }
}
