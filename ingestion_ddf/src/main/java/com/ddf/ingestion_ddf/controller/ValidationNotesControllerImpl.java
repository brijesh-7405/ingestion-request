package com.ddf.ingestion_ddf.controller;

import com.ddf.ingestion_ddf.api.ValidationNotesController;
import com.ddf.ingestion_ddf.exception.CustomResponseStatusException;
import com.ddf.ingestion_ddf.request.ValidationNotesRequestDTO;
import com.ddf.ingestion_ddf.response.ValidationNotesDTO;
import com.ddf.ingestion_ddf.service.ValidationNotesService;
import com.ddf.ingestion_ddf.validators.ApiTemplateValidators;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;


/**
 * Implementation of the ValidationNotesController interface.
 * Handles HTTP requests related to validation notes for ingestion requests.
 */
@RestController
@Slf4j
public class ValidationNotesControllerImpl implements ValidationNotesController {

    /**
     * The service responsible for handling validation notes.
     */
    private final ValidationNotesService validationNotesService;

    /**
     * Constructs a new instance of ValidationNotesControllerImpl.
     *
     * @param validationNotesService The service responsible for handling validation notes.
     */
    public ValidationNotesControllerImpl(ValidationNotesService validationNotesService) {
        this.validationNotesService = validationNotesService;
    }

    /**
     * Creates a new validation note for an ingestion request.
     *
     * @param ingestionRequestId The ID of the ingestion request.
     * @param note               The details of the note to be created.
     * @param bindingResult      Binding result for validation.
     * @return ResponseEntity containing the created validation note.
     * @throws CustomResponseStatusException If there are validation errors.
     */
    @Override
    public ResponseEntity<ValidationNotesDTO> createNote(Long ingestionRequestId,
                                                         ValidationNotesRequestDTO note,
                                                         BindingResult bindingResult) throws CustomResponseStatusException {
        if (bindingResult.hasErrors()) {
            log.error("Error occurred while creating validation note for ingestion request with ID: {}", ingestionRequestId);
            throw new CustomResponseStatusException(ApiTemplateValidators.createErrorMessage(bindingResult));
        }
        log.info("Creating validation note for ingestion request with ID: {}", ingestionRequestId);
        return ResponseEntity.ok(validationNotesService.createOrUpdateNote(ingestionRequestId, note, null));
    }

    /**
     * Updates an existing validation note for an ingestion request.
     *
     * @param ingestionRequestId The ID of the ingestion request.
     * @param noteId             The ID of the note to be updated.
     * @param noteDetails        The updated details of the note.
     * @param bindingResult      Binding result for validation.
     * @return ResponseEntity containing the updated validation note.
     * @throws CustomResponseStatusException If there are validation errors.
     */
    @Override
    public ResponseEntity<ValidationNotesDTO> updateNote(Long ingestionRequestId,
                                                         Long noteId,
                                                         ValidationNotesRequestDTO noteDetails,
                                                         BindingResult bindingResult) throws CustomResponseStatusException {
        if (bindingResult.hasErrors()) {
            log.error("Error occurred while updating validation note with ID {} for ingestion request with ID: {}", noteId, ingestionRequestId);
            throw new CustomResponseStatusException(ApiTemplateValidators.createErrorMessage(bindingResult));
        }
        log.info("Updating validation note with ID {} for ingestion request with ID: {}", noteId, ingestionRequestId);
        return ResponseEntity.ok(validationNotesService.createOrUpdateNote(ingestionRequestId, noteDetails, noteId));
    }

    /**
     * Deletes a validation note for an ingestion request.
     *
     * @param ingestionRequestId The ID of the ingestion request.
     * @param noteId             The ID of the note to be deleted.
     * @return ResponseEntity with no content.
     */
    @Override
    public ResponseEntity<Void> deleteNote(Long ingestionRequestId,
                                           Long noteId) {
        log.info("Deleting validation note with ID {} for ingestion request with ID: {}", noteId, ingestionRequestId);
        validationNotesService.deleteNote(ingestionRequestId, noteId);
        return ResponseEntity.noContent().build();
    }
}