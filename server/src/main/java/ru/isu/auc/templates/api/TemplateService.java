package ru.isu.auc.templates.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.isu.auc.auction.model.EntityNotFoundException;
import ru.isu.auc.exception.model.AbstractException;
import ru.isu.auc.security.model.User;
import ru.isu.auc.templates.model.Template;
import ru.isu.auc.templates.model.TemplateData;

public interface TemplateService {
    void createTemplate(TemplateData templateData, User user, boolean isPrivate);
    void approveTemplate(Long templateId, Long userId) throws AbstractException;
    void unapproveTemplate(Long templateId, Long userId) throws AbstractException;
    Page<Template> getUserTemplates(Pageable p, Long userId) throws AbstractException;
    Page<Template> getPublicTemplates(Pageable p, Long userId) throws AbstractException;
    Template getSingleTemplate(Long id, Long userId) throws AbstractException;
    void deleteTemplate(Long templateId, Long id) throws AbstractException;
}
