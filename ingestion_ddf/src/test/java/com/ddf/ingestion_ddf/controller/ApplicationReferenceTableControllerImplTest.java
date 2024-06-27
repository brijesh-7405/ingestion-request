package com.ddf.ingestion_ddf.controller;

import com.ddf.ingestion_ddf.response.ApplicationReferenceTableDTO;
import com.ddf.ingestion_ddf.service.ApplicationReferenceTableService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

/**
 * Test class for {@link ApplicationReferenceTableControllerImpl}.
 */
public class ApplicationReferenceTableControllerImplTest {

    @Mock
    private ApplicationReferenceTableService referenceTableService;

    @InjectMocks
    private ApplicationReferenceTableControllerImpl controller;

    /**
     * Setup method to initialize mocks.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test for retrieving all references.
     */
    @Test
    void testGetAllReferences() {
        // Arrange
        List<ApplicationReferenceTableDTO> dummyReferences = createDummyReferences();
        when(referenceTableService.getAllReferences()).thenReturn(dummyReferences);

        // Act
        ResponseEntity<List<ApplicationReferenceTableDTO>> responseEntity = controller.getAllReferences();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(dummyReferences, responseEntity.getBody());
        verify(referenceTableService, times(1)).getAllReferences();
    }

    /**
     * Helper method to create dummy reference data for testing.
     *
     * @return List of dummy ApplicationReferenceTableDTO objects
     */
    private List<ApplicationReferenceTableDTO> createDummyReferences() {
        List<ApplicationReferenceTableDTO> references = new ArrayList<>();
        ApplicationReferenceTableDTO reference1 = new ApplicationReferenceTableDTO();
        reference1.setReferenceData("Data1");
        reference1.setReferenceDataType("Type1");
        reference1.setReferenceOrder(1L);
        references.add(reference1);

        ApplicationReferenceTableDTO reference2 = new ApplicationReferenceTableDTO();
        reference2.setReferenceData("Data2");
        reference2.setReferenceDataType("Type2");
        reference2.setReferenceOrder(2L);
        references.add(reference2);

        return references;
    }
}
