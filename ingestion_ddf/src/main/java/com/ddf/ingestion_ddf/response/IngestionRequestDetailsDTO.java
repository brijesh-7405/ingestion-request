package com.ddf.ingestion_ddf.response;

import com.ddf.ingestion_ddf.entity.ValidationNotes;
import com.ddf.ingestion_ddf.request.IngestionRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Data transfer object (DTO) for ingestion request details.
 */
@Data
public class IngestionRequestDetailsDTO extends IngestionRequest {
    /** Ingestion request ID. */
    private Long ingestionRequestId;

    /** Active request status. */
    private RequestStatusDetailsDTO activeRequestStatus;

    /** Flag indicating if existing data location is identified. */
    private String existingDataLocationIdentified;

    /** List of validation notes. */
    private List<ValidationNotes> notes;

    /** User who created the request. */
    private String createdBy;

    /** Date of creation. */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdDate;

    /** User who modified the request. */
    private String modifiedBy;

    /** Date of modification. */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modifiedDate;
}