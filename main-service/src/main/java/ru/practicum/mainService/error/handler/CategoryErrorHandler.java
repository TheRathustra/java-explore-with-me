package ru.practicum.mainService.error.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.mainService.controller.admins.CategoryControllerAdmin;
import ru.practicum.mainService.error.ApiError;
import ru.practicum.mainService.error.exception.CategoryNotFoundException;
import ru.practicum.mainService.error.exception.CategoryValidationException;

@RestControllerAdvice(assignableTypes = {CategoryControllerAdmin.class})
public class CategoryErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiError> handleCategoryNotFoundException(final CategoryNotFoundException e) {
        ApiError error = new ApiError(e.getMessage());
        error.setReason("The required object was not found.");
        error.setStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handleCategoryValidationException(final CategoryValidationException e) {
        ApiError error = new ApiError(e.getMessage());
        error.setReason("Incorrectly made request.");
        error.setStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


}
