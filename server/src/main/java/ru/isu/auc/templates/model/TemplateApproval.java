package ru.isu.auc.templates.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TemplateApproval {
    @Id
    Long templateId;

    @ElementCollection
    Map<Long, Boolean> approvals = new HashMap<>();

    public boolean hasApproved(Long userId){
        Boolean approved = approvals.get(userId);
        return approved!=null?approved: false;
    }

    public TemplateApproval setTemplateId(Long templateId) {
        this.templateId = templateId;
        return this;
    }
}
