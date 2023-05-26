package ru.isu.auc.templates.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.isu.auc.templates.model.Template;

public interface TemplateRepo extends JpaRepository<Template, Long> {

    @Modifying
    @Query("update Template t set t.approvesAmount = t.approvesAmount+1 where t.id = :id")
    void increaseApprovalCount(@Param("id") Long id);

    @Modifying
    @Query("update Template t set t.approvesAmount = t.approvesAmount-1 where t.id = :id")
    void decreaseApprovalCount(@Param("id") Long id);

    @Query("select t from Template t where t.isPrivate=false and t.isDefault=false")
    Page<Template> getPublicTemplates(Pageable pageable);

    @Query("select t from Template t where " +
           "t.id in (select tt.id from Template tt where tt.creator.id=:userId) " +
        "or t.id in (select ta.templateId from TemplateApproval ta join ta.approvals app where key(app)=:userId and value(app)) " +
        "or t.id in (select ttt.id from Template ttt where ttt.isDefault)"
    )
    Page<Template> getUserTemplates(Pageable pageable, @Param("userId") Long userId);

    @Query("select t.isPrivate from Template t where t.id=:templateId")
    boolean isPrivate(@Param("templateId") Long templateId);
}
