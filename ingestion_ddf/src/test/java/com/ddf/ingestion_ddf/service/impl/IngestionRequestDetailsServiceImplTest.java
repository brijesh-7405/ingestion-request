package com.ddf.ingestion_ddf.service.impl;

import com.ddf.ingestion_ddf.constants.ApiConstants;
import com.ddf.ingestion_ddf.entity.EmailTemplate;
import com.ddf.ingestion_ddf.entity.IngestionRequestDetails;
import com.ddf.ingestion_ddf.entity.Status;
import com.ddf.ingestion_ddf.enums.IngestionRequestStatus;
import com.ddf.ingestion_ddf.enums.IngestionStatus;
import com.ddf.ingestion_ddf.enums.OrderByField;
import com.ddf.ingestion_ddf.enums.OrderDirection;
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
import com.ddf.ingestion_ddf.util.TestDataUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;

/**
 * Test cases for the {@link IngestionRequestDetailsServiceImpl} class.
 */
@ExtendWith(MockitoExtension.class)
public class IngestionRequestDetailsServiceImplTest {

    @Mock
    private IngestionRequestDetailsRepository ingestionRequestDetailsRepository;

    @Mock
    private StatusRepository statusRepository;

    @Mock
    private IngestionRequestMapper ingestionRequestMapper;

    @Mock
    private IngestionDetailsResponseMapper ingestionDetailsResponseMapper;

    @Mock
    private RequestStatusDetailsRepository requestStatusDetailsRepository;

    @Mock
    private TechnicalDetailsRepository technicalDetailsRepository;

    @Mock
    private EmailTemplateRepository emailTemplateRepository;

    @Mock
    private EmailTemplateService emailTemplateService;

    @InjectMocks
    private IngestionRequestDetailsServiceImpl ingestionRequestDetailsService;

    IngestionRequest ingestionRequest;
    IngestionRequestDetails ingestionRequestDetails;
    String createdBy;
    String modifiedBy;

    /**
     * Set up test data before each test method.
     */
    @BeforeEach
    public void setUp() {
        ingestionRequest = TestDataUtil.createTestIngestionRequest();
        ingestionRequestDetails = TestDataUtil.createTestIngestionRequestDetails();
        createdBy = ApiConstants.CREATED_BY;
        modifiedBy = ApiConstants.MODIFIED_BY;
    }

    /**
     * Test case for creating or updating an ingestion request when the ingestion ID is not null
     * and the request does not exist.
     */
    @Test
    void testCreateOrUpdateIRWhenIngestionIdIsNotNullAndRequestNotExist() {
        when(ingestionRequestDetailsRepository.findById(1L))
                .thenReturn(Optional.empty());
        // Call the service method and verify that it throws an exception
        assertThrows(IngestionRequestDetailsNotFoundException.class, () -> {
            ingestionRequestDetailsService.createOrUpdateIngestionRequest(1L, ingestionRequest, false);
        });
    }

    /**
     * Test case for creating or updating an ingestion request when the ingestion ID is not null,
     * the request exists, and submit is false.
     */
    @Test
    void testCreateOrUpdateIRWhenIngestionIdIsNotNullAndRequestExistAndSubmitIsFalse() {
        boolean submit = false;
        when(ingestionRequestDetailsRepository.findById(1L)).thenReturn(Optional.of(new IngestionRequestDetails()));
        when(ingestionRequestMapper.mapToIngestionRequestDetails(any(), any(), any(), any())).thenReturn(new IngestionRequestDetails());
        when(ingestionDetailsResponseMapper.mapToIngestionRequestDetailsDTO(any())).thenReturn(new IngestionRequestDetailsDTO());

        // Call the service method
        IngestionRequestDetailsDTO result = ingestionRequestDetailsService.createOrUpdateIngestionRequest(1l, ingestionRequest, submit);

        // Assertions
        assertNotNull(result);
    }

