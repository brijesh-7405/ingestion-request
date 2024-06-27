package com.ddf.ingestion_ddf.request.mappers;

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

import com.ddf.ingestion_ddf.request.IngestionRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper class to convert {@link IngestionRequest} objects into various entity objects.
 * <p>
 * This class provides methods to map data from an {@link IngestionRequest} to entities such as
 * {@link IngestionRequestDetails}, {@link DatasetDetails}, {@link TechnicalDetails}, and other related entities.
 * The mappings include setting properties and managing nested entities.
 * </p>
 * <p>
 * The class uses Spring's {@link BeanUtils} for copying properties and logs operations using SLF4J.
 * </p>
 */
@Component
@Slf4j
public class IngestionRequestMapper {
    /**
     * Maps an IngestionRequest object to an IngestionRequestDetails object.
     *
     * @param ingestionRequest The IngestionRequest object to map from.
     * @param createdBy        The user who created the request.
     * @param modifiedBy       The user who modified the request.
     * @param details          The existing IngestionRequestDetails object, if any.
     * @return The mapped IngestionRequestDetails object.
     */
    public IngestionRequestDetails mapToIngestionRequestDetails(IngestionRequest ingestionRequest, String createdBy, String modifiedBy, IngestionRequestDetails details) {
        log.info("Mapping IngestionRequest to IngestionRequestDetails");
        IngestionRequestDetails ingestionRequestDetails;
        if (details != null) {
            ingestionRequestDetails = details;
            ingestionRequestDetails.setModifiedBy(modifiedBy);
        } else {
            ingestionRequestDetails = new IngestionRequestDetails();
            ingestionRequestDetails.setCreatedBy(createdBy);
            ingestionRequestDetails.setModifiedBy(modifiedBy);
        }
        BeanUtils.copyProperties(ingestionRequest, ingestionRequestDetails);

        ingestionRequestDetails.setDatasetDetails(mapToDatasetDetails(ingestionRequest, ingestionRequestDetails, createdBy, modifiedBy));
        ingestionRequestDetails.setTechnicalDetails(mapToTechnicalDetails(ingestionRequest, ingestionRequestDetails, createdBy, modifiedBy));

        return ingestionRequestDetails;
    }

    /**
     * Maps an IngestionRequest object to a DatasetDetails object.
     *
     * @param ingestionRequest        The IngestionRequest object to map from.
     * @param ingestionRequestDetails The corresponding IngestionRequestDetails object.
     * @param createdBy               The user who created the request.
     * @param modifiedBy              The user who modified the request.
     * @return The mapped DatasetDetails object.
     */
    public DatasetDetails mapToDatasetDetails(IngestionRequest ingestionRequest,
                                              IngestionRequestDetails ingestionRequestDetails, String createdBy, String modifiedBy) {
        log.info("Mapping IngestionRequest to DatasetDetails");
        DatasetDetails datasetDetails;
        //Will Add CreatedBy and ModifiedBy when it is for create API requests
        if (ingestionRequestDetails.getDatasetDetails() == null) {
            datasetDetails = new DatasetDetails();
            datasetDetails.setCreatedBy(createdBy);
            datasetDetails.setModifiedBy(modifiedBy);
        } else {
            //Will Update ModifiedBy field when it is for update API requests
            datasetDetails = ingestionRequestDetails.getDatasetDetails();
            datasetDetails.setModifiedBy(modifiedBy);
        }
        // Mapping dataset details
        BeanUtils.copyProperties(ingestionRequest, datasetDetails);
        datasetDetails.setIngestionRequest(ingestionRequestDetails);

        datasetDetails.setDatasetUserUsageRestriction(mapToDatasetUserUsageRestriction(ingestionRequest, datasetDetails, createdBy, modifiedBy));
        datasetDetails.setDatasetRoleDetails(mapToDatasetRoleDetails(ingestionRequest, datasetDetails));
        datasetDetails.setDatasetIndications(mapToDatasetIndication(ingestionRequest, datasetDetails));
        datasetDetails.setDatasetTechAndAssays(mapToDatasetTechAndAssay(ingestionRequest, datasetDetails));
        datasetDetails.setDatasetTherapies(mapToDatasetTherapy(ingestionRequest, datasetDetails));
        datasetDetails.setDatasetStudies(mapToDatasetStudy(ingestionRequest, datasetDetails));
        datasetDetails.setDatasetDataCategories(mapToDatasetDataCategory(ingestionRequest, datasetDetails));

        return datasetDetails;
    }

