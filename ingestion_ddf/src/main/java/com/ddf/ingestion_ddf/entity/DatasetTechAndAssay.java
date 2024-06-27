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
 * Entity class representing technical and assay details associated with a dataset.
 */
@Data
@Entity
@Table(name = "dataset_tech_and_assay")
public class DatasetTechAndAssay {

    /**
     * Unique identifier for the technical and assay details.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long datasetTechAndAssayId;

    /**
     * The dataset to which the technical and assay details belong.
     */
    @ManyToOne
    @JoinColumn(name = "dataset_id")
    @JsonIgnore
    private DatasetDetails datasetId;

    /**
     * Technique and assay details.
     */
    private String techniqueAndAssay;

}

