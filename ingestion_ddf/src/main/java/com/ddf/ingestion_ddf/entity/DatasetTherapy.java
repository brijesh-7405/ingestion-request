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
 * Entity class representing therapy details associated with a dataset.
 */
@Data
@Entity
@Table(name = "dataset_therapy")
public class DatasetTherapy {


    /**
     * Unique identifier for the therapy details.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long datasetTherapyId;

    /**
     * The dataset to which the therapy details belong.
     */
    @ManyToOne
    @JoinColumn(name = "dataset_id")
    @JsonIgnore
    private DatasetDetails datasetId;

    /**
     * Therapy details.
     */
    private String therapy;

}

