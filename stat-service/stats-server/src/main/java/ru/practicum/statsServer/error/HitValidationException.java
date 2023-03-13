package ru.practicum.statsServer.error;

public class HitValidationException extends RuntimeException {
    public HitValidationException(String message) {
        super(message);
    }
}
