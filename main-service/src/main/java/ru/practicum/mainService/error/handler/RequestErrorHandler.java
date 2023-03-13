package ru.practicum.mainService.error.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.mainService.controller.privates.RequestControllerPrivate;
import ru.practicum.mainService.error.ApiError;
import ru.practicum.mainService.error.exception.event.EventNotFoundException;
import ru.practicum.mainService.error.exception.request.InvalidRequestException;
import ru.practicum.mainService.error.exception.request.RequestAlreadyExistException;
import ru.practicum.mainService.error.exception.request.RequestNotFoundException;
import ru.practicum.mainService.error.exception.user.UserNotFoundException;

@RestControllerAdvice(assignableTypes = {RequestControllerPrivate.class})
public class RequestErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiError> handleUserNotFoundException(final UserNotFoundException e) {
        ApiError error = new ApiError(e.getMessage());
        error.setReason("The required object was not found.");
        error.setStatus(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiError> handleEventNotFoundException(final EventNotFoundException e) {
        ApiError error = new ApiError(e.getMessage());
        error.setReason("The required object was not found.");
        error.setStatus(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiError> handleRequestNotFoundException(final RequestNotFoundException e) {
        ApiError error = new ApiError(e.getMessage());
        error.setReason("The required object was not found.");
        error.setStatus(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ApiError> handleRequestAlreadyExistException(final RequestAlreadyExistException e) {
        ApiError error = new ApiError(e.getMessage());
        error.setReason("Integrity constraint has been violated.");
        error.setStatus(HttpStatus.CONFLICT);

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ApiError> handleInvalidRequestException(final InvalidRequestException e) {
        ApiError error = new ApiError(e.getMessage());
        error.setReason("Integrity constraint has been violated.");
        error.setStatus(HttpStatus.CONFLICT);

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

}
