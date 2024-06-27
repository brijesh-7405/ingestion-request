package com.ddf.ingestion_ddf.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link IngestionStatus}.
 */
public class IngestionStatusTest {


    /**
     * Test to verify that all enum values are present.
     */
    @Test
    void testEnumValues() {
        // Arrange
        IngestionStatus[] expectedValues = {
                IngestionStatus.DRAFT,
                IngestionStatus.TRIAGE_PENDING_APPROVAL,
                IngestionStatus.APPROVED,
                IngestionStatus.REJECTED,
                IngestionStatus.INGESTION_IN_PROGRESS,
                IngestionStatus.INGESTION_COMPLETED,
                IngestionStatus.INGESTION_FAILURE
        };

        // Act & Assert
        assertEquals(expectedValues.length, IngestionStatus.values().length);
        for (int i = 0; i < expectedValues.length; i++) {
            assertEquals(expectedValues[i], IngestionStatus.values()[i]);
        }
    }

    /**
     * Test to verify the {@code toString} method of the enum.
     */
    @Test
    void testToString() {
        // Arrange
        String[] expectedDisplayNames = {
                "Draft",
                "Triage Pending Approval",
                "Approved",
                "Rejected",
                "Ingestion In-Progress",
                "Ingestion Completed",
                "Ingestion Failure"
        };

        // Act & Assert
        for (int i = 0; i < expectedDisplayNames.length; i++) {
            assertEquals(expectedDisplayNames[i], IngestionStatus.values()[i].toString());
        }
    }
}
