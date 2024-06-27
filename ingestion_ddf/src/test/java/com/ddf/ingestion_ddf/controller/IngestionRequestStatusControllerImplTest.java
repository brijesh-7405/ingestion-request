package com.ddf.ingestion_ddf.controller;

import com.ddf.ingestion_ddf.enums.IngestionStatus;
import com.ddf.ingestion_ddf.exception.CustomResponseStatusException;
import com.ddf.ingestion_ddf.request.DecisionRequestDTO;
import com.ddf.ingestion_ddf.response.IngestionRequestDetailsDTO;
import com.ddf.ingestion_ddf.service.IngestionRequestDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;

/**
 * Unit tests for the {@link IngestionRequestStatusControllerImpl} class.
 * <p>
 * This class contains tests for various methods of the {@link IngestionRequestStatusControllerImpl} class
 * to ensure proper functionality and error handling.
 * </p>
 */
public class IngestionRequestStatusControllerImplTest {


    // Mocks
    private IngestionRequestDetailsService ingestionRequestDetailsService;
    private IngestionRequestStatusControllerImpl controller;
    private DecisionRequestDTO decisionRequestDTO;
    private BindingResult bindingResult;


    /**
     * Sets up the test environment by initializing mocks and creating the controller instance.
     */
    @BeforeEach
    void setUp() {
        ingestionRequestDetailsService = mock(IngestionRequestDetailsService.class);
        controller = new IngestionRequestStatusControllerImpl(ingestionRequestDetailsService);
        decisionRequestDTO = new DecisionRequestDTO();
        bindingResult = mock(BindingResult.class);
    }

