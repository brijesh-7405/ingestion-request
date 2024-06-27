package com.ddf.ingestion_ddf.configurations;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Configuration class for application-level beans and settings.
 * Implements {@link WebMvcConfigurer} for customizing the configuration of Spring MVC.
 */
@Configuration
public class ApplicationConfiguration implements WebMvcConfigurer {

    // bean to log each incoming and outgoing request

    /**
     * Bean to log each incoming and outgoing request.
     *
     * @return a configured {@link CommonsRequestLoggingFilter} for logging HTTP requests.
     */
    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        var loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeClientInfo(false);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(false);
        loggingFilter.setIncludeHeaders(true);
        return loggingFilter;
    }

    /**
     * Bean to create and configure a {@link RestTemplate} for making REST API calls.
     *
     * @param builder a {@link RestTemplateBuilder} for building the RestTemplate.
     * @return a configured {@link RestTemplate}.
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    /**
     * Bean to configure a {@link MappingJackson2HttpMessageConverter} for JSON processing.
     *
     * @return a configured {@link MappingJackson2HttpMessageConverter}.
     */
    @Bean
    public MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter() {
        var converter = new MappingJackson2HttpMessageConverter();
        var mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.ALWAYS);
        converter.setObjectMapper(mapper);
        return converter;
    }

    /**
     * Configures Cross-Origin Resource Sharing (CORS) settings for the application.
     *
     * Allows requests from "http://localhost:4200" with specified methods and enables credentials.
     *
     * @param registry the {@link CorsRegistry} to configure.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

}
