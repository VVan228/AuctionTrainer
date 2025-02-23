package ru.isu.auc.templates.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.isu.auc.security.model.User;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Boolean isDefault;
    LocalDateTime creationTime;
    @ManyToOne
    User creator;
    Integer approvesAmount;
    Boolean isPrivate;
    @Embedded
    TemplateData data;

    public Template setId(Long id) {
        this.id = id;
        return this;
    }

    public Template setDefault(Boolean aDefault) {
        isDefault = aDefault;
        return this;
    }

    public Template setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
        return this;
    }

    public Template setCreator(User creator) {
        this.creator = creator;
        return this;
    }

    public Template setApprovesAmount(Integer approvesAmount) {
        this.approvesAmount = approvesAmount;
        return this;
    }

    public Template setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
        return this;
    }

    public Template setData(TemplateData data) {
        this.data = data;
        return this;
    }
}
