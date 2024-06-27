package com.ddf.ingestion_ddf.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Data transfer object (DTO) for validation notes requests.
 */
@Data
public class ValidationNotesRequestDTO {
    /**
     * Validation notes for Ingestion Request.
     */
    @NotEmpty(message = "Note is required")
    @Size(max = 255, message = "Notes must be at most 255 characters long")
    private String notes;
}
