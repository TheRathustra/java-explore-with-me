package ru.practicum.mainService.error.exception.user;

public class UserAlreayExistException extends RuntimeException {
    public UserAlreayExistException(String message) {
        super(message);
    }
}
