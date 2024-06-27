package com.ddf.ingestion_ddf.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

/**
 * Data transfer object (DTO) for validation notes.
 */
@Data
public class ValidationNotesDTO {
    /** Unique identifier for the notes. */
    private Long notesId;

    /** Notes content. */
    @NotBlank(message = "Validation Note is required")
    private String notes;

    /** User who created the notes. */
    private String createdBy;

    /** Date when the notes were created. */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdDate;

    /** User who last modified the notes. */
    private String modifiedBy;

    /** Date when the notes were last modified. */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modifiedDate;
}
