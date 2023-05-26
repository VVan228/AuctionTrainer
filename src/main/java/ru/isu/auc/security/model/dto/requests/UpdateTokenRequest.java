package ru.isu.auc.security.model.dto.requests;

import lombok.Data;

@Data
public class UpdateTokenRequest {

    String refresh_token;
}