    /**
     * Maps usage and user restrictions from an IngestionRequest to a list of DatasetUserUsageRestriction objects.
     *
     * @param ingestionRequest The IngestionRequest object containing the restrictions.
     * @param datasetDetails   The DatasetDetails object to which the restrictions belong.
     * @param createdBy        The user who created the restrictions.
     * @param modifiedBy       The user who modified the restrictions.
     * @return The list of mapped DatasetUserUsageRestriction objects.
     */
    public List<DatasetUserUsageRestriction> mapToDatasetUserUsageRestriction(IngestionRequest ingestionRequest,
                                                                              DatasetDetails datasetDetails, String createdBy, String modifiedBy) {
        log.info("Mapping dataset user usage restrictions");
        // Initialize list to hold user usage restrictions
        List<DatasetUserUsageRestriction> datasetUserUsageRestrictionList = datasetDetails.getDatasetUserUsageRestriction() != null ? datasetDetails.getDatasetUserUsageRestriction() : new ArrayList<>();
        datasetUserUsageRestrictionList.clear();
        // Populate usage restrictions
        populateRestrictions(ingestionRequest.getUsageRestrictions(), ApiConstants.RESTRICTION_TYPE_USAGE, datasetUserUsageRestrictionList, datasetDetails, createdBy, modifiedBy);
        // Populate user restrictions
        populateRestrictions(ingestionRequest.getUserRestrictions(), ApiConstants.RESTRICTION_TYPE_USER, datasetUserUsageRestrictionList, datasetDetails, createdBy, modifiedBy);
        return datasetUserUsageRestrictionList;
    }

    /**
     * Populates the list of DatasetUserUsageRestriction objects with the provided restrictions.
     *
     * @param restrictions    The list of restrictions to populate.
     * @param restrictionType The type of restriction (usage or user).
     * @param restrictionList The list to populate with DatasetUserUsageRestriction objects.
     * @param datasetDetails  The DatasetDetails object to which the restrictions belong.
     * @param createdBy       The user who created the restrictions.
     * @param modifiedBy      The user who modified the restrictions.
     */
    private void populateRestrictions(List<String> restrictions, String restrictionType, List<DatasetUserUsageRestriction> restrictionList,
                                      DatasetDetails datasetDetails, String createdBy, String modifiedBy) {
        if (restrictions != null && !restrictions.isEmpty()) {
            for (String usage : restrictions) {
                DatasetUserUsageRestriction datasetUserUsageRestriction = new DatasetUserUsageRestriction();
                datasetUserUsageRestriction.setDatasetId(datasetDetails);
                datasetUserUsageRestriction.setRestrictionTypeRef(restrictionType);
                datasetUserUsageRestriction.setRestrictionRef(usage);
                // Set creator and modifier details based on existing restrictions
                //Will Add CreatedBy and ModifiedBy when it is for create API requests
                if (datasetDetails.getDatasetUserUsageRestriction() == null) {
                    datasetUserUsageRestriction.setCreatedBy(createdBy);
                    datasetUserUsageRestriction.setModifiedBy(modifiedBy);
                } else {
                    //Will update ModifiedBy field when it is for update API requests
                    datasetUserUsageRestriction.setModifiedBy(modifiedBy);
                    datasetUserUsageRestriction.setCreatedBy(datasetDetails.getCreatedBy());
                }
                // Add the restriction to the list
                restrictionList.add(datasetUserUsageRestriction);
            }
        }
    }

