package com.ddf.ingestion_ddf.exception;

/**
 * Custom exception to represent the scenario when there is an issue with ingestion request status.
 */
public class IngestionRequestStatusException extends RuntimeException {
    /**
     * Constructs a new IngestionRequestStatusException with the specified detail message.
     *
     * @param message the detail message
     */
    public IngestionRequestStatusException(String message) {
        super(message);
    }
}
