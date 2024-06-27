package com.ddf.ingestion_ddf.response;

import com.ddf.ingestion_ddf.entity.ValidationNotes;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit tests for {@link IngestionRequestDetailsDTO}.
 */
public class IngestionRequestDetailsDTOTest {

    /**
     * Tests the setting and getting of fields in the DTO.
     */
    @Test
    public void testSetAndGetFields() {
        // Arrange
        IngestionRequestDetailsDTO dto = new IngestionRequestDetailsDTO();
        Long ingestionRequestId = 1L;
        RequestStatusDetailsDTO activeRequestStatus = new RequestStatusDetailsDTO();
        String existingDataLocationIdentified = "Data location";
        String createdBy = "User";
        Date createdDate = new Date();
        String modifiedBy = "User";
        Date modifiedDate = new Date();
        List<ValidationNotes> notes = new ArrayList<>();

        // Act
        dto.setIngestionRequestId(ingestionRequestId);
        dto.setActiveRequestStatus(activeRequestStatus);
        dto.setExistingDataLocationIdentified(existingDataLocationIdentified);
        dto.setNotes(notes);
        dto.setCreatedBy(createdBy);
        dto.setCreatedDate(createdDate);
        dto.setModifiedBy(modifiedBy);
        dto.setModifiedDate(modifiedDate);

        // Assert
        assertEquals(ingestionRequestId, dto.getIngestionRequestId());
        assertEquals(activeRequestStatus, dto.getActiveRequestStatus());
        assertEquals(existingDataLocationIdentified, dto.getExistingDataLocationIdentified());
        assertEquals(notes, dto.getNotes());
        assertEquals(createdBy, dto.getCreatedBy());
        assertEquals(createdDate, dto.getCreatedDate());
        assertEquals(modifiedBy, dto.getModifiedBy());
        assertEquals(modifiedDate, dto.getModifiedDate());
    }

    /**
     * Tests the default constructor of the DTO.
     */
    @Test
    public void testConstructor() {
        // Act
        IngestionRequestDetailsDTO dto = new IngestionRequestDetailsDTO();

        // Assert
        assertNotNull(dto);
    }

    /**
     * Tests the initialization of the notes list in the DTO.
     */
    @Test
    public void testListInitialization() {
        IngestionRequestDetailsDTO dto = new IngestionRequestDetailsDTO();
        List<ValidationNotes> notes = new ArrayList<>();
        dto.setNotes(notes);
        List<ValidationNotes> notes1 = dto.getNotes();

        // Assert
        assertNotNull(notes);
        assertEquals(0, notes1.size());
    }
}

