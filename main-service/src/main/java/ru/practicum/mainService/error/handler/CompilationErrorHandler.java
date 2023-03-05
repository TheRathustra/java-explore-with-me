package ru.practicum.mainService.error.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.mainService.controller.publics.CompilationControllerPublic;
import ru.practicum.mainService.error.ApiError;
import ru.practicum.mainService.error.exception.CompilationNotFountException;

@RestControllerAdvice(assignableTypes = {CompilationControllerPublic.class})
public class CompilationErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiError> handleCompilationNotFountException(final CompilationNotFountException e) {
        ApiError error = new ApiError(e.getMessage());
        error.setReason("The required object was not found.");
        error.setStatus(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
