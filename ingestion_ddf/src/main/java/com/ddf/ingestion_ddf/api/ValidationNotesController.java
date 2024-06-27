package com.ddf.ingestion_ddf.api;

import com.ddf.ingestion_ddf.exception.CustomResponseStatusException;
import com.ddf.ingestion_ddf.request.ValidationNotesRequestDTO;
import com.ddf.ingestion_ddf.response.ValidationNotesDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

/**
 * Controller interface for managing validation notes related to ingestion requests.
 * Defines endpoints for creating, updating, and deleting validation notes.
 */
@RequestMapping("/ingestion_requests")
@Tag(name = "Ingestion Request Note")
public interface ValidationNotesController {

    /**
     * Creates a new validation note for a specific ingestion request.
     *
     * @param ingestionRequestId The ID of the ingestion request to add the note to.
     * @param note               The validation note to create.
     * @param bindingResult      The result of request data validation.
     * @return ResponseEntity containing the details of the created validation note.
     * @throws CustomResponseStatusException if there is a custom response status exception.
     */
    @PostMapping("/{ingestion_request_id}/notes")
    @Operation(summary = "Create a new Validation Note")
    ResponseEntity<ValidationNotesDTO> createNote(@PathVariable("ingestion_request_id") Long ingestionRequestId,
                                                  @Valid @RequestBody ValidationNotesRequestDTO note,
                                                  BindingResult bindingResult) throws CustomResponseStatusException;

    /**
     * Updates an existing validation note associated with a specific ingestion request.
     *
     * @param ingestionRequestId The ID of the ingestion request containing the note to update.
     * @param noteId             The ID of the note to update.
     * @param noteDetails        The updated details of the validation note.
     * @param bindingResult      The result of request data validation.
     * @return ResponseEntity containing the details of the updated validation note.
     * @throws CustomResponseStatusException if there is a custom response status exception.
     */
    @PutMapping("/{ingestion_request_id}/notes/{note_id}")
    @Operation(summary = "Update a Validation Note")
    ResponseEntity<ValidationNotesDTO> updateNote(@PathVariable("ingestion_request_id") Long ingestionRequestId,
                                                  @PathVariable("note_id") Long noteId,
                                                  @Valid @RequestBody ValidationNotesRequestDTO noteDetails,
                                                  BindingResult bindingResult) throws CustomResponseStatusException;

    /**
     * Deletes a validation note associated with a specific ingestion request.
     *
     * @param ingestionRequestId The ID of the ingestion request containing the note to delete.
     * @param noteId             The ID of the note to delete.
     * @return ResponseEntity indicating the success of the deletion operation.
     */
    @DeleteMapping("/{ingestion_request_id}/notes/{note_id}")
    @Operation(summary = "Delete a Validation Note")
    ResponseEntity<Void> deleteNote(@PathVariable("ingestion_request_id") Long ingestionRequestId,
                                    @PathVariable("note_id") Long noteId);
}
