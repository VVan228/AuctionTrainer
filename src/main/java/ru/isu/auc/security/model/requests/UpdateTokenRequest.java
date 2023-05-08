package ru.isu.auc.security.model.requests;

import lombok.Data;

@Data
public class UpdateTokenRequest {

    String refresh_token;
}

