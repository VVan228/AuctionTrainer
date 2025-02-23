package ru.isu.auc.auction.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.util.UUID;

@Data
@MappedSuperclass
public abstract class BaseEntity {
    @JsonIgnore
    UUID uid = UUID.randomUUID();
}
