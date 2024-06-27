package com.ddf.ingestion_ddf.exception.handler;

import com.ddf.ingestion_ddf.configurations.ErrorProperties;
import com.ddf.ingestion_ddf.exception.CustomResponseStatusException;
import com.ddf.ingestion_ddf.exception.IngestionRequestDetailsNotFoundException;
import com.ddf.ingestion_ddf.exception.IngestionRequestStatusException;
import com.ddf.ingestion_ddf.exception.ValidationNotesNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link GlobalExceptionHandler}.
 */
public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler exceptionHandler;

    /**
     * Setup method executed before each test method.
     */
    @BeforeEach
    void setUp() {
        ErrorProperties errorProperties = new ErrorProperties();
        Map<String, String> errors = new HashMap<>();
        errors.put("ingestion.request.not.exist", "Ingestion Request Not Exists");
        errors.put("bad_request.invalid_field", "Invalid Field Value");
        errors.put("status_update", "Status Update Failed");
        errors.put("notes", "notes not exist");
        errors.put("bad_request", "Bad Request");
        errors.put("not_found", "Not Found");
        errorProperties.setErrors(errors);
        // Initialize ErrorProperties as required for testing
        // For simplicity, we won't set actual error messages here
        exceptionHandler = new GlobalExceptionHandler(errorProperties);
    }

    /**
     * Test case for handling IngestionRequestDetailsNotFoundException.
     */
    @Test
    void testHandleIngestionRequestNotFoundException() {
        // Arrange
        IngestionRequestDetailsNotFoundException ex = new IngestionRequestDetailsNotFoundException("1");
        // Act
        ResponseEntity<Map<String, Object>> responseEntity = exceptionHandler.handleIngestionRequestNotFoundException(ex);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Ingestion Request Not Exists", responseEntity.getBody().get("message"));
    }

    /**
     * Test case for handling MethodArgumentTypeMismatchException.
     */
    @Test
    void testHandleMethodArgumentTypeMismatchException() {
        // Arrange
        MethodArgumentTypeMismatchException ex = new MethodArgumentTypeMismatchException("fieldValue", null, "fieldName", null, null);

        // Act
        ResponseEntity<Map<String, Object>> responseEntity = exceptionHandler.handleMethodArgumentTypeMismatchException(ex);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Invalid Field Value", responseEntity.getBody().get("message"));
    }

    /**
     * Test case for handling CustomResponseStatusException.
     */
    @Test
    void testHandleResponseStatusException() {
        // Arrange
        CustomResponseStatusException ex = new CustomResponseStatusException("Custom exception");
        // Act
        ResponseEntity<Map<String, Object>> responseEntity = exceptionHandler.handleResponseStatusException(ex);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Custom exception", responseEntity.getBody().get("message"));
    }

    /**
     * Test case for handling IngestionRequestStatusException.
     */
    @Test
    void testHandleIngestionRequestStatusException() {
        // Arrange
        IngestionRequestStatusException ex = new IngestionRequestStatusException("Status Update Failed");

        // Act
        ResponseEntity<Map<String, Object>> responseEntity = exceptionHandler.handleIngestionRequestStatusException(ex);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Status Update Failed", responseEntity.getBody().get("message"));
    }

    /**
     * Test case for handling ValidationNotesNotFoundException.
     */
    @Test
    void testHandleValidationNotesNotFoundException() {
        // Arrange
        ValidationNotesNotFoundException ex = new ValidationNotesNotFoundException("Notes not found");

        // Act
        ResponseEntity<Map<String, Object>> responseEntity = exceptionHandler.handleValidationNotesNotFoundException(ex);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("notes not exist", responseEntity.getBody().get("message"));
    }

}

