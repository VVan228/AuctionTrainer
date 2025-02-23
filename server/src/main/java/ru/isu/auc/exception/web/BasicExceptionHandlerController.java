package ru.isu.auc.exception.web;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.isu.auc.exception.model.AbstractException;
import ru.isu.auc.exception.model.SendableError;
import ru.isu.auc.security.model.NotAllowedException;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class BasicExceptionHandlerController {

    @ResponseBody
    @ExceptionHandler(AbstractException.class)
    public ResponseEntity<SendableError> handle(AbstractException ex) {
        System.out.println("wippidy woppidy this error is being intercepted");
        return new ResponseEntity<>(ex.getError(), ex.getStatus());
    }
}
