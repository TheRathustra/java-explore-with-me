package ru.practicum.mainService.error.exception;

public class CompilationNotFountException extends RuntimeException {

    public CompilationNotFountException() {
    }

    public CompilationNotFountException(String message) {
        super(message);
    }

}
