package com.ddf.ingestion_ddf.service.impl;

import com.ddf.ingestion_ddf.constants.ApiConstants;
import com.ddf.ingestion_ddf.entity.IngestionRequestDetails;
import com.ddf.ingestion_ddf.entity.ValidationNotes;
import com.ddf.ingestion_ddf.exception.IngestionRequestDetailsNotFoundException;
import com.ddf.ingestion_ddf.exception.ValidationNotesNotFoundException;
import com.ddf.ingestion_ddf.repository.IngestionRequestDetailsRepository;
import com.ddf.ingestion_ddf.repository.ValidationNotesRepository;
import com.ddf.ingestion_ddf.request.ValidationNotesRequestDTO;
import com.ddf.ingestion_ddf.response.ValidationNotesDTO;
import com.ddf.ingestion_ddf.service.ValidationNotesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link ValidationNotesService} interface.
 * This service provides methods to handle ingestion request's validation notes details.
 * It interacts with various repositories to access and manipulate data.
 *
 */
@Service
@AllArgsConstructor
@Slf4j
public class ValidationNotesServiceImpl implements ValidationNotesService {

    /**
     * Repository for managing {@link ValidationNotes} entities.
     */
    private final ValidationNotesRepository validationNotesRepository;

    /**
     * Repository for managing {@link IngestionRequestDetails} entities.
     */
    private final IngestionRequestDetailsRepository ingestionRequestDetailsRepository;

    /**
     * Creates or updates a validation note for the specified ingestion request.
     *
     * @param ingestionRequestId        The ID of the ingestion request.
     * @param validationNotesRequestDTO The DTO containing the validation note details.
     * @param noteId                    The ID of the validation note, if updating an existing note.
     * @return The DTO representation of the created or updated validation note.
     * @throws IngestionRequestDetailsNotFoundException if the ingestion request is not found.
     * @throws ValidationNotesNotFoundException if the validation note is not found.
     */
    @Override
    public ValidationNotesDTO createOrUpdateNote(Long ingestionRequestId, ValidationNotesRequestDTO validationNotesRequestDTO, Long noteId) throws IngestionRequestDetailsNotFoundException, ValidationNotesNotFoundException {
        // Retrieve the ingestion request details by ID
        IngestionRequestDetails ingestionRequestDetails = ingestionRequestDetailsRepository.findById(ingestionRequestId)
                .orElseThrow(() -> new IngestionRequestDetailsNotFoundException(ingestionRequestId.toString()));

        ValidationNotes notes;
        // As logged-in user details are not available, using static emails for createdBy and modifiedBy
        String createdBy = ApiConstants.CREATED_BY;  // Update with logged-in user email
        String modifyBy = ApiConstants.MODIFIED_BY;  // Update with logged-in user email

        // Check if the note ID is provided and exists in the repository if exists then update operation needed
        if (noteId != null) {
            notes = validationNotesRepository.findById(noteId)
                    .orElseThrow(() -> new ValidationNotesNotFoundException(noteId.toString()));
            notes.setNotesId(noteId);
            notes.setModifiedBy(modifyBy);
            log.info("Updating validation note with ID: {} for ingestion request with ID: {}", noteId, ingestionRequestId);
        } else {
            log.info("Creating validation note for ingestion request with ID: {}", ingestionRequestId);
            notes = new ValidationNotes();
            notes.setIngestionRequest(ingestionRequestDetails);
            notes.setCreatedBy(createdBy);
            notes.setModifiedBy(modifyBy);
        }
        notes.setNotes(validationNotesRequestDTO.getNotes());
        // Save the note and convert to DTO
        return convertToDto(validationNotesRepository.save(notes));
    }

    /**
     * Deletes the validation note with the specified ID for the given ingestion request.
     *
     * @param ingestionRequestId The ID of the ingestion request.
     * @param noteId             The ID of the validation note to delete.
     * @throws IngestionRequestDetailsNotFoundException if the ingestion request is not found.
     * @throws ValidationNotesNotFoundException if the validation note is not found.
     */

    @Override
    public void deleteNote(Long ingestionRequestId, Long noteId) throws IngestionRequestDetailsNotFoundException, ValidationNotesNotFoundException {
        log.info("Deleting validation note with ID: {} for ingestion request with ID: {}", noteId, ingestionRequestId);
        // Check if both ingestion request and note exist, then delete the note
        ingestionRequestDetailsRepository.findById(ingestionRequestId)
                .orElseThrow(() -> new IngestionRequestDetailsNotFoundException(ingestionRequestId.toString()));
        if (noteId != null) {
            validationNotesRepository.findById(noteId)
                    .orElseThrow(() -> new ValidationNotesNotFoundException(noteId.toString()));
            validationNotesRepository.deleteById(noteId);
        }
    }

    /**
     * Converts a ValidationNotes entity to its DTO representation.
     *
     * @param validationNotes The ValidationNotes entity to convert.
     * @return The DTO representation of the validation notes.
     */
    private ValidationNotesDTO convertToDto(ValidationNotes validationNotes) {
        ValidationNotesDTO dto = new ValidationNotesDTO();
        BeanUtils.copyProperties(validationNotes, dto);
        return dto;
    }
}
