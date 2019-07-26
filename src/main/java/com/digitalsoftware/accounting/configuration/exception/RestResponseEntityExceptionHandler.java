package com.digitalsoftware.accounting.configuration.exception;

import com.digitalsoftware.accounting.configuration.exception.exception.ResourceMissingException;
import com.digitalsoftware.accounting.configuration.exception.exception.UserExistsException;
import com.digitalsoftware.accounting.response.ErrorResponse;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.InvalidParameterException;


@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    private static ErrorResponse errorResponseHandler(int statusCode, Exception ex, String message, HttpServletRequest request) {
        StringWriter writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        printWriter.flush();
        String stackTrace = writer.toString();

        stackTrace = stackTrace.replace("\t", "        ");

        return new ErrorResponse(statusCode,
            message,
            stackTrace.split("\n"),
            request.getServletPath());
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class,
        ConstraintViolationException.class})
    @ResponseStatus(code = HttpStatus.CONFLICT)
    @ResponseBody
    protected ErrorResponse handleConflict(DataIntegrityViolationException ex, HttpServletRequest request) {
        return errorResponseHandler(HttpStatus.CONFLICT.value(),
            ex, "Cannot do this. " + ex.getMostSpecificCause().getMessage(), request);
    }

    @ExceptionHandler(value = {UserExistsException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ErrorResponse handleConflict(Exception ex, HttpServletRequest request) {
        return errorResponseHandler(HttpStatus.BAD_REQUEST.value(),
            ex, "Cannot do this. " + ex.getMessage(), request);
    }

    @ExceptionHandler(InvalidRequestException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ErrorResponse handleInvalidRequest(InvalidRequestException ex, HttpServletRequest request) {
        return errorResponseHandler(HttpStatus.BAD_REQUEST.value(),
            ex, "Please follow the API document. " + ex.getMessage(), request);
    }

    @ExceptionHandler(ResourceMissingException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ResponseBody
    protected ErrorResponse handleResourceMissing(ResourceMissingException ex, HttpServletRequest request) {
        return errorResponseHandler(HttpStatus.NOT_FOUND.value(),
            ex, "Wrong item. " + ex.getMessage(), request);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    @ResponseBody
    protected ErrorResponse handlePermissionDenied(AccessDeniedException ex, HttpServletRequest request) {
        return errorResponseHandler(HttpStatus.FORBIDDEN.value(),
            ex, "Wrong action. " + ex.getMessage(), request);
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    protected ErrorResponse handleUnauthorized(AuthenticationException ex, HttpServletRequest request) {
        return errorResponseHandler(HttpStatus.UNAUTHORIZED.value(),
            ex, "Wrong person. " + ex.getMessage(), request);
    }

    @ExceptionHandler(value = {NullPointerException.class,
        IOException.class,
        InvalidParameterException.class,
        IllegalArgumentException.class,
        DataAccessException.class,
        HttpServerErrorException.class,
        RestClientException.class})
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    protected ErrorResponse handleInternelError(RuntimeException ex, HttpServletRequest request) {
        return errorResponseHandler(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            ex, "We've found a problem. " + ex.getMessage(), request);
    }
}