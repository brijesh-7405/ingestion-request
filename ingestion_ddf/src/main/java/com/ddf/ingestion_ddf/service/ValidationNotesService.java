package com.ddf.ingestion_ddf.service;

import com.ddf.ingestion_ddf.exception.IngestionRequestDetailsNotFoundException;
import com.ddf.ingestion_ddf.exception.ValidationNotesNotFoundException;
import com.ddf.ingestion_ddf.request.ValidationNotesRequestDTO;
import com.ddf.ingestion_ddf.response.ValidationNotesDTO;

/**
 * Service interface for managing validation notes.
 */
public interface ValidationNotesService {
    /**
     * Creates or updates a validation note associated with the specified ingestion request.
     *
     * @param ingestionRequestId The ID of the ingestion request.
     * @param note               The validation note to create or update.
     * @param noteId             The ID of the note to update (if updating an existing note).
     * @return The created or updated validation note DTO.
     * @throws IngestionRequestDetailsNotFoundException if the ingestion request details are not found.
     * @throws ValidationNotesNotFoundException         if the validation notes are not found.
     */
    ValidationNotesDTO createOrUpdateNote(Long ingestionRequestId, ValidationNotesRequestDTO note, Long noteId) throws IngestionRequestDetailsNotFoundException, ValidationNotesNotFoundException;

    /**
     * Deletes the validation note with the specified ID associated with the given ingestion request.
     *
     * @param ingestionRequestId The ID of the ingestion request.
     * @param noteId             The ID of the note to delete.
     * @throws IngestionRequestDetailsNotFoundException if the ingestion request details are not found.
     * @throws ValidationNotesNotFoundException         if the validation notes are not found.
     */
    void deleteNote(Long ingestionRequestId, Long noteId) throws IngestionRequestDetailsNotFoundException, ValidationNotesNotFoundException;
}