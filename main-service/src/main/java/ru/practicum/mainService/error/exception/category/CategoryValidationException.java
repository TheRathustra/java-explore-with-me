package ru.practicum.mainService.error.exception.category;

public class CategoryValidationException extends RuntimeException {

    public CategoryValidationException(String message) {
        super(message);
    }
}
