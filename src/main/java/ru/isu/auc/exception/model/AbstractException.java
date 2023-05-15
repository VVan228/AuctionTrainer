package ru.isu.auc.exception.model;

import org.springframework.http.HttpStatus;

import java.util.Map;

public abstract class AbstractException extends Exception{

    SendableError error;
    HttpStatus status;
    public SendableError getError() {
        return this.error;
    }
    public HttpStatus getStatus(){
        return this.status;
    }

    public AbstractException(
        HttpStatus status,
        SendableError error) {
        super();
        this.error = error;
        this.status = status;
    }
}
