package com.ddf.ingestion_ddf.service.impl;

import com.ddf.ingestion_ddf.constants.ApiConstants;
import com.ddf.ingestion_ddf.entity.EmailTemplate;
import com.ddf.ingestion_ddf.entity.IngestionRequestDetails;
import com.ddf.ingestion_ddf.entity.RequestStatusDetails;
import com.ddf.ingestion_ddf.entity.Status;
import com.ddf.ingestion_ddf.entity.TechnicalDetails;

import com.ddf.ingestion_ddf.enums.IngestionRequestStatus;
import com.ddf.ingestion_ddf.enums.IngestionStatus;
import com.ddf.ingestion_ddf.enums.OrderByField;
import com.ddf.ingestion_ddf.enums.OrderDirection;
import com.ddf.ingestion_ddf.exception.CustomResponseStatusException;
import com.ddf.ingestion_ddf.exception.IngestionRequestDetailsNotFoundException;
import com.ddf.ingestion_ddf.exception.IngestionRequestStatusException;
import com.ddf.ingestion_ddf.repository.EmailTemplateRepository;
import com.ddf.ingestion_ddf.repository.IngestionRequestDetailsRepository;
import com.ddf.ingestion_ddf.repository.RequestStatusDetailsRepository;
import com.ddf.ingestion_ddf.repository.TechnicalDetailsRepository;
import com.ddf.ingestion_ddf.repository.StatusRepository;


import com.ddf.ingestion_ddf.request.DecisionRequestDTO;
import com.ddf.ingestion_ddf.request.IngestionRequest;
import com.ddf.ingestion_ddf.request.mappers.IngestionRequestMapper;
import com.ddf.ingestion_ddf.response.IngestionRequestDetailsDTO;
import com.ddf.ingestion_ddf.response.IngestionRequestSummaryDTO;
import com.ddf.ingestion_ddf.response.mappers.IngestionDetailsResponseMapper;
import com.ddf.ingestion_ddf.service.EmailTemplateService;
import com.ddf.ingestion_ddf.service.IngestionRequestDetailsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Date;
import java.util.Arrays;

/**
 * Implementation of the {@link IngestionRequestDetailsService} interface.
 * This service provides methods to handle ingestion request details.
 * It interacts with various repositories to access and manipulate data.
 *
 */
@Service
@AllArgsConstructor
@Slf4j
public class IngestionRequestDetailsServiceImpl implements IngestionRequestDetailsService {
    /**
     * Mapper for converting {@link IngestionRequestDetails} to {@link IngestionRequestDetailsDTO}.
     */
    private final IngestionDetailsResponseMapper ingestionDetailsResponseMapper;

    /**
     * Repository for managing {@link Status} entities.
     */
    private final StatusRepository statusRepository;

    /**
     * Repository for managing {@link IngestionRequestDetails} entities.
     */
    private final IngestionRequestDetailsRepository ingestionRequestDetailsRepository;

    /**
     * Repository for managing {@link RequestStatusDetails} entities.
     */
    private final RequestStatusDetailsRepository requestStatusRepository;

    /**
     * Repository for managing {@link TechnicalDetails} entities.
     */
    private final TechnicalDetailsRepository technicalDetailsRepository;

    /**
     * Service for managing email templates and sending emails.
     */
    private final EmailTemplateService emailTemplateService;

    /**
     * Mapper for converting {@link IngestionRequest} to {@link IngestionRequestDetails}.
     */
    private final IngestionRequestMapper ingestionRequestMapper;

    /**
     * Repository for managing {@link EmailTemplate} entities.
     */
    private final EmailTemplateRepository emailTemplateRepository;

