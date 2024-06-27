package com.ddf.ingestion_ddf.service.impl;

import com.ddf.ingestion_ddf.entity.ApplicationReferenceTable;
import com.ddf.ingestion_ddf.repository.ApplicationReferenceTableRepository;
import com.ddf.ingestion_ddf.response.ApplicationReferenceTableDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link ApplicationReferenceTableServiceImpl}.
 */
@ExtendWith(MockitoExtension.class)
public class ApplicationReferenceTableServiceImplTest {

    @Mock
    private ApplicationReferenceTableRepository referenceTableRepository;

    @InjectMocks
    private ApplicationReferenceTableServiceImpl referenceTableService;

    private ApplicationReferenceTable reference1;
    private ApplicationReferenceTable reference2;

    /**
     * Initializes test data before each test case.
     */
    @BeforeEach
    public void setUp() {
        reference1 = new ApplicationReferenceTable();
        reference1.setReferenceId(1L);
        reference1.setReferenceData("Other");
        reference1.setReferenceDataType("usage_restrictions");
        reference1.setReferenceOrder(2L);

        reference2 = new ApplicationReferenceTable();
        reference2.setReferenceId(2L);
        reference2.setReferenceData("Confidential");
        reference2.setReferenceDataType("information_classification_type");
        reference2.setReferenceOrder(1L);
    }

    /**
     * Tests the retrieval of all references.
     */
    @Test
    public void testGetAllReferences() {
        List<ApplicationReferenceTable> references = Arrays.asList(reference1, reference2);
        when(referenceTableRepository.findAllByOrderByReferenceOrder()).thenReturn(references);

        List<ApplicationReferenceTableDTO> result = referenceTableService.getAllReferences();

        assertThat(result).hasSize(2);
    }

    /**
     * Tests the retrieval of all references when the list is empty.
     */
    @Test
    public void testGetAllReferencesWithEmptyList() {
        when(referenceTableRepository.findAllByOrderByReferenceOrder()).thenReturn(null);

        List<ApplicationReferenceTableDTO> result = referenceTableService.getAllReferences();

        assertThat(result).hasSize(0);
    }


    /**
     * Tests the retrieval of all references when an exception occurs.
     */
    @Test
    public void testGetAllReferencesWithException() {
        doThrow(new RuntimeException("Database error")).when(referenceTableRepository).findAllByOrderByReferenceOrder();

        List<ApplicationReferenceTableDTO> result = referenceTableService.getAllReferences();

        assertThat(result).hasSize(0);
    }

}
