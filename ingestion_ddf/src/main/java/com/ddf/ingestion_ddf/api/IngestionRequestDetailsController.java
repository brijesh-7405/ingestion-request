package com.ddf.ingestion_ddf.api;

import com.ddf.ingestion_ddf.enums.IngestionRequestStatus;
import com.ddf.ingestion_ddf.enums.OrderByField;
import com.ddf.ingestion_ddf.enums.OrderDirection;
import com.ddf.ingestion_ddf.exception.CustomResponseStatusException;
import com.ddf.ingestion_ddf.exception.IngestionRequestDetailsNotFoundException;
import com.ddf.ingestion_ddf.request.IngestionRequest;
import com.ddf.ingestion_ddf.response.IngestionRequestDetailsDTO;
import com.ddf.ingestion_ddf.response.IngestionRequestSummaryDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Controller interface for managing ingestion request details.
 * Defines endpoints for creating, retrieving, updating, and searching ingestion requests.
 */
@RequestMapping("/ingestion_requests")
@Tag(name = "Ingestion Request")
public interface IngestionRequestDetailsController {

    /**
     * Creates a new ingestion request.
     *
     * @param submit        Indicates whether to submit the request immediately.
     * @param requestDto    The ingestion request data.
     * @param bindingResult The result of request data validation.
     * @return ResponseEntity containing the created ingestion request details.
     * @throws IngestionRequestDetailsNotFoundException if the ingestion request details are not found.
     * @throws CustomResponseStatusException            if there is a custom response status exception.
     */
    @PostMapping
    @Operation(summary = "Create a new Ingestion Request")
    ResponseEntity<IngestionRequestDetailsDTO> createIngestionRequest(
            @RequestParam(name = "submit", defaultValue = "false") Boolean submit,
            @Valid @RequestBody IngestionRequest requestDto,
            BindingResult bindingResult) throws IngestionRequestDetailsNotFoundException, CustomResponseStatusException;

    /**
     * Retrieves details of a specific ingestion request.
     *
     * @param ingestionRequestId The ID of the ingestion request to retrieve.
     * @return ResponseEntity containing the ingestion request details.
     * @throws IngestionRequestDetailsNotFoundException if the ingestion request details are not found.
     */
    @GetMapping("/{ingestion_request_id}")
    @Operation(summary = "Get Ingestion Request")
    ResponseEntity<IngestionRequestDetailsDTO> getIngestionRequest(@PathVariable("ingestion_request_id") Long ingestionRequestId) throws IngestionRequestDetailsNotFoundException;

    /**
     * Updates an existing ingestion request.
     *
     * @param ingestionRequestId The ID of the ingestion request to update.
     * @param submit             Indicates whether to submit the updated request immediately.
     * @param requestDto         The updated ingestion request data.
     * @param bindingResult      The result of request data validation.
     * @return ResponseEntity containing the updated ingestion request details.
     * @throws IngestionRequestDetailsNotFoundException if the ingestion request details are not found.
     * @throws CustomResponseStatusException            if there is a custom response status exception.
     */
    @PutMapping("/{ingestion_request_id}")
    @Operation(summary = "Update Ingestion Request")
    ResponseEntity<IngestionRequestDetailsDTO> updateIngestionRequest(
            @PathVariable("ingestion_request_id") Long ingestionRequestId,
            @RequestParam(name = "submit", defaultValue = "false") Boolean submit,
            @Valid @RequestBody IngestionRequest requestDto,
            BindingResult bindingResult) throws IngestionRequestDetailsNotFoundException, CustomResponseStatusException;

    /**
     * Searches for ingestion requests based on various criteria.
     *
     * @param myApprovals    Indicates whether to include requests pending user's approval.
     * @param mySubmissions  Indicates whether to include requests submitted by the user.
     * @param status         The status of the ingestion requests to search for.
     * @param page           The page number of the search results.
     * @param perPage        The number of results per page.
     * @param orderBy        The field to order the results by.
     * @param orderDirection The direction of ordering (ASC or DESC).
     * @return ResponseEntity containing the summary of ingestion requests matching the search criteria.
     */
    @GetMapping
    @Operation(summary = "Search Ingestion Requests")
    ResponseEntity<IngestionRequestSummaryDTO> searchIngestionRequests(
            @RequestParam(name = "my_approvals", defaultValue = "false") Boolean myApprovals,
            @RequestParam(name = "my_submissions", defaultValue = "true") Boolean mySubmissions,
            @RequestParam(name = "status", defaultValue = "All") IngestionRequestStatus status,
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "per_page", defaultValue = "20") Integer perPage,
            @RequestParam(name = "order_by", defaultValue = "modifiedDate") OrderByField orderBy,
            @RequestParam(name = "order_direction", defaultValue = "desc") OrderDirection orderDirection
    );
}
