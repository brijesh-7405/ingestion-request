package com.ddf.ingestion_ddf.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

/**
 * Entity class representing user usage restrictions associated with a dataset.
 */
@Data
@Entity
@Table(name = "dataset_user_usage_restriction")
public class DatasetUserUsageRestriction extends BaseModel{

    /**
     * Unique identifier for the user usage restriction.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usageUserRestrictionId;

    /**
     * The dataset to which the user usage restriction belongs.
     */
    @ManyToOne
    @JoinColumn(name = "dataset_id")
    @JsonIgnore
    private DatasetDetails datasetId;

    /**
     * Reference to the type of restriction.
     */
    private String restrictionTypeRef;

    /**
     * Reference to the restriction.
     */
    private String restrictionRef;

    /**
     * Reason for other restriction.
     */
    private String reasonForOther;

}

