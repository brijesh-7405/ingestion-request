package com.ddf.ingestion_ddf.controller;

import com.ddf.ingestion_ddf.api.IngestionRequestStatusController;
import com.ddf.ingestion_ddf.enums.IngestionStatus;
import com.ddf.ingestion_ddf.exception.CustomResponseStatusException;
import com.ddf.ingestion_ddf.exception.IngestionRequestDetailsNotFoundException;
import com.ddf.ingestion_ddf.request.DecisionRequestDTO;
import com.ddf.ingestion_ddf.response.IngestionRequestDetailsDTO;
import com.ddf.ingestion_ddf.service.IngestionRequestDetailsService;
import com.ddf.ingestion_ddf.validators.ApiTemplateValidators;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implementation of the IngestionRequestStatusController interface.
 * Handles HTTP requests related to updating the status of ingestion requests.
 */
@RestController
@Slf4j
public class IngestionRequestStatusControllerImpl implements IngestionRequestStatusController {

    /**
     * The service responsible for handling ingestion request details.
     */
    private final IngestionRequestDetailsService ingestionRequestDetailsService;

    /**
     * Constructs a new instance of IngestionRequestStatusControllerImpl.
     *
     * @param ingestionRequestDetailsService The service responsible for handling ingestion request details.
     */
    public IngestionRequestStatusControllerImpl(IngestionRequestDetailsService ingestionRequestDetailsService) {
        this.ingestionRequestDetailsService = ingestionRequestDetailsService;
    }

    /**
     * Submits an ingestion request.
     *
     * @param ingestionRequestId The ID of the ingestion request to submit.
     * @return ResponseEntity containing the updated ingestion request details.
     * @throws IngestionRequestDetailsNotFoundException If the ingestion request details are not found.
     * @throws CustomResponseStatusException            If there is a custom response status exception.
     */
    @Override
    public ResponseEntity<IngestionRequestDetailsDTO> submitIngestionRequest(Long ingestionRequestId) throws IngestionRequestDetailsNotFoundException, CustomResponseStatusException {
        log.info("Submitting ingestion request with ID: {}", ingestionRequestId);
        return ResponseEntity.ok(ingestionRequestDetailsService.updateIngestionRequestStatus(ingestionRequestId, IngestionStatus.TRIAGE_PENDING_APPROVAL, null));
    }

    /**
     * Approves an ingestion request.
     *
     * @param ingestionRequestId The ID of the ingestion request to approve.
     * @param decisionRequestDTO The decision request details.
     * @param bindingResult      Binding result for validation.
     * @return ResponseEntity containing the approved ingestion request details.
     * @throws IngestionRequestDetailsNotFoundException If the ingestion request details are not found.
     * @throws CustomResponseStatusException            If there is a custom response status exception.
     */
    @Override
    public ResponseEntity<IngestionRequestDetailsDTO> approveIngestionRequest(
            Long ingestionRequestId,
            DecisionRequestDTO decisionRequestDTO,
            BindingResult bindingResult) throws IngestionRequestDetailsNotFoundException, CustomResponseStatusException {
        if (bindingResult.hasErrors()) {
            log.error("Error occurred while approving ingestion request with ID: {}", ingestionRequestId);
            throw new CustomResponseStatusException(ApiTemplateValidators.createErrorMessage(bindingResult));
        }
        log.info("Approving ingestion request with ID: {}", ingestionRequestId);
        return ResponseEntity.ok(ingestionRequestDetailsService.updateIngestionRequestStatus(ingestionRequestId, IngestionStatus.APPROVED, decisionRequestDTO));
    }

    /**
     * Rejects an ingestion request.
     *
     * @param ingestionRequestId The ID of the ingestion request to reject.
     * @param decisionRequestDTO The decision request details.
     * @param bindingResult      Binding result for validation.
     * @return ResponseEntity containing the rejected ingestion request details.
     * @throws IngestionRequestDetailsNotFoundException If the ingestion request details are not found.
     * @throws CustomResponseStatusException            If there is a custom response status exception.
     */
    @Override
    public ResponseEntity<IngestionRequestDetailsDTO> rejectIngestionRequest(
            Long ingestionRequestId,
            DecisionRequestDTO decisionRequestDTO,
            BindingResult bindingResult) throws IngestionRequestDetailsNotFoundException, CustomResponseStatusException {
        if (bindingResult.hasErrors()) {
            log.error("Error occurred while rejecting ingestion request with ID: {}", ingestionRequestId);
            throw new CustomResponseStatusException(ApiTemplateValidators.createErrorMessage(bindingResult));
        }
        log.info("Rejecting ingestion request with ID: {}", ingestionRequestId);
        return ResponseEntity.ok(ingestionRequestDetailsService.updateIngestionRequestStatus(ingestionRequestId, IngestionStatus.REJECTED, decisionRequestDTO));
    }

