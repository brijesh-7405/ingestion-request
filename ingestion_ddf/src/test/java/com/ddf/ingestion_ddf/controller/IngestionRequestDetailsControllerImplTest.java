package com.ddf.ingestion_ddf.controller;

import com.ddf.ingestion_ddf.enums.IngestionRequestStatus;
import com.ddf.ingestion_ddf.enums.OrderByField;
import com.ddf.ingestion_ddf.enums.OrderDirection;
import com.ddf.ingestion_ddf.exception.CustomResponseStatusException;
import com.ddf.ingestion_ddf.request.IngestionRequest;
import com.ddf.ingestion_ddf.response.IngestionRequestDetailsDTO;
import com.ddf.ingestion_ddf.response.IngestionRequestSummaryDTO;
import com.ddf.ingestion_ddf.service.IngestionRequestDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the {@link IngestionRequestDetailsControllerImpl} class.
 * This class contains tests for the various methods of the IngestionRequestDetailsControllerImpl,
 * ensuring they function correctly and handle errors appropriately.
 */
public class IngestionRequestDetailsControllerImplTest {

    @Mock
    private IngestionRequestDetailsService ingestionRequestDetailsService;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private IngestionRequestDetailsControllerImpl controller;

    /**
     * Initializes mocks before each test method.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Tests that a valid ingestion request is successfully created.
     * Verifies that the response entity contains the expected response and a status code of 200.
     */
    @Test
    void testCreateIngestionRequestWithValidRequest() {
        // Arrange
        boolean submit = true;
        IngestionRequest requestDto = new IngestionRequest();
        IngestionRequestDetailsDTO expectedResponse = new IngestionRequestDetailsDTO();
        when(bindingResult.hasErrors()).thenReturn(false);
        when(ingestionRequestDetailsService.createOrUpdateIngestionRequest(null, requestDto, submit)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<IngestionRequestDetailsDTO> responseEntity = controller.createIngestionRequest(submit, requestDto, bindingResult);

        // Assert
        assertEquals(expectedResponse, responseEntity.getBody());
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    /**
     * Tests that a valid ingestion request is successfully created.
     * Verifies that the response entity contains the expected response and a status code of 200.
     */
    @Test
    void testCreateIngestionRequestWithValidRequestAndSubmitFalse() {
        // Arrange
        boolean submit = false;
        IngestionRequest requestDto = new IngestionRequest();
        IngestionRequestDetailsDTO expectedResponse = new IngestionRequestDetailsDTO();
        when(bindingResult.hasErrors()).thenReturn(false);
        when(ingestionRequestDetailsService.createOrUpdateIngestionRequest(null, requestDto, submit)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<IngestionRequestDetailsDTO> responseEntity = controller.createIngestionRequest(submit, requestDto, bindingResult);

        // Assert
        assertEquals(expectedResponse, responseEntity.getBody());
        assertEquals(200, responseEntity.getStatusCodeValue());
    }


    /**
     * Tests that a valid ingestion request ID returns the corresponding request details.
     * Verifies that the response entity contains the expected response and a status code of 200.
     */
    @Test
    void testGetIngestionRequestWithValidId() {
        // Arrange
        Long ingestionRequestId = 1L;
        IngestionRequestDetailsDTO expectedResponse = new IngestionRequestDetailsDTO();
        when(ingestionRequestDetailsService.getIngestionRequestDetail(ingestionRequestId)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<IngestionRequestDetailsDTO> responseEntity = controller.getIngestionRequest(ingestionRequestId);

        // Assert
        assertEquals(expectedResponse, responseEntity.getBody());
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    /**
     * Tests that an invalid ingestion request ID returns a 404 status code.
     * Verifies that the response entity has a status code of 404 when no request details are found.
     */
    @Test
    void testGetIngestionRequestWithInvalidIdAndReturnsNotFound() {
        // Arrange
        Long ingestionRequestId = 1L;
        when(ingestionRequestDetailsService.getIngestionRequestDetail(ingestionRequestId)).thenReturn(null);

        // Act
        ResponseEntity<IngestionRequestDetailsDTO> responseEntity = controller.getIngestionRequest(ingestionRequestId);

        // Assert
        assertEquals(404, responseEntity.getStatusCodeValue());
    }

    /**
     * Tests that a valid ingestion request is successfully updated.
     * Verifies that the response entity contains the expected response and a status code of 200.
     */
    @Test
    void testUpdateIngestionRequestWithValidRequest() {
        // Arrange
        Long ingestionRequestId = 1L;
        boolean submit = true;
        IngestionRequest requestDto = new IngestionRequest();
        IngestionRequestDetailsDTO expectedResponse = new IngestionRequestDetailsDTO();
        when(bindingResult.hasErrors()).thenReturn(false);
        when(ingestionRequestDetailsService.createOrUpdateIngestionRequest(ingestionRequestId, requestDto, submit)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<IngestionRequestDetailsDTO> responseEntity = controller.updateIngestionRequest(ingestionRequestId, submit, requestDto, bindingResult);

        // Assert
        assertEquals(expectedResponse, responseEntity.getBody());
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    /**
     * Tests that a search with valid parameters returns the expected result.
     * Verifies that the response entity contains the expected response and a status code of 200.
     */
    @Test
    void testSearchIngestionRequestsWithValidParameters() {
        // Arrange
        boolean myApprovals = true;
        boolean mySubmissions = false;
        IngestionRequestStatus status = IngestionRequestStatus.PendingApproval;
        int page = 1;
        int perPage = 10;
        OrderByField orderBy = OrderByField.ingestionRequestId;
        OrderDirection orderDirection = OrderDirection.asc;
        IngestionRequestSummaryDTO expectedResponse = new IngestionRequestSummaryDTO();
        when(ingestionRequestDetailsService.searchIngestionRequests(myApprovals, mySubmissions, status, page, perPage, orderBy, orderDirection)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<IngestionRequestSummaryDTO> responseEntity = controller.searchIngestionRequests(myApprovals, mySubmissions, status, page, perPage, orderBy, orderDirection);

        // Assert
        assertEquals(expectedResponse, responseEntity.getBody());
        assertEquals(200, responseEntity.getStatusCodeValue());
    }


    /**
     * Tests that creating an ingestion request with an invalid request throws a CustomResponseStatusException.
     * Verifies that the controller throws the appropriate exception when the binding result has errors.
     */
    @Test
    void testCreateIngestionRequestWithInvalidRequestAndThrowsCustomResponseStatusException() {
        // Arrange
        boolean submit = true;
        IngestionRequest requestDto = new IngestionRequest();
        when(bindingResult.hasErrors()).thenReturn(true);

        // Act & Assert
        assertThrows(CustomResponseStatusException.class, () -> controller.createIngestionRequest(submit, requestDto, bindingResult));
    }


    /**
     * Tests that updating an ingestion request with an invalid request throws a CustomResponseStatusException.
     * Verifies that the controller throws the appropriate exception when the binding result has errors.
     */
    @Test
    void testUpdateIngestionRequestWithInvalidRequestAndThrowsCustomResponseStatusException() {
        // Arrange
        Long ingestionRequestId = 1L;
        boolean submit = true;
        IngestionRequest requestDto = new IngestionRequest();
        when(bindingResult.hasErrors()).thenReturn(true);

        // Act & Assert
        assertThrows(CustomResponseStatusException.class, () -> controller.updateIngestionRequest(ingestionRequestId, submit, requestDto, bindingResult));
    }

}