    /**
     * Creates an ingestion request with the provided details.
     *
     * @param ingestionRequestId the ID of the ingestion request (optional) only required when ingestionRequest is updating
     * @param ingestionRequest   the ingestion request details to addOrUpdate the IngestionRequestDetails and there related Data
     * @param submit             a flag false to only create the request in DRAFT status, true to create the request in TRIAGE PENDING APPROVAL status
     * @return the DTO representation of the created ingestion request details
     */
    @Override
    public IngestionRequestDetailsDTO createOrUpdateIngestionRequest(Long ingestionRequestId, IngestionRequest ingestionRequest, Boolean submit) throws IngestionRequestDetailsNotFoundException, CustomResponseStatusException {

        // As logged-in user details are not available, using static emails for createdBy and modifiedBy
        String createdBy = ApiConstants.CREATED_BY;  // Update with logged-in user email
        String modifiedBy = ApiConstants.MODIFIED_BY;  // Update with logged-in user email
        IngestionRequestDetails ingestionRequestDetails;

        // Check if ingestionRequestId is provided and exists in the repository if exists then operation is Update Operation
        if (ingestionRequestId != null && ingestionRequestId != 0) {
            log.info("Updating existing ingestion request details with ID: {}", ingestionRequestId);
            IngestionRequestDetails details = ingestionRequestDetailsRepository.findById(ingestionRequestId)
                    .orElseThrow(() -> new IngestionRequestDetailsNotFoundException(ingestionRequestId.toString()));
            ingestionRequestDetails = ingestionRequestMapper.mapToIngestionRequestDetails(ingestionRequest, createdBy, modifiedBy, details);
            if (submit) {
                log.info("Submitting ingestion request for approval with ID: {}", ingestionRequestId);
                List<RequestStatusDetails> requestStatusDetailsList = updatedRequestStatusDetailsList(details, IngestionStatus.TRIAGE_PENDING_APPROVAL, createdBy, modifiedBy, null);
                ingestionRequestDetails.setRequestStatusDetails(requestStatusDetailsList);
            } else {
                ingestionRequestDetails.setRequestStatusDetails(details.getRequestStatusDetails());
            }
        } else {
            log.info("Creating new ingestion request");
            ingestionRequestDetails = ingestionRequestMapper.mapToIngestionRequestDetails(ingestionRequest, createdBy, modifiedBy, null);
            List<RequestStatusDetails> requestStatusDetailsList = new ArrayList<>();
            RequestStatusDetails requestStatusDetails = new RequestStatusDetails();
            Status status;
            if (submit) {
                log.info("Submitting new ingestion request for approval");
                status = statusRepository.findByStatusNameIgnoreCase(IngestionStatus.TRIAGE_PENDING_APPROVAL.toString());
            } else {
                log.info("Saving new ingestion request as draft");
                status = statusRepository.findByStatusNameIgnoreCase(IngestionStatus.DRAFT.toString());
            }
            requestStatusDetails.setStatus(status);
            requestStatusDetails.setIngestionRequest(ingestionRequestDetails);
            requestStatusDetails.setActiveFlag(true);
            requestStatusDetails.setCreatedBy(createdBy);
            requestStatusDetails.setModifiedBy(modifiedBy);
            requestStatusDetailsList.add(requestStatusDetails);
            ingestionRequestDetails.setRequestStatusDetails(requestStatusDetailsList);
        }

        // As logged-in user details are not available, using static values requestedByName, requestedByMudid, requestedByEmail
        String requestedByName = ApiConstants.REQUESTED_BY_NAME; // Update with logged-in username
        String requestedByMudid = ApiConstants.REQUESTED_BY_MUDID; // Update with logged-in user mudid
        String requestedByEmail = ApiConstants.REQUESTED_BY_EMAIL; // Update with logged-in user email

        ingestionRequestDetails.setRequestedByName(requestedByName);
        ingestionRequestDetails.setRequestedByMudid(requestedByMudid);
        ingestionRequestDetails.setRequestedByEmail(requestedByEmail);

        log.info("Saving ingestion request details");
        return ingestionDetailsResponseMapper.mapToIngestionRequestDetailsDTO(ingestionRequestDetailsRepository.save(ingestionRequestDetails));
    }

    /**
     * Retrieves the details of an ingestion request by its ID.
     *
     * @param ingestionRequestId The ID of the ingestion request to retrieve.
     * @return The details of the ingestion request as a Data Transfer Object (DTO), or null if the request does not exist.
     */
    @Override
    public IngestionRequestDetailsDTO getIngestionRequestDetail(Long ingestionRequestId) throws IngestionRequestDetailsNotFoundException {
        if (ingestionRequestId == null || ingestionRequestId <= 0) {
            throw new IllegalArgumentException("Invalid ingestionRequestId provided");
        }
        // Retrieve the ingestion request details from the repository
        IngestionRequestDetails requestDetails = ingestionRequestDetailsRepository.findById(ingestionRequestId)
                .orElseThrow(() -> new IngestionRequestDetailsNotFoundException(ingestionRequestId.toString()));
        log.info("Retrieving ingestion request details with ID: {}", ingestionRequestId);
        return ingestionDetailsResponseMapper.mapToIngestionRequestDetailsDTO(requestDetails);
    }

