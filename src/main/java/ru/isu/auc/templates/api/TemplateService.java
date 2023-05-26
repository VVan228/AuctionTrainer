package ru.isu.auc.templates.api;

import org.springframework.data.domain.Page;
import ru.isu.auc.auction.model.EntityNotFoundException;
import ru.isu.auc.exception.model.AbstractException;
import ru.isu.auc.security.model.User;
import ru.isu.auc.templates.model.Template;
import ru.isu.auc.templates.model.TemplateData;

public interface TemplateService {
    void createTemplate(TemplateData templateData, User user, boolean isPrivate);
    void approveTemplate(Long templateId, Long userId) throws AbstractException;
    void unapproveTemplate(Long templateId, Long userId) throws AbstractException;
    Page<Template> getUserTemplates(Long userId) throws AbstractException;
    Page<Template> getPublicTemplates() throws AbstractException;
}
