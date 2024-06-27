package com.ddf.ingestion_ddf.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit tests for the {@link ApplicationConfiguration} class.
 * This class contains tests for the bean methods defined in the ApplicationConfiguration class.
 */
public class ApplicationConfigurationTest {

    private ApplicationConfiguration configuration;

    /**
     * Sets up the ApplicationConfiguration instance before each test.
     * This method is run before each test method to initialize the configuration object.
     */
    @BeforeEach
    void setUp() {
        configuration = new ApplicationConfiguration();
    }

    /**
     * Tests the requestLoggingFilter method of the ApplicationConfiguration class.
     * Verifies that the requestLoggingFilter method returns a non-null CommonsRequestLoggingFilter object.
     */
    @Test
    void testRequestLoggingFilterBean() {
        // Act: Call the requestLoggingFilter method
        CommonsRequestLoggingFilter loggingFilter = configuration.requestLoggingFilter();

        // Assert: Verify that the returned CommonsRequestLoggingFilter is not null
        assertNotNull(loggingFilter);
    }

    /**
     * Tests the restTemplate method of the ApplicationConfiguration class.
     * Verifies that the restTemplate method returns a non-null RestTemplate object.
     */
    @Test
    void testRestTemplateBean() {
        // Arrange: Create a RestTemplateBuilder instance
        RestTemplateBuilder builder = new RestTemplateBuilder();

        // Act: Call the restTemplate method with the builder
        RestTemplate restTemplate = configuration.restTemplate(builder);

        // Assert: Verify that the returned RestTemplate is not null
        assertNotNull(restTemplate);
    }

    /**
     * Tests the mappingJacksonHttpMessageConverter method of the ApplicationConfiguration class.
     * Verifies that the mappingJacksonHttpMessageConverter method returns a non-null MappingJackson2HttpMessageConverter object.
     * Additionally, verifies that the ObjectMapper within the converter is not null.
     */
    @Test
    void testMappingJacksonHttpMessageConverterBean() {
        // Act: Call the mappingJacksonHttpMessageConverter method
        MappingJackson2HttpMessageConverter converter = configuration.mappingJacksonHttpMessageConverter();

        // Assert: Verify that the returned MappingJackson2HttpMessageConverter is not null
        assertNotNull(converter);
        // Assert: Verify that the ObjectMapper within the converter is not null
        ObjectMapper objectMapper = converter.getObjectMapper();
        assertNotNull(objectMapper);
    }
}
