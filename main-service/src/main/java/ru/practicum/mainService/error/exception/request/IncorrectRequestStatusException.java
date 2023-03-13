package ru.practicum.mainService.error.exception.request;

public class IncorrectRequestStatusException extends RuntimeException {
    public IncorrectRequestStatusException(String message) {
        super(message);
    }
}
