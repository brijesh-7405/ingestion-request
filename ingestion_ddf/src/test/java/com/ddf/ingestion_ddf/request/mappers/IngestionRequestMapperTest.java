package com.ddf.ingestion_ddf.request.mappers;

import com.ddf.ingestion_ddf.constants.ApiConstants;
import com.ddf.ingestion_ddf.entity.IngestionRequestDetails;
import com.ddf.ingestion_ddf.request.IngestionRequest;
import com.ddf.ingestion_ddf.util.TestDataUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class for {@link IngestionRequestMapper}.
 */
public class IngestionRequestMapperTest {
    private IngestionRequestMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new IngestionRequestMapper();
    }

    /**
     * Test mapping to IngestionRequestDetails with null ingestion details.
     */
    @Test
    void testMapToIngestionRequestDetailsWithNullIngestionDetails() {
        IngestionRequest ingestionRequest = TestDataUtil.createTestIngestionRequest();
        String createdBy = ApiConstants.CREATED_BY;
        String modifiedBy = ApiConstants.MODIFIED_BY;

        // Act
        IngestionRequestDetails result = mapper.mapToIngestionRequestDetails(ingestionRequest, createdBy, modifiedBy, null);

        // Assert
        assertNotNull(result);
    }

    /**
     * Test mapping to IngestionRequestDetails with not null ingestion details.
     */
    @Test
    void testMapToIngestionRequestDetailsWithNotNullIngestionDetails() {
        IngestionRequest ingestionRequest = TestDataUtil.createTestIngestionRequest();
        IngestionRequestDetails details = TestDataUtil.createTestIngestionRequestDetails();

        String createdBy = ApiConstants.CREATED_BY;
        String modifiedBy = ApiConstants.MODIFIED_BY;

        // Act
        IngestionRequestDetails result = mapper.mapToIngestionRequestDetails(ingestionRequest, createdBy, modifiedBy, details);

        // Assert
        assertNotNull(result);
    }
}
