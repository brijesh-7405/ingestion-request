package com.ddf.ingestion_ddf.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link IngestionRequestStatus}.
 */
public class IngestionRequestStatusTest {

    /**
     * Test to verify that all enum values are present.
     */
    @Test
    void testEnumValues() {
        // Arrange
        IngestionRequestStatus[] expectedValues = {
                IngestionRequestStatus.All,
                IngestionRequestStatus.PendingApproval,
                IngestionRequestStatus.CompletedRequest,
                IngestionRequestStatus.Rejected
        };

        // Act & Assert
        assertEquals(expectedValues.length, IngestionRequestStatus.values().length);
        for (int i = 0; i < expectedValues.length; i++) {
            assertEquals(expectedValues[i], IngestionRequestStatus.values()[i]);
        }
    }
}
