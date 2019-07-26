package com.digitalsoftware.accounting.configuration.exception.exception;

import com.digitalsoftware.accounting.configuration.exception.RootException;

public class DataConflictException extends RootException {

    public DataConflictException() {
        super("Database has the data conflict");

    }

    public DataConflictException(String message) {
        super(message);

    }
}
