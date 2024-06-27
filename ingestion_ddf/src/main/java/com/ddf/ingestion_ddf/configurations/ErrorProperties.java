package com.ddf.ingestion_ddf.configurations;

import java.util.HashMap;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
import lombok.Setter;

/**
 * Configuration class for loading error properties from a properties file.
 * This class is used to map error codes and messages defined in the properties file.
 */
@Getter
@Setter
@Configuration
@PropertySource("classpath:error.properties")
@ConfigurationProperties
public class ErrorProperties {
    /**
     * A map that holds error codes and their corresponding messages.
     * This map is populated with values from the 'error.properties' file.
     */
    private Map<String, String> errors = new HashMap<>();

}
