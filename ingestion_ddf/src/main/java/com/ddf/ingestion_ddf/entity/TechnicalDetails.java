package com.ddf.ingestion_ddf.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import java.util.Date;

/**
 * Entity class representing technical details related to ingestion requests.
 */
@Data
@Entity
@Table(name = "technical_details")
public class TechnicalDetails extends BaseModel{

    /**
     * Unique identifier for the technical request.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long technicalRequestId;

    /**
     * Ingestion request associated with these technical details.
     */
    @OneToOne
    @JoinColumn(name = "ingestion_request_id")
    @JsonIgnore
    private IngestionRequestDetails ingestionRequest;

    /**
     * Path of the data location.
     */
    private String dataLocationPath;
    /**
     * Frequency of data refresh.
     */
    private String dataRefreshFrequency;
    /**
     * Target ingestion start date.
     */
    private Date targetIngestionStartDate;
    /**
     * Target ingestion end date.
     */
    private Date targetIngestionEndDate;
    /**
     * Target path for ingestion.
     */
    private String targetPath;
    /**
     * Reference to dataset type for ingestion.
     */
    private String datasetTypeIngestionRef;
    /**
     * Email addresses of guest users.
     */
    private String guestUsersEmail;
    /**
     * Whitelisted IP addresses.
     */
    private String whitelistIpAddresses;
    /**
     * External staging container name.
     */
    private String externalStagingContainerName;
    /**
     * External data source location.
     */
    private String externalDataSourceLocation;
    /**
     * Reference to GSK access source location.
     */
    private String gskAccessSourceLocationRef;
    /**
     * Name of external source secret key.
     */
    private String externalSourceSecretKeyName;
    /**
     * Domain request identifier.
     */
    private String domainRequestId;
    /**
     * Identification of existing data location.
     */
    private String existingDataLocationIdentified;

}

