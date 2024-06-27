package com.ddf.ingestion_ddf.request;

import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Test class for {@link DecisionRequestDTO}.
 */
public class DecisionRequestDTOTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * Test valid DecisionRequestDTO.
     */
    @Test
    public void testValidDecisionRequestDTO() {
        // Arrange
        DecisionRequestDTO decisionRequestDTO = new DecisionRequestDTO();
        decisionRequestDTO.setDecisionComments("Approved");
        decisionRequestDTO.setNotifyThroughEmail(true);
        decisionRequestDTO.setRejectionReason(null);
        decisionRequestDTO.setExistingDataLocationIdentified("Data Warehouse");

        // Assert
        assertEquals("Approved", decisionRequestDTO.getDecisionComments());
        assertTrue(decisionRequestDTO.getNotifyThroughEmail());
        assertNull(decisionRequestDTO.getRejectionReason());
        assertEquals("Data Warehouse", decisionRequestDTO.getExistingDataLocationIdentified());

        // Validate no errors
        Errors errors = validateDecisionRequestDTO(decisionRequestDTO);

        assertFalse(errors.hasErrors());
    }

    /**
     * Test invalid DecisionRequestDTO with empty comments.
     */
    @Test
    public void testInvalidDecisionRequestDTOWithEmptyComments() {
        // Arrange
        DecisionRequestDTO decisionRequestDTO = new DecisionRequestDTO();
        decisionRequestDTO.setDecisionComments(""); // Invalid as per @NotBlank
        decisionRequestDTO.setNotifyThroughEmail(true);

        // Act
        Errors errors = validateDecisionRequestDTO(decisionRequestDTO);

        // Assert
        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getFieldErrorCount("decisionComments"));
        assertEquals("Comments are required", errors.getFieldError("decisionComments").getDefaultMessage());
    }

    /**
     * Test invalid DecisionRequestDTO with null notifyThroughEmail.
     */
    @Test
    public void testInvalidDecisionRequestDTOWithNullNotifyThroughEmail() {
        // Arrange
        DecisionRequestDTO decisionRequestDTO = new DecisionRequestDTO();
        decisionRequestDTO.setDecisionComments("Approved");
        decisionRequestDTO.setNotifyThroughEmail(null); // Invalid as per @NotNull

        // Act
        Errors errors = validateDecisionRequestDTO(decisionRequestDTO);

        // Assert
        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getFieldErrorCount("notifyThroughEmail"));
        assertEquals("Notify Email flag is required", errors.getFieldError("notifyThroughEmail").getDefaultMessage());
    }

    /**
     * Test invalid DecisionRequestDTO with long comments.
     */
    @Test
    public void testInvalidDecisionRequestDTOWithLongComments() {
        // Arrange
        String longComment = "A".repeat(256); // 256 characters long string
        DecisionRequestDTO decisionRequestDTO = new DecisionRequestDTO();
        decisionRequestDTO.setDecisionComments(longComment); // Invalid as per @Size(max = 255)
        decisionRequestDTO.setNotifyThroughEmail(true);

        // Act
        Errors errors = validateDecisionRequestDTO(decisionRequestDTO);

        // Assert
        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getFieldErrorCount("decisionComments"));
        assertEquals("Decision Comments must be at most 255 characters long", errors.getFieldError("decisionComments").getDefaultMessage());
    }

    /**
     * Test invalid DecisionRequestDTO with multiple errors.
     */
    @Test
    public void testInvalidDecisionRequestDTOWithMultipleErrors() {
        // Arrange
        String longComment = "A".repeat(256); // 256 characters long string
        DecisionRequestDTO decisionRequestDTO = new DecisionRequestDTO();
        decisionRequestDTO.setDecisionComments(longComment); // Invalid as per @Size(max = 255)
        decisionRequestDTO.setNotifyThroughEmail(null); // Invalid as per @NotNull

        // Act
        Errors errors = validateDecisionRequestDTO(decisionRequestDTO);

        // Assert
        assertTrue(errors.hasErrors());
        assertEquals(2, errors.getErrorCount());

        assertEquals("Decision Comments must be at most 255 characters long", errors.getFieldError("decisionComments").getDefaultMessage());
        assertEquals("Notify Email flag is required", errors.getFieldError("notifyThroughEmail").getDefaultMessage());
    }

    /**
     * Validates DecisionRequestDTO and returns Errors.
     *
     * @param decisionRequestDTO DecisionRequestDTO object to be validated
     * @return Errors object containing validation errors
     */
    private Errors validateDecisionRequestDTO(DecisionRequestDTO decisionRequestDTO) {
        Errors errors = new BeanPropertyBindingResult(decisionRequestDTO, "decisionRequestDTO");
        SpringValidatorAdapter springValidator = new SpringValidatorAdapter(validator);
        springValidator.validate(decisionRequestDTO, errors);
        return errors;
    }
}
