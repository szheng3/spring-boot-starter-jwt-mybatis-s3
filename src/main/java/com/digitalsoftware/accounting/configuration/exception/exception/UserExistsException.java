package com.digitalsoftware.accounting.configuration.exception.exception;

import com.digitalsoftware.accounting.configuration.exception.RootException;

public class UserExistsException extends RootException {


    public UserExistsException() {
        super("Your username is not available. Please provide a different Username ");


    }

    public UserExistsException(String message) {
        super(message);
    }


}