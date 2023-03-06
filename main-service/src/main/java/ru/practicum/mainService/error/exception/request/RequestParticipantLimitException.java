package ru.practicum.mainService.error.exception.request;

public class RequestParticipantLimitException extends RuntimeException {
    public RequestParticipantLimitException(String message) {
        super(message);
    }
}
