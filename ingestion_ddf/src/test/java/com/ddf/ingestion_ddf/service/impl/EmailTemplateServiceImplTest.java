package com.ddf.ingestion_ddf.service.impl;

import com.ddf.ingestion_ddf.entity.EmailTemplate;
import com.ddf.ingestion_ddf.repository.EmailTemplateRepository;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;

/**
 * Unit tests for {@link EmailTemplateServiceImpl}.
 */
@ExtendWith(MockitoExtension.class)
public class EmailTemplateServiceImplTest {

    @Mock
    private JavaMailSender javaMailSender;

    @Mock
    private EmailTemplateRepository emailTemplateRepository;

    @InjectMocks
    private EmailTemplateServiceImpl emailTemplateService;

    private EmailTemplate emailTemplate;
    private List<String> receiverEmails;

    /**
     * Initializes test data before each test case.
     */
    @BeforeEach
    public void setUp() {
        emailTemplate = new EmailTemplate();
        emailTemplate.setTemplateId(1L);
        emailTemplate.setTemplateCode("welcome");
        emailTemplate.setSubject("Welcome");
        emailTemplate.setBody("Welcome to our service!");

        receiverEmails = Arrays.asList("receiver1@example.com", "receiver2@example.com");
    }

    /**
     * Tests the sending of mail.
     */
    @Test
    public void testSendMail() {
        ReflectionTestUtils.setField(emailTemplateService, "senderEmail", "test@example.com");
        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);

        // Act
        emailTemplateService.sendMail(emailTemplate.getSubject(),emailTemplate.getBody(), receiverEmails);

        // Assert
        verify(javaMailSender, times(1)).send((MimeMessage) any());
    }

    /**
     * Tests the sending of mail when an exception occurs.
     */
    @Test
    void testSendMailException() {
        doThrow(RuntimeException.class).when(javaMailSender).createMimeMessage();

        // Call the service method and verify that it throws an exception
        assertThrows(RuntimeException.class, () -> {
            emailTemplateService.sendMail(emailTemplate.getSubject(),emailTemplate.getBody(), receiverEmails);
        });
    }

    /**
     * Tests the sending of mail with invalid arguments.
     */
    @Test
    void testSendMailArgumentException() {
        // Call the service method and verify that it throws an exception
        assertThrows(IllegalArgumentException.class, () -> {
            emailTemplateService.sendMail(emailTemplate.getSubject(),emailTemplate.getBody(), null);
        });
    }
}

