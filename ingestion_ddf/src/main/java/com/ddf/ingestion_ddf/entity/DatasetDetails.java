package com.ddf.ingestion_ddf.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import lombok.Data;

import java.util.Date;
import java.util.List;


/**
 * Entity class representing details of a dataset.
 */
@Data
@Entity
@Table(name = "dataset_details")
public class DatasetDetails extends BaseModel{

    /**
     * Unique identifier for the dataset.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long datasetId;

    /**
     * The ingestion request associated with this dataset.
     */
    @OneToOne
    @JoinColumn(name = "ingestion_request_id")
    @JsonIgnore
    private IngestionRequestDetails ingestionRequest;

    /**
     * Name of the dataset.
     */
    private String datasetName;

    /**
     * Origin source of the dataset.
     */
    private String datasetOriginSource;

    /**
     * Reference to the current data location.
     */
    private String currentDataLocationRef;

    /**
     * Flag indicating meteor space domino usage.
     */
    private Boolean meteorSpaceDominoUsageFlag;

    /**
     * Flag indicating IHD (Ingestion History Data).
     */
    private Boolean ihdFlag;

    /**
     * Reference to the dataset's required usage.
     */
    private String datasetRequiredForRef;

    /**
     * Reference to the estimated data volume.
     */
    private String estimatedDataVolumeRef;

    /**
     * Date of analysis initiation.
     */
    private Date analysisInitDt;

    /**
     * Date of analysis completion.
     */
    private Date analysisEndDt;

    /**
     * Flag indicating DTA contract completion.
     */
    private Boolean dtaContractCompleteFlag;

    /**
     * Expected completion date for DTA.
     */
    private Date dtaExpectedCompletionDate;

    /**
     * Reference to the dataset type.
     */
    private String datasetTypeRef;

    /**
     * Reference to the information classification type.
     */
    private String informationClassificationTypeRef;

    /**
     * Reference to the PII (Personally Identifiable Information) type.
     */
    private String piiTypeRef;

    /**
     * Rules for retention of data.
     */
    private String retentionRules;

    /**
     * Date of retention rules contract.
     */
    private Date retentionRulesContractDate;

    /**
     * Partner associated with the contract.
     */
    private String contractPartner;

    /**
     * List of user usage restrictions for the dataset.
     */
    @OneToMany(mappedBy = "datasetId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DatasetUserUsageRestriction> datasetUserUsageRestriction;

    /**
     * List of data categories associated with the dataset.
     */
    @OneToMany(mappedBy = "datasetId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DatasetDataCategory> datasetDataCategories;

    /**
     * List of studies associated with the dataset.
     */
    @OneToMany(mappedBy = "datasetId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DatasetStudy> datasetStudies;

    /**
     * List of therapies associated with the dataset.
     */
    @OneToMany(mappedBy = "datasetId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DatasetTherapy> datasetTherapies;

    /**
     * List of technologies and assays associated with the dataset.
     */
    @OneToMany(mappedBy = "datasetId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DatasetTechAndAssay> datasetTechAndAssays;

    /**
     * List of indications associated with the dataset.
     */
    @OneToMany(mappedBy = "datasetId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DatasetIndication> datasetIndications;

    /**
     * List of role details associated with the dataset.
     */
    @OneToMany(mappedBy = "datasetId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DatasetRoleDetails> datasetRoleDetails;
}

