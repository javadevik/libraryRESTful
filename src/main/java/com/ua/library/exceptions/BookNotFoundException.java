package com.ua.library.exceptions;

public class BookNotFoundException extends Exception {

    public BookNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
