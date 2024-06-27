package com.ddf.ingestion_ddf.util;

import com.ddf.ingestion_ddf.constants.ApiConstants;
import com.ddf.ingestion_ddf.entity.IngestionRequestDetails;
import com.ddf.ingestion_ddf.entity.DatasetDetails;
import com.ddf.ingestion_ddf.entity.TechnicalDetails;
import com.ddf.ingestion_ddf.entity.DatasetDataCategory;
import com.ddf.ingestion_ddf.entity.DatasetUserUsageRestriction;
import com.ddf.ingestion_ddf.entity.DatasetTherapy;
import com.ddf.ingestion_ddf.entity.DatasetStudy;
import com.ddf.ingestion_ddf.entity.DatasetTechAndAssay;
import com.ddf.ingestion_ddf.entity.DatasetRoleDetails;
import com.ddf.ingestion_ddf.entity.DatasetIndication;
import com.ddf.ingestion_ddf.entity.Status;
import com.ddf.ingestion_ddf.entity.RequestStatusDetails;

import com.ddf.ingestion_ddf.request.IngestionRequest;
import com.ddf.ingestion_ddf.response.IngestionRequestDetailsDTO;
import com.ddf.ingestion_ddf.response.RequestStatusDetailsDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Utility class for creating test data related to ingestion requests and datasets.
 * This class provides methods to generate test instances of various entities and DTOs
 * for testing purposes.
 */
public class TestDataUtil {

    /**
     * Creates and returns a test instance of {@link IngestionRequestDetails}.
     * This method populates various fields of the ingestion request details for testing purposes.
     *
     * @return A test instance of {@link IngestionRequestDetails}.
     */
    public static IngestionRequestDetails createTestIngestionRequestDetails() {
        IngestionRequestDetails ingestionRequestDetails = new IngestionRequestDetails();
        ingestionRequestDetails.setRequesterName("Alice Johnson");
        ingestionRequestDetails.setRequesterMudid("AJ789");
        ingestionRequestDetails.setRequesterEmail("alice.johnson@example.com");
        ingestionRequestDetails.setRequestRationaleReason("Need data for analysis");
        ingestionRequestDetails.setModifiedReason("Initial request");
        ingestionRequestDetails.setRequestedByName("Bob Brown");
        ingestionRequestDetails.setRequestedByMudid("BB987");
        ingestionRequestDetails.setRequestedByEmail("bob.brown@example.com");

        DatasetDetails datasetDetails = createDatasetDetails();
        datasetDetails.setIngestionRequest(ingestionRequestDetails);
        ingestionRequestDetails.setDatasetDetails(datasetDetails);

        TechnicalDetails technicalDetails = new TechnicalDetails();
        technicalDetails.setDataLocationPath("Path1");
        technicalDetails.setDataRefreshFrequency("Weekly");
        technicalDetails.setTargetIngestionStartDate(new Date());
        technicalDetails.setTargetIngestionEndDate(new Date());
        technicalDetails.setTargetPath("TargetPath");
        technicalDetails.setDatasetTypeIngestionRef("IngestType1");
        technicalDetails.setGuestUsersEmail("guest@example.com,guest2@example.com");
        technicalDetails.setWhitelistIpAddresses("");
        technicalDetails.setExternalStagingContainerName("Container1");
        technicalDetails.setExternalDataSourceLocation("Location1");
        technicalDetails.setGskAccessSourceLocationRef("GSKRef1");
        technicalDetails.setExternalSourceSecretKeyName("SecretKey1");
        technicalDetails.setDomainRequestId("Domain1");
        technicalDetails.setExistingDataLocationIdentified("Identified1");
        technicalDetails.setIngestionRequest(ingestionRequestDetails);
        ingestionRequestDetails.setTechnicalDetails(technicalDetails);

        List<RequestStatusDetails> requestStatusDetailsList = new ArrayList<>();
        RequestStatusDetails requestStatusDetails1 = new RequestStatusDetails();
        Status status = new Status();
        status.setStatusId(1l);
        status.setStatusCode("status01");
        status.setStatusName("Draft");
        requestStatusDetails1.setStatus(status);
        requestStatusDetails1.setDecisionByName("Alice Johnson");
        requestStatusDetails1.setDecisionByMudid("AJ789");
        requestStatusDetails1.setDecisionByEmail("alice.johnson@example.com");
        requestStatusDetails1.setDecisionDate(new Date());
        requestStatusDetails1.setDecisionComments("Pending approval");
        requestStatusDetails1.setRejectionReason(null);
        requestStatusDetails1.setActiveFlag(true);
        requestStatusDetails1.setIngestionRequest(ingestionRequestDetails);
        requestStatusDetailsList.add(requestStatusDetails1);

        RequestStatusDetails requestStatusDetails2 = new RequestStatusDetails();
        Status status2 = new Status();
        status2.setStatusId(2l);
        status2.setStatusCode("status01");
        status2.setStatusName("Approved");
        requestStatusDetails2.setStatus(status2);
        requestStatusDetails2.setDecisionByName("Bob Brown");
        requestStatusDetails2.setDecisionByMudid("BB987");
        requestStatusDetails2.setDecisionByEmail("bob.brown@example.com");
        requestStatusDetails2.setDecisionDate(new Date());
        requestStatusDetails2.setDecisionComments("Approved for processing");
        requestStatusDetails2.setRejectionReason(null);
        requestStatusDetails2.setActiveFlag(true);
        requestStatusDetails2.setIngestionRequest(ingestionRequestDetails);
        requestStatusDetailsList.add(requestStatusDetails2);

        ingestionRequestDetails.setRequestStatusDetails(requestStatusDetailsList);

        return ingestionRequestDetails;
    }

