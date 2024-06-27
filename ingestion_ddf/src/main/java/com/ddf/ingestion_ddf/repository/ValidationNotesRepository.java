package com.ddf.ingestion_ddf.repository;

import com.ddf.ingestion_ddf.entity.IngestionRequestDetails;
import com.ddf.ingestion_ddf.entity.ValidationNotes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for managing {@link ValidationNotes} entities.
 */
public interface ValidationNotesRepository extends JpaRepository<ValidationNotes, Long> {
    /**
     * Retrieves a list of {@link ValidationNotes} entities associated with the given {@link IngestionRequestDetails}.
     *
     * @param ingestionRequestDetails the ingestion request details to search for
     * @return a list of {@link ValidationNotes} entities associated with the given ingestion request details
     */
    List<ValidationNotes> findByIngestionRequest(IngestionRequestDetails ingestionRequestDetails);
}
