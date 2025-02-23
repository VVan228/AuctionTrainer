package ru.isu.auc.templates.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.isu.auc.auction.model.EntityNotFoundException;
import ru.isu.auc.exception.model.AbstractException;
import ru.isu.auc.security.model.NotAllowedException;
import ru.isu.auc.security.model.User;
import ru.isu.auc.templates.api.TemplateService;
import ru.isu.auc.templates.model.Template;
import ru.isu.auc.templates.model.TemplateApproval;
import ru.isu.auc.templates.model.TemplateData;
import ru.isu.auc.templates.repo.TemplateApprovalRepo;
import ru.isu.auc.templates.repo.TemplateRepo;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class TemplateServiceImpl implements TemplateService {

    TemplateRepo templateRepo;
    TemplateApprovalRepo templateApprovalRepo;

    @Autowired
    public TemplateServiceImpl(TemplateRepo templateRepo, TemplateApprovalRepo templateApprovalRepo) {
        this.templateRepo = templateRepo;
        this.templateApprovalRepo = templateApprovalRepo;
    }

    @Override
    public void createTemplate(TemplateData templateData, User user, boolean isPrivate) {
        Template template = new Template()
            .setData(templateData)
            .setCreator(user)
            .setPrivate(isPrivate)
            .setCreationTime(LocalDateTime.now())
            .setDefault(false)
            .setApprovesAmount(0);
        template.getData().getRounds().forEach(r->{
            if(r.getAscending()==null)
                r.setAscending(true);
        });
        template = templateRepo.save(template);
        templateApprovalRepo.save(new TemplateApproval().setTemplateId(template.getId()));
    }

    @Override
    @Transactional
    public void approveTemplate(Long templateId, Long userId) throws AbstractException {
        TemplateApproval ta = templateApprovalRepo.findById(templateId).orElseThrow(EntityNotFoundException::template);

        boolean templateIsPrivate = templateRepo.isPrivate(templateId);
        if(templateIsPrivate){
            throw NotAllowedException.templateIsPrivate();
        }

        Boolean approved = ta.getApprovals().get(userId);
        if(approved==null || !approved) {
            ta.getApprovals().put(userId, true);
            templateApprovalRepo.save(ta);
            templateRepo.increaseApprovalCount(templateId);
            return;
        }
        throw NotAllowedException.alreadyApproved();
    }

    @Transactional
    @Override
    public void unapproveTemplate(Long templateId, Long userId) throws AbstractException {
        TemplateApproval ta = templateApprovalRepo.findById(templateId).orElseThrow(EntityNotFoundException::template);

        if(templateRepo.isPrivate(templateId)){
            throw NotAllowedException.templateIsPrivate();
        }

        Boolean approved = ta.getApprovals().get(userId);
        if(approved!=null && approved) {
            ta.getApprovals().put(userId, false);
            templateApprovalRepo.save(ta);
            templateRepo.decreaseApprovalCount(templateId);
            return;
        }
        throw NotAllowedException.notApproved();
    }

    @Override
    public Page<Template> getUserTemplates(Pageable p, Long userId) throws AbstractException {
        return templateRepo.getUserTemplates(p, userId);
    }

    @Override
    public Page<Template> getPublicTemplates(Pageable p, Long userId) throws AbstractException {
        return templateRepo.getPublicTemplates(p, userId);
    }

    @Override
    public Template getSingleTemplate(Long id, Long userId) throws AbstractException {
        Template res = templateRepo.findById(id).orElseThrow(EntityNotFoundException::template);
        if(res.getIsPrivate() && !res.getCreator().getId().equals(userId))
            throw NotAllowedException.templateIsPrivate();
        return res;
    }

    @Override
    public void deleteTemplate(Long templateId, Long id) throws AbstractException {
        Template t = templateRepo.findById(templateId).orElseThrow(EntityNotFoundException::template);

        if(!Objects.equals(t.getCreator().getId(), id)) {
            throw NotAllowedException.templateIsPrivate();
        }

        templateRepo.deleteById(templateId);
        templateApprovalRepo.deleteById(templateId);
    }
}
