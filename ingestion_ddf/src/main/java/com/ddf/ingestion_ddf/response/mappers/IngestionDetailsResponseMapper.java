package com.ddf.ingestion_ddf.response.mappers;

import com.ddf.ingestion_ddf.constants.ApiConstants;
import com.ddf.ingestion_ddf.entity.DatasetDetails;
import com.ddf.ingestion_ddf.entity.IngestionRequestDetails;
import com.ddf.ingestion_ddf.entity.TechnicalDetails;
import com.ddf.ingestion_ddf.entity.DatasetUserUsageRestriction;
import com.ddf.ingestion_ddf.entity.DatasetRoleDetails;
import com.ddf.ingestion_ddf.entity.DatasetDataCategory;
import com.ddf.ingestion_ddf.entity.DatasetStudy;
import com.ddf.ingestion_ddf.entity.DatasetTherapy;
import com.ddf.ingestion_ddf.entity.DatasetTechAndAssay;
import com.ddf.ingestion_ddf.entity.DatasetIndication;
import com.ddf.ingestion_ddf.entity.RequestStatusDetails;

import com.ddf.ingestion_ddf.repository.ValidationNotesRepository;
import com.ddf.ingestion_ddf.response.IngestionRequestDetailsDTO;
import com.ddf.ingestion_ddf.response.RequestStatusDetailsDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Mapper class to convert {@link IngestionRequestDetails} entities into their corresponding
 * Response Data Transfer Objects (DTOs).
 * <p>
 * This class provides methods to map data from {@link IngestionRequestDetails} to
 * {@link IngestionRequestDetailsDTO}, including mapping nested entities such as
 * {@link DatasetDetails}, {@link TechnicalDetails}, and other related entities.
 * </p>
 * <p>
 * The class uses Spring's {@link BeanUtils} for copying properties and logs operations using SLF4J.
 * It also interacts with a {@link ValidationNotesRepository} to retrieve validation notes.
 * </p>
 */
@Component
@AllArgsConstructor
@Slf4j
public class IngestionDetailsResponseMapper {

    /**
     * Repository for managing validation notes related to ingestion requests.
     */
    private final ValidationNotesRepository validationNotesRepository;

    /**
     * Converts an IngestionRequestDetails entity to its corresponding Response Data Transfer Object (DTO).
     *
     * @param ingestionRequestDetails The IngestionRequestDetails entity to convert.
     * @return The converted IngestionRequestDetailsDTO containing details of the ingestion request.
     */
    public IngestionRequestDetailsDTO mapToIngestionRequestDetailsDTO(IngestionRequestDetails ingestionRequestDetails) {
        log.info("Mapping ingestion request details dto");
        IngestionRequestDetailsDTO ingestRequestDetailsDTO = new IngestionRequestDetailsDTO();
        BeanUtils.copyProperties(ingestionRequestDetails, ingestRequestDetailsDTO);
        // Map dataset details if available
        if (ingestionRequestDetails.getDatasetDetails() != null) {
            DatasetDetails datasetDetails = ingestionRequestDetails.getDatasetDetails();
            ingestRequestDetailsDTO.setDatasetName(datasetDetails.getDatasetName());

            mapDatasetRoleDetails(ingestRequestDetailsDTO, datasetDetails);
            mapBasicDatasetDetails(ingestRequestDetailsDTO, datasetDetails);
            mapDatasetDataCategories(ingestRequestDetailsDTO, datasetDetails);
            mapDatasetStudies(ingestRequestDetailsDTO, datasetDetails);
            mapDatasetUserUsageRestrictions(ingestRequestDetailsDTO, datasetDetails);
            mapDatasetTherapies(ingestRequestDetailsDTO, datasetDetails);
            mapDatasetTechAndAssays(ingestRequestDetailsDTO, datasetDetails);
            mapDatasetIndications(ingestRequestDetailsDTO, datasetDetails);
        }

        // Map technical details if available
        if (ingestionRequestDetails.getTechnicalDetails() != null) {
            TechnicalDetails technicalDetails = ingestionRequestDetails.getTechnicalDetails();
            mapTechnicalDetails(ingestRequestDetailsDTO, technicalDetails);
        }

        ingestRequestDetailsDTO.setActiveRequestStatus(mapRequestStatusDetails(ingestionRequestDetails.getRequestStatusDetails()));
        ingestRequestDetailsDTO.setNotes(validationNotesRepository.findByIngestionRequest(ingestionRequestDetails));
        return ingestRequestDetailsDTO;
    }

