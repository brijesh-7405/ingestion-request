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
 * Entity class representing a study associated with a dataset.
 */
@Data
@Entity
@Table(name = "dataset_study")
public class DatasetStudy {

    /**
     * Unique identifier for the dataset study.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long datasetStudyId;

    /**
     * The dataset to which the study belongs.
     */
    @ManyToOne
    @JoinColumn(name = "dataset_id")
    @JsonIgnore
    private DatasetDetails datasetId;

    /**
     * Identifier for the study.
     */
    private String studyId;

}

