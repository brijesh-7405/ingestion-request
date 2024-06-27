package com.ddf.ingestion_ddf.validators;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link ApiTemplateValidators}.
 */
public class ApiTemplateValidatorsTest {

    /**
     * Tests the {@link ApiTemplateValidators#createErrorMessage(BindingResult)} method with various input scenarios.
     *
     * @param fieldErrors          The list of field errors to be used as input.
     * @param expectedErrorMessage The expected error message.
     */
    @ParameterizedTest
    @MethodSource("createTestData")
    public void testCreateErrorMessage(List<FieldError> fieldErrors, String expectedErrorMessage) {
        // Arrange
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);

        // Act
        String result = ApiTemplateValidators.createErrorMessage(bindingResult);

        // Assert
        assertEquals(expectedErrorMessage, result);
    }

    /**
     * Provides test data for parameterized tests.
     *
     * @return Stream of object arrays containing input field errors and expected error messages.
     */
    private static Stream<Object[]> createTestData() {
        return Stream.of(
                new Object[]{List.of(), "Validation failed: "},
                new Object[]{List.of(new FieldError("objectName", "fieldName", "defaultMessage")), "Validation failed: fieldName - defaultMessage; "},
                new Object[]{List.of(new FieldError("objectName", "fieldName1", "defaultMessage1"), new FieldError("objectName", "fieldName2", "defaultMessage2")), "Validation failed: fieldName1 - defaultMessage1; fieldName2 - defaultMessage2; "},
                new Object[]{List.of(new FieldError("objectName", "fieldName", "")), "Validation failed: fieldName - ; "}
        );
    }
}

