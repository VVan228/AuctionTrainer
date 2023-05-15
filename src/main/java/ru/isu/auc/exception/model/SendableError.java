package ru.isu.auc.exception.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class SendableError {
    Map<String, Object> errors = new HashMap<>();
    String message;

    public SendableError setMessage(String message) {
        this.message = message;
        return this;
    }

    public SendableError put(String key, Object val) {
        errors.put(key, val);
        return this;
    }
}
