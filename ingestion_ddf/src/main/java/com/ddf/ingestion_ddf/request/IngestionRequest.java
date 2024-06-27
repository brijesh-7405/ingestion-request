package com.ddf.ingestion_ddf.request;

import com.ddf.ingestion_ddf.annotation.ListSize;
import com.ddf.ingestion_ddf.validators.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Data transfer object (DTO) for ingestion requests.
 */
@Data
public class IngestionRequest {

    /**
     * Name of the requester.
     */
    @NotBlank(groups = {ValidationGroups.BasicFieldValidationGroup.class, ValidationGroups.SubmitValidationGroup.class},message = "Requester name is required")
    @Size(max = 255, message = "Requester name must be at most 255 characters long")
    private String requesterName;

    /**
     * MUDID of the requester.
     */
    @NotBlank(groups = {ValidationGroups.BasicFieldValidationGroup.class, ValidationGroups.SubmitValidationGroup.class},message = "Requester MUDID is required")
    @Size(max = 50, message = "Requester MUDID must be at most 50 characters long")
    private String requesterMudid;

    /**
     * Email of the requester.
     */
    @Email(message = "Requester email must be a valid email address")
    @NotBlank(groups = {ValidationGroups.BasicFieldValidationGroup.class, ValidationGroups.SubmitValidationGroup.class},message = "Requester email is required")
    @Size(max = 255, message = "Requester email must be at most 255 characters long")
    private String requesterEmail;

    /**
     * Name of the dataset.
     */
    @NotBlank(groups = {ValidationGroups.BasicFieldValidationGroup.class, ValidationGroups.SubmitValidationGroup.class},message = "Dataset name is required")
    @Size(max = 255, message = "Dataset name must be at most 255 characters long")
    private String datasetName;

    /**
     * Name of the datasetSME for Data SME Role.
     */
    @NotBlank(groups = {ValidationGroups.BasicFieldValidationGroup.class, ValidationGroups.SubmitValidationGroup.class},message = "Dataset SME name is required")
    @Size(max = 255, message = "Dataset SME name must be at most 255 characters long")
    private String datasetSmeName;

    /**
     * MUDID of the datasetSME for Data SME Role.
     */
    @NotBlank(groups = {ValidationGroups.BasicFieldValidationGroup.class, ValidationGroups.SubmitValidationGroup.class},message = "Dataset SME MUDID is required")
    @Size(max = 50, message = "Dataset SME MUDID must be at most 50 characters long")
    private String datasetSmeMudid;

    /**
     * Email of the datasetSME for Data SME Role.
     */
    @Email(message = "Dataset SME email must be a valid email address")
    @NotBlank(groups = {ValidationGroups.BasicFieldValidationGroup.class, ValidationGroups.SubmitValidationGroup.class},message = "Dataset SME email is required")
    @Size(max = 255, message = "Dataset SME email must be at most 255 characters long")
    private String datasetSmeEmail;

    /**
     * Reason for the ingestion request.
     */
    @NotBlank(groups = {ValidationGroups.BasicFieldValidationGroup.class, ValidationGroups.SubmitValidationGroup.class},message = "Request rationale reason is required")
    @Size(max = 255, message = "Request rationale reason must be at most 255 characters long")
    private String requestRationaleReason;

    /**
     * Origin source of the dataset.
     */
    @NotBlank(groups = {ValidationGroups.BasicFieldValidationGroup.class, ValidationGroups.SubmitValidationGroup.class},message = "Dataset origin source is required")
    @Size(max = 255, message = "Dataset origin source must be at most 255 characters long")
    private String datasetOriginSource;

    /**
     * Current data location reference.
     */
    @Size(max = 255, message = "Current Data Location Reference must be at most 255 characters long")
    private String currentDataLocationRef;

    /**
     * Path of the data location.
     */
    @Size(max = 255, message = "Data Location Path must be at most 255 characters long")
    private String dataLocationPath;

    /**
     * Flag indicating meteor space Domino usage.
     */
    @NotNull(groups = {ValidationGroups.BasicFieldValidationGroup.class, ValidationGroups.SubmitValidationGroup.class},message = "Meteor space Domino usage flag is required")
    private Boolean meteorSpaceDominoUsageFlag;

    /**
     * Flag indicating IHD usage.
     */
    @NotNull(groups = {ValidationGroups.BasicFieldValidationGroup.class, ValidationGroups.SubmitValidationGroup.class},message = "IHD flag is required")
    private Boolean ihdFlag;

    /**
     * Reference for dataset requirement.
     */
    @NotBlank(groups = {ValidationGroups.BasicFieldValidationGroup.class, ValidationGroups.SubmitValidationGroup.class},message = "Dataset required for reference is required")
    @Size(max = 255, message = "Dataset required for reference must be at most 255 characters long")
    private String datasetRequiredForRef;

    /**
     * Reference for estimated data volume.
     */
    @Size(max = 255, message = "Estimated Data Volume must be at most 255 characters long")
    private String estimatedDataVolumeRef;

    /**
     * Refresh frequency of the data.
     */
    @NotBlank(groups = {ValidationGroups.PrioritizationDetailValidationGroup.class, ValidationGroups.SubmitValidationGroup.class},message = "Data refresh frequency is required")
    @Size(max = 255, message = "Data refresh frequency must be at most 255 characters long")
    private String dataRefreshFrequency;

