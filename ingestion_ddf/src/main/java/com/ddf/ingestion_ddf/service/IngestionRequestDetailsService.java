package com.ddf.ingestion_ddf.service;

import com.ddf.ingestion_ddf.enums.IngestionRequestStatus;
import com.ddf.ingestion_ddf.enums.IngestionStatus;
import com.ddf.ingestion_ddf.enums.OrderByField;
import com.ddf.ingestion_ddf.enums.OrderDirection;
import com.ddf.ingestion_ddf.exception.CustomResponseStatusException;
import com.ddf.ingestion_ddf.exception.IngestionRequestDetailsNotFoundException;
import com.ddf.ingestion_ddf.request.DecisionRequestDTO;
import com.ddf.ingestion_ddf.request.IngestionRequest;
import com.ddf.ingestion_ddf.response.IngestionRequestDetailsDTO;
import com.ddf.ingestion_ddf.response.IngestionRequestSummaryDTO;

/**
 * Service interface for managing ingestion request details.
 */
public interface IngestionRequestDetailsService {
    /**
     * Creates or updates an ingestion request with the given details.
     *
     * @param ingestionRequestId The ID of the ingestion request.
     * @param ingestionRequest   The ingestion request details to create or update.
     * @param submit             A boolean indicating whether to submit the request.
     * @return The details of the created or updated ingestion request.
     * @throws IngestionRequestDetailsNotFoundException if the ingestion request details are not found.
     * @throws CustomResponseStatusException            if there is a custom response status exception.
     */
    IngestionRequestDetailsDTO createOrUpdateIngestionRequest(Long ingestionRequestId, IngestionRequest ingestionRequest, Boolean submit) throws IngestionRequestDetailsNotFoundException, CustomResponseStatusException;

    /**
     * Retrieves the details of an ingestion request with the given ID.
     *
     * @param ingestionRequestId The ID of the ingestion request to retrieve.
     * @return The details of the ingestion request.
     * @throws IngestionRequestDetailsNotFoundException if the ingestion request details are not found.
     */
    IngestionRequestDetailsDTO getIngestionRequestDetail(Long ingestionRequestId) throws IngestionRequestDetailsNotFoundException;

    /**
     * Searches for ingestion requests based on specified criteria.
     *
     * @param myApprovals    A boolean indicating whether to include requests for which the user is an approver.
     * @param mySubmissions  A boolean indicating whether to include requests submitted by the user.
     * @param status         The status of the ingestion requests to search for.
     * @param page           The page number of the search results.
     * @param perPage        The number of results per page.
     * @param orderBy        The field to order the results by.
     * @param orderDirection The direction of the ordering.
     * @return The summary of the searched ingestion requests.
     */
    IngestionRequestSummaryDTO searchIngestionRequests(Boolean myApprovals, Boolean mySubmissions, IngestionRequestStatus status, Integer page, Integer perPage, OrderByField orderBy, OrderDirection orderDirection);

    /**
     * Updates the status of an ingestion request with the given ID.
     *
     * @param ingestionRequestId The ID of the ingestion request to update.
     * @param newStatus          The new status to set for the ingestion request.
     * @param decisionRequestDTO The decision request DTO containing decision-related information.
     * @return The updated details of the ingestion request.
     * @throws IngestionRequestDetailsNotFoundException if the ingestion request details are not found.
     * @throws CustomResponseStatusException            if there is a custom response status exception.
     */
    IngestionRequestDetailsDTO updateIngestionRequestStatus(Long ingestionRequestId, IngestionStatus newStatus, DecisionRequestDTO decisionRequestDTO) throws IngestionRequestDetailsNotFoundException, CustomResponseStatusException;
}
