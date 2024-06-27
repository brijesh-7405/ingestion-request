package com.ddf.ingestion_ddf.controller;

import com.ddf.ingestion_ddf.api.ApplicationReferenceTableController;
import com.ddf.ingestion_ddf.response.ApplicationReferenceTableDTO;
import com.ddf.ingestion_ddf.service.ApplicationReferenceTableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Implementation of the ApplicationReferenceTableController interface.
 * Handles HTTP requests related to application reference tables.
 */
@RestController
@Slf4j
public class ApplicationReferenceTableControllerImpl implements ApplicationReferenceTableController {

    /**
     * The service responsible for retrieving application reference data.
     */
    private final ApplicationReferenceTableService referenceTableService;

    /**
     * Constructs a new instance of ApplicationReferenceTableControllerImpl.
     *
     * @param referenceTableService The service responsible for retrieving application reference data.
     */
    public ApplicationReferenceTableControllerImpl(ApplicationReferenceTableService referenceTableService) {
        this.referenceTableService = referenceTableService;
    }

    /**
     * Retrieves all application reference data.
     *
     * @return ResponseEntity containing a list of application reference table DTOs.
     */
    @Override
    public ResponseEntity<List<ApplicationReferenceTableDTO>> getAllReferences() {
        log.info("Fetching all application reference data");
        return new ResponseEntity<>(referenceTableService.getAllReferences(), HttpStatus.OK);
    }

}