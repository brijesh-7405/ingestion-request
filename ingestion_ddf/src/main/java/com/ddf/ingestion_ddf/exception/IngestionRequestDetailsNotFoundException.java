package com.ddf.ingestion_ddf.exception;

/**
 * Custom exception to represent the scenario when ingestion request details are not found.
 */
public class IngestionRequestDetailsNotFoundException extends RuntimeException {
    /**
     * Constructs a new IngestionRequestDetailsNotFoundException with the specified detail message.
     *
     * @param message the detail message
     */
    public IngestionRequestDetailsNotFoundException(String message) {
        super(message);
    }
}
