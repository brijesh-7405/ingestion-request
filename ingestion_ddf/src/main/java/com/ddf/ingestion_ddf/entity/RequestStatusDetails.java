package com.ddf.ingestion_ddf.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import java.util.Date;

/**
 * Entity class representing request status details.
 */
@Data
@Entity
@Table(name = "request_status_details")
public class RequestStatusDetails extends BaseModel{

    /**
     * Unique identifier for the request status.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestStatusId;

    /**
     * The ingestion request associated with the status.
     */
    @ManyToOne
    @JoinColumn(name = "ingestion_request_id")
    @JsonIgnore
    private IngestionRequestDetails ingestionRequest;

    /**
     * The status of the request.
     */
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    /**
     * Name of the user who made the decision.
     */
    private String decisionByName;

    /**
     * MUDID of the user who made the decision.
     */
    private String decisionByMudid;

    /**
     * Email of the user who made the decision.
     */
    private String decisionByEmail;

    /**
     * Date of the decision.
     */
    private Date decisionDate;

    /**
     * Comments associated with the decision.
     */
    private String decisionComments;

    /**
     * Reason for rejection, if any.
     */
    private String rejectionReason;

    /**
     * Flag indicating if the status is active.
     */
    private Boolean activeFlag;

}

