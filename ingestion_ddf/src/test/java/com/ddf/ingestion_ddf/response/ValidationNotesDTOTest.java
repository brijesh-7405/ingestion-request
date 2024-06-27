package com.ddf.ingestion_ddf.response;

import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link ValidationNotesDTO}.
 */
public class ValidationNotesDTOTest {

    /**
     * Tests the setting and getting of fields in the DTO.
     */
    @Test
    public void testGettersAndSetters() throws Exception {
        ValidationNotesDTO validationNotesDTO = new ValidationNotesDTO();
        Long notesId = 1L;
        String notes = "Sample note";
        String createdBy = "user@example.com";
        String modifiedBy = "user2@example.com";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date createdDate = sdf.parse("2023-01-01");
        Date modifiedDate = sdf.parse("2023-01-02");

        validationNotesDTO.setNotesId(notesId);
        validationNotesDTO.setNotes(notes);
        validationNotesDTO.setCreatedBy(createdBy);
        validationNotesDTO.setCreatedDate(createdDate);
        validationNotesDTO.setModifiedBy(modifiedBy);
        validationNotesDTO.setModifiedDate(modifiedDate);

        assertEquals(notesId, validationNotesDTO.getNotesId());
        assertEquals(notes, validationNotesDTO.getNotes());
        assertEquals(createdBy, validationNotesDTO.getCreatedBy());
        assertEquals(createdDate, validationNotesDTO.getCreatedDate());
        assertEquals(modifiedBy, validationNotesDTO.getModifiedBy());
        assertEquals(modifiedDate, validationNotesDTO.getModifiedDate());
    }

    /**
     * Tests the equals and hashCode methods of the DTO.
     */
    @Test
    public void testEqualsAndHashCode() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date createdDate = sdf.parse("2023-01-01");
        Date modifiedDate = sdf.parse("2023-01-02");

        ValidationNotesDTO validationNotesDTO1 = new ValidationNotesDTO();
        validationNotesDTO1.setNotesId(1L);
        validationNotesDTO1.setNotes("Sample note");
        validationNotesDTO1.setCreatedBy("user@example.com");
        validationNotesDTO1.setCreatedDate(createdDate);
        validationNotesDTO1.setModifiedBy("user2@example.com");
        validationNotesDTO1.setModifiedDate(modifiedDate);

        ValidationNotesDTO validationNotesDTO2 = new ValidationNotesDTO();
        validationNotesDTO2.setNotesId(1L);
        validationNotesDTO2.setNotes("Sample note");
        validationNotesDTO2.setCreatedBy("user@example.com");
        validationNotesDTO2.setCreatedDate(createdDate);
        validationNotesDTO2.setModifiedBy("user2@example.com");
        validationNotesDTO2.setModifiedDate(modifiedDate);

        ValidationNotesDTO validationNotesDTO3 = new ValidationNotesDTO();
        validationNotesDTO3.setNotesId(2L);
        validationNotesDTO3.setNotes("Different note");
        validationNotesDTO3.setCreatedBy("user3@example.com");
        validationNotesDTO3.setCreatedDate(createdDate);
        validationNotesDTO3.setModifiedBy("user4@example.com");
        validationNotesDTO3.setModifiedDate(modifiedDate);

        assertEquals(validationNotesDTO1, validationNotesDTO2);
        assertNotEquals(validationNotesDTO1, validationNotesDTO3);
        assertEquals(validationNotesDTO1.hashCode(), validationNotesDTO2.hashCode());
        assertNotEquals(validationNotesDTO1.hashCode(), validationNotesDTO3.hashCode());
    }

    /**
     * Tests the toString method of the DTO.
     */
    @Test
    public void testToString() throws Exception {
        ValidationNotesDTO validationNotesDTO = new ValidationNotesDTO();
        validationNotesDTO.setNotesId(1L);
        validationNotesDTO.setNotes("Sample note");
        validationNotesDTO.setCreatedBy("user@example.com");
        validationNotesDTO.setCreatedDate(null);
        validationNotesDTO.setModifiedBy("user2@example.com");
        validationNotesDTO.setModifiedDate(null);

        String expected = "ValidationNotesDTO(notesId=1, notes=Sample note, createdBy=user@example.com, createdDate=null, modifiedBy=user2@example.com, modifiedDate=null)";
        assertEquals(expected, validationNotesDTO.toString());
    }

    /**
     * Tests the validation of the DTO.
     */
    @Test
    public void testValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        jakarta.validation.Validator validator = factory.getValidator();

        ValidationNotesDTO validationNotesDTO = new ValidationNotesDTO();
        validationNotesDTO.setNotes(""); // Invalid as per @NotBlank

        Errors errors = new BeanPropertyBindingResult(validationNotesDTO, "validationNotesDTO");
        Validator springValidator = new SpringValidatorAdapter(validator);
        springValidator.validate(validationNotesDTO, errors);

        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getFieldErrorCount("notes"));
        assertEquals("Validation Note is required", errors.getFieldError("notes").getDefaultMessage());
    }
}

