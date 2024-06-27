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
 * Entity class representing the relationship between a dataset and its associated data category.
 */
@Data
@Entity
@Table(name = "dataset_data_category")
public class DatasetDataCategory {

    /**
     * Unique identifier for the dataset data category.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long datasetDataCategoryId;

    /**
     * The dataset to which this data category belongs.
     */
    @ManyToOne
    @JoinColumn(name = "dataset_id")
    @JsonIgnore
    private DatasetDetails datasetId;

    /**
     * Reference to the data category.
     */
    private String dataCategoryRef;

}