    /**
     * Tests the submitIngestionRequest method with a valid request.
     * Expects an OK response status and a non-null response body.
     */
    @Test
    void testSubmitIngestionRequestWithValidRequest() {
        // Arrange
        Long ingestionRequestId = 1L;
        IngestionRequestDetailsDTO expectedResponse = new IngestionRequestDetailsDTO();
        when(ingestionRequestDetailsService.updateIngestionRequestStatus(ingestionRequestId, IngestionStatus.TRIAGE_PENDING_APPROVAL, null))
                .thenReturn(expectedResponse);

        // Act
        ResponseEntity<IngestionRequestDetailsDTO> responseEntity = controller.submitIngestionRequest(ingestionRequestId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    /**
     * Tests the approveIngestionRequest method with a valid request.
     * Uses a helper method to reduce redundancy.
     */
    @Test
    void testApproveIngestionRequestWithValidRequest() {
        testDecisionRequest(IngestionStatus.APPROVED, (id, dto, result) -> controller.approveIngestionRequest(id, dto, result));
    }

    /**
     * Tests the approveIngestionRequest method with an invalid request.
     * Expects a CustomResponseStatusException to be thrown.
     */
    @Test
    void testApproveIngestionRequestWithInvalidRequestAndThrowsCustomResponseStatusException() {
        testInvalidDecisionRequest((id, dto, result) -> controller.approveIngestionRequest(id, dto, result));
    }

    /**
     * Tests the rejectIngestionRequest method with a valid request.
     * Uses a helper method to reduce redundancy.
     */
    @Test
    void testRejectIngestionRequestWithValidRequest() {
        testDecisionRequest(IngestionStatus.REJECTED, (id, dto, result) -> controller.rejectIngestionRequest(id, dto, result));
    }

    /**
     * Tests the rejectIngestionRequest method with an invalid request.
     * Expects a CustomResponseStatusException to be thrown.
     */
    @Test
    void testRejectIngestionRequestWithInvalidRequestAndThrowsCustomResponseStatusException() {
        testInvalidDecisionRequest((id, dto, result) -> controller.rejectIngestionRequest(id, dto, result));
    }

    /**
     * Tests the markIngestionInProgress method with a valid request.
     * Uses a helper method to reduce redundancy.
     */
    @Test
    void testIngestionInProgressIngestionRequestWithValidRequest() {
        testDecisionRequest(IngestionStatus.INGESTION_IN_PROGRESS, (id, dto, result) -> controller.markIngestionInProgress(id, dto, result));
    }

    /**
     * Tests the markIngestionInProgress method with an invalid request.
     * Expects a CustomResponseStatusException to be thrown.
     */
    @Test
    void testIngestionInProgressIngestionRequestWithInvalidRequestAndThrowsCustomResponseStatusException() {
        testInvalidDecisionRequest((id, dto, result) -> controller.markIngestionInProgress(id, dto, result));
    }

    /**
     * Tests the markIngestionComplete method with a valid request.
     * Uses a helper method to reduce redundancy.
     */
    @Test
    void testIngestionCompleteIngestionRequestWithValidRequest() {
        testDecisionRequest(IngestionStatus.INGESTION_COMPLETED, (id, dto, result) -> controller.markIngestionComplete(id, dto, result));
    }

    /**
     * Tests the markIngestionComplete method with an invalid request.
     * Expects a CustomResponseStatusException to be thrown.
     */
    @Test
    void testIngestionCompleteIngestionRequestWithInvalidRequestAndThrowsCustomResponseStatusException() {
        testInvalidDecisionRequest((id, dto, result) -> controller.markIngestionComplete(id, dto, result));
    }

    /**
     * Tests the markIngestionFailure method with a valid request.
     * Uses a helper method to reduce redundancy.
     */
    @Test
    void testIngestionFailureIngestionRequestWithValidRequest() {
        testDecisionRequest(IngestionStatus.INGESTION_FAILURE, (id, dto, result) -> controller.markIngestionFailure(id, dto, result));
    }

    /**
     * Tests the markIngestionFailure method with an invalid request.
     * Expects a CustomResponseStatusException to be thrown.
     */
    @Test
    void testIngestionFailureIngestionRequestWithInvalidRequestAndThrowsCustomResponseStatusException() {
        testInvalidDecisionRequest((id, dto, result) -> controller.markIngestionFailure(id, dto, result));
    }

    /**
     * Helper method to test valid decision requests for different ingestion statuses.
     *
     * @param status the ingestion status to be tested
     * @param tester the method reference for the specific controller method being tested
     */
    private void testDecisionRequest(IngestionStatus status, DecisionRequestTester tester) {
        Long ingestionRequestId = 1L;
        IngestionRequestDetailsDTO expectedDTO = new IngestionRequestDetailsDTO();
        when(bindingResult.hasErrors()).thenReturn(false);
        when(ingestionRequestDetailsService.updateIngestionRequestStatus(ingestionRequestId, status, decisionRequestDTO))
                .thenReturn(expectedDTO);

        // Act
        ResponseEntity<IngestionRequestDetailsDTO> responseEntity = tester.test(ingestionRequestId, decisionRequestDTO, bindingResult);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedDTO, responseEntity.getBody());
        verify(ingestionRequestDetailsService).updateIngestionRequestStatus(ingestionRequestId, status, decisionRequestDTO);
    }

    /**
     * Helper method to test invalid decision requests for different ingestion statuses.
     *
     * @param tester the method reference for the specific controller method being tested
     */
    private void testInvalidDecisionRequest(DecisionRequestTester tester) {
        Long ingestionRequestId = 1L;
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getAllErrors()).thenReturn(Collections.singletonList(new ObjectError("field", "Error message")));

        // Act & Assert
        assertThrows(CustomResponseStatusException.class, () -> tester.test(ingestionRequestId, decisionRequestDTO, bindingResult));
    }

    /**
     * Functional interface representing a tester for decision requests.
     * <p>
     * This functional interface defines a single method for testing decision requests,
     * taking a request ID, a decision request DTO, and a binding result as parameters
     * and returning a ResponseEntity containing details of the ingestion request.
     * </p>
     */
    @FunctionalInterface
    private interface DecisionRequestTester {
        /**
         * Tests a decision request.
         *
         * @param ingestionRequestId   the ID of the ingestion request
         * @param decisionRequestDTO   the decision request DTO
         * @param bindingResult        the binding result containing errors, if any
         * @return a ResponseEntity containing details of the ingestion request
         */
        ResponseEntity<IngestionRequestDetailsDTO> test(Long ingestionRequestId, DecisionRequestDTO decisionRequestDTO, BindingResult bindingResult);
    }

}