    /**
     * Initial analysis date.
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(groups = {ValidationGroups.PrioritizationDetailValidationGroup.class, ValidationGroups.SubmitValidationGroup.class},message = "Analysis init date is required")
    private Date analysisInitDt;

    /**
     * End analysis date.
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(groups = {ValidationGroups.PrioritizationDetailValidationGroup.class, ValidationGroups.SubmitValidationGroup.class},message = "Analysis end date is required")
    private Date analysisEndDt;

    /**
     * Flag indicating DTA contract completion.
     */
    @NotNull(groups = {ValidationGroups.PrioritizationDetailValidationGroup.class, ValidationGroups.SubmitValidationGroup.class},message = "DTA contract complete flag is required")
    private Boolean dtaContractCompleteFlag;

    /**
     * Expected completion date for DTA.
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dtaExpectedCompletionDate;

    /**
     * List of data category references.
     */
    @ListSize(max = 255, message = "Each data category reference must be at most 255 characters long")
    private List<String> dataCategoryRefs;

    /**
     * Reference for dataset type.
     */
    @NotBlank(groups = {ValidationGroups.DatasetDetailsValidationGroup.class, ValidationGroups.SubmitValidationGroup.class},message = "Dataset type reference is required")
    @Size(max = 255, message = "Dataset type reference must be at most 255 characters long")
    private String datasetTypeRef;

    /**
     * List of study IDs.
     */
    @ListSize(max = 255, message = "Each studyId must be at most 255 characters long")
    private List<String> studyIds;

    /**
     * Name of the dataset owner for Data owner role.
     */
    @Size(max = 255, message = "Dataset Owner Name must be at most 255 characters long")
    private String datasetOwnerName;

    /**
     * MUDID of the dataset owner for Data owner role.
     */
    @Size(max = 50, message = "Dataset owner MUDID must be at most 255 characters long")
    private String datasetOwnerMudid;

    /**
     * Email of the dataset owner for Data owner role.
     */
    @Size(max = 255, message = "Dataset owner email must be at most 255 characters long")
    private String datasetOwnerEmail;

    /**
     * Name of the dataset steward for Data steward role.
     */
    @Size(max = 255, message = "Dataset steward name must be at most 255 characters long")
    private String datasetStewardName;

    /**
     * MUDID of the dataset steward for Data steward role.
     */
    @Size(max = 255, message = "Dataset steward mudid must be at most 255 characters long")
    private String datasetStewardMudid;

    /**
     * Email of the dataset steward for Data steward role.
     */
    @Size(max = 255, message = "Dataset steward email must be at most 255 characters long")
    private String datasetStewardEmail;

    /**
     * Contract partner associated with the dataset.
     */
    @Size(max = 255, message = "Dataset owner email must be at most 255 characters long")
    private String contractPartner;

    /**
     * Retention rules for the dataset.
     */
    @Size(max = 255, message = "Retention rules must be at most 255 characters long")
    private String retentionRules;

    /**
     * Contract date for retention rules.
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date retentionRulesContractDate;

    /**
     * List of usage restrictions.
     */
    @ListSize(max = 255, message = "Each usage restrictions must be at most 255 characters long")
    private List<String> usageRestrictions;

    /**
     * List of user restrictions.
     */
    @ListSize(max = 255, message = "Each user restrictions must be at most 255 characters long")
    private List<String> userRestrictions;

    /**
     * Reference for information classification type.
     */
    @Size(max = 255, message = "Information Classification Type must be at most 255 characters long")
    private String informationClassificationTypeRef;

    /**
     * Reference for PII type.
     */
    @Size(max = 255, message = "PII type must be at most 255 characters long")
    private String piiTypeRef;

    /**
     * List of therapy areas associated with the dataset.
     */
    @ListSize(max = 255, message = "Each therapy area must be at most 255 characters long")
    private List<String> therapyAreas;

    /**
     * List of techniques and assays used in the dataset.
     */
    @ListSize(max = 255, message = "Each technique and assay must be at most 255 characters long")
    private List<String> techniqueAndAssays;

    /**
     * List of indications for the dataset.
     */
    @ListSize(max = 255, message = "Each indications must be at most 255 characters long")
    private List<String> indications;

    /**
     * Target ingestion start date.
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date targetIngestionStartDate;

    /**
     * Target ingestion end date.
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date targetIngestionEndDate;

    /**
     * Target path for ingestion.
     */
    @Size(max = 255, message = "Target path must be at most 255 characters long")
    private String targetPath;

    /**
     * Reference for dataset type ingestion.
     */
    @Size(max = 255, message = "Dataset Type Ingestion Ref must be at most 255 characters long")
    private String datasetTypeIngestionRef;

    /**
     * List of guest users' emails.
     */
    @ListSize(max = 255, message = "Each guest users email must be at most 255 characters long")
    private List<String> guestUsersEmail;

    /**
     * List of whitelisted IP addresses.
     */
    @ListSize(max = 255, message = "Each whitelist Ip addresses must be at most 255 characters long")
    private List<String> whitelistIpAddresses;

    /**
     * Name of the external staging container.
     */
    @Size(max = 255, message = "External Staging Container Name must be at most 255 characters long")
    private String externalStagingContainerName;

    /**
     * Domain request ID.
     */
    @Size(max = 50, message = "Domain Request Id must be at most 255 characters long")
    private String domainRequestId;

    /**
     * Location of the external data source.
     */
    @Size(max = 255, message = "External DataSource Location must be at most 255 characters long")
    private String externalDataSourceLocation;

    /**
     * GSK access source location reference.
     */
    @Size(max = 255, message = "gskAccessSourceLocationRef must be at most 255 characters long")
    private String gskAccessSourceLocationRef;

    /**
     * Name of the external source secret key.
     */
    @Size(max = 255, message = "External Source Secret Key Name must be at most 255 characters long")
    private String externalSourceSecretKeyName;

    /**
     * Modified Reason why user want to update the request.
     */
    @Size(max = 255, message = "Modified reason must be at most 255 characters long")
    private String modifiedReason;
}