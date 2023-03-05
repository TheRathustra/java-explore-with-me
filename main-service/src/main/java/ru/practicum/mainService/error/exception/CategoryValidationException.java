package ru.practicum.mainService.error.exception;

public class CategoryValidationException extends RuntimeException {

    public CategoryValidationException(String message) {
        super(message);
    }
}
