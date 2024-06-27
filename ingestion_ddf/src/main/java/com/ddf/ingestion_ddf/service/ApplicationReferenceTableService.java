package com.ddf.ingestion_ddf.service;

import com.ddf.ingestion_ddf.response.ApplicationReferenceTableDTO;

import java.util.List;

/**
 * Interface for retrieving all application reference tables.
 */
public interface ApplicationReferenceTableService {

    /**
     * Retrieves all application reference tables.
     *
     * @return A list of ApplicationReferenceTableDTO containing all application reference tables.
     */
    List<ApplicationReferenceTableDTO> getAllReferences();
}
