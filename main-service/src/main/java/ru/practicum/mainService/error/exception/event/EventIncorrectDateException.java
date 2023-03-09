package ru.practicum.mainService.error.exception.event;

public class EventIncorrectDateException extends RuntimeException {
    public EventIncorrectDateException(String message) {
        super(message);
    }
}
