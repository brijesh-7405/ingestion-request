package com.ddf.ingestion_ddf.service.impl;

import com.ddf.ingestion_ddf.repository.ApplicationReferenceTableRepository;
import com.ddf.ingestion_ddf.entity.ApplicationReferenceTable;
import com.ddf.ingestion_ddf.response.ApplicationReferenceTableDTO;
import com.ddf.ingestion_ddf.service.ApplicationReferenceTableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link ApplicationReferenceTableService} interface.
 * This service provides methods to retrieve application reference table data.
 * It interacts with the {@link ApplicationReferenceTableRepository} to access the data layer.
 *
 */
@Service
@Slf4j
public class ApplicationReferenceTableServiceImpl implements ApplicationReferenceTableService {

    /**
     * Repository for managing application reference table data.
     */
    private final ApplicationReferenceTableRepository referenceTableRepository;

    /**
     * Constructs a new {@code ApplicationReferenceTableServiceImpl} with the specified repository.
     *
     * @param referenceTableRepository the repository for application reference table data
     */
    public ApplicationReferenceTableServiceImpl(ApplicationReferenceTableRepository referenceTableRepository) {
        this.referenceTableRepository = referenceTableRepository;
    }

    /**
     * Retrieves all application reference table entries sorted by reference order.
     * Method retrieves all reference table entries and returns them as a list of DTOs.
     *
     * @return a list of {@link ApplicationReferenceTableDTO} containing the reference table data
     */
    @Override
    public List<ApplicationReferenceTableDTO> getAllReferences() {
        log.info("Retrieving all application reference table entries.");
        try {
            // Retrieve all application reference table entries from the repository
            List<ApplicationReferenceTable> references = referenceTableRepository.findAllByOrderByReferenceOrder();

            if (references == null || references.isEmpty()) {
                log.warn("No application reference table entries found.");
                return Collections.emptyList();
            } else {
                // Map each reference entity to its corresponding DTO
                // Collect the DTOs into a list and return
                return references.stream()
                        .map(this::convertToDto)
                        .collect(Collectors.toList());
            }
        } catch (Exception e) {
            log.error("An error occurred while retrieving and mapping application reference table entries: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    /**
     * Converts an {@link ApplicationReferenceTable} entity to its corresponding DTO.
     * Method converts the given reference entity to a DTO for representation.
     *
     * @param reference the reference table entity to convert
     * @return the DTO representation of the reference table entity
     */
    private ApplicationReferenceTableDTO convertToDto(ApplicationReferenceTable reference) {
        ApplicationReferenceTableDTO dto = new ApplicationReferenceTableDTO();
        // Copy properties from the entity to the DTO
        BeanUtils.copyProperties(reference, dto);
        return dto;
    }

}