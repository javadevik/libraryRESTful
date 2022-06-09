package com.ua.library.exceptions;

public class BookIsNotReturnException extends Exception {

    public BookIsNotReturnException(String message) {
        super(message);
    }

    public BookIsNotReturnException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public synchronized Throwable getCause() {
        return super.getCause();
    }
}
