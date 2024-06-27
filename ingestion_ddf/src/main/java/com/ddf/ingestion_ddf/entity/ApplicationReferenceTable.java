package com.ddf.ingestion_ddf.entity;

import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.io.Serializable;

/**
 * Represents an entry in the application reference table.
 * This entity contains reference data and associated metadata.
 */
@Data
@Entity
@Table(name = "application_reference_table")
public class ApplicationReferenceTable extends BaseModel implements Serializable {
    /**
     * The unique identifier for the reference entry.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long referenceId;

    /**
     * The reference data.
     */
    private String referenceData;
    /**
     * The type of the reference data.
     */
    private String referenceDataType;

    /**
     * The group type of the reference data.
     */
    private String referenceGroupType;

    /**
     * The order of the reference data within its group.
     */
    private Long referenceOrder;

}
