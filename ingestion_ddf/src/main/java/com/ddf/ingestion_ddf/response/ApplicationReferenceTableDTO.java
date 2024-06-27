package com.ddf.ingestion_ddf.response;

import lombok.Data;


/**
 * Data transfer object (DTO) for application reference table.
 */
@Data
public class ApplicationReferenceTableDTO {
    /** Reference data. */
    private String referenceData;

    /** Type of reference data. */
    private String referenceDataType;

    /** Order of reference data. */
    private Long referenceOrder;
}