    /**
     * Maps the dataset roles from an IngestionRequest to a list of DatasetRoleDetails objects.
     *
     * @param ingestionRequest The IngestionRequest object containing the dataset roles.
     * @param datasetDetails   The DatasetDetails object to which the roles belong.
     * @return The list of mapped DatasetRoleDetails objects.
     */
    public List<DatasetRoleDetails> mapToDatasetRoleDetails(IngestionRequest ingestionRequest,
                                                            DatasetDetails datasetDetails) {
        log.info("Mapping dataset role details");
        // Initialize list to hold dataset role details
        List<DatasetRoleDetails> datasetRoleDetailsList = datasetDetails.getDatasetRoleDetails() != null ? datasetDetails.getDatasetRoleDetails() : new ArrayList<>();

        if (datasetDetails.getDatasetRoleDetails() != null && !datasetDetails.getDatasetRoleDetails().isEmpty()) {
            for (DatasetRoleDetails datasetRole : datasetRoleDetailsList) {
                // If dataset role details already exist, update them
                updateRoleDetails(datasetRole, ingestionRequest, datasetDetails);
            }
        } else {
            // If dataset role details don't exist, create new ones
            datasetRoleDetailsList.clear();
            datasetRoleDetailsList.add(createRoleDetails(ApiConstants.ROLE_DATA_OWNER, ingestionRequest.getDatasetOwnerName(),
                    ingestionRequest.getDatasetOwnerMudid(), ingestionRequest.getDatasetOwnerEmail(), datasetDetails));
            datasetRoleDetailsList.add(createRoleDetails(ApiConstants.ROLE_DATA_STEWARD, ingestionRequest.getDatasetStewardName(),
                    ingestionRequest.getDatasetStewardMudid(), ingestionRequest.getDatasetStewardEmail(), datasetDetails));
            datasetRoleDetailsList.add(createRoleDetails(ApiConstants.ROLE_DATA_SME, ingestionRequest.getDatasetSmeName(),
                    ingestionRequest.getDatasetSmeMudid(), ingestionRequest.getDatasetSmeEmail(), datasetDetails));
        }
        return datasetRoleDetailsList;
    }

    /**
     * Updates the properties of a DatasetRoleDetails object based on the role specified in the IngestionRequest.
     *
     * @param datasetRole      The DatasetRoleDetails object to update.
     * @param ingestionRequest The IngestionRequest object containing the role information.
     * @param datasetDetails   The DatasetDetails object to which the role belongs.
     */
    private void updateRoleDetails(DatasetRoleDetails datasetRole, IngestionRequest ingestionRequest, DatasetDetails datasetDetails) {
        if (datasetRole.getRole().equalsIgnoreCase(ApiConstants.ROLE_DATA_OWNER)) {
            datasetRole.setName(ingestionRequest.getDatasetOwnerName());
            datasetRole.setMudid(ingestionRequest.getDatasetOwnerMudid());
            datasetRole.setEmail(ingestionRequest.getDatasetOwnerEmail());
        } else if (datasetRole.getRole().equalsIgnoreCase(ApiConstants.ROLE_DATA_STEWARD)) {
            datasetRole.setName(ingestionRequest.getDatasetStewardName());
            datasetRole.setMudid(ingestionRequest.getDatasetStewardMudid());
            datasetRole.setEmail(ingestionRequest.getDatasetStewardEmail());
        } else if (datasetRole.getRole().equalsIgnoreCase(ApiConstants.ROLE_DATA_SME)) {
            datasetRole.setName(ingestionRequest.getDatasetSmeName());
            datasetRole.setMudid(ingestionRequest.getDatasetSmeMudid());
            datasetRole.setEmail(ingestionRequest.getDatasetSmeEmail());
        }
        datasetRole.setDatasetId(datasetDetails);
    }

    /**
     * Creates a new DatasetRoleDetails object with the specified properties.
     *
     * @param role           The role of the dataset.
     * @param name           The name associated with the role.
     * @param mudid          The mudid associated with the role.
     * @param email          The email associated with the role.
     * @param datasetDetails The DatasetDetails object to which the role belongs.
     * @return The created DatasetRoleDetails object.
     */
    private DatasetRoleDetails createRoleDetails(String role, String name, String mudid, String email, DatasetDetails datasetDetails) {
        DatasetRoleDetails datasetRoleDetails = new DatasetRoleDetails();
        datasetRoleDetails.setRole(role);
        datasetRoleDetails.setName(name);
        datasetRoleDetails.setMudid(mudid);
        datasetRoleDetails.setEmail(email);
        datasetRoleDetails.setDatasetId(datasetDetails);
        return datasetRoleDetails;
    }

