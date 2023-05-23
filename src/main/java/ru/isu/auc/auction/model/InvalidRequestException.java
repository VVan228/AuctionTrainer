package ru.isu.auc.auction.model;

import org.springframework.http.HttpStatus;
import ru.isu.auc.exception.model.AbstractException;
import ru.isu.auc.exception.model.SendableError;

public class InvalidRequestException extends AbstractException {

    static String defaultMsg = "Bet is invalid, ";

    public InvalidRequestException(HttpStatus status, SendableError error) {
        super(status, error);
    }

    public static InvalidRequestException roomIsAlreadyGoing(){
        return new InvalidRequestException(
            HttpStatus.FORBIDDEN,
            new SendableError()
                .setMessage("Room is already going"));
    }

    public static InvalidRequestException noId(){
        return new InvalidRequestException(
            HttpStatus.BAD_REQUEST,
            new SendableError()
                .setMessage("No identification provided"));
    }

    public static InvalidRequestException alreadyMadeBet(){
        return new InvalidRequestException(
            HttpStatus.FORBIDDEN,
            new SendableError()
                .setMessage("User already made bet"));
    }

    public static InvalidRequestException lotIsNotEnded(){
        return new InvalidRequestException(
            HttpStatus.FORBIDDEN,
            new SendableError()
                .setMessage("Lot is not ended"));
    }


    public static InvalidRequestException betIsTooSmall(){
        return new InvalidRequestException(
            HttpStatus.FORBIDDEN,
            new SendableError()
                .setMessage(defaultMsg + "round is ascending but bet is lower then start bet"));
    }

    public static InvalidRequestException betIsTooBig(){
        return new InvalidRequestException(
            HttpStatus.FORBIDDEN,
            new SendableError()
                .setMessage(defaultMsg + "round is descending but bet is higher then start bet"));
    }

    public static InvalidRequestException betHasBadStep(){
        return new InvalidRequestException(
            HttpStatus.FORBIDDEN,
            new SendableError()
                .setMessage(defaultMsg + "bet is smaller then min bet step"));
    }

    public static InvalidRequestException lotIsNotOngoing(){
        return new InvalidRequestException(
            HttpStatus.FORBIDDEN,
            new SendableError()
                .setMessage("Lot is not ongoing"));
    }
}
