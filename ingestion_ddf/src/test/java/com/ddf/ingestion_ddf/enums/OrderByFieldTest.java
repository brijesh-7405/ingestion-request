package com.ddf.ingestion_ddf.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link OrderByField}.
 */
public class OrderByFieldTest {

    /**
     * Test to verify that the {@code getFieldName} method returns the expected field names.
     */
    @Test
    void testGetFieldName() {
        // Arrange
        String[] expectedFieldNames = {
                "ingestionRequestId",
                "datasetDetails.datasetRequiredForRef",
                "requestedByName",
                "datasetDetails.analysisInitDt",
                "datasetDetails.analysisEndDt",
                "modifiedDate",
                "datasetDetails.datasetRoleDetails.role",
                "requesterEmail",
                "requestStatusDetails.status.statusName"
        };

        // Act & Assert
        for (int i = 0; i < expectedFieldNames.length; i++) {
            assertEquals(expectedFieldNames[i], OrderByField.values()[i].getFieldName());
        }
    }
}
