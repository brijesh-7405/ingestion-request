package com.ddf.ingestion_ddf.service.impl;

import com.ddf.ingestion_ddf.entity.IngestionRequestDetails;
import com.ddf.ingestion_ddf.entity.ValidationNotes;
import com.ddf.ingestion_ddf.exception.IngestionRequestDetailsNotFoundException;
import com.ddf.ingestion_ddf.exception.ValidationNotesNotFoundException;
import com.ddf.ingestion_ddf.repository.IngestionRequestDetailsRepository;
import com.ddf.ingestion_ddf.repository.ValidationNotesRepository;
import com.ddf.ingestion_ddf.request.ValidationNotesRequestDTO;
import com.ddf.ingestion_ddf.response.ValidationNotesDTO;
import com.ddf.ingestion_ddf.util.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;

/**
 * Unit tests for the {@link ValidationNotesServiceImpl} class.
 * This class tests the functionality of creating, updating, and deleting validation notes.
 */
@ExtendWith(MockitoExtension.class)
public class ValidationNotesServiceImplTest {

    @Mock
    private ValidationNotesRepository validationNotesRepository;

    @Mock
    private IngestionRequestDetailsRepository ingestionRequestDetailsRepository;

    @InjectMocks
    private ValidationNotesServiceImpl validationNotesService;

    /**
     * Tests the creation of a new validation note.
     * The method should create a new note and return it.
     */
    @Test
    void testCreateOrUpdateNoteWithCreateNewNote() {
        // Mock data
        Long ingestionRequestId = 1L;
        Long noteId = null;
        String noteContent = "Test notes";
        ValidationNotesRequestDTO requestDTO = new ValidationNotesRequestDTO();
        requestDTO.setNotes(noteContent);
        IngestionRequestDetails ingestionRequestDetails = TestDataUtil.createTestIngestionRequestDetails();

        // Mock repositories
        when(ingestionRequestDetailsRepository.findById(ingestionRequestId))
                .thenReturn(Optional.of(ingestionRequestDetails));
        when(validationNotesRepository.save(any(ValidationNotes.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Call the service method
        ValidationNotesDTO result = validationNotesService.createOrUpdateNote(ingestionRequestId, requestDTO, noteId);

        // Verify the result
        assertEquals(noteContent, result.getNotes());
    }

    /**
     * Tests the update of an existing validation note.
     * The method should update the existing note and return it.
     */
    @Test
    void testCreateOrUpdateNoteWithUpdateExistingNote() {
        // Mock data
        Long ingestionRequestId = 1L;
        Long noteId = 1L;
        String noteContent = "Updated notes";
        ValidationNotesRequestDTO requestDTO = new ValidationNotesRequestDTO();
        requestDTO.setNotes(noteContent);
        IngestionRequestDetails ingestionRequestDetails = TestDataUtil.createTestIngestionRequestDetails();
        ValidationNotes existingNote = new ValidationNotes();
        existingNote.setNotesId(noteId);

        // Mock repositories
        when(ingestionRequestDetailsRepository.findById(ingestionRequestId))
                .thenReturn(Optional.of(ingestionRequestDetails));
        when(validationNotesRepository.findById(noteId))
                .thenReturn(Optional.of(existingNote));
        when(validationNotesRepository.save(any(ValidationNotes.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Call the service method
        ValidationNotesDTO result = validationNotesService.createOrUpdateNote(ingestionRequestId, requestDTO, noteId);

        // Verify the result
        assertEquals(noteContent, result.getNotes());
    }

    /**
     * Tests the scenario where the note to be updated does not exist.
     * The method should throw a ValidationNotesNotFoundException.
     */
    @Test
    void testUpdateNoteWithNoteNotExists() {
        // Mock data
        Long ingestionRequestId = 1L;
        Long noteId = 1L;
        ValidationNotesRequestDTO validationNotesRequestDTO = new ValidationNotesRequestDTO();
        validationNotesRequestDTO.setNotes("Test note");

        // Mock repositories
        when(ingestionRequestDetailsRepository.findById(ingestionRequestId))
                .thenReturn(Optional.of(new IngestionRequestDetails()));
        when(validationNotesRepository.findById(noteId))
                .thenReturn(Optional.empty());

        // Call the service method and verify that it throws an exception
        assertThrows(ValidationNotesNotFoundException.class, () -> {
            validationNotesService.createOrUpdateNote(ingestionRequestId, validationNotesRequestDTO, noteId);
        });
    }

    /**
     * Tests the scenario where the ingestion request does not exist.
     * The method should throw an IngestionRequestDetailsNotFoundException.
     */
    @Test
    void testCreateOrUpdateNoteWithIngestionRequestNotFound() {
        // Mock data
        Long ingestionRequestId = 1L;
        Long noteId = null;
        ValidationNotesRequestDTO requestDTO = new ValidationNotesRequestDTO();

        // Mock repositories
        when(ingestionRequestDetailsRepository.findById(ingestionRequestId))
                .thenReturn(Optional.empty());

        // Call the service method and verify that it throws an exception
        assertThrows(IngestionRequestDetailsNotFoundException.class,
                () -> validationNotesService.createOrUpdateNote(ingestionRequestId, requestDTO, noteId));
    }

    /**
     * Tests the deletion of an existing validation note.
     * The method should delete the note and verify that deleteById was called once.
     */
    @Test
    void testDeleteNoteWithNoteExists() {
        // Mock data
        Long ingestionRequestId = 1L;
        Long noteId = 1L;
        ValidationNotes existingNote = new ValidationNotes();

        // Mock repositories
        when(ingestionRequestDetailsRepository.findById(ingestionRequestId))
                .thenReturn(Optional.of(new IngestionRequestDetails()));
        when(validationNotesRepository.findById(noteId))
                .thenReturn(Optional.of(existingNote));

        // Call the service method
        validationNotesService.deleteNote(ingestionRequestId, noteId);

        // Verify that deleteById was called once
        verify(validationNotesRepository, times(1)).deleteById(noteId);
    }

    /**
     * Tests the scenario where the note to be deleted does not exist.
     * The method should throw a ValidationNotesNotFoundException.
     */
    @Test
    void testDeleteNoteWithNoteDoesNotExist() {
        // Mock data
        Long ingestionRequestId = 1L;
        Long noteId = 1L;

        // Mock repositories
        when(ingestionRequestDetailsRepository.findById(ingestionRequestId))
                .thenReturn(Optional.of(new IngestionRequestDetails()));
        when(validationNotesRepository.findById(noteId))
                .thenReturn(Optional.empty());

        // Call the service method and verify that it throws an exception
        assertThrows(ValidationNotesNotFoundException.class, () -> {
            validationNotesService.deleteNote(ingestionRequestId, noteId);
        });
    }

    /**
     * Tests the scenario where the ingestion request does not exist.
     * The method should throw an IngestionRequestDetailsNotFoundException.
     */
    @Test
    void testDeleteNoteWithIngestionRequestNotExists() {
        // Mock data
        Long ingestionRequestId = 1L;
        Long noteId = 1L;

        // Mock repository to return empty Optional for ingestion request
        when(ingestionRequestDetailsRepository.findById(ingestionRequestId))
                .thenReturn(Optional.empty());

        // Call the service method and verify that it throws an exception
        assertThrows(IngestionRequestDetailsNotFoundException.class, () -> {
            validationNotesService.deleteNote(ingestionRequestId, noteId);
        });
    }

}
