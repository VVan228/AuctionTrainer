package ru.isu.auc.templates.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.isu.auc.common.api.DTOMapper;
import ru.isu.auc.exception.model.AbstractException;
import ru.isu.auc.security.model.SecurityUser;
import ru.isu.auc.security.model.User;
import ru.isu.auc.templates.api.TemplateService;
import ru.isu.auc.templates.model.Template;
import ru.isu.auc.templates.model.TemplateData;
import ru.isu.auc.templates.model.dto.request.TemplateDataDTO;
import ru.isu.auc.templates.model.dto.response.TemplateDTO;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TemplateController {

    @Autowired
    DTOMapper<TemplateDataDTO, TemplateData> templateDataMapper;

    @Autowired
    DTOMapper<TemplateDTO, Template> templateMapper;

    @Autowired
    TemplateService templateService;

    @ResponseBody
    @RequestMapping(
        value="/template/create",
        method = RequestMethod.POST
    )
    public void createTemplate(@RequestParam boolean isPrivate, @RequestBody TemplateDataDTO templateData) {
        TemplateData templateData1 = templateDataMapper.mapFromDto(templateData);
        User user = SecurityUser.getCurrent().getUser();

        templateService.createTemplate(templateData1, user, isPrivate);
    }

    @ResponseBody
    @RequestMapping(
        value="/template/approve",
        method = RequestMethod.POST
    )
    public void approve(@RequestParam Long templateId) throws AbstractException {
        User user = SecurityUser.getCurrent().getUser();

        templateService.approveTemplate(templateId,user.getId());
    }

    @ResponseBody
    @RequestMapping(
        value="/template/unapprove",
        method = RequestMethod.POST
    )
    public void unapprove(@RequestParam Long templateId) throws AbstractException {
        User user = SecurityUser.getCurrent().getUser();

        templateService.unapproveTemplate(templateId,user.getId());
    }

    @ResponseBody
    @RequestMapping(
        value="/template/get/my",
        method = RequestMethod.GET
    )
    public List<TemplateDTO> getMyTemplates() throws AbstractException {
        User user = SecurityUser.getCurrent().getUser();

        var res = templateService.getUserTemplates(user.getId()).getContent().stream().map(t->templateMapper.mapToDto(t)).collect(Collectors.toList());
        return res;
    }

    @ResponseBody
    @RequestMapping(
        value="/template/get/public",
        method = RequestMethod.GET
    )
    public List<TemplateDTO> getPublicTemplates() throws AbstractException {
        User user = SecurityUser.getCurrent().getUser();

        var res = templateService.getPublicTemplates().getContent().stream().map(t->templateMapper.mapToDto(t)).collect(Collectors.toList());
        return res;
    }
}