    /**
     * Updates the status of an ingestion request and optionally adds decision details.
     *
     * @param ingestionRequestId The ID of the ingestion request to update.
     * @param newStatus          The new status to set for the ingestion request.
     * @param decisionRequestDTO The decision details including comments, rejection reason, etc based on the required conditions.
     * @return The updated details of the ingestion request as a Data Transfer Object (DTO), or null if the request does not exist.
     */
    @Override
    public IngestionRequestDetailsDTO updateIngestionRequestStatus(Long ingestionRequestId, IngestionStatus newStatus, DecisionRequestDTO decisionRequestDTO) throws IngestionRequestDetailsNotFoundException, CustomResponseStatusException {
        log.info("Updating status of ingestion request with ID: {}", ingestionRequestId);

        // Retrieve the ingestion request details from the repository
        IngestionRequestDetails requestDetails = ingestionRequestDetailsRepository.findById(ingestionRequestId)
                .orElseThrow(() -> new IngestionRequestDetailsNotFoundException(ingestionRequestId.toString()));

        // As logged-in user details are not available, using static emails for createdBy and modifiedBy
        String createdBy = ApiConstants.CREATED_BY; // Update with logged-in user email
        String modifyBy = ApiConstants.MODIFIED_BY;  // Update with logged-in user email
        List<RequestStatusDetails> requestStatusDetailsList = updatedRequestStatusDetailsList(requestDetails, newStatus, createdBy, modifyBy, decisionRequestDTO);
        if (requestStatusDetailsList != null) {
            requestStatusRepository.saveAll(requestStatusDetailsList);
            requestDetails.setRequestStatusDetails(requestStatusDetailsList);
        }
        log.info("Status update completed for ingestion request ID: {}", ingestionRequestId);
        return ingestionDetailsResponseMapper.mapToIngestionRequestDetailsDTO(requestDetails);
    }

