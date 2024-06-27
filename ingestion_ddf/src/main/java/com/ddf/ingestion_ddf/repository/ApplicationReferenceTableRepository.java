package com.ddf.ingestion_ddf.repository;

import com.ddf.ingestion_ddf.entity.ApplicationReferenceTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for managing {@link ApplicationReferenceTable} entities.
 */
public interface ApplicationReferenceTableRepository extends JpaRepository<ApplicationReferenceTable, Long> {
    /**
     * Retrieves all {@link ApplicationReferenceTable} entities ordered by the {@code referenceOrder} field.
     *
     * @return a list of {@link ApplicationReferenceTable} entities ordered by {@code referenceOrder}.
     */
    List<ApplicationReferenceTable> findAllByOrderByReferenceOrder();
}
