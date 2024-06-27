package com.ddf.ingestion_ddf.response.mappers;

import com.ddf.ingestion_ddf.entity.IngestionRequestDetails;
import com.ddf.ingestion_ddf.entity.RequestStatusDetails;
import com.ddf.ingestion_ddf.entity.Status;
import com.ddf.ingestion_ddf.repository.ValidationNotesRepository;
import com.ddf.ingestion_ddf.response.IngestionRequestDetailsDTO;
import com.ddf.ingestion_ddf.util.TestDataUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link IngestionDetailsResponseMapper}.
 */
public class IngestionDetailsResponseMapperTest {


    @Mock
    private ValidationNotesRepository validationNotesRepository;

    @InjectMocks
    private IngestionDetailsResponseMapper mapper;

    /**
     * Sets up the mock objects before each test case.
     */
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case to verify mapping to IngestionRequestDetailsDTO.
     */
    @Test
    public void testMapToIngestionRequestDetailsDTO() {
        // Arrange
        IngestionRequestDetails ingestionRequestDetails = new IngestionRequestDetails();
        IngestionRequestDetailsDTO expectedDto = new IngestionRequestDetailsDTO();
        expectedDto.setNotes(new ArrayList<>());
        when(validationNotesRepository.findByIngestionRequest(ingestionRequestDetails)).thenReturn(new ArrayList<>());

        // Act
        IngestionRequestDetailsDTO result = mapper.mapToIngestionRequestDetailsDTO(ingestionRequestDetails);

        // Assert
        assertEquals(expectedDto, result);
    }

    /**
     * Test case to verify mapping to IngestionRequestDetailsDTO with dataset details and technical status.
     */
    @Test
    public void testMapToIngestionRequestDetailsDTOWithDatasetDetailsAndTechnicalStatus() {
        // Arrange
        IngestionRequestDetails ingestionRequestDetails = TestDataUtil.createTestIngestionRequestDetails();
        IngestionRequestDetailsDTO expectedDto = TestDataUtil.createTestIngestionRequestDetailsDTO();
        expectedDto.setNotes(new ArrayList<>());
        when(validationNotesRepository.findByIngestionRequest(ingestionRequestDetails)).thenReturn(new ArrayList<>());

        // Act
        IngestionRequestDetailsDTO result = mapper.mapToIngestionRequestDetailsDTO(ingestionRequestDetails);

        // Assert
        assertEquals(expectedDto.toString(), result.toString());
    }

    /**
     * Test case to verify mapping to IngestionRequestDetailsDTO when request status details is null.
     */
    @Test
    public void testMapToIngestionRequestDetailsDTOWithRequestStatusDetailsIsNull() {
        // Arrange
        IngestionRequestDetails ingestionRequestDetails = TestDataUtil.createTestIngestionRequestDetails();
        ingestionRequestDetails.setRequestStatusDetails(null);
        IngestionRequestDetailsDTO expectedDto = TestDataUtil.createTestIngestionRequestDetailsDTO();
        expectedDto.setActiveRequestStatus(null);
        expectedDto.setNotes(new ArrayList<>());
        when(validationNotesRepository.findByIngestionRequest(ingestionRequestDetails)).thenReturn(new ArrayList<>());

        // Act
        IngestionRequestDetailsDTO result = mapper.mapToIngestionRequestDetailsDTO(ingestionRequestDetails);

        // Assert
        assertEquals(expectedDto.toString(), result.toString());
    }

    /**
     * Test case to verify mapping to IngestionRequestDetailsDTO when active status is null.
     */
    @Test
    public void testMapToIngestionRequestDetailsDTOWithActiveStatusIsNull() {
        // Arrange
        IngestionRequestDetails ingestionRequestDetails = TestDataUtil.createTestIngestionRequestDetails();
        RequestStatusDetails requestStatusDetails2 = new RequestStatusDetails();
        Status status2 = new Status();
        status2.setStatusCode("status01");
        status2.setStatusName("Approved");
        requestStatusDetails2.setStatus(status2);
        requestStatusDetails2.setActiveFlag(false);
        ingestionRequestDetails.setRequestStatusDetails(List.of(requestStatusDetails2));
        IngestionRequestDetailsDTO expectedDto = TestDataUtil.createTestIngestionRequestDetailsDTO();
        expectedDto.setActiveRequestStatus(null);
        expectedDto.setNotes(new ArrayList<>());
        when(validationNotesRepository.findByIngestionRequest(ingestionRequestDetails)).thenReturn(new ArrayList<>());

        // Act
        IngestionRequestDetailsDTO result = mapper.mapToIngestionRequestDetailsDTO(ingestionRequestDetails);

        // Assert
        assertEquals(expectedDto.toString(), result.toString());
    }
}
