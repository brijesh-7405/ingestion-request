package com.ddf.ingestion_ddf.api;

import com.ddf.ingestion_ddf.exception.CustomResponseStatusException;
import com.ddf.ingestion_ddf.exception.IngestionRequestDetailsNotFoundException;
import com.ddf.ingestion_ddf.request.DecisionRequestDTO;
import com.ddf.ingestion_ddf.response.IngestionRequestDetailsDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller interface for managing ingestion request status.
 * Defines endpoints for submitting, approving, rejecting, and updating the status of ingestion requests.
 */
@RequestMapping("/ingestion_requests")
@Tag(name = "Ingestion Request Status")
public interface IngestionRequestStatusController {

    /**
     * Submits an ingestion request for processing.
     *
     * @param ingestionRequestId The ID of the ingestion request to submit.
     * @return ResponseEntity containing the details of the submitted ingestion request.
     * @throws IngestionRequestDetailsNotFoundException if the ingestion request details are not found.
     * @throws CustomResponseStatusException            if there is a custom response status exception.
     */
    @PutMapping("/{ingestion_request_id}/submit")
    @Operation(summary = "Submit Ingestion Request")
    ResponseEntity<IngestionRequestDetailsDTO> submitIngestionRequest(@PathVariable("ingestion_request_id") Long ingestionRequestId) throws IngestionRequestDetailsNotFoundException, CustomResponseStatusException;

    /**
     * Approves an ingestion request.
     *
     * @param ingestionRequestId The ID of the ingestion request to approve.
     * @param decisionRequestDTO The decision data for the approval.
     * @param bindingResult      The result of request data validation.
     * @return ResponseEntity containing the details of the approved ingestion request.
     * @throws IngestionRequestDetailsNotFoundException if the ingestion request details are not found.
     * @throws CustomResponseStatusException            if there is a custom response status exception.
     */
    @PutMapping("/{ingestion_request_id}/approve")
    @Operation(summary = "Approve Ingestion Request")
    ResponseEntity<IngestionRequestDetailsDTO> approveIngestionRequest(
            @PathVariable("ingestion_request_id") Long ingestionRequestId,
            @Valid @RequestBody DecisionRequestDTO decisionRequestDTO,
            BindingResult bindingResult) throws IngestionRequestDetailsNotFoundException, CustomResponseStatusException;

    /**
     * Rejects an ingestion request.
     *
     * @param ingestionRequestId The ID of the ingestion request to reject.
     * @param decisionRequestDTO The decision data for the rejection.
     * @param bindingResult      The result of request data validation.
     * @return ResponseEntity containing the details of the rejected ingestion request.
     * @throws IngestionRequestDetailsNotFoundException if the ingestion request details are not found.
     * @throws CustomResponseStatusException            if there is a custom response status exception.
     */
    @PutMapping("/{ingestion_request_id}/reject")
    @Operation(summary = "Reject Ingestion Request")
    ResponseEntity<IngestionRequestDetailsDTO> rejectIngestionRequest(
            @PathVariable("ingestion_request_id") Long ingestionRequestId,
            @Valid @RequestBody DecisionRequestDTO decisionRequestDTO,
            BindingResult bindingResult) throws IngestionRequestDetailsNotFoundException, CustomResponseStatusException;

    /**
     * Update ingestion request status to Ingestion In Process.
     *
     * @param ingestionRequestId The ID of the ingestion request to reject.
     * @param decisionRequestDTO The decision data for the rejection.
     * @param bindingResult      The result of request data validation.
     * @return ResponseEntity containing the details of the rejected ingestion request.
     * @throws IngestionRequestDetailsNotFoundException if the ingestion request details are not found.
     * @throws CustomResponseStatusException            if there is a custom response status exception.
     */
    @PutMapping("/{ingestion_request_id}/ingestion_in_progress")
    @Operation(summary = "In-Progress Ingestion Request")
    ResponseEntity<IngestionRequestDetailsDTO> markIngestionInProgress(
            @PathVariable("ingestion_request_id") Long ingestionRequestId,
            @Valid @RequestBody DecisionRequestDTO decisionRequestDTO,
            BindingResult bindingResult) throws IngestionRequestDetailsNotFoundException, CustomResponseStatusException;

    /**
     * Update ingestion request status to Ingestion Completed.
     *
     * @param ingestionRequestId The ID of the ingestion request to reject.
     * @param decisionRequestDTO The decision data for the rejection.
     * @param bindingResult      The result of request data validation.
     * @return ResponseEntity containing the details of the rejected ingestion request.
     * @throws IngestionRequestDetailsNotFoundException if the ingestion request details are not found.
     * @throws CustomResponseStatusException            if there is a custom response status exception.
     */
    @PutMapping("/{ingestion_request_id}/ingestion_complete")
    @Operation(summary = "Complete Ingestion Request")
    ResponseEntity<IngestionRequestDetailsDTO> markIngestionComplete(
            @PathVariable("ingestion_request_id") Long ingestionRequestId,
            @Valid @RequestBody DecisionRequestDTO decisionRequestDTO,
            BindingResult bindingResult) throws IngestionRequestDetailsNotFoundException, CustomResponseStatusException;

    /**
     * Update ingestion request status to Ingestion Failure.
     *
     * @param ingestionRequestId The ID of the ingestion request to reject.
     * @param decisionRequestDTO The decision data for the rejection.
     * @param bindingResult      The result of request data validation.
     * @return ResponseEntity containing the details of the rejected ingestion request.
     * @throws IngestionRequestDetailsNotFoundException if the ingestion request details are not found.
     * @throws CustomResponseStatusException            if there is a custom response status exception.
     */
    @PutMapping("/{ingestion_request_id}/ingestion_failure")
    @Operation(summary = "Failure Ingestion Request")
    ResponseEntity<IngestionRequestDetailsDTO> markIngestionFailure(
            @PathVariable("ingestion_request_id") Long ingestionRequestId,
            @Valid @RequestBody DecisionRequestDTO decisionRequestDTO,
            BindingResult bindingResult) throws IngestionRequestDetailsNotFoundException, CustomResponseStatusException;

}
