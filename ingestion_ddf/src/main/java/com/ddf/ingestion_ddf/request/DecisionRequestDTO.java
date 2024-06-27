package com.ddf.ingestion_ddf.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Data transfer object (DTO) for decision requests.
 */
@Data
public class DecisionRequestDTO {
    /**
     * Comments associated with the decision.
     */
    @NotBlank(message = "Comments are required")
    @Size(max = 255, message = "Decision Comments must be at most 255 characters long")
    private String decisionComments;
    /**
     * Flag indicating whether to send email.
     */
    @NotNull(message = "Notify Email flag is required")
    private Boolean notifyThroughEmail;
    /**
     * Reason for rejection, if applicable.
     */
    @Size(max = 255, message = "Rejection Reason must be at most 255 characters long")
    private String rejectionReason;
    /**
     * Identifier for existing data location, if identified.
     */
    @Size(max = 255, message = "Existing Data Location Identifier must be at most 255 characters long")
    private String existingDataLocationIdentified;
}
