package com.example.brokerage._exception;

public class EmailExistsException extends Exception {

    public EmailExistsException(String errorMessage) {
        super(errorMessage);
    }

    public EmailExistsException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
