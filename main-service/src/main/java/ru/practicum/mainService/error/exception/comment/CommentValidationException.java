package ru.practicum.mainService.error.exception.comment;

public class CommentValidationException extends RuntimeException {
    public CommentValidationException(String message) {
        super(message);
    }
}
