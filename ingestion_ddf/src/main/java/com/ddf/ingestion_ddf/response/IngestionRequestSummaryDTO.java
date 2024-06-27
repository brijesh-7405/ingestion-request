package com.ddf.ingestion_ddf.response;

import lombok.Data;

import java.util.List;


/**
 * Data transfer object (DTO) for ingestion request summary.
 */
@Data
public class IngestionRequestSummaryDTO {
    /** Total number of all ingestion requests. */
    private int totalAll;

    /** Total number of ingestion requests pending approval. */
    private int totalPendingApproval;

    /** Total number of completed ingestion requests. */
    private int totalCompletedRequest;

    /** Total number of rejected ingestion requests. */
    private int totalRejected;

    /** List of ingestion request details. */
    private List<IngestionRequestDetailsDTO> items;

}
