package com.ddf.ingestion_ddf.repository;

import com.ddf.ingestion_ddf.entity.RequestStatusDetails;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link RequestStatusDetails} entities.
 */
public interface RequestStatusDetailsRepository extends JpaRepository<RequestStatusDetails, Long> {
}