    /**
     * Updates the request status details list for a given ingestion request based on the new status and decision details provided.
     *
     * @param requestDetails     The details of the ingestion request to be updated.
     * @param newStatus          The new status to be set for the ingestion request.
     * @param createdBy          The user who created the status update.
     * @param modifyBy           The user who modified the status update.
     * @param decisionRequestDTO The decision details associated with the status update.
     * @return The updated list of request status details.
     * @throws IngestionRequestStatusException if the new status is not valid based on the current status.
     */
    private List<RequestStatusDetails> updatedRequestStatusDetailsList(IngestionRequestDetails requestDetails, IngestionStatus newStatus, String createdBy, String modifyBy, DecisionRequestDTO decisionRequestDTO) {
        List<RequestStatusDetails> requestStatusDetailsList = requestDetails.getRequestStatusDetails();

        if (requestStatusDetailsList != null && !requestStatusDetailsList.isEmpty()) {
            boolean statusUpdated = false;

            // Map defining valid previous statuses for each new status
            Map<IngestionStatus, String> validPreviousStatusOfRequest = Map.of(
                    IngestionStatus.APPROVED, IngestionStatus.TRIAGE_PENDING_APPROVAL.toString(),
                    IngestionStatus.REJECTED, IngestionStatus.TRIAGE_PENDING_APPROVAL.toString(),
                    IngestionStatus.INGESTION_IN_PROGRESS, IngestionStatus.APPROVED.toString(),
                    IngestionStatus.INGESTION_COMPLETED, IngestionStatus.INGESTION_IN_PROGRESS.toString(),
                    IngestionStatus.INGESTION_FAILURE, IngestionStatus.INGESTION_IN_PROGRESS.toString(),
                    IngestionStatus.TRIAGE_PENDING_APPROVAL, IngestionStatus.DRAFT.toString()
            );

            log.info("Validating previous status for new status: {}", newStatus);
            // Check if the request is in a valid previous status for the new status
            boolean validPreviousStatus = requestStatusDetailsList.stream()
                    .anyMatch(requestStatus -> requestStatus.getActiveFlag() && requestStatus.getStatus().getStatusName().equalsIgnoreCase(validPreviousStatusOfRequest.getOrDefault(newStatus, null)));

            if (validPreviousStatusOfRequest.getOrDefault(newStatus, null) == null || !validPreviousStatus) {
                log.error("Invalid previous status for new status: {}", newStatus);
                throw new IngestionRequestStatusException(newStatus.toString());
            }

            // Loop through existing status details and deactivate the current active status
            for (RequestStatusDetails requestStatus : requestStatusDetailsList) {
                if (requestStatus.getActiveFlag() && requestStatus.getStatus().getStatusName().equalsIgnoreCase(validPreviousStatusOfRequest.getOrDefault(newStatus, null))) {
                    log.info("Deactivating current active status: {}", requestStatus.getStatus().getStatusName());
                    requestStatus.setActiveFlag(false);
                    requestStatus.setModifiedBy(modifyBy);
                    statusUpdated = true;
                }
            }

            if (statusUpdated) {
                log.info("Creating new status detail record for new status: {}", newStatus);
                // Create a new status detail record for the new status
                RequestStatusDetails newRequestStatusDetails = new RequestStatusDetails();
                newRequestStatusDetails.setIngestionRequest(requestDetails);
                newRequestStatusDetails.setActiveFlag(true);
                newRequestStatusDetails.setCreatedBy(createdBy);
                newRequestStatusDetails.setModifiedBy(modifyBy);
                newRequestStatusDetails.setStatus(statusRepository.findByStatusNameIgnoreCase(newStatus.toString()));
                // If decision details are provided, set them in the new status detail
                if (decisionRequestDTO != null) {
                    if (decisionRequestDTO.getDecisionComments() != null) {
                        log.info("Adding decision comments and details");
                        newRequestStatusDetails.setDecisionComments(decisionRequestDTO.getDecisionComments());
                        newRequestStatusDetails.setRejectionReason(decisionRequestDTO.getRejectionReason());
                        newRequestStatusDetails.setDecisionDate(new Date());
                        newRequestStatusDetails.setDecisionByName(requestDetails.getRequestedByName());
                        newRequestStatusDetails.setDecisionByMudid(requestDetails.getRequestedByMudid());
                        newRequestStatusDetails.setDecisionByEmail(requestDetails.getRequestedByEmail());
                    }
                    if (decisionRequestDTO.getNotifyThroughEmail()) {
                        log.info("Sending notification email");
                        // Create an asynchronous task to send the email
                        try {
                            EmailTemplate emailTemplate = emailTemplateRepository.findByTemplateCodeIgnoreCase(ApiConstants.EMAIL_TEMPLATE_CODE).orElseThrow(
                                    () -> new IllegalArgumentException("Email template not found with template code: " + ApiConstants.EMAIL_TEMPLATE_CODE)
                            );
                            String emailBody = emailTemplate.getBody().replace("{oldStatus}", validPreviousStatusOfRequest.getOrDefault(newStatus, null))
                                    .replace("{newStatus}", newStatus.toString());
                            List<String> receiverEmails = Arrays.asList(requestDetails.getRequesterEmail(), requestDetails.getRequestedByEmail());
                            emailTemplateService.sendMail(emailTemplate.getSubject(), emailBody, receiverEmails);
                        } catch (IllegalArgumentException e) {
                            log.error("Exception occurred fetching email template: ", e);
                        }
                    }
                    // Update technical details if provided
                    if (decisionRequestDTO.getExistingDataLocationIdentified() != null && !decisionRequestDTO.getExistingDataLocationIdentified().isEmpty()) {
                        log.info("Updating technical details");
                        TechnicalDetails technicalDetails = requestDetails.getTechnicalDetails();
                        technicalDetails.setExistingDataLocationIdentified(decisionRequestDTO.getExistingDataLocationIdentified());
                        technicalDetails.setIngestionRequest(requestDetails);
                        technicalDetails.setModifiedBy(modifyBy);
                        technicalDetailsRepository.save(technicalDetails);
                    }
                }
                log.info("Saving new status details and updating status");
                requestStatusDetailsList.add(newRequestStatusDetails);
            }
            return requestStatusDetailsList;
        }
        return null;
    }

