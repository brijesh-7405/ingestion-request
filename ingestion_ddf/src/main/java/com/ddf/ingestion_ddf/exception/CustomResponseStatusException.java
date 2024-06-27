package com.ddf.ingestion_ddf.exception;

/**
 * Custom exception to represent a response status exception.
 */
public class CustomResponseStatusException extends RuntimeException {
    /**
     * Constructs a new CustomResponseStatusException with the specified detail message.
     *
     * @param message the detail message
     */
    public CustomResponseStatusException(String message) {
        super(message);
    }
}
