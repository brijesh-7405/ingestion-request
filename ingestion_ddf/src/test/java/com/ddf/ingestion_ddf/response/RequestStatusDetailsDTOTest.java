package com.ddf.ingestion_ddf.response;

import com.ddf.ingestion_ddf.entity.Status;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit tests for {@link RequestStatusDetailsDTO}.
 */
public class RequestStatusDetailsDTOTest {

    /**
     * Tests the setting and getting of fields in the DTO.
     */
    @Test
    public void testSetAndGetFields() {
        // Arrange
        RequestStatusDetailsDTO dto = new RequestStatusDetailsDTO();
        Long requestStatusId = 1L;
        Long ingestionRequestId = 100L;
        String decisionByName = "John Doe";
        String decisionByMudid = "john.doe";
        String decisionByEmail = "john.doe@example.com";
        Date decisionDate = new Date();
        String decisionComments = "Decision comments";
        String rejectionReason = "Rejection reason";
        Boolean activeFlag = true;
        Status status = new Status();

        // Act
        dto.setRequestStatusId(requestStatusId);
        dto.setIngestionRequestId(ingestionRequestId);
        dto.setDecisionByName(decisionByName);
        dto.setDecisionByMudid(decisionByMudid);
        dto.setDecisionByEmail(decisionByEmail);
        dto.setDecisionDate(decisionDate);
        dto.setDecisionComments(decisionComments);
        dto.setRejectionReason(rejectionReason);
        dto.setActiveFlag(activeFlag);
        dto.setStatus(status);

        // Assert
        assertEquals(requestStatusId, dto.getRequestStatusId());
        assertEquals(ingestionRequestId, dto.getIngestionRequestId());
        assertEquals(decisionByName, dto.getDecisionByName());
        assertEquals(decisionByMudid, dto.getDecisionByMudid());
        assertEquals(decisionByEmail, dto.getDecisionByEmail());
        assertEquals(decisionDate, dto.getDecisionDate());
        assertEquals(decisionComments, dto.getDecisionComments());
        assertEquals(rejectionReason, dto.getRejectionReason());
        assertEquals(activeFlag, dto.getActiveFlag());
        assertEquals(status, dto.getStatus());
    }

    /**
     * Tests the default constructor of the DTO.
     */
    @Test
    public void testConstructor() {
        // Act
        RequestStatusDetailsDTO dto = new RequestStatusDetailsDTO();

        // Assert
        assertNotNull(dto);
    }
}