    /**
     * Test case for creating or updating an ingestion request when the ingestion ID is not null,
     * the request exists, and submit is true.
     */
    @Test
    void testCreateOrUpdateIRWhenIngestionIdIsNotNullAndRequestExistAndSubmitIsTrue() {
        boolean submit = true;
        ingestionRequestDetails.setIngestionRequestId(1l);
        when(ingestionRequestDetailsRepository.findById(1L)).thenReturn(Optional.of(ingestionRequestDetails));
        when(ingestionRequestMapper.mapToIngestionRequestDetails(any(), any(), any(), any())).thenReturn(new IngestionRequestDetails());
        when(ingestionDetailsResponseMapper.mapToIngestionRequestDetailsDTO(any())).thenReturn(new IngestionRequestDetailsDTO());

        // Call the service method
        IngestionRequestDetailsDTO result = ingestionRequestDetailsService
                .createOrUpdateIngestionRequest(1l, ingestionRequest, submit);

        // Assertions
        assertNotNull(result);
    }

    /**
     * Test case for creating or updating an ingestion request when the ingestion ID is null and submit is false.
     */
    @Test
    void testCreateOrUpdateIRWhenIngestionIdIsNullAndSubmitIsFalse() {
        boolean submit = false;
        when(ingestionRequestMapper.mapToIngestionRequestDetails(any(), any(), any(), any())).thenReturn(ingestionRequestDetails);
        when(statusRepository.findByStatusNameIgnoreCase(any())).thenReturn(new Status());
        // Mock behavior
        when(ingestionRequestDetailsRepository.save(any())).thenReturn(ingestionRequestDetails);
        when(ingestionDetailsResponseMapper.mapToIngestionRequestDetailsDTO(any())).thenReturn(new IngestionRequestDetailsDTO());

        // Call the service method
        IngestionRequestDetailsDTO result = ingestionRequestDetailsService.createOrUpdateIngestionRequest(null, ingestionRequest, submit);

        // Assertions
        assertNotNull(result);
        // Add more assertions as needed
    }


    /**
     * Test case for creating or updating an ingestion request when the ingestion ID is null and submit is true.
     */
    @Test
    void testCreateOrUpdateIRWhenIngestionIdIsNullAndSubmitIsTrue() {
        boolean submit = true;
        when(ingestionRequestMapper.mapToIngestionRequestDetails(any(), any(), any(), any())).thenReturn(ingestionRequestDetails);
        when(statusRepository.findByStatusNameIgnoreCase(any())).thenReturn(new Status());
        // Mock behavior
        when(ingestionRequestDetailsRepository.save(any())).thenReturn(ingestionRequestDetails);
        when(ingestionDetailsResponseMapper.mapToIngestionRequestDetailsDTO(any())).thenReturn(new IngestionRequestDetailsDTO());

        // Call the service method
        IngestionRequestDetailsDTO result = ingestionRequestDetailsService.createOrUpdateIngestionRequest(null, ingestionRequest, submit);

        // Assertions
        assertNotNull(result);
    }

    /**
     * Test case for getting ingestion request details when the request does not exist.
     */
    @Test
    void testGetIngestionRequestDetailsAndRequestNotExist() {
        when(ingestionRequestDetailsRepository.findById(1L))
                .thenReturn(Optional.empty());
        // Call the service method and verify that it throws an exception
        assertThrows(IngestionRequestDetailsNotFoundException.class, () -> {
            ingestionRequestDetailsService.getIngestionRequestDetail(1L);
        });
    }

    /**
     * Test case for getting ingestion request details when the request exists.
     */
    @Test
    void testGetIngestionRequestDetailsAndRequestExist() {
        when(ingestionRequestDetailsRepository.findById(1L))
                .thenReturn(Optional.of(ingestionRequestDetails));
        when(ingestionDetailsResponseMapper.mapToIngestionRequestDetailsDTO(any())).thenReturn(new IngestionRequestDetailsDTO());

        // Call the service method
        IngestionRequestDetailsDTO result = ingestionRequestDetailsService.getIngestionRequestDetail(1l);

        // Assertions
        assertNotNull(result);
    }

