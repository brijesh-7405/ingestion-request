package com.ddf.ingestion_ddf.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class for {@link ApplicationReferenceTableDTO}.
 */
public class ApplicationReferenceTableDTOTest {

    /**
     * Test to verify setting and getting fields of the DTO.
     */
    @Test
    public void testSetAndGetFields() {
        // Arrange
        ApplicationReferenceTableDTO dto = new ApplicationReferenceTableDTO();
        String referenceData = "Data";
        String referenceDataType = "Type";
        Long referenceOrder = 1L;

        // Act
        dto.setReferenceData(referenceData);
        dto.setReferenceDataType(referenceDataType);
        dto.setReferenceOrder(referenceOrder);

        // Assert
        assertEquals(referenceData, dto.getReferenceData());
        assertEquals(referenceDataType, dto.getReferenceDataType());
        assertEquals(referenceOrder, dto.getReferenceOrder());
    }

    /**
     * Test to verify the constructor.
     */
    @Test
    public void testConstructor() {
        // Act
        ApplicationReferenceTableDTO dto = new ApplicationReferenceTableDTO();

        // Assert
        assertNotNull(dto);
    }
}
