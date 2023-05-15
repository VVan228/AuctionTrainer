package ru.isu.auc.security.model;

import org.springframework.http.HttpStatus;
import ru.isu.auc.auction.model.EntityNotFoundException;
import ru.isu.auc.exception.model.AbstractException;
import ru.isu.auc.exception.model.SendableError;

public class NotAllowedException extends AbstractException {

    static final String DEFAULT_MESSAGE = "User has no permission for this action";

    public NotAllowedException(HttpStatus status, SendableError error) {
        super(status, error);
    }

    public static EntityNotFoundException roomNextPoint(){
        return new EntityNotFoundException(
            HttpStatus.FORBIDDEN,
            new SendableError()
                .put("action", "room/nextPoint")
                .setMessage(DEFAULT_MESSAGE));
    }
}
