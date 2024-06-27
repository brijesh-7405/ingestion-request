package com.ddf.ingestion_ddf.entity;

import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

/**
 * Entity class representing email templates.
 */
@Data
@Entity
@Table(name = "email_template")
public class EmailTemplate {

    /**
     * Unique identifier for the email template.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long templateId;

    /**
     * Code associated with the email template.
     */
    private String templateCode;

    /**
     * Subject of the email template.
     */
    private String subject;

    /**
     * Body content of the email template.
     */
    private String body;

}