    /**
     * Maps the data categories from an IngestionRequest to a list of DatasetDataCategory objects.
     *
     * @param ingestionRequestDTO The IngestionRequest object containing the data category references.
     * @param datasetDetails      The DatasetDetails object to which the data categories belong.
     * @return The list of mapped DatasetDataCategory objects.
     */
    public List<DatasetDataCategory> mapToDatasetDataCategory(IngestionRequest ingestionRequestDTO, DatasetDetails datasetDetails) {
        log.info("Mapping dataset data category");
        // Initialize list to hold dataset data categories
        List<DatasetDataCategory> datasetDataCategories = datasetDetails.getDatasetDataCategories() != null ? datasetDetails.getDatasetDataCategories() : new ArrayList<>();
        datasetDataCategories.clear();
        if (ingestionRequestDTO.getDataCategoryRefs() != null && !ingestionRequestDTO.getDataCategoryRefs().isEmpty()) {
            // Iterate through data category references in the ingestion request
            for (String datasetDataCategory : ingestionRequestDTO.getDataCategoryRefs()) {
                DatasetDataCategory category = new DatasetDataCategory();
                category.setDataCategoryRef(datasetDataCategory);
                category.setDatasetId(datasetDetails);
                datasetDataCategories.add(category);
            }
        }
        return datasetDataCategories;
    }

    /**
     * Maps the study IDs from an IngestionRequest to a list of DatasetStudy objects.
     *
     * @param ingestionRequest The IngestionRequest object containing the study IDs.
     * @param datasetDetails   The DatasetDetails object to which the studies belong.
     * @return The list of mapped DatasetStudy objects.
     */
    public List<DatasetStudy> mapToDatasetStudy(IngestionRequest ingestionRequest,
                                                DatasetDetails datasetDetails) {
        log.info("Mapping dataset study");
        // Initialize list to hold dataset studies
        List<DatasetStudy> datasetStudies = datasetDetails.getDatasetStudies() != null ? datasetDetails.getDatasetStudies() : new ArrayList<>();
        datasetStudies.clear();
        // If study IDs are provided, map them to DatasetStudy objects
        if (ingestionRequest.getStudyIds() != null && !ingestionRequest.getStudyIds().isEmpty()) {
            for (String studyId : ingestionRequest.getStudyIds()) {
                DatasetStudy datasetStudy = new DatasetStudy();
                datasetStudy.setStudyId(studyId);
                datasetStudy.setDatasetId(datasetDetails);
                datasetStudies.add(datasetStudy);
            }
            // Update datasetDetails with the newly mapped studies
            datasetDetails.setDatasetStudies(datasetStudies);
        }
        return datasetStudies;
    }


    /**
     * Maps the therapy areas from an IngestionRequest to a list of DatasetTherapy objects.
     *
     * @param ingestionRequest The IngestionRequest object containing the therapy areas.
     * @param datasetDetails   The DatasetDetails object to which the therapies belong.
     * @return The list of mapped DatasetTherapy objects.
     */
    public List<DatasetTherapy> mapToDatasetTherapy(IngestionRequest ingestionRequest,
                                                    DatasetDetails datasetDetails) {
        log.info("Mapping dataset therapy");
        // Initialize list to hold dataset therapies
        List<DatasetTherapy> datasetTherapies = datasetDetails.getDatasetTherapies() != null ? datasetDetails.getDatasetTherapies() : new ArrayList<>();
        datasetTherapies.clear();
        // If therapy areas are provided, map them to DatasetTherapy objects
        if (ingestionRequest.getTherapyAreas() != null && !ingestionRequest.getTherapyAreas().isEmpty()) {
            for (String therapy : ingestionRequest.getTherapyAreas()) {
                DatasetTherapy datasetTherapy = new DatasetTherapy();
                datasetTherapy.setDatasetId(datasetDetails);
                datasetTherapy.setTherapy(therapy);
                datasetTherapies.add(datasetTherapy);
            }
        }
        return datasetTherapies;
    }

