package ru.isu.auc.templates.model.dto.mapper;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Embedded;
import jakarta.persistence.ManyToOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.isu.auc.auction.model.dto.response.UserDTO;
import ru.isu.auc.common.api.DTOMapper;
import ru.isu.auc.security.model.User;
import ru.isu.auc.templates.model.Template;
import ru.isu.auc.templates.model.TemplateData;
import ru.isu.auc.templates.model.dto.request.TemplateDataDTO;
import ru.isu.auc.templates.model.dto.response.TemplateDTO;

import java.time.LocalDateTime;

@Component
public class TemplateMapper implements DTOMapper<TemplateDTO, Template> {

    @Autowired
    DTOMapper<UserDTO, User> userMapper;
    @Autowired
    DTOMapper<TemplateDataDTO, TemplateData> templateDataMapper;

    @Override
    public Template mapFromDto(TemplateDTO templateDTO) {
        throw new UnsupportedOperationException();
    }

    @Override
    public TemplateDTO mapToDto(Template template) {
        return new TemplateDTO()
            .setCreator(userMapper.mapToDto(template.getCreator()))
            .setApprovesAmount(template.getApprovesAmount())
            .setCreationTime(template.getCreationTime())
            .setData(templateDataMapper.mapToDto(template.getData()));
    }
}
