package ru.practicum.mainService.error.exception.event;

public class EventIncorrectState extends RuntimeException {
    public EventIncorrectState(String message) {
        super(message);
    }
}