    /**
     * Maps the techniques and assays from an IngestionRequest to a list of DatasetTechAndAssay objects.
     *
     * @param ingestionRequest The IngestionRequest object containing the techniques and assays.
     * @param datasetDetails   The DatasetDetails object to which the techniques and assays belong.
     * @return The list of mapped DatasetTechAndAssay objects.
     */
    public List<DatasetTechAndAssay> mapToDatasetTechAndAssay(IngestionRequest ingestionRequest,
                                                              DatasetDetails datasetDetails) {
        log.info("Mapping dataset tech and assay");
        List<DatasetTechAndAssay> datasetTechAndAssays = datasetDetails.getDatasetTechAndAssays() != null ? datasetDetails.getDatasetTechAndAssays() : new ArrayList<>();
        datasetTechAndAssays.clear();
        if (ingestionRequest.getTechniqueAndAssays() != null && !ingestionRequest.getTechniqueAndAssays().isEmpty()) {
            for (String technique : ingestionRequest.getTechniqueAndAssays()) {
                DatasetTechAndAssay datasetTechAndAssay = new DatasetTechAndAssay();
                datasetTechAndAssay.setDatasetId(datasetDetails);
                datasetTechAndAssay.setTechniqueAndAssay(technique);
                datasetTechAndAssays.add(datasetTechAndAssay);
            }
        }
        return datasetTechAndAssays;
    }

    /**
     * Maps the indications from an {@link IngestionRequest} to a list of {@link DatasetIndication} objects.
     *
     * @param ingestionRequest The {@link IngestionRequest} object containing the indications.
     * @param datasetDetails   The {@link DatasetDetails} object to which the indications belong.
     * @return The list of mapped {@link DatasetIndication} objects.
     */
    public List<DatasetIndication> mapToDatasetIndication(IngestionRequest ingestionRequest,
                                                          DatasetDetails datasetDetails) {
        log.info("Mapping dataset indication");
        // Initialize list to hold dataset techniques and assays
        List<DatasetIndication> datasetIndications = datasetDetails.getDatasetIndications() != null ? datasetDetails.getDatasetIndications() : new ArrayList<>();
        datasetIndications.clear();
        // If indications are provided, map them to DatasetIndication objects
        if (ingestionRequest.getIndications() != null && !ingestionRequest.getIndications().isEmpty()) {
            for (String indication : ingestionRequest.getIndications()) {
                DatasetIndication datasetIndication = new DatasetIndication();
                datasetIndication.setDatasetId(datasetDetails);
                datasetIndication.setIndication(indication);
                datasetIndications.add(datasetIndication);
            }
        }
        return datasetIndications;
    }

    /**
     * Maps the technical details from an IngestionRequest to a TechnicalDetails object.
     *
     * @param ingestionRequest        The IngestionRequest object containing the technical details.
     * @param ingestionRequestDetails The IngestionRequestDetails object to which the technical details belong.
     * @param createdBy               The username of the creator.
     * @param modifiedBy              The username of the modifier.
     * @return The TechnicalDetails object containing the mapped technical details.
     */
    public TechnicalDetails mapToTechnicalDetails(IngestionRequest ingestionRequest,
                                                  IngestionRequestDetails ingestionRequestDetails, String createdBy, String modifiedBy) {
        log.info("Mapping technical details");
        // Set technical details properties from ingestionRequest object
        TechnicalDetails technicalDetails;
        //Will Add CreatedBy and ModifiedBy when it is for create API requests
        if (ingestionRequestDetails.getTechnicalDetails() == null) {
            technicalDetails = new TechnicalDetails();
            technicalDetails.setCreatedBy(createdBy);
            technicalDetails.setModifiedBy(modifiedBy);
        } else {
            //Will update ModifiedBy field when it is for update API requests
            technicalDetails = ingestionRequestDetails.getTechnicalDetails();
            technicalDetails.setModifiedBy(modifiedBy);
        }
        // Map technical details properties from IngestionRequest object
        BeanUtils.copyProperties(ingestionRequest, technicalDetails);

        // Set guest users email if provided
        if (ingestionRequest.getGuestUsersEmail() != null && !ingestionRequest.getGuestUsersEmail().isEmpty()) {
            String email = String.join(",", ingestionRequest.getGuestUsersEmail());
            technicalDetails.setGuestUsersEmail(email);
        }
        // Set whitelist IP addresses if provided
        if (ingestionRequest.getWhitelistIpAddresses() != null && !ingestionRequest.getWhitelistIpAddresses().isEmpty()) {
            String ipAddress = String.join(",", ingestionRequest.getWhitelistIpAddresses());
            technicalDetails.setWhitelistIpAddresses(ipAddress);
        }
        technicalDetails.setIngestionRequest(ingestionRequestDetails);

        return technicalDetails;
    }
}