    /**
     * Searches for ingestion requests based on specified criteria.
     *
     * @param myApprovals    Flag indicating whether to search for requests are in Non-Draft status.
     * @param mySubmissions  Flag indicating whether to search for requests submitted by the logged-in user.
     * @param status         Status of the ingestion requests to search for.
     * @param page           Page number of the search results.
     * @param perPage        Number of items per page in the search results.
     * @param orderBy        Field to order the results by.
     * @param orderDirection Direction of the ordering (ascending or descending).
     * @return An IngestionRequestSummaryDTO containing the search results.
     */
    @Override
    public IngestionRequestSummaryDTO searchIngestionRequests(Boolean myApprovals, Boolean mySubmissions, IngestionRequestStatus status,
                                                              Integer page, Integer perPage, OrderByField orderBy, OrderDirection orderDirection) {
        log.info("Searching ingestion requests with parameters - myApprovals: {}, mySubmissions: {}, status: {}, page: {}, perPage: {}, orderBy: {}, orderDirection: {}",
                myApprovals, mySubmissions, status, page, perPage, orderBy, orderDirection);

        // Create a sort object based on the provided field and direction
        Sort sort = orderDirection == OrderDirection.asc ? Sort.by(orderBy.getFieldName()).ascending() : Sort.by(orderBy.getFieldName()).descending();
        // Create a pageable object for pagination
        Pageable pageable = PageRequest.of(page - 1, perPage, sort);

        IngestionRequestSummaryDTO summary = new IngestionRequestSummaryDTO();
        Page<IngestionRequestDetails> requestDetails = null;
        if (!myApprovals) {
            if (mySubmissions) {
                log.info("Filtering requests based on mySubmissions and status: {}", status);
                // Filter requests based on status
                switch (status) {
                    case All:
                        log.debug("Fetching all requests for status: All");
                        requestDetails = ingestionRequestDetailsRepository.findByRequestStatusDetailsStatusStatusNameInAndRequestStatusDetailsActiveFlag(Arrays.asList(IngestionStatus.TRIAGE_PENDING_APPROVAL.toString(),
                                IngestionStatus.APPROVED.toString(), IngestionStatus.REJECTED.toString(), IngestionStatus.INGESTION_IN_PROGRESS.toString(), IngestionStatus.INGESTION_COMPLETED.toString()
                                , IngestionStatus.INGESTION_FAILURE.toString()), true, pageable);
                        break;
                    case PendingApproval:
                        log.debug("Fetching requests for status: PendingApproval");
                        requestDetails = ingestionRequestDetailsRepository.findByRequestStatusDetailsStatusStatusNameInAndRequestStatusDetailsActiveFlag(Arrays.asList(IngestionStatus.TRIAGE_PENDING_APPROVAL.toString(), IngestionStatus.INGESTION_IN_PROGRESS.toString()), true, pageable);
                        break;
                    case CompletedRequest:
                        log.debug("Fetching requests for status: CompletedRequest");
                        requestDetails = ingestionRequestDetailsRepository.findByRequestStatusDetailsStatusStatusNameInAndRequestStatusDetailsActiveFlag(Arrays.asList(IngestionStatus.INGESTION_COMPLETED.toString()), true, pageable);
                        break;
                    case Rejected:
                        log.debug("Fetching requests for status: Rejected");
                        requestDetails = ingestionRequestDetailsRepository.findByRequestStatusDetailsStatusStatusNameInAndRequestStatusDetailsActiveFlag(Arrays.asList(IngestionStatus.REJECTED.toString()), true, pageable);
                        break;
                }
                log.info("Setting summary totals");
                // Set summary totals based on filtered results
                summary.setTotalPendingApproval(ingestionRequestDetailsRepository.countByRequestStatusDetailsStatusStatusNameInAndRequestStatusDetailsActiveFlag(Arrays.asList(IngestionStatus.TRIAGE_PENDING_APPROVAL.toString(), IngestionStatus.INGESTION_IN_PROGRESS.toString()), true));
                summary.setTotalCompletedRequest(ingestionRequestDetailsRepository.countByRequestStatusDetailsStatusStatusNameInAndRequestStatusDetailsActiveFlag(Arrays.asList(IngestionStatus.INGESTION_COMPLETED.toString()), true));
                summary.setTotalRejected(ingestionRequestDetailsRepository.countByRequestStatusDetailsStatusStatusNameInAndRequestStatusDetailsActiveFlag(Arrays.asList(IngestionStatus.REJECTED.toString()), true));
            }
        } else {
            log.info("Filtering requests based on myApprovals");
            requestDetails = ingestionRequestDetailsRepository.findByRequestStatusDetailsStatusStatusNameInAndRequestStatusDetailsActiveFlag(Arrays.asList(IngestionStatus.DRAFT.toString()), true, pageable);
        }
        // Process the search results
        if (requestDetails != null) {
            // Convert request details to DTOs and set them in the summary
            List<IngestionRequestDetailsDTO> requestDetailsDTOs = requestDetails.getContent().stream()
                    .map(ingestionDetailsResponseMapper::mapToIngestionRequestDetailsDTO)
                    .toList();
            summary.setItems(requestDetailsDTOs);
            summary.setTotalAll((int) requestDetails.getTotalElements());
            log.info("Search completed successfully with {} results", requestDetailsDTOs.size());
            return summary;
        }
        log.warn("No results found for the given parameters");
        return null; // Return null if no results were found
    }
}