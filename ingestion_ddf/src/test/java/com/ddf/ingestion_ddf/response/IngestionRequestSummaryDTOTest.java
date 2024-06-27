package com.ddf.ingestion_ddf.response;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit tests for {@link IngestionRequestSummaryDTO}.
 */
public class IngestionRequestSummaryDTOTest {

    List<IngestionRequestDetailsDTO> items = new ArrayList<>();

    /**
     * Tests the setting and getting of fields in the DTO.
     */
    @Test
    public void testSetAndGetFields() {
        // Arrange
        IngestionRequestSummaryDTO dto = new IngestionRequestSummaryDTO();
        int totalAll = 10;
        int totalPendingApproval = 5;
        int totalCompletedRequest = 3;
        int totalRejected = 2;

        // Act
        dto.setTotalAll(totalAll);
        dto.setTotalPendingApproval(totalPendingApproval);
        dto.setTotalCompletedRequest(totalCompletedRequest);
        dto.setTotalRejected(totalRejected);
        dto.setItems(items);

        // Assert
        assertEquals(totalAll, dto.getTotalAll());
        assertEquals(totalPendingApproval, dto.getTotalPendingApproval());
        assertEquals(totalCompletedRequest, dto.getTotalCompletedRequest());
        assertEquals(totalRejected, dto.getTotalRejected());
        assertEquals(items, dto.getItems());
    }

    /**
     * Tests the default constructor of the DTO.
     */
    @Test
    public void testConstructor() {
        // Act
        IngestionRequestSummaryDTO dto = new IngestionRequestSummaryDTO();

        // Assert
        assertNotNull(dto);
    }

    /**
     * Tests the initialization of the items list in the DTO.
     */
    @Test
    public void testListInitialization() {
        // Arrange
        IngestionRequestSummaryDTO dto = new IngestionRequestSummaryDTO();
        dto.setItems(items);

        // Act
        List<IngestionRequestDetailsDTO> items = dto.getItems();

        // Assert
        assertNotNull(items);
        assertEquals(0, items.size());
    }
}

