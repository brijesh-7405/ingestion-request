package com.ddf.ingestion_ddf.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

import java.util.Date;
/**
 * Base model class for common entity fields.
 * This class contains fields for tracking creation and modification details.
 */
@Data
@Embeddable
@MappedSuperclass
public class BaseModel {
    /**
     * The username of the user who created the entity.
     */
    @Column(name = "created_by", insertable = true, updatable = false,nullable = false)
    private String createdBy;

    /**
     * The date and time when the entity was created.
     */
    @Column(name = "created_date", insertable = true, updatable = false,nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    /**
     * The username of the user who last modified the entity.
     */
    @Column(name = "modified_by", insertable = true, updatable = true)
    private String modifiedBy;

    /**
     * The date and time when the entity was last modified.
     */
    @Column(name = "modified_date", insertable = true, updatable = true)
    private Date modifiedDate;

    /**
     * Automatically sets the creation and modification dates before persisting a new entity.
     */
    @PrePersist
    protected void onCreate() {
        this.createdDate = new Date();
        this.modifiedDate = new Date();
    }

    /**
     * Automatically updates the modification date before updating an existing entity.
     */
    @PreUpdate
    protected void onUpdate() {
        this.modifiedDate = new Date();
    }
}