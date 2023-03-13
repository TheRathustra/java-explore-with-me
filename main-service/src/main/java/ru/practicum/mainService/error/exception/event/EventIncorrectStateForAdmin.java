package ru.practicum.mainService.error.exception.event;

public class EventIncorrectStateForAdmin extends RuntimeException {
    public EventIncorrectStateForAdmin(String message) {
        super(message);
    }
}
