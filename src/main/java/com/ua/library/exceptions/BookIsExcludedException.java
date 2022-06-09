package com.ua.library.exceptions;

public class BookIsExcludedException extends Exception {

    public BookIsExcludedException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
