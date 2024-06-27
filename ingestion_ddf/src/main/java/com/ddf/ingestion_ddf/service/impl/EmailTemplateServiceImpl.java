package com.ddf.ingestion_ddf.service.impl;

import com.ddf.ingestion_ddf.service.EmailTemplateService;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the {@link EmailTemplateService} interface to send emails asynchronously.
 * This service interacts with the {@link JavaMailSender} to send MIME messages.
 */
@Service
@Slf4j
public class EmailTemplateServiceImpl implements EmailTemplateService {

    /**
     * JavaMailSender instance provided by Spring framework to send emails.
     */
    private final JavaMailSender javaMailSender;

    /**
     * The email address of the sender.
     */
    @Value("${sender.email}")
    private String senderEmail;

    /**
     * Constructs a new {@code EmailTemplateServiceImpl} with the specified JavaMailSender.
     *
     * @param javaMailSender the JavaMailSender instance used for sending emails
     */
    public EmailTemplateServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /**
     * Sends an email asynchronously using the provided email template and receiver email addresses.
     *
     * @param subject  The subject of the email.
     * @param body  The body of the email.
     * @param receiverEmails The list of receiver email addresses.
     * @throws IllegalArgumentException if receiverEmails is null or empty
     * @throws RuntimeException         if there's an error while sending the email
     */
    @Override
    @Async("taskExecutor")  // Executes asynchronously using Spring's taskExecutor bean
    public void sendMail(String subject, String body, List<String> receiverEmails) {
        if (receiverEmails == null || receiverEmails.isEmpty()) {
            throw new IllegalArgumentException("Receiver emails cannot be null or empty");
        }
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(senderEmail);
            mimeMessageHelper.setTo(receiverEmails.toArray(new String[0]));
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body);

            javaMailSender.send(mimeMessage);
            log.info("Email sent successfully.");
        } catch (Exception e) {
            log.error("Failed to send email: {}", e.getMessage());
            throw new RuntimeException("Failed to send email", e);
        }
    }


}
