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
 * Entity class representing role details associated with a dataset.
 */
@Data
@Entity
@Table(name = "dataset_role_details")
public class DatasetRoleDetails {

    /**
     * Unique identifier for the dataset role details.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long datasetRoleDetailsId;
    /**
     * The dataset to which the role details belong.
     */
    @ManyToOne
    @JoinColumn(name = "dataset_id")
    @JsonIgnore
    private DatasetDetails datasetId;
    /**
     * The role associated with the dataset.
     */
    private String role;
    /**
     * Email address associated with the role.
     */
    private String email;

    /**
     * Mudid associated with the role.
     */
    private String mudid;

    /**
     * Name associated with the role.
     */
    private String name;

}

