package com.ddf.ingestion_ddf.api;

import com.ddf.ingestion_ddf.response.ApplicationReferenceTableDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Represents the controller interface for managing application reference data.
 * This interface defines endpoints for retrieving reference data related to applications.
 */
@Tag(name = "Application Reference")
public interface ApplicationReferenceTableController {
    /**
     * Retrieves all reference data related to applications.
     *
     * @return ResponseEntity containing a list of ApplicationReferenceTableDTO objects
     * representing the reference data.
     */
    @GetMapping("/application_references")
    @Operation(summary = "Get all reference data")
    ResponseEntity<List<ApplicationReferenceTableDTO>> getAllReferences();
}
