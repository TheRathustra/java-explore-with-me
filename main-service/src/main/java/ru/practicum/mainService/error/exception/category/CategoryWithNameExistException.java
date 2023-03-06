package ru.practicum.mainService.error.exception.category;

public class CategoryWithNameExistException extends RuntimeException {
    public CategoryWithNameExistException(String message) {
        super(message);
    }
}
