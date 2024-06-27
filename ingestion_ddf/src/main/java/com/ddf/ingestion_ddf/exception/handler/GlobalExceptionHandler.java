package com.ddf.ingestion_ddf.exception.handler;

import com.ddf.ingestion_ddf.configurations.ErrorProperties;
import com.ddf.ingestion_ddf.exception.CustomResponseStatusException;
import com.ddf.ingestion_ddf.exception.IngestionRequestDetailsNotFoundException;
import com.ddf.ingestion_ddf.exception.IngestionRequestStatusException;
import com.ddf.ingestion_ddf.exception.ValidationNotesNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler to handle various exceptions across the application.
 */
@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {

    /**
     * The properties containing error messages.
     */
    private final ErrorProperties errorProperties;

    /**
     * Handles exceptions when ingestion request details are not found.
     */
    @ExceptionHandler(IngestionRequestDetailsNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleIngestionRequestNotFoundException(IngestionRequestDetailsNotFoundException ex) {
        return handleException(HttpStatus.NOT_FOUND, "ingestion.request.not.exist", ex.getMessage());
    }

    /**
     * Handles exceptions when method argument type mismatch occurs.
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return handleException(HttpStatus.BAD_REQUEST, "bad_request.invalid_field", ex);
    }

    /**
     * Handles custom response status exceptions.
     */
    @ExceptionHandler(CustomResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleResponseStatusException(CustomResponseStatusException ex) {
        return handleException(HttpStatus.BAD_REQUEST, null, ex.getMessage());
    }

    /**
     * Handles exceptions related to ingestion request status.
     */
    @ExceptionHandler(IngestionRequestStatusException.class)
    public ResponseEntity<Map<String, Object>> handleIngestionRequestStatusException(IngestionRequestStatusException ex) {
        return handleException(HttpStatus.BAD_REQUEST, "status_update", ex.getMessage());
    }

    /**
     * Handles exceptions when validation notes are not found.
     */
    @ExceptionHandler(ValidationNotesNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleValidationNotesNotFoundException(ValidationNotesNotFoundException ex) {
        return handleException(HttpStatus.NOT_FOUND, "notes", ex.getMessage());
    }

    /**
     * Handles exceptions and constructs the response entity with appropriate error message.
     */
    private ResponseEntity<Map<String, Object>> handleException(HttpStatus status, String messageKey, Object... args) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", System.currentTimeMillis());
        response.put("status", status.value());
        response.put("error", errorProperties.getErrors().get(status.equals(HttpStatus.BAD_REQUEST) ? "bad_request" : "not_found"));
        String errorMessage;
        if (messageKey != null) {
            errorMessage = errorProperties.getErrors().get(messageKey);
            if (errorMessage != null && args != null && args.length > 0) {
                errorMessage = replaceArgsInErrorMessage(errorMessage, args[0]);
            }
        } else {
            errorMessage = args != null && args.length > 0 ? args[0].toString() : "";
        }
        response.put("message", errorMessage);
        return new ResponseEntity<>(response, status);
    }

    /**
     * Replaces placeholders in the error message with the provided arguments.
     *
     * @param errorMessage the error message containing placeholders to be replaced
     * @param arg          the argument to be used for replacing placeholders in the error message;
     *                     can be an instance of {@link MethodArgumentTypeMismatchException} or any other object
     * @return the error message with placeholders replaced by the provided argument's values
     */
    private String replaceArgsInErrorMessage(String errorMessage, Object arg) {
        if (arg instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException ex = (MethodArgumentTypeMismatchException) arg;
            return errorMessage.replace("{0}", ex.getName()).replace("{1}", ex.getValue().toString());
        } else {
            return errorMessage.replace("{0}", arg.toString());
        }
    }


}
