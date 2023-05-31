package ru.isu.auc.templates.model.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.isu.auc.auction.model.dto.response.UserDTO;
import ru.isu.auc.templates.model.dto.request.TemplateDataDTO;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TemplateDTO {

    UserDTO creator;
    Integer approvesAmount;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    LocalDateTime creationTime;
    Boolean isDefault;
    Long id;

    TemplateDataDTO data;

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

    public TemplateDTO setDefault(Boolean aDefault) {
        isDefault = aDefault;
        return this;
    }

    public TemplateDTO setId(Long id) {
        this.id = id;
        return this;
    }
}
