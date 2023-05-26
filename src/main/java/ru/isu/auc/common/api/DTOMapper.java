package ru.isu.auc.common.api;

public interface DTOMapper<DTO, OBJ> {
    OBJ mapFromDto(DTO dto);
    DTO mapToDto(OBJ obj);
}
