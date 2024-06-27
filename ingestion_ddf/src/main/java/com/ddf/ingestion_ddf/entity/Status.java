package com.ddf.ingestion_ddf.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;

import java.util.List;

/**
 * Entity class representing status information.
 */
@Data
@Entity
@Table(name = "status")
public class Status {

    /**
     * Unique identifier for the status.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statusId;

    /**
     * Code representing the status.
     */
    private String statusCode;

    /**
     * Name of the status.
     */
    private String statusName;

    /**
     * List of request status details associated with this status.
     */
    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<RequestStatusDetails> requestStatusDetails;


}