    /**
     * Test case for getting ingestion request details with a null ID, expecting an IllegalArgumentException.
     */
    @Test
    void testGetIngestionRequestWithNullId() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> ingestionRequestDetailsService.getIngestionRequestDetail(null));
    }

    /**
     * Test case for updating status when the request does not exist, expecting an IngestionRequestDetailsNotFoundException.
     */
    @Test
    void testUpdateStatusAndRequestNotExist() {
        when(ingestionRequestDetailsRepository.findById(1L))
                .thenReturn(Optional.empty());
        // Call the service method and verify that it throws an exception
        assertThrows(IngestionRequestDetailsNotFoundException.class, () -> {
            ingestionRequestDetailsService.updateIngestionRequestStatus(1L, null, null);
        });
    }

    /**
     * Test case for updating status when the request exists with an incorrect previous status, expecting an IngestionRequestStatusException.
     */
    @Test
    void testUpdateStatusAndRequestExistWithPreviousStatusIncorrect() {
        when(ingestionRequestDetailsRepository.findById(1L))
                .thenReturn(Optional.of(ingestionRequestDetails));
        // Call the service method and verify that it throws an exception
        assertThrows(IngestionRequestStatusException.class, () -> {
            ingestionRequestDetailsService.updateIngestionRequestStatus(1L, IngestionStatus.DRAFT, null);
        });
    }

    /**
     * Tests the updateIngestionRequestStatus method when the request exists but has a null request status.
     * <p>
     * This test case ensures that when an ingestion request with a null request status is updated,
     * the method handles the null status appropriately and returns the expected result.
     */
    @Test
    void testUpdateStatusAndRequestExistWithRequestStatusNull() {
        // Set up the test data
        ingestionRequestDetails.setRequestStatusDetails(null);
        when(ingestionRequestDetailsRepository.findById(1L))
                .thenReturn(Optional.of(ingestionRequestDetails));
        when(ingestionDetailsResponseMapper.mapToIngestionRequestDetailsDTO(any())).thenReturn(new IngestionRequestDetailsDTO());

        // Call the service method
        IngestionRequestDetailsDTO result = ingestionRequestDetailsService.updateIngestionRequestStatus(1l, IngestionStatus.TRIAGE_PENDING_APPROVAL, null);
        // Assert the expected result
        assertNull(result.getActiveRequestStatus());

    }

    /**
     * Test case for updating status when the request exists with a correct previous status and the decision is null.
     */
    @Test
    void testUpdateStatusAndRequestExistWithPreviousStatusCorrectAndDecisionIsNull() {
        when(ingestionRequestDetailsRepository.findById(1L))
                .thenReturn(Optional.of(ingestionRequestDetails));
        when(statusRepository.findByStatusNameIgnoreCase(any())).thenReturn(new Status());
        when(ingestionDetailsResponseMapper.mapToIngestionRequestDetailsDTO(any())).thenReturn(new IngestionRequestDetailsDTO());

        // Call the service method
        IngestionRequestDetailsDTO result = ingestionRequestDetailsService.updateIngestionRequestStatus(1l, IngestionStatus.TRIAGE_PENDING_APPROVAL, null);

        // Assertions
        assertNotNull(result);
    }

    /**
     * Test case for updating status when the request exists with a correct previous status and the decision is not null.
     */
    @Test
    void testUpdateStatusAndRequestExistWithPreviousStatusCorrectAndDecisionIsNotNull() {
        DecisionRequestDTO decisionRequestDTO = new DecisionRequestDTO();
        decisionRequestDTO.setDecisionComments("testComments");
        decisionRequestDTO.setNotifyThroughEmail(true);
        decisionRequestDTO.setExistingDataLocationIdentified("testDataLocationIdentified");
        EmailTemplate emailTemplate = new EmailTemplate();
        emailTemplate.setBody("Dear user status changes from {oldsStatus} to {newStatus}");
        when(ingestionRequestDetailsRepository.findById(1L))
                .thenReturn(Optional.of(ingestionRequestDetails));
        when(statusRepository.findByStatusNameIgnoreCase(any())).thenReturn(new Status());
        when(ingestionDetailsResponseMapper.mapToIngestionRequestDetailsDTO(any())).thenReturn(new IngestionRequestDetailsDTO());
        when(emailTemplateRepository.findByTemplateCodeIgnoreCase(ApiConstants.EMAIL_TEMPLATE_CODE)).thenReturn(Optional.of(emailTemplate));
        // Call the service method
        IngestionRequestDetailsDTO result = ingestionRequestDetailsService.updateIngestionRequestStatus(1l, IngestionStatus.TRIAGE_PENDING_APPROVAL, decisionRequestDTO);

        // Assertions
        assertNotNull(result);
    }

    /**
     * Test case for updating status when the request exists with a correct previous status and the decision is not null but email template not found.
     */
    @Test
    void testUpdateStatusAndRequestExistWithPreviousStatusCorrectAndDecisionIsNotNullAndTemplateNotFound() {
        DecisionRequestDTO decisionRequestDTO = new DecisionRequestDTO();
        decisionRequestDTO.setDecisionComments("testComments");
        decisionRequestDTO.setNotifyThroughEmail(true);
        decisionRequestDTO.setExistingDataLocationIdentified("testDataLocationIdentified");
        when(ingestionRequestDetailsRepository.findById(1L))
                .thenReturn(Optional.of(ingestionRequestDetails));
        when(statusRepository.findByStatusNameIgnoreCase(any())).thenReturn(new Status());
        when(ingestionDetailsResponseMapper.mapToIngestionRequestDetailsDTO(any())).thenReturn(new IngestionRequestDetailsDTO());
        when(emailTemplateRepository.findByTemplateCodeIgnoreCase(any())).thenReturn(Optional.empty());

        // Call the service method
        IngestionRequestDetailsDTO result = ingestionRequestDetailsService.updateIngestionRequestStatus(1l, IngestionStatus.TRIAGE_PENDING_APPROVAL, decisionRequestDTO);

        // Assertions
        assertNotNull(result);
        verify(emailTemplateService, never()).sendMail(any(),any(), any());

    }

    /**
     * Test case for searching ingestion requests with the option to search for "My Approvals" with ascending direction and expecting a non-null result.
     */
    @Test
    void testSearchIngestionRequestsWithMyApprovalWithAscDirectionAndResultIsNotNull() {
        boolean myApprovals = true;
        OrderDirection orderDirection = OrderDirection.asc;
        OrderByField orderBy = OrderByField.ingestionRequestId;
        int page = 1, perPage = 10;
        Sort sort = Sort.by(orderBy.getFieldName()).ascending();
        Pageable pageable = PageRequest.of(page - 1, perPage, sort);

        Page<IngestionRequestDetails> requestDetails = new PageImpl<>(new ArrayList<IngestionRequestDetails>());
        when(ingestionRequestDetailsRepository.findByRequestStatusDetailsStatusStatusNameInAndRequestStatusDetailsActiveFlag(Arrays.asList(IngestionStatus.DRAFT.toString()), true, pageable))
                .thenReturn(requestDetails);

        IngestionRequestSummaryDTO result = ingestionRequestDetailsService
                .searchIngestionRequests(myApprovals, true, IngestionRequestStatus.All, page, perPage, orderBy, orderDirection);
        assertNotNull(result);

    }

    /**
     * Test case for searching ingestion requests with the option to search for "My Approvals" with ascending direction and expecting a null result.
     */
    @Test
    void testSearchIngestionRequestsWithMyApprovalWithAscDirectionAndResultIsNull() {
        boolean myApprovals = true;
        OrderDirection orderDirection = OrderDirection.asc;
        OrderByField orderBy = OrderByField.ingestionRequestId;
        int page = 1, perPage = 10;
        Sort sort = Sort.by(orderBy.getFieldName()).ascending();
        Pageable pageable = PageRequest.of(page - 1, perPage, sort);

        when(ingestionRequestDetailsRepository.findByRequestStatusDetailsStatusStatusNameInAndRequestStatusDetailsActiveFlag(Arrays.asList(IngestionStatus.DRAFT.toString()), true, pageable))
                .thenReturn(null);

        IngestionRequestSummaryDTO result = ingestionRequestDetailsService
                .searchIngestionRequests(myApprovals, true, IngestionRequestStatus.All, page, perPage, orderBy, orderDirection);
        assertNull(result);

    }

    /**
     * Test case for searching ingestion requests with the option to search for "My Approvals" with descending direction and expecting a non-null result.
     */
    @Test
    void testSearchIngestionRequestsWithMyApprovalWithDescDirectionAndResultIsNotNull() {
        boolean myApprovals = true;
        OrderDirection orderDirection = OrderDirection.desc;
        OrderByField orderBy = OrderByField.ingestionRequestId;
        int page = 1, perPage = 10;
        Sort sort = Sort.by(orderBy.getFieldName()).descending();
        Pageable pageable = PageRequest.of(page - 1, perPage, sort);

        Page<IngestionRequestDetails> requestDetails = new PageImpl<>(new ArrayList<IngestionRequestDetails>());
        when(ingestionRequestDetailsRepository.findByRequestStatusDetailsStatusStatusNameInAndRequestStatusDetailsActiveFlag(Arrays.asList(IngestionStatus.DRAFT.toString()), true, pageable))
                .thenReturn(requestDetails);

        IngestionRequestSummaryDTO result = ingestionRequestDetailsService
                .searchIngestionRequests(myApprovals, true, IngestionRequestStatus.All, page, perPage, orderBy, orderDirection);
        assertNotNull(result);

    }

    /**
     * Test case for searching ingestion requests with the option to search for "My Approvals" with descending direction and expecting a null result.
     */
    @Test
    void testSearchIngestionRequestsWithMyApprovalWithDescDirectionAndResultIsNull() {
        boolean myApprovals = true;
        OrderDirection orderDirection = OrderDirection.desc;
        OrderByField orderBy = OrderByField.ingestionRequestId;
        int page = 1, perPage = 10;
        Sort sort = Sort.by(orderBy.getFieldName()).descending();
        Pageable pageable = PageRequest.of(page - 1, perPage, sort);

        when(ingestionRequestDetailsRepository.findByRequestStatusDetailsStatusStatusNameInAndRequestStatusDetailsActiveFlag(Arrays.asList(IngestionStatus.DRAFT.toString()), true, pageable))
                .thenReturn(null);

        IngestionRequestSummaryDTO result = ingestionRequestDetailsService
                .searchIngestionRequests(myApprovals, true, IngestionRequestStatus.All, page, perPage, orderBy, orderDirection);
        assertNull(result);

    }

    /**
     * Test case for searching ingestion requests without "My Approvals" and "My Submissions" with descending direction and expecting a null result.
     */
    @Test
    void testSearchIngestionRequestsWithoutMyApprovalAndWithoutMySubmissionWithDescDirection() {
        boolean myApprovals = false, mySubmissions = false;
        OrderDirection orderDirection = OrderDirection.desc;
        OrderByField orderBy = OrderByField.ingestionRequestId;
        int page = 1, perPage = 10;
        IngestionRequestSummaryDTO result = ingestionRequestDetailsService
                .searchIngestionRequests(myApprovals, mySubmissions, IngestionRequestStatus.All, page, perPage, orderBy, orderDirection);
        assertNull(result);

    }

    /**
     * Test case for searching ingestion requests without "My Approvals" and "My Submissions" with ascending direction and expecting a null result.
     */
    @Test
    void testSearchIngestionRequestsWithoutMyApprovalAndWithoutMySubmissionWithAscDirection() {
        boolean myApprovals = false, mySubmissions = false;
        OrderDirection orderDirection = OrderDirection.asc;
        OrderByField orderBy = OrderByField.ingestionRequestId;
        int page = 1, perPage = 10;
        IngestionRequestSummaryDTO result = ingestionRequestDetailsService
                .searchIngestionRequests(myApprovals, mySubmissions, IngestionRequestStatus.All, page, perPage, orderBy, orderDirection);
        assertNull(result);

    }

    /**
     * Test case for searching ingestion requests without "My Approvals" and with "My Submissions" with ascending direction and expecting a null result.
     */
    @Test
    void testSearchIngestionRequestsWithoutMyApprovalAndWithMySubmissionWithAscDirection() {
        boolean myApprovals = false, mySubmissions = false;
        OrderDirection orderDirection = OrderDirection.asc;
        OrderByField orderBy = OrderByField.ingestionRequestId;
        int page = 1, perPage = 10;
        IngestionRequestSummaryDTO result = ingestionRequestDetailsService
                .searchIngestionRequests(myApprovals, mySubmissions, IngestionRequestStatus.All, page, perPage, orderBy, orderDirection);
        assertNull(result);

    }

    /**
     * Tests the search for ingestion requests with no approvals and submissions, across all statuses, ordered in ascending order.
     */
    @Test
    void testNoApprovalsWithSubmissionsAllStatusWithAsc() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(OrderByField.ingestionRequestId.getFieldName()).ascending());
        Page<IngestionRequestDetails> page = new PageImpl<>(List.of(ingestionRequestDetails));
        when(ingestionRequestDetailsRepository.findByRequestStatusDetailsStatusStatusNameInAndRequestStatusDetailsActiveFlag(anyList(), eq(true), eq(pageable)))
                .thenReturn(page);
        when(ingestionDetailsResponseMapper.mapToIngestionRequestDetailsDTO(any())).thenReturn(new IngestionRequestDetailsDTO());
        when(ingestionRequestDetailsRepository.countByRequestStatusDetailsStatusStatusNameInAndRequestStatusDetailsActiveFlag(anyList(), eq(true))).thenReturn(1);

        IngestionRequestSummaryDTO result = ingestionRequestDetailsService.searchIngestionRequests(false, true, IngestionRequestStatus.All, 1, 10, OrderByField.ingestionRequestId, OrderDirection.asc);

        assertNotNull(result);
        assertEquals(1, result.getItems().size());
        assertEquals(1, result.getTotalAll());
        assertEquals(1, result.getTotalPendingApproval());
        assertEquals(1, result.getTotalCompletedRequest());
        assertEquals(1, result.getTotalRejected());
    }

    /**
     * Tests the search for ingestion requests with no approvals and submissions, in pending approval status, ordered in ascending order.
     */
    @Test
    void testNoApprovalsWithSubmissionsPendingApprovalWithAsc() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(OrderByField.ingestionRequestId.getFieldName()).ascending());
        Page<IngestionRequestDetails> page = new PageImpl<>(List.of(ingestionRequestDetails));
        when(ingestionRequestDetailsRepository.findByRequestStatusDetailsStatusStatusNameInAndRequestStatusDetailsActiveFlag(anyList(), eq(true), eq(pageable)))
                .thenReturn(page);
        when(ingestionDetailsResponseMapper.mapToIngestionRequestDetailsDTO(any())).thenReturn(new IngestionRequestDetailsDTO());
        when(ingestionRequestDetailsRepository.countByRequestStatusDetailsStatusStatusNameInAndRequestStatusDetailsActiveFlag(anyList(), eq(true))).thenReturn(1);

        IngestionRequestSummaryDTO result = ingestionRequestDetailsService.searchIngestionRequests(false, true, IngestionRequestStatus.PendingApproval, 1, 10, OrderByField.ingestionRequestId, OrderDirection.asc);

        assertNotNull(result);
        assertEquals(1, result.getItems().size());
        assertEquals(1, result.getTotalAll());
        assertEquals(1, result.getTotalPendingApproval());
    }

    /**
     * Tests the search for ingestion requests with no approvals and submissions, in completed request status, ordered in ascending order.
     */
    @Test
    void testNoApprovalsWithSubmissionsCompletedRequestWithAsc() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(OrderByField.ingestionRequestId.getFieldName()).ascending());
        Page<IngestionRequestDetails> page = new PageImpl<>(List.of(ingestionRequestDetails));
        when(ingestionRequestDetailsRepository.findByRequestStatusDetailsStatusStatusNameInAndRequestStatusDetailsActiveFlag(anyList(), eq(true), eq(pageable)))
                .thenReturn(page);
        when(ingestionDetailsResponseMapper.mapToIngestionRequestDetailsDTO(any())).thenReturn(new IngestionRequestDetailsDTO());
        when(ingestionRequestDetailsRepository.countByRequestStatusDetailsStatusStatusNameInAndRequestStatusDetailsActiveFlag(anyList(), eq(true))).thenReturn(1);

        IngestionRequestSummaryDTO result = ingestionRequestDetailsService.searchIngestionRequests(false, true, IngestionRequestStatus.CompletedRequest, 1, 10, OrderByField.ingestionRequestId, OrderDirection.asc);

        assertNotNull(result);
        assertEquals(1, result.getItems().size());
        assertEquals(1, result.getTotalAll());
        assertEquals(1, result.getTotalCompletedRequest());
    }

    /**
     * Tests the search for ingestion requests with no approvals and submissions, in rejected status, ordered in ascending order.
     */
    @Test
    void testNoApprovalsWithSubmissionsRejectedWithAsc() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(OrderByField.ingestionRequestId.getFieldName()).ascending());
        Page<IngestionRequestDetails> page = new PageImpl<>(List.of(ingestionRequestDetails));
        when(ingestionRequestDetailsRepository.findByRequestStatusDetailsStatusStatusNameInAndRequestStatusDetailsActiveFlag(anyList(), eq(true), eq(pageable)))
                .thenReturn(page);
        when(ingestionDetailsResponseMapper.mapToIngestionRequestDetailsDTO(any())).thenReturn(new IngestionRequestDetailsDTO());
        when(ingestionRequestDetailsRepository.countByRequestStatusDetailsStatusStatusNameInAndRequestStatusDetailsActiveFlag(anyList(), eq(true))).thenReturn(1);

        IngestionRequestSummaryDTO result = ingestionRequestDetailsService.searchIngestionRequests(false, true, IngestionRequestStatus.Rejected, 1, 10, OrderByField.ingestionRequestId, OrderDirection.asc);

        assertNotNull(result);
        assertEquals(1, result.getItems().size());
        assertEquals(1, result.getTotalAll());
        assertEquals(1, result.getTotalRejected());
    }

    /**
     * Tests the search for ingestion requests with no approvals and submissions, across all statuses, ordered in descending order.
     */
    @Test
    void testNoApprovalsWithSubmissionsAllStatusWithDesc() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(OrderByField.ingestionRequestId.getFieldName()).descending());
        Page<IngestionRequestDetails> page = new PageImpl<>(List.of(ingestionRequestDetails));
        when(ingestionRequestDetailsRepository.findByRequestStatusDetailsStatusStatusNameInAndRequestStatusDetailsActiveFlag(anyList(), eq(true), eq(pageable)))
                .thenReturn(page);
        when(ingestionDetailsResponseMapper.mapToIngestionRequestDetailsDTO(any())).thenReturn(new IngestionRequestDetailsDTO());
        when(ingestionRequestDetailsRepository.countByRequestStatusDetailsStatusStatusNameInAndRequestStatusDetailsActiveFlag(anyList(), eq(true))).thenReturn(1);

        IngestionRequestSummaryDTO result = ingestionRequestDetailsService.searchIngestionRequests(false, true, IngestionRequestStatus.All, 1, 10, OrderByField.ingestionRequestId, OrderDirection.desc);

        assertNotNull(result);
        assertEquals(1, result.getItems().size());
        assertEquals(1, result.getTotalAll());
        assertEquals(1, result.getTotalPendingApproval());
        assertEquals(1, result.getTotalCompletedRequest());
        assertEquals(1, result.getTotalRejected());
    }

    /**
     * Tests the search for ingestion requests with no approvals and submissions, in pending approval status, ordered in descending order.
     */
    @Test
    void testNoApprovalsWithSubmissionsPendingApprovalWithDesc() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(OrderByField.ingestionRequestId.getFieldName()).descending());
        Page<IngestionRequestDetails> page = new PageImpl<>(List.of(ingestionRequestDetails));
        when(ingestionRequestDetailsRepository.findByRequestStatusDetailsStatusStatusNameInAndRequestStatusDetailsActiveFlag(anyList(), eq(true), eq(pageable)))
                .thenReturn(page);
        when(ingestionDetailsResponseMapper.mapToIngestionRequestDetailsDTO(any())).thenReturn(new IngestionRequestDetailsDTO());
        when(ingestionRequestDetailsRepository.countByRequestStatusDetailsStatusStatusNameInAndRequestStatusDetailsActiveFlag(anyList(), eq(true))).thenReturn(1);

        IngestionRequestSummaryDTO result = ingestionRequestDetailsService.searchIngestionRequests(false, true, IngestionRequestStatus.PendingApproval, 1, 10, OrderByField.ingestionRequestId, OrderDirection.desc);

        assertNotNull(result);
        assertEquals(1, result.getItems().size());
        assertEquals(1, result.getTotalAll());
        assertEquals(1, result.getTotalPendingApproval());
    }

    /**
     * Tests the search for ingestion requests with no approvals and submissions, in completed request status, ordered in descending order.
     */
    @Test
    void testNoApprovalsWithSubmissionsCompletedRequestWithDesc() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(OrderByField.ingestionRequestId.getFieldName()).descending());
        Page<IngestionRequestDetails> page = new PageImpl<>(List.of(ingestionRequestDetails));
        when(ingestionRequestDetailsRepository.findByRequestStatusDetailsStatusStatusNameInAndRequestStatusDetailsActiveFlag(anyList(), eq(true), eq(pageable)))
                .thenReturn(page);
        when(ingestionDetailsResponseMapper.mapToIngestionRequestDetailsDTO(any())).thenReturn(new IngestionRequestDetailsDTO());
        when(ingestionRequestDetailsRepository.countByRequestStatusDetailsStatusStatusNameInAndRequestStatusDetailsActiveFlag(anyList(), eq(true))).thenReturn(1);

        IngestionRequestSummaryDTO result = ingestionRequestDetailsService.searchIngestionRequests(false, true, IngestionRequestStatus.CompletedRequest, 1, 10, OrderByField.ingestionRequestId, OrderDirection.desc);

        assertNotNull(result);
        assertEquals(1, result.getItems().size());
        assertEquals(1, result.getTotalAll());
        assertEquals(1, result.getTotalCompletedRequest());
    }

    /**
     * Tests the search for ingestion requests with no approvals and submissions, in rejected status, ordered in descending order.
     */
    @Test
    void testNoApprovalsWithSubmissionsRejectedWitDesc() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(OrderByField.ingestionRequestId.getFieldName()).descending());
        ingestionRequestDetails.setIngestionRequestId(1l);
        Page<IngestionRequestDetails> page = new PageImpl<>(List.of(ingestionRequestDetails));
        when(ingestionRequestDetailsRepository.findByRequestStatusDetailsStatusStatusNameInAndRequestStatusDetailsActiveFlag(anyList(), eq(true), eq(pageable)))
                .thenReturn(page);
        when(ingestionDetailsResponseMapper.mapToIngestionRequestDetailsDTO(any())).thenReturn(new IngestionRequestDetailsDTO());
        when(ingestionRequestDetailsRepository.countByRequestStatusDetailsStatusStatusNameInAndRequestStatusDetailsActiveFlag(anyList(), eq(true))).thenReturn(1);

        IngestionRequestSummaryDTO result = ingestionRequestDetailsService.searchIngestionRequests(false, true, IngestionRequestStatus.Rejected, 1, 10, OrderByField.ingestionRequestId, OrderDirection.desc);

        assertNotNull(result);
        assertEquals(1, result.getItems().size());
        assertEquals(1, result.getTotalAll());
        assertEquals(1, result.getTotalRejected());
    }

}