    /**
     * Creates and returns a test instance of {@link IngestionRequest}.
     * This method populates various fields of the ingestion request for testing purposes.
     *
     * @return A test instance of {@link IngestionRequest}.
     */
    public static IngestionRequest createTestIngestionRequest() {
        IngestionRequest ingestionRequest = new IngestionRequest();

        ingestionRequest.setRequesterName("John Doe");
        ingestionRequest.setRequesterMudid("JD12345");
        ingestionRequest.setRequesterEmail("john.doe@example.com");
        ingestionRequest.setDatasetName("Clinical Trial Data");
        ingestionRequest.setDatasetSmeName("Jane Smith");
        ingestionRequest.setDatasetSmeMudid("JS54321");
        ingestionRequest.setDatasetSmeEmail("jane.smith@example.com");
        ingestionRequest.setRequestRationaleReason("For statistical analysis");
        ingestionRequest.setDatasetOriginSource("Internal Research");
        ingestionRequest.setCurrentDataLocationRef("Data Warehouse");
        ingestionRequest.setDataLocationPath("/data/warehouse/clinical_trials");
        ingestionRequest.setMeteorSpaceDominoUsageFlag(true);
        ingestionRequest.setIhdFlag(false);
        ingestionRequest.setDatasetRequiredForRef("Exploration");
        ingestionRequest.setEstimatedDataVolumeRef("500GB");
        ingestionRequest.setDataRefreshFrequency("Monthly");
        ingestionRequest.setAnalysisInitDt(new Date(2024, 1, 1, 0, 0, 0)); // 2024-01-01T00:00:00Z
        ingestionRequest.setAnalysisEndDt(new Date(2024, 12, 31, 23, 59, 59)); // 2024-12-31T23:59:59Z
        ingestionRequest.setDtaContractCompleteFlag(true);
        ingestionRequest.setDtaExpectedCompletionDate(new Date(2024, 6, 30, 0, 0, 0)); // 2024-06-30T00:00:00Z
        ingestionRequest.setDataCategoryRefs(Arrays.asList("Clinical", "Genomics"));
        ingestionRequest.setDatasetTypeRef("Clinical Trial Data");
        ingestionRequest.setStudyIds(Arrays.asList("ST123", "ST456"));
        ingestionRequest.setDatasetOwnerName("Alice Johnson");
        ingestionRequest.setDatasetOwnerMudid("AJ67890");
        ingestionRequest.setDatasetOwnerEmail("alice.johnson@example.com");
        ingestionRequest.setDatasetStewardName("Bob Williams");
        ingestionRequest.setDatasetStewardMudid("BW09876");
        ingestionRequest.setDatasetStewardEmail("bob.williams@example.com");
        ingestionRequest.setContractPartner("XYZ Pharmaceuticals");
        ingestionRequest.setRetentionRules("7 years");
        ingestionRequest.setRetentionRulesContractDate(new Date(2024, 1, 1, 0, 0, 0)); // 2024-01-01T00:00:00Z
        ingestionRequest.setUsageRestrictions(Arrays.asList("Internal Use Only", "Confidential"));
        ingestionRequest.setUserRestrictions(Arrays.asList("Role-Based Access", "Restricted Access"));
        ingestionRequest.setInformationClassificationTypeRef("Confidential");
        ingestionRequest.setPiiTypeRef("None");
        ingestionRequest.setTherapyAreas(Arrays.asList("Cardiology", "Neurology"));
        ingestionRequest.setTechniqueAndAssays(Arrays.asList("PCR", "ELISA"));
        ingestionRequest.setIndications(Arrays.asList("Hypertension", "Alzheimer's Disease"));
        ingestionRequest.setTargetIngestionStartDate(new Date(2024, 7, 1, 0, 0, 0)); // 2024-07-01T00:00:00Z
        ingestionRequest.setTargetIngestionEndDate(new Date(2024, 7, 31, 23, 59, 59)); // 2024-07-31T23:59:59Z
        ingestionRequest.setTargetPath("/data/ingestion/clinical_trials");
        ingestionRequest.setDatasetTypeIngestionRef("Batch");
        ingestionRequest.setGuestUsersEmail(Arrays.asList("guest1@example.com", "guest2@example.com"));
        ingestionRequest.setWhitelistIpAddresses(Arrays.asList("192.168.1.1", "192.168.1.2"));
        ingestionRequest.setExternalStagingContainerName("staging-container");
        ingestionRequest.setDomainRequestId("DR78901");
        ingestionRequest.setExternalDataSourceLocation("/external/source/location");
        ingestionRequest.setGskAccessSourceLocationRef("GSK Internal Server");
        ingestionRequest.setExternalSourceSecretKeyName("external-secret-key");

        return ingestionRequest;
    }

