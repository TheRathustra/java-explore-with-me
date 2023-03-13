package ru.practicum.mainService.error.exception.event;

public class UserIsNotAnInitiatorOfEventException extends RuntimeException {
    public UserIsNotAnInitiatorOfEventException(String message) {
        super(message);
    }
}
