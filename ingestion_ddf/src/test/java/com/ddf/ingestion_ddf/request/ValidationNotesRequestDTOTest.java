package com.ddf.ingestion_ddf.request;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for validating the behavior of the {@link ValidationNotesRequestDTO} class.
 * This class tests various scenarios related to the validation of notes in the DTO.
 */
public class ValidationNotesRequestDTOTest {

    private Validator validator;
    private SpringValidatorAdapter springValidator;

    /**
     * Sets up the necessary components before each test.
     */
    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        springValidator = new SpringValidatorAdapter(validator);
    }

    /**
     * Tests the getters and setters of the ValidationNotesRequestDTO class.
     */
    @Test
    public void testGettersAndSetters() {
        // Create an instance of the DTO
        ValidationNotesRequestDTO dto = new ValidationNotesRequestDTO();
        dto.setNotes("This is a test note.");

        // Assert that the getter returns the expected value
        assertEquals("This is a test note.", dto.getNotes());
    }

    /**
     * Tests the scenario when the notes are valid.
     */
    @Test
    public void testValidNotes() {
        ValidationNotesRequestDTO validationNotesRequestDTO = new ValidationNotesRequestDTO();
        validationNotesRequestDTO.setNotes("This is a valid note.");

        Errors errors = validate(validationNotesRequestDTO);

        assertTrue(!errors.hasErrors());
    }

    /**
     * Tests the scenario when the notes are empty.
     */
    @Test
    public void testEmptyNotes() {
        ValidationNotesRequestDTO validationNotesRequestDTO = new ValidationNotesRequestDTO();
        validationNotesRequestDTO.setNotes("");

        Errors errors = validate(validationNotesRequestDTO);

        assertTrue(errors.hasErrors());
        assertEquals("Note is required", errors.getFieldError("notes").getDefaultMessage());
    }

    /**
     * Tests the scenario when the notes exceed the maximum allowed length.
     */
    @Test
    public void testNotesExceedingMaxLength() {
        ValidationNotesRequestDTO validationNotesRequestDTO = new ValidationNotesRequestDTO();
        validationNotesRequestDTO.setNotes("This note is intentionally made very long to exceed the maximum length of 255 characters. ".repeat(10));

        Errors errors = validate(validationNotesRequestDTO);

        assertTrue(errors.hasErrors());
        assertEquals("Notes must be at most 255 characters long", errors.getFieldError("notes").getDefaultMessage());
    }

    /**
     * Validates the given ValidationNotesRequestDTO and returns the validation errors.
     *
     * @param validationNotesRequestDTO The ValidationNotesRequestDTO to be validated.
     * @return The validation errors.
     */
    private Errors validate(ValidationNotesRequestDTO validationNotesRequestDTO) {
        Errors errors = new BeanPropertyBindingResult(validationNotesRequestDTO, "validationNotesRequestDTO");
        springValidator.validate(validationNotesRequestDTO, errors);
        return errors;
    }
}