    /**
     * Creates and returns a test instance of {@link IngestionRequestDetailsDTO}.
     * This method populates various fields of the ingestion request details DTO for testing purposes.
     *
     * @return A test instance of {@link IngestionRequestDetailsDTO}.
     */
    public static IngestionRequestDetailsDTO createTestIngestionRequestDetailsDTO() {
        IngestionRequestDetailsDTO ingestionRequestDetailsDTO = new IngestionRequestDetailsDTO();
        ingestionRequestDetailsDTO.setRequesterName("Alice Johnson");
        ingestionRequestDetailsDTO.setRequesterMudid("AJ789");
        ingestionRequestDetailsDTO.setRequesterEmail("alice.johnson@example.com");
        ingestionRequestDetailsDTO.setRequestRationaleReason("Need data for analysis");
        ingestionRequestDetailsDTO.setModifiedReason("Initial request");

        ingestionRequestDetailsDTO.setDatasetName("Sample Dataset");
        ingestionRequestDetailsDTO.setDatasetOriginSource("Internal");
        ingestionRequestDetailsDTO.setCurrentDataLocationRef("LocationRef");
        ingestionRequestDetailsDTO.setMeteorSpaceDominoUsageFlag(true);
        ingestionRequestDetailsDTO.setIhdFlag(true);
        ingestionRequestDetailsDTO.setDatasetRequiredForRef("Research");
        ingestionRequestDetailsDTO.setEstimatedDataVolumeRef("1TB");
        ingestionRequestDetailsDTO.setAnalysisInitDt(new Date());
        ingestionRequestDetailsDTO.setAnalysisEndDt(new Date());
        ingestionRequestDetailsDTO.setDtaContractCompleteFlag(true);
        ingestionRequestDetailsDTO.setDtaExpectedCompletionDate(new Date());
        ingestionRequestDetailsDTO.setDatasetTypeRef("Type1");
        ingestionRequestDetailsDTO.setInformationClassificationTypeRef("Class1");
        ingestionRequestDetailsDTO.setPiiTypeRef("PII1");
        ingestionRequestDetailsDTO.setRetentionRules("Keep for 7 years");
        ingestionRequestDetailsDTO.setRetentionRulesContractDate(new Date());
        ingestionRequestDetailsDTO.setContractPartner("Partner1");
        ingestionRequestDetailsDTO.setExistingDataLocationIdentified("Identified1");

        RequestStatusDetailsDTO requestStatusDetailsDTO1 = new RequestStatusDetailsDTO();
        Status status1 = new Status();
        status1.setStatusId(1l);
        status1.setStatusCode("status01");
        status1.setStatusName("Draft");
        requestStatusDetailsDTO1.setStatus(status1);
        requestStatusDetailsDTO1.setDecisionByName("Alice Johnson");
        requestStatusDetailsDTO1.setDecisionByMudid("AJ789");
        requestStatusDetailsDTO1.setDecisionByEmail("alice.johnson@example.com");
        requestStatusDetailsDTO1.setDecisionDate(new Date());
        requestStatusDetailsDTO1.setDecisionComments("Pending approval");
        requestStatusDetailsDTO1.setRejectionReason(null);
        requestStatusDetailsDTO1.setActiveFlag(true);

        ingestionRequestDetailsDTO.setActiveRequestStatus(requestStatusDetailsDTO1);


        return ingestionRequestDetailsDTO;
    }

