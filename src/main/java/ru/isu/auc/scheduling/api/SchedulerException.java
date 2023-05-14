package ru.isu.auc.scheduling.api;

public class SchedulerException extends Exception{

    public SchedulerException(Throwable cause) {
        super(cause);
    }

    public SchedulerException(String message, Throwable cause) {
        super(message, cause);
    }
}
