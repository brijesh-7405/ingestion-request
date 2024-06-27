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
 * Entity class representing validation notes associated with ingestion requests.
 */
@Data
@Entity
@Table(name = "validation_notes")
public class ValidationNotes extends BaseModel{

    /**
     * Unique identifier for the validation notes.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notesId;

    /**
     * Ingestion request associated with these validation notes.
     */
    @ManyToOne
    @JoinColumn(name = "ingestion_request_id")
    @JsonIgnore
    private IngestionRequestDetails ingestionRequest;

    /**
     * Notes related to validation.
     */
    private String notes;

}
