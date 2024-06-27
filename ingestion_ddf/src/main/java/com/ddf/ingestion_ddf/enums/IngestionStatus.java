package com.ddf.ingestion_ddf.enums;


/**
 * Enum representing different statuses of ingestion processes.
 */
public enum IngestionStatus {
    /**
     * Represents the draft status of an ingestion request process.
     */
    DRAFT("Draft"),

    /**
     * Represents a status where the ingestion request process is pending approval during triage.
     */
    TRIAGE_PENDING_APPROVAL("Triage Pending Approval"),

    /**
     * Represents a status where the ingestion request process has been approved.
     */
    APPROVED("Approved"),

    /**
     * Represents a status where the ingestion request process has been rejected.
     */
    REJECTED("Rejected"),

    /**
     * Represents a status where the ingestion request process is currently in progress.
     */
    INGESTION_IN_PROGRESS("Ingestion In-Progress"),

    /**
     * Represents a status where the ingestion request process has been completed successfully.
     */
    INGESTION_COMPLETED("Ingestion Completed"),

    /**
     * Represents a status where the ingestion request process has failed.
     */
    INGESTION_FAILURE("Ingestion Failure");

    /**
     * The display name of the status.
     */
    private final String displayName;

    /**
     * Constructs an {@code IngestionStatus} enum with the specified display name.
     *
     * @param displayName the display name of the status
     */
    IngestionStatus(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Returns the display name of the status.
     *
     * @return the display name of the status
     */
    @Override
    public String toString() {
        return displayName;
    }
}
