package com.ddf.ingestion_ddf.exception;

/**
 * Custom exception to represent the scenario when validation notes are not found.
 */
public class ValidationNotesNotFoundException extends RuntimeException {
    /**
     * Constructs a new ValidationNotesNotFoundException with the specified detail message.
     *
     * @param message the detail message
     */
    public ValidationNotesNotFoundException(String message) {
        super(message);
    }
}