    /**
     * Marks an ingestion request as ingestion in-progress.
     *
     * @param ingestionRequestId The ID of the ingestion request to mark as in-progress.
     * @param decisionRequestDTO The decision request details.
     * @param bindingResult      Binding result for validation.
     * @return ResponseEntity containing the updated ingestion request details.
     * @throws IngestionRequestDetailsNotFoundException If the ingestion request details are not found.
     * @throws CustomResponseStatusException            If there is a custom response status exception.
     */
    @Override
    public ResponseEntity<IngestionRequestDetailsDTO> markIngestionInProgress(
            Long ingestionRequestId,
            DecisionRequestDTO decisionRequestDTO,
            BindingResult bindingResult) throws IngestionRequestDetailsNotFoundException, CustomResponseStatusException {
        if (bindingResult.hasErrors()) {
            log.error("Error occurred while marking ingestion in progress for request with ID: {}", ingestionRequestId);
            throw new CustomResponseStatusException(ApiTemplateValidators.createErrorMessage(bindingResult));
        }
        log.info("Marking ingestion in progress for request with ID: {}", ingestionRequestId);
        return ResponseEntity.ok(ingestionRequestDetailsService.updateIngestionRequestStatus(ingestionRequestId, IngestionStatus.INGESTION_IN_PROGRESS, decisionRequestDTO));
    }

    /**
     * Marks an ingestion request as ingestion complete.
     *
     * @param ingestionRequestId The ID of the ingestion request to mark as complete.
     * @param decisionRequestDTO The decision request details.
     * @param bindingResult      Binding result for validation.
     * @return ResponseEntity containing the updated ingestion request details.
     * @throws IngestionRequestDetailsNotFoundException If the ingestion request details are not found.
     * @throws CustomResponseStatusException            If there is a custom response status exception.
     */
    @Override
    public ResponseEntity<IngestionRequestDetailsDTO> markIngestionComplete(
            Long ingestionRequestId,
            DecisionRequestDTO decisionRequestDTO,
            BindingResult bindingResult) throws IngestionRequestDetailsNotFoundException, CustomResponseStatusException {
        if (bindingResult.hasErrors()) {
            log.error("Error occurred while marking ingestion complete for request with ID: {}", ingestionRequestId);
            throw new CustomResponseStatusException(ApiTemplateValidators.createErrorMessage(bindingResult));
        }
        log.info("Marking ingestion complete for request with ID: {}", ingestionRequestId);
        return ResponseEntity.ok(ingestionRequestDetailsService.updateIngestionRequestStatus(ingestionRequestId, IngestionStatus.INGESTION_COMPLETED, decisionRequestDTO));
    }

    /**
     * Marks an ingestion request as a ingestion failure.
     *
     * @param ingestionRequestId The ID of the ingestion request to mark as a failure.
     * @param decisionRequestDTO The decision request details.
     * @param bindingResult      Binding result for validation.
     * @return ResponseEntity containing the updated ingestion request details.
     * @throws IngestionRequestDetailsNotFoundException If the ingestion request details are not found.
     * @throws CustomResponseStatusException            If there is a custom response status exception.
     */
    @Override
    public ResponseEntity<IngestionRequestDetailsDTO> markIngestionFailure(
            Long ingestionRequestId,
            DecisionRequestDTO decisionRequestDTO,
            BindingResult bindingResult) throws IngestionRequestDetailsNotFoundException, CustomResponseStatusException {
        if (bindingResult.hasErrors()) {
            log.error("Error occurred while marking ingestion failure for request with ID: {}", ingestionRequestId);
            throw new CustomResponseStatusException(ApiTemplateValidators.createErrorMessage(bindingResult));
        }
        log.info("Marking ingestion failure for request with ID: {}", ingestionRequestId);
        return ResponseEntity.ok(ingestionRequestDetailsService.updateIngestionRequestStatus(ingestionRequestId, IngestionStatus.INGESTION_FAILURE, decisionRequestDTO));
    }
}
