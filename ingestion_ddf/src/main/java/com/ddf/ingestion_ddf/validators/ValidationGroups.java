package com.ddf.ingestion_ddf.validators;

/**
 * Validation groups for IngestionRequest DTO.
 */
public interface ValidationGroups {

    /**
     * Validation group for basic field validation.
     */
    interface BasicFieldValidationGroup {}

    /**
     * Validation group for prioritization detail validation.
     */
    interface PrioritizationDetailValidationGroup {}

    /**
     * Validation group for dataset details validation.
     */
    interface DatasetDetailsValidationGroup {}

    /**
     * Validation group for submit validation.
     */
    interface SubmitValidationGroup {}
}
