package ru.isu.auc.templates.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.isu.auc.templates.model.TemplateApproval;

public interface TemplateApprovalRepo extends JpaRepository<TemplateApproval, Long> {

//    @Query("select appr  \n" +
//        "from TemplateApproval ta join ta.approvals appr where ta.templateId=:templateId")
//    Boolean hasApproved(@Param("userId") Long userId, @Param("templateId") Long templateId);
}
