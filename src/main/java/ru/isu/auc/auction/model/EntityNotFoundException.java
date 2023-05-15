package ru.isu.auc.auction.model;

import org.springframework.http.HttpStatus;
import ru.isu.auc.common.impl.MapBuilder;
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
}
