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

    public static EntityNotFoundException notCreatorOfTheRoom(){
        return new EntityNotFoundException(
            HttpStatus.FORBIDDEN,
            new SendableError()
                .put("action", "room/nextPoint")
                .setMessage("User is not creator of this room"));
    }

    public static EntityNotFoundException notInRoom(){
        return new EntityNotFoundException(
            HttpStatus.FORBIDDEN,
            new SendableError()
                .put("action", "room/nextPoint")
                .setMessage("User is not participant of the room"));
    }

    public static EntityNotFoundException alreadyInRoom(){
        return new EntityNotFoundException(
            HttpStatus.FORBIDDEN,
            new SendableError()
                .put("action", "room/nextPoint")
                .setMessage("User is already participant of the room"));
    }

    public static NotAllowedException alreadyApproved(){
        return new NotAllowedException(
            HttpStatus.FORBIDDEN,
            new SendableError()
                .put("action", "template/approve")
                .setMessage("User already approved this template"));
    }
    public static NotAllowedException notApproved(){
        return new NotAllowedException(
            HttpStatus.FORBIDDEN,
            new SendableError()
                .put("action", "template/unapprove")
                .setMessage("User did not approve this template"));
    }

    public static NotAllowedException templateIsPrivate(){
        return new NotAllowedException(
            HttpStatus.FORBIDDEN,
            new SendableError()
                .put("action", "template/approve")
                .setMessage("Template is private"));
    }
}
