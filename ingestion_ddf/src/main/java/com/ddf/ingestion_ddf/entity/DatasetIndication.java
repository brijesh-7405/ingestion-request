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
 * Entity class representing indications associated with a dataset.
 */
@Data
@Entity
@Table(name = "dataset_indication")
public class DatasetIndication {
    /**
     * Unique identifier for the dataset indication.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long datasetIndicationId;

    /**
     * The dataset to which the indication belongs.
     */
    @ManyToOne
    @JoinColumn(name = "dataset_id")
    @JsonIgnore
    private DatasetDetails datasetId;
    /**
     * The indication associated with the dataset.
     */
    private String indication;

}

