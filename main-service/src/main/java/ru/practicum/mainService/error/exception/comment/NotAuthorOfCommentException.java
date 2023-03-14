package ru.practicum.mainService.error.exception.comment;

public class NotAuthorOfCommentException extends RuntimeException {
    public NotAuthorOfCommentException(String message) {
        super(message);
    }
}
