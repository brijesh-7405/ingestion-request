package com.ddf.ingestion_ddf.repository;

import com.ddf.ingestion_ddf.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link Status} entities.
 */
public interface StatusRepository extends JpaRepository<Status, Long> {

    /**
     * Retrieves a {@link Status} entity by its status name, ignoring case.
     *
     * @param statusName the status name to search for
     * @return the {@link Status} entity with the matching status name, or {@code null} if none found
     */
    Status findByStatusNameIgnoreCase(String statusName);
}
