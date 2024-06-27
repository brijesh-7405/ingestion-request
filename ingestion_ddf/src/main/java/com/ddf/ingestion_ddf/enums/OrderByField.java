package com.ddf.ingestion_ddf.enums;

/**
 * Enum representing fields by which ingestion requests can be ordered.
 */
public enum OrderByField {
    /**
     * Represents the field for ordering by ingestion request ID.
     */
    ingestionRequestId("ingestionRequestId"),

    /**
     * Represents the field for ordering by dataset required for reference.
     */
    datasetRequiredForRef("datasetDetails.datasetRequiredForRef"),

    /**
     * Represents the field for ordering by the name of the requester.
     */
    requestedByName("requestedByName"),

    /**
     * Represents the field for ordering by analysis initialization date.
     */
    analysisInitDt("datasetDetails.analysisInitDt"),

    /**
     * Represents the field for ordering by analysis end date.
     */
    analysisEndDt("datasetDetails.analysisEndDt"),

    /**
     * Represents the field for ordering by modified date.
     */
    modifiedDate("modifiedDate"),

    /**
     * Represents the field for ordering by dataset SME name.
     */
    datasetSmeName("datasetDetails.datasetRoleDetails.role"),

    /**
     * Represents the field for ordering by the email of the requester.
     */
    requesterByEmail("requesterEmail"),

    /**
     * Represents the field for ordering by the active request status.
     */
    activeRequestStatus("requestStatusDetails.status.statusName");

    /**
     * The name of the field.
     */
    private final String fieldName;

    /**
     * Constructs an {@code OrderByField} enum with the specified field name.
     *
     * @param fieldName the name of the field
     */
    OrderByField(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * Returns the name of the field.
     *
     * @return the name of the field
     */
    public String getFieldName() {
        return fieldName;
    }

}