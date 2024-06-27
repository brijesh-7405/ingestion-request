package com.ddf.ingestion_ddf.controller;

import com.ddf.ingestion_ddf.exception.CustomResponseStatusException;
import com.ddf.ingestion_ddf.request.ValidationNotesRequestDTO;
import com.ddf.ingestion_ddf.response.ValidationNotesDTO;
import com.ddf.ingestion_ddf.service.ValidationNotesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;


/**
 * Test class for {@link ValidationNotesControllerImpl}.
 */
public class ValidationNotesControllerImplTest {

    @Mock
    private ValidationNotesService validationNotesService;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private ValidationNotesControllerImpl controller;

    /**
     * Set up the mocks and controller before each test.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Tests creating a note with valid data.
     */
    @Test
    void testCreateNoteWithValidNote() {
        // Arrange
        Long ingestionRequestId = 1L;
        ValidationNotesRequestDTO note = new ValidationNotesRequestDTO();
        ValidationNotesDTO expectedNote = new ValidationNotesDTO();
        mockServiceForCreateOrUpdateNote(ingestionRequestId, note, null, expectedNote);

        // Act
        ResponseEntity<ValidationNotesDTO> responseEntity = controller.createNote(ingestionRequestId, note, bindingResult);

        // Assert
        assertEquals(expectedNote, responseEntity.getBody());
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    /**
     * Tests creating a note with invalid data, expecting an exception.
     */
    @Test
    void testCreateNoteWithInvalidNoteAndThrowsException() {
        // Arrange
        Long ingestionRequestId = 1L;
        ValidationNotesRequestDTO note = new ValidationNotesRequestDTO();
        mockBindingResultForErrors(true);

        // Act & Assert
        assertThrows(CustomResponseStatusException.class, () -> {
            controller.createNote(ingestionRequestId, note, bindingResult);
        });
    }

    /**
     * Tests updating a note with valid data.
     */
    @Test
    void testUpdateNoteWithValidNote() {
        // Arrange
        Long ingestionRequestId = 1L;
        Long noteId = 1L;
        ValidationNotesRequestDTO noteDetails = new ValidationNotesRequestDTO();
        ValidationNotesDTO expectedNote = new ValidationNotesDTO();
        mockServiceForCreateOrUpdateNote(ingestionRequestId, noteDetails, noteId, expectedNote);

        // Act
        ResponseEntity<ValidationNotesDTO> responseEntity = controller.updateNote(ingestionRequestId, noteId, noteDetails, bindingResult);

        // Assert
        assertEquals(expectedNote, responseEntity.getBody());
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    /**
     * Tests updating a note with invalid data, expecting an exception.
     */
    @Test
    void testUpdateNoteWithInvalidNoteAndThrowsException() {
        // Arrange
        Long ingestionRequestId = 1L;
        Long noteId = 1L;
        ValidationNotesRequestDTO noteDetails = new ValidationNotesRequestDTO();
        mockBindingResultForErrors(true);

        // Act & Assert
        assertThrows(CustomResponseStatusException.class, () -> {
            controller.updateNote(ingestionRequestId, noteId, noteDetails, bindingResult);
        });
    }

    /**
     * Tests deleting a note with valid data.
     */
    @Test
    void testDeleteNoteWithValidNote() {
        // Arrange
        Long ingestionRequestId = 1L;
        Long noteId = 1L;
        doNothing().when(validationNotesService).deleteNote(ingestionRequestId, noteId);

        // Act
        ResponseEntity<Void> responseEntity = controller.deleteNote(ingestionRequestId, noteId);

        // Assert
        assertEquals(204, responseEntity.getStatusCodeValue());
    }

    /**
     * Mocks the service method for creating or updating a validation note.
     *
     * @param ingestionRequestId the ID of the ingestion request
     * @param note               the validation notes request DTO
     * @param noteId             the ID of the note
     * @param expectedNote       the expected validation notes DTO
     */
    private void mockServiceForCreateOrUpdateNote(Long ingestionRequestId, ValidationNotesRequestDTO note, Long noteId, ValidationNotesDTO expectedNote) {
        // Mock service method for createOrUpdateNote
        when(bindingResult.hasErrors()).thenReturn(false);
        when(validationNotesService.createOrUpdateNote(ingestionRequestId, note, noteId)).thenReturn(expectedNote);
    }

    /**
     * Mocks the binding result to simulate errors or no errors.
     *
     * @param hasErrors boolean indicating whether the binding result should simulate errors
     */
    private void mockBindingResultForErrors(boolean hasErrors) {
        // Mock binding result with errors
        when(bindingResult.hasErrors()).thenReturn(hasErrors);
    }
}
