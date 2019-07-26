package com.digitalsoftware.accounting.configuration.exception.exception;

import com.digitalsoftware.accounting.configuration.exception.RootException;

public class ResourceMissingException extends RootException {


    public ResourceMissingException() {
        super("No Resource ");
    }

    public ResourceMissingException(String message) {
        super(message);
    }


}


