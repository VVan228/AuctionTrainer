package ru.isu.auc.auction.model;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.util.UUID;

@Data
@MappedSuperclass
public abstract class BaseEntity {
    UUID uid = UUID.randomUUID();
}
