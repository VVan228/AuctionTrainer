package ru.isu.auc.auction.model;

import org.springframework.http.HttpStatus;
import ru.isu.auc.exception.model.AbstractException;
import ru.isu.auc.exception.model.SendableError;

public class EntityNotFoundException extends AbstractException {

    static final String DEFAULT_MESSAGE = "Entity not found";


    public EntityNotFoundException(HttpStatus status, SendableError error) {
        super(status, error);
    }

    public static EntityNotFoundException room(){
        return new EntityNotFoundException(
            HttpStatus.BAD_REQUEST,
            new SendableError()
                .put("entity", "room")
                .setMessage(DEFAULT_MESSAGE));
    }

    public static EntityNotFoundException interval(){
        return new EntityNotFoundException(
            HttpStatus.BAD_REQUEST,
            new SendableError()
                .put("entity", "interval")
                .setMessage(DEFAULT_MESSAGE));
    }

    public static EntityNotFoundException connectedUsers(){
        return new EntityNotFoundException(
            HttpStatus.BAD_REQUEST,
            new SendableError()
                .put("entity", "connectedUsers")
                .setMessage(DEFAULT_MESSAGE));
    }

    public static EntityNotFoundException template(){
        return new EntityNotFoundException(
            HttpStatus.BAD_REQUEST,
            new SendableError()
                .put("entity", "template")
                .setMessage(DEFAULT_MESSAGE));
    }
}