    /**
     * Copies technical details from TechnicalDetails object to IngestionRequestDetailsDTO object
     * and converts certain fields to lists.
     *
     * @param ingestRequestDetailsDTO The DTO object to which technical details will be mapped.
     * @param technicalDetails        The TechnicalDetails object from which details will be copied.
     */
    private void mapTechnicalDetails(IngestionRequestDetailsDTO ingestRequestDetailsDTO, TechnicalDetails technicalDetails) {
        log.info("Mapping technical details to ingestion request details dto");
        // Copy technical details using BeanUtils
        BeanUtils.copyProperties(technicalDetails, ingestRequestDetailsDTO);
        // Convert comma-separated strings to lists
        ingestRequestDetailsDTO.setGuestUsersEmail(convertStringToList(technicalDetails.getGuestUsersEmail()));
        ingestRequestDetailsDTO.setWhitelistIpAddresses(convertStringToList(technicalDetails.getWhitelistIpAddresses()));
    }

    /**
     * Maps dataset role details from DatasetDetails object to IngestionRequestDetailsDTO object.
     *
     * @param dto            The DTO object to which dataset role details will be mapped.
     * @param datasetDetails The DatasetDetails object from which details will be copied.
     */
    private void mapDatasetRoleDetails(IngestionRequestDetailsDTO dto, DatasetDetails datasetDetails) {
        log.info("Mapping dataset role details to ingestion request details dto");
        if (datasetDetails.getDatasetRoleDetails() != null) {
            for (DatasetRoleDetails details : datasetDetails.getDatasetRoleDetails()) {
                // Map role details based on role type
                switch (details.getRole().toLowerCase()) {
                    case ApiConstants.ROLE_DATA_SME:
                        dto.setDatasetSmeName(details.getName());
                        dto.setDatasetSmeMudid(details.getMudid());
                        dto.setDatasetSmeEmail(details.getEmail());
                        break;
                    case ApiConstants.ROLE_DATA_OWNER:
                        dto.setDatasetOwnerName(details.getName());
                        dto.setDatasetOwnerMudid(details.getMudid());
                        dto.setDatasetOwnerEmail(details.getEmail());
                        break;
                    case ApiConstants.ROLE_DATA_STEWARD:
                        dto.setDatasetStewardName(details.getName());
                        dto.setDatasetStewardMudid(details.getMudid());
                        dto.setDatasetStewardEmail(details.getEmail());
                        break;
                }
            }
        }
    }

    /**
     * Copies basic dataset details from DatasetDetails object to IngestionRequestDetailsDTO object.
     *
     * @param dto            The DTO object to which basic dataset details will be mapped.
     * @param datasetDetails The DatasetDetails object from which details will be copied.
     */
    private void mapBasicDatasetDetails(IngestionRequestDetailsDTO dto, DatasetDetails datasetDetails) {
        log.info("Mapping dataset details to ingestion request details dto");
        // Copy basic dataset details using BeanUtils
        BeanUtils.copyProperties(datasetDetails, dto);
    }

    /**
     * Maps dataset data categories from DatasetDetails object to IngestionRequestDetailsDTO object.
     *
     * @param dto            The DTO object to which dataset data categories will be mapped.
     * @param datasetDetails The DatasetDetails object from which data categories will be copied.
     */
    private void mapDatasetDataCategories(IngestionRequestDetailsDTO dto, DatasetDetails datasetDetails) {
        log.info("Mapping dataset data categories to ingestion request details dto");
        if (datasetDetails.getDatasetDataCategories() != null) {
            // Extract data category references and collect them into a list
            List<String> datasetDataCategories = datasetDetails.getDatasetDataCategories().stream()
                    .map(DatasetDataCategory::getDataCategoryRef)
                    .collect(Collectors.toList());
            dto.setDataCategoryRefs(datasetDataCategories);
        }
    }

    /**
     * Maps dataset studies from DatasetDetails object to IngestionRequestDetailsDTO object.
     *
     * @param dto            The DTO object to which dataset studies will be mapped.
     * @param datasetDetails The DatasetDetails object from which studies will be copied.
     */
    private void mapDatasetStudies(IngestionRequestDetailsDTO dto, DatasetDetails datasetDetails) {
        log.info("Mapping dataset studies to ingestion request details dto");
        if (datasetDetails.getDatasetStudies() != null) {
            // Extract study IDs and collect them into a list
            List<String> studyIds = datasetDetails.getDatasetStudies().stream()
                    .map(DatasetStudy::getStudyId)
                    .collect(Collectors.toList());
            dto.setStudyIds(studyIds);
        }
    }

