package com.ddf.ingestion_ddf.repository;

import com.ddf.ingestion_ddf.entity.EmailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for managing {@link EmailTemplate} entities.
 */
public interface EmailTemplateRepository extends JpaRepository<EmailTemplate, Long> {

    /**
     * Retrieves an {@link EmailTemplate} entity by its template code, ignoring case.
     *
     * @param templateCode the template code to search for
     * @return an {@link Optional} containing the found {@link EmailTemplate}, or {@link Optional#empty()} if none found
     */

    Optional<EmailTemplate> findByTemplateCodeIgnoreCase(String templateCode);
}