    /**
     * Creates and returns a test instance of {@link DatasetDetails}.
     * This method populates various fields of the dataset details for testing purposes.
     *
     * @return A test instance of {@link DatasetDetails}.
     */
    public static DatasetDetails createDatasetDetails() {
        DatasetDetails datasetDetails = new DatasetDetails();
        datasetDetails.setDatasetName("Clinical Trial Data");
        datasetDetails.setDatasetOriginSource("Internal Research");
        datasetDetails.setCurrentDataLocationRef("Data Warehouse");
        datasetDetails.setMeteorSpaceDominoUsageFlag(true);
        datasetDetails.setIhdFlag(false);
        datasetDetails.setDatasetRequiredForRef("Exploration");
        datasetDetails.setEstimatedDataVolumeRef("500GB");
        datasetDetails.setAnalysisInitDt(new Date(2024, 1, 1, 0, 0, 0)); // 2024-01-01T00:00:00Z
        datasetDetails.setAnalysisEndDt(new Date(2024, 12, 31, 23, 59, 59)); // 2024-12-31T23:59:59Z
        datasetDetails.setDtaContractCompleteFlag(true);
        datasetDetails.setDtaExpectedCompletionDate(new Date(2024, 6, 30, 0, 0, 0)); // 2024-06-30T00:00:00Z
        datasetDetails.setDatasetTypeRef("Clinical Trial Data");
        datasetDetails.setInformationClassificationTypeRef("Confidential");
        datasetDetails.setPiiTypeRef("None");
        datasetDetails.setRetentionRules("7 years");
        datasetDetails.setRetentionRulesContractDate(new Date(2024, 1, 1, 0, 0, 0)); // 2024-01-01T00:00:00Z
        datasetDetails.setContractPartner("XYZ Pharmaceuticals");

        // Add dummy data for datasetUserUsageRestriction
        List<DatasetUserUsageRestriction> userUsageRestrictions = new ArrayList<>();
        DatasetUserUsageRestriction restriction1 = new DatasetUserUsageRestriction();
        restriction1.setRestrictionTypeRef(ApiConstants.RESTRICTION_TYPE_USER);
        restriction1.setRestrictionRef("Restriction1");
        restriction1.setReasonForOther("Reason for Other 1");
        userUsageRestrictions.add(restriction1);

        DatasetUserUsageRestriction restriction2 = new DatasetUserUsageRestriction();
        restriction2.setRestrictionTypeRef(ApiConstants.RESTRICTION_TYPE_USAGE);
        restriction2.setRestrictionRef("Restriction2");
        restriction2.setReasonForOther("Reason for Other 2");
        userUsageRestrictions.add(restriction2);

        datasetDetails.setDatasetUserUsageRestriction(userUsageRestrictions);

        // Add dummy data for datasetDataCategories
        List<DatasetDataCategory> dataCategories = new ArrayList<>();
        DatasetDataCategory category1 = new DatasetDataCategory();
        category1.setDataCategoryRef("Category1");
        dataCategories.add(category1);

        DatasetDataCategory category2 = new DatasetDataCategory();
        category2.setDataCategoryRef("Category2");
        dataCategories.add(category2);

        datasetDetails.setDatasetDataCategories(dataCategories);

        // Add dummy data for datasetStudies
        List<DatasetStudy> studies = new ArrayList<>();
        DatasetStudy study1 = new DatasetStudy();
        study1.setStudyId("Study1");
        studies.add(study1);

        DatasetStudy study2 = new DatasetStudy();
        study2.setStudyId("Study2");
        studies.add(study2);

        datasetDetails.setDatasetStudies(studies);

        // Add dummy data for datasetTherapies
        List<DatasetTherapy> therapies = new ArrayList<>();
        DatasetTherapy therapy1 = new DatasetTherapy();
        therapy1.setTherapy("Therapy1");
        therapies.add(therapy1);

        DatasetTherapy therapy2 = new DatasetTherapy();
        therapy2.setTherapy("Therapy2");
        therapies.add(therapy2);

        datasetDetails.setDatasetTherapies(therapies);

        // Add dummy data for datasetTechAndAssays
        List<DatasetTechAndAssay> techAndAssays = new ArrayList<>();
        DatasetTechAndAssay techAndAssay1 = new DatasetTechAndAssay();
        techAndAssay1.setTechniqueAndAssay("TechAndAssay1");
        techAndAssays.add(techAndAssay1);

        DatasetTechAndAssay techAndAssay2 = new DatasetTechAndAssay();
        techAndAssay2.setTechniqueAndAssay("TechAndAssay2");
        techAndAssays.add(techAndAssay2);

        datasetDetails.setDatasetTechAndAssays(techAndAssays);

        // Add dummy data for datasetIndications
        List<DatasetIndication> indications = new ArrayList<>();
        DatasetIndication indication1 = new DatasetIndication();
        indication1.setIndication("Indication1");
        indications.add(indication1);

        DatasetIndication indication2 = new DatasetIndication();
        indication2.setIndication("Indication2");
        indications.add(indication2);

        datasetDetails.setDatasetIndications(indications);

        // Add dummy data for datasetRoleDetails
        List<DatasetRoleDetails> roleDetails = new ArrayList<>();
        DatasetRoleDetails roleDetail1 = new DatasetRoleDetails();
        roleDetail1.setRole(ApiConstants.ROLE_DATA_SME);
        roleDetail1.setEmail("role1@example.com");
        roleDetail1.setMudid("MUDID1");
        roleDetail1.setName("Name1");
        roleDetails.add(roleDetail1);

        DatasetRoleDetails roleDetail2 = new DatasetRoleDetails();
        roleDetail2.setRole(ApiConstants.ROLE_DATA_STEWARD);
        roleDetail2.setEmail("role2@example.com");
        roleDetail2.setMudid("MUDID2");
        roleDetail2.setName("Name2");
        roleDetails.add(roleDetail2);

        DatasetRoleDetails roleDetail3 = new DatasetRoleDetails();
        roleDetail3.setRole(ApiConstants.ROLE_DATA_OWNER);
        roleDetail3.setEmail("role3@example.com");
        roleDetail3.setMudid("MUDID3");
        roleDetail3.setName("Name3");
        roleDetails.add(roleDetail3);

        datasetDetails.setDatasetRoleDetails(roleDetails);

        return datasetDetails;
    }
}