    /**
     * Maps dataset user usage restrictions from DatasetDetails object to IngestionRequestDetailsDTO object.
     *
     * @param dto            The DTO object to which dataset user usage restrictions will be mapped.
     * @param datasetDetails The DatasetDetails object from which user usage restrictions will be copied.
     */
    private void mapDatasetUserUsageRestrictions(IngestionRequestDetailsDTO dto, DatasetDetails datasetDetails) {
        log.info("Mapping dataset user usage restrictions to ingestion request details dto");
        if (datasetDetails.getDatasetUserUsageRestriction() != null) {
            List<String> userRestrictions = new ArrayList<>();
            List<String> usageRestrictions = new ArrayList<>();
            // Iterate through each user usage restriction and categorize them based on their type
            for (DatasetUserUsageRestriction user : datasetDetails.getDatasetUserUsageRestriction()) {
                if (ApiConstants.RESTRICTION_TYPE_USER.equalsIgnoreCase(user.getRestrictionTypeRef())) {
                    userRestrictions.add(user.getRestrictionRef());
                } else if (ApiConstants.RESTRICTION_TYPE_USAGE.equalsIgnoreCase(user.getRestrictionTypeRef())) {
                    usageRestrictions.add(user.getRestrictionRef());
                }
            }
            // Set the categorized restrictions in the DTO object
            dto.setUserRestrictions(userRestrictions);
            dto.setUsageRestrictions(usageRestrictions);
        }
    }

    /**
     * Maps dataset therapies from DatasetDetails object to IngestionRequestDetailsDTO object.
     *
     * @param dto            The DTO object to which dataset therapies will be mapped.
     * @param datasetDetails The DatasetDetails object from which therapies will be copied.
     */
    private void mapDatasetTherapies(IngestionRequestDetailsDTO dto, DatasetDetails datasetDetails) {
        log.info("Mapping dataset therapies to ingestion request details dto");
        if (datasetDetails.getDatasetTherapies() != null) {
            // Extract therapy areas and collect them into a list
            List<String> therapies = datasetDetails.getDatasetTherapies().stream()
                    .map(DatasetTherapy::getTherapy)
                    .collect(Collectors.toList());
            dto.setTherapyAreas(therapies);
        }
    }

    /**
     * Maps dataset tech and assays from DatasetDetails object to IngestionRequestDetailsDTO object.
     *
     * @param dto            The DTO object to which dataset tech and assays will be mapped.
     * @param datasetDetails The DatasetDetails object from which tech and assays will be copied.
     */
    private void mapDatasetTechAndAssays(IngestionRequestDetailsDTO dto, DatasetDetails datasetDetails) {
        log.info("Mapping dataset tech and assays to ingestion request details dto");
        if (datasetDetails.getDatasetTechAndAssays() != null) {
            // Extract tech and assays and collect them into a list
            List<String> techAndAssayList = datasetDetails.getDatasetTechAndAssays().stream()
                    .map(DatasetTechAndAssay::getTechniqueAndAssay)
                    .collect(Collectors.toList());
            dto.setTechniqueAndAssays(techAndAssayList);
        }
    }


    /**
     * Maps dataset indications from DatasetDetails object to IngestionRequestDetailsDTO object.
     *
     * @param dto            The DTO object to which dataset indications will be mapped.
     * @param datasetDetails The DatasetDetails object from which indications will be copied.
     */
    private void mapDatasetIndications(IngestionRequestDetailsDTO dto, DatasetDetails datasetDetails) {
        log.info("Mapping dataset indications to ingestion request details dto");
        if (datasetDetails.getDatasetIndications() != null) {
            // Extract indications and collect them into a list
            List<String> indicationList = datasetDetails.getDatasetIndications().stream()
                    .map(DatasetIndication::getIndication)
                    .collect(Collectors.toList());
            dto.setIndications(indicationList);
        }
    }

    /**
     * Maps the active request status details to DTO.
     *
     * @param requestStatusDetails the list of request status details
     * @return the DTO representation of the active request status
     */
    private RequestStatusDetailsDTO mapRequestStatusDetails(List<RequestStatusDetails> requestStatusDetails) {
        log.info("Mapping request status details to ingestion request details status dto");
        // Check if the list is null or empty
        if (requestStatusDetails == null || requestStatusDetails.isEmpty()) {
            return null;
        }

        // Find the active request status from the list
        RequestStatusDetails activeRequestStatus = requestStatusDetails.stream()
                .filter(status -> status.getActiveFlag() != null && status.getActiveFlag())
                .findFirst()
                .orElse(null);

        // If no active request status found, return null
        if (activeRequestStatus == null) {
            return null;
        }

        // Map details of the active request status to a RequestStatusDetailsDTO
        RequestStatusDetailsDTO activeRequestStatusDTO = new RequestStatusDetailsDTO();
        BeanUtils.copyProperties(activeRequestStatus, activeRequestStatusDTO);
        activeRequestStatusDTO.setIngestionRequestId(activeRequestStatus.getIngestionRequest().getIngestionRequestId());

        return activeRequestStatusDTO;
    }

    /**
     * Converts a comma-separated string to a list of strings.
     *
     * @param commaSeparatedString the comma-separated string
     * @return the list of strings
     */
    private List<String> convertStringToList(String commaSeparatedString) {
        // Check if the input string is null or empty
        if (commaSeparatedString == null || commaSeparatedString.isEmpty()) {
            return Collections.emptyList();
        }
        // Split the input string at commas and return as a list
        return Arrays.asList(commaSeparatedString.split(","));
    }

}
