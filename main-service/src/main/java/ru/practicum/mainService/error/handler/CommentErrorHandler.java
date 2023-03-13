package ru.practicum.mainService.error.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.mainService.controller.admins.CommentControllerAdmin;
import ru.practicum.mainService.controller.publics.CommentControllerPublic;
import ru.practicum.mainService.error.ApiError;
import ru.practicum.mainService.error.exception.comment.CommentNotFoundException;
import ru.practicum.mainService.error.exception.comment.CommentValidationException;
import ru.practicum.mainService.error.exception.comment.NotAuthorOfCommentException;

@RestControllerAdvice(assignableTypes = {CommentControllerPublic.class, CommentControllerAdmin.class})
public class CommentErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handleCommentValidationException(final CommentValidationException e) {
        ApiError error = new ApiError(e.getMessage());
        error.setReason("For the requested operation the conditions are not met.");
        error.setStatus(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handleCommentNotFoundException(final CommentNotFoundException e) {
        ApiError error = new ApiError(e.getMessage());
        error.setReason("For the requested operation the conditions are not met.");
        error.setStatus(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handleNotAuthorOfCommentException(final NotAuthorOfCommentException e) {
        ApiError error = new ApiError(e.getMessage());
        error.setReason("For the requested operation the conditions are not met.");
        error.setStatus(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
