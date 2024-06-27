package com.ddf.ingestion_ddf.response;

import com.ddf.ingestion_ddf.entity.Status;
import lombok.Data;

import java.util.Date;

/**
 * Data transfer object (DTO) for request status details.
 */
@Data
public class RequestStatusDetailsDTO {
    /** Unique identifier for the request status. */
    private Long requestStatusId;

    /** Unique identifier for the ingestion request. */
    private Long ingestionRequestId;

    /** Name of the decision maker. */
    private String decisionByName;

    /** Mud ID of the decision maker. */
    private String decisionByMudid;

    /** Email of the decision maker. */
    private String decisionByEmail;

    /** Date of the decision. */
    private Date decisionDate;

    /** Comments regarding the decision. */
    private String decisionComments;

    /** Reason for rejection, if applicable. */
    private String rejectionReason;

    /** Flag indicating whether the status is active. */
    private Boolean activeFlag;

    /** Status of the request. */
    private Status status;
}
