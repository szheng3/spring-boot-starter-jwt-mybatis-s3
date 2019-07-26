package com.digitalsoftware.accounting.configuration.exception;

public class RootException extends RuntimeException {

    private String message;

    public RootException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
