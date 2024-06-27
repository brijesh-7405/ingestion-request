package com.ddf.ingestion_ddf.service;

import java.util.List;

/**
 * Interface for sending emails using email templates.
 */
public interface EmailTemplateService {

    /**
     * Sends an email using the provided email template to the specified list of receiver emails.
     *
     * @param subject  The subject of the email.
     * @param body  The body of the email.
     * @param receiverEmails The list of receiver emails to whom the email will be sent.
     */
    void sendMail(String subject, String body, List<String> receiverEmails);
}
