package com.ddf.ingestion_ddf.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link OrderDirection}.
 */
public class OrderDirectionTest {

    /**
     * Test to verify that the {@code getDirection} method returns the expected directions.
     */
    @Test
    void testGetDirection() {
        // Arrange
        String[] expectedDirections = {"asc", "desc"};

        // Act & Assert
        for (int i = 0; i < expectedDirections.length; i++) {
            assertEquals(expectedDirections[i], OrderDirection.values()[i].getDirection());
        }
    }
}
