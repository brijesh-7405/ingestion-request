package com.ddf.ingestion_ddf.entity;

import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;

import java.util.List;

/**
 * Entity class representing ingestion request details.
 */
@Data
@Entity
@Table(name = "ingestion_request_details")
public class IngestionRequestDetails extends BaseModel{

    /**
     * Unique identifier for the ingestion request.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ingestionRequestId;

    /**
     * Name of the requester.
     */
    private String requesterName;

    /**
     * MUDID of the requester.
     */
    private String requesterMudid;

    /**
     * Email of the requester.
     */
    private String requesterEmail;

    /**
     * Reason for the request.
     */
    private String requestRationaleReason;

    /**
     * Reason for modification.
     */
    private String modifiedReason;

    /**
     * Name of the requested by user.
     */
    private String requestedByName;

    /**
     * MUDID of the requested by user.
     */
    private String requestedByMudid;

    /**
     * Email of the requested by user.
     */
    private String requestedByEmail;

    /**
     * Details of the dataset associated with the ingestion request.
     */
    @OneToOne(mappedBy = "ingestionRequest", cascade = CascadeType.ALL, orphanRemoval = true)
    private DatasetDetails datasetDetails;

    /**
     * Technical details associated with the ingestion request.
     */
    @OneToOne(mappedBy = "ingestionRequest", cascade = CascadeType.ALL, orphanRemoval = true)
    private TechnicalDetails technicalDetails;

    /**
     * List of request status details associated with the ingestion request.
     */
    @OneToMany(mappedBy = "ingestionRequest", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RequestStatusDetails> requestStatusDetails;

}
