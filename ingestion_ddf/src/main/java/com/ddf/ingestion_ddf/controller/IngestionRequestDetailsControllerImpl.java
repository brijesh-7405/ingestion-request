package com.ddf.ingestion_ddf.controller;

import com.ddf.ingestion_ddf.api.IngestionRequestDetailsController;
import com.ddf.ingestion_ddf.enums.IngestionRequestStatus;
import com.ddf.ingestion_ddf.enums.OrderByField;
import com.ddf.ingestion_ddf.enums.OrderDirection;
import com.ddf.ingestion_ddf.exception.CustomResponseStatusException;
import com.ddf.ingestion_ddf.exception.IngestionRequestDetailsNotFoundException;
import com.ddf.ingestion_ddf.request.IngestionRequest;
import com.ddf.ingestion_ddf.response.IngestionRequestDetailsDTO;
import com.ddf.ingestion_ddf.response.IngestionRequestSummaryDTO;
import com.ddf.ingestion_ddf.service.IngestionRequestDetailsService;
import com.ddf.ingestion_ddf.validators.ApiTemplateValidators;
import com.ddf.ingestion_ddf.validators.ValidationGroups;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implementation of the IngestionRequestDetailsController interface.
 * Handles HTTP requests related to ingestion request details.
 */
@RestController
@Slf4j
public class IngestionRequestDetailsControllerImpl implements IngestionRequestDetailsController {

    /**
     * The service responsible for handling ingestion request details.
     */
    private final IngestionRequestDetailsService ingestionRequestDetailsService;

    /**
     * Constructs a new instance of IngestionRequestDetailsControllerImpl.
     *
     * @param ingestionRequestDetailsService The service responsible for handling ingestion request details.
     */
    public IngestionRequestDetailsControllerImpl(IngestionRequestDetailsService ingestionRequestDetailsService) {
        this.ingestionRequestDetailsService = ingestionRequestDetailsService;
    }

    /**
     * Creates a new ingestion request.
     *
     * @param submit        Indicates whether to submit the request.
     * @param requestDto    The ingestion request details.
     * @param bindingResult Binding result for validation.
     * @return ResponseEntity containing the created ingestion request details.
     * @throws IngestionRequestDetailsNotFoundException If the ingestion request details are not found.
     * @throws CustomResponseStatusException            If there is a custom response status exception.
     */
    @Override
    public ResponseEntity<IngestionRequestDetailsDTO> createIngestionRequest(Boolean submit, IngestionRequest requestDto, BindingResult bindingResult) throws IngestionRequestDetailsNotFoundException, CustomResponseStatusException {
        determineValidationGroup(submit);
        if (bindingResult.hasErrors()) {
            log.error("Error creating ingestion request: {}", ApiTemplateValidators.createErrorMessage(bindingResult));
            throw new CustomResponseStatusException(ApiTemplateValidators.createErrorMessage(bindingResult));
        }

        IngestionRequestDetailsDTO response = ingestionRequestDetailsService.createOrUpdateIngestionRequest(null, requestDto, submit);
        log.info("Ingestion request created successfully");
        return ResponseEntity.ok(response);
    }

    /**
     * Determines the validation group based on the submission flag.
     *
     * @param submit Indicates whether to submit the ingestion request.
     * @return The validation group class to use for validation.
     *         Returns {@link ValidationGroups.SubmitValidationGroup}
     *         if submit is true or {@link ValidationGroups.BasicFieldValidationGroup}
     *         otherwise.
     */
    private Class<?> determineValidationGroup(Boolean submit) {
        if (submit != null && submit) {
            return ValidationGroups.SubmitValidationGroup.class;
        } else {
            return ValidationGroups.BasicFieldValidationGroup.class;
        }
    }

    /**
     * Retrieves an ingestion request by its ID.
     *
     * @param ingestionRequestId The ID of the ingestion request.
     * @return ResponseEntity containing the ingestion request details.
     * @throws IngestionRequestDetailsNotFoundException If the ingestion request details are not found.
     */
    @Override
    public ResponseEntity<IngestionRequestDetailsDTO> getIngestionRequest(Long ingestionRequestId) throws IngestionRequestDetailsNotFoundException {
        log.info("Retrieving ingestion request with ID: {}", ingestionRequestId);
        IngestionRequestDetailsDTO ingestionRequestDetail = ingestionRequestDetailsService.getIngestionRequestDetail(ingestionRequestId);
        return ingestionRequestDetail == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(ingestionRequestDetail);
    }

    /**
     * Updates an existing ingestion request.
     *
     * @param ingestionRequestId The ID of the ingestion request to update.
     * @param submit             Indicates whether to submit the request.
     * @param requestDto         The updated ingestion request details.
     * @param bindingResult      Binding result for validation.
     * @return ResponseEntity containing the updated ingestion request details.
     * @throws IngestionRequestDetailsNotFoundException If the ingestion request details are not found.
     * @throws CustomResponseStatusException            If there is a custom response status exception.
     */
    @Override
    public ResponseEntity<IngestionRequestDetailsDTO> updateIngestionRequest(
            Long ingestionRequestId,
            Boolean submit,
            IngestionRequest requestDto,
            BindingResult bindingResult) throws IngestionRequestDetailsNotFoundException, CustomResponseStatusException {
        if (bindingResult.hasErrors()) {
            log.error("Error updating ingestion request with ID {}: {}", ingestionRequestId, ApiTemplateValidators.createErrorMessage(bindingResult));
            throw new CustomResponseStatusException(ApiTemplateValidators.createErrorMessage(bindingResult));
        }

        IngestionRequestDetailsDTO response = ingestionRequestDetailsService.createOrUpdateIngestionRequest(ingestionRequestId, requestDto, submit);
        log.info("Ingestion request updated successfully");
        return ResponseEntity.ok(response);
    }

    /**
     * Searches for ingestion requests based on various parameters.
     *
     * @param myApprovals    Indicates whether to include approvals by the user.
     * @param mySubmissions  Indicates whether to include submissions by the user.
     * @param status         The status of the ingestion requests to search for.
     * @param page           The page number for pagination.
     * @param perPage        The number of items per page for pagination.
     * @param orderBy        The field to order by.
     * @param orderDirection The direction to order by (ASC or DESC).
     * @return ResponseEntity containing the search results for ingestion requests.
     */
    @Override
    public ResponseEntity<IngestionRequestSummaryDTO> searchIngestionRequests(
            Boolean myApprovals,
            Boolean mySubmissions,
            IngestionRequestStatus status,
            Integer page,
            Integer perPage,
            OrderByField orderBy,
            OrderDirection orderDirection
    ) {
        log.info("Searching for ingestion requests with parameters: myApprovals={}, mySubmissions={}, status={}, page={}, perPage={}, orderBy={}, orderDirection={}", myApprovals, mySubmissions, status, page, perPage, orderBy, orderDirection);
        return ResponseEntity.ok(ingestionRequestDetailsService.searchIngestionRequests(
                myApprovals, mySubmissions, status, page, perPage, orderBy, orderDirection
        ));
    }

}
