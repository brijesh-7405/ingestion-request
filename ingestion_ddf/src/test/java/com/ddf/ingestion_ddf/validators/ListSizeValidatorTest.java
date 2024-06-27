package com.ddf.ingestion_ddf.validators;

import com.ddf.ingestion_ddf.annotation.ListSize;
import jakarta.validation.Payload;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for {@link ListSizeValidator}.
 * Tests the validation of lists of strings to ensure each element does not exceed the specified maximum length.
 */
public class ListSizeValidatorTest {

    private ListSizeValidator validator;

    /**
     * Sets up the test environment by initializing the {@link ListSizeValidator} with a max length of 255.
     */
    @BeforeEach
    public void setUp() {
        validator = new ListSizeValidator();
        ListSize listSize = new ListSize() {
            @Override
            public Class<? extends ListSize> annotationType() {
                return ListSize.class;
            }

            @Override
            public String message() {
                return "List contains elements longer than {max} characters";
            }

            @Override
            public int max() {
                return 255;
            }

            @Override
            public Class<?>[] groups() {
                return new Class[0];
            }

            @Override
            public Class<? extends Payload>[] payload() {
                return new Class[0];
            }
        };
        validator.initialize(listSize);
    }

    /**
     * Tests a valid list where all strings are within the maximum length.
     */
    @Test
    public void testValidList() {
        List<String> validList = Arrays.asList("short", "medium length", "a".repeat(255));
        assertTrue(validator.isValid(validList, null));
    }

    /**
     * Tests an invalid list where one string exceeds the maximum length.
     */
    @Test
    public void testInvalidList() {
        List<String> invalidList = Arrays.asList("short", "this is too long".repeat(16));
        assertFalse(validator.isValid(invalidList, null));
    }

    /**
     * Tests a null list, which should be considered valid.
     */
    @Test
    public void testNullList() {
        assertTrue(validator.isValid(null, null));
    }

    /**
     * Tests an empty list, which should be considered valid.
     */
    @Test
    public void testEmptyList() {
        List<String> emptyList = Collections.emptyList();
        assertTrue(validator.isValid(emptyList, null));
    }
}
