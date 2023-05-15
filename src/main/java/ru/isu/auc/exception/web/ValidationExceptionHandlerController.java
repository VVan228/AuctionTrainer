package ru.isu.auc.exception.web;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.isu.auc.exception.model.SendableError;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ValidationExceptionHandlerController {

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<SendableError> methodArgumentNotValidException(
        MethodArgumentNotValidException ex) {

        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return new ResponseEntity<>(
            processFieldErrors(fieldErrors)
                .setMessage("Validation error"),
            BAD_REQUEST);
    }

    private SendableError processFieldErrors(List<FieldError> fieldErrors) {
        SendableError error = new SendableError();
        for (org.springframework.validation.FieldError fieldError: fieldErrors) {
            error.put(fieldError.getObjectName() + "/" + fieldError.getField(), fieldError.getDefaultMessage());
        }
        return error;
    }
}
