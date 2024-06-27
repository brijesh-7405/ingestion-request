package com.ddf.ingestion_ddf.enums;

/**
 * Enum representing different statuses of ingestion requests.
 */
public enum IngestionRequestStatus {
    /**
     * Represents all types of ingestion requests.
     */
    All,
    /**
     * Represents ingestion requests pending approval.
     */
    PendingApproval,
    /**
     * Represents completed ingestion requests.
     */
    CompletedRequest,
    /**
     * Represents rejected ingestion requests.
     */
    Rejected
}
