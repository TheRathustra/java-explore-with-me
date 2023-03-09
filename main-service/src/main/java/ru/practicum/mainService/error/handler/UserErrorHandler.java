package ru.practicum.mainService.error.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.mainService.controller.admins.UserControllerAdmin;
import ru.practicum.mainService.error.ApiError;
import ru.practicum.mainService.error.exception.user.UserAlreadyExistException;
import ru.practicum.mainService.error.exception.user.UserNotFoundException;
import ru.practicum.mainService.error.exception.user.UserValidationException;
import ru.practicum.mainService.service.impl.publics.UserServicePublicImpl;

@RestControllerAdvice(assignableTypes = {UserServicePublicImpl.class, UserControllerAdmin.class})
public class UserErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiError> handleUserNotFoundException(final UserNotFoundException e) {
        ApiError error = new ApiError(e.getMessage());
        error.setReason("The required object was not found.");
        error.setStatus(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handleUserValidationException(final UserValidationException e) {
        ApiError error = new ApiError(e.getMessage());
        error.setReason("Incorrectly made request.");
        error.setStatus(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ApiError> handleUserAlreadyExistException(final UserAlreadyExistException e) {
        ApiError error = new ApiError(e.getMessage());
        error.setReason("Incorrectly made request.");
        error.setStatus(HttpStatus.CONFLICT);

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }


}
