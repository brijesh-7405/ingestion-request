package com.ddf.ingestion_ddf.validators;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * Utility class for API template validators.
 */
public class ApiTemplateValidators {

    /**
     * Creates an error message from the binding result.
     *
     * @param bindingResult The binding result containing validation errors.
     * @return The error message.
     */
    public static String createErrorMessage(BindingResult bindingResult) {
        StringBuilder errorMessage = new StringBuilder("Validation failed: ");
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMessage.append(fieldError.getField())
                    .append(" - ")
                    .append(fieldError.getDefaultMessage())
                    .append("; ");
        }
        return errorMessage.toString();
    }
}
