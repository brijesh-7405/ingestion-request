package com.ddf.ingestion_ddf.validators;

import com.ddf.ingestion_ddf.annotation.ListSize;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * Validator for the {@link ListSize} annotation.
 * Ensures that each string in a list does not exceed a specified maximum length.
 */
public class ListSizeValidator implements ConstraintValidator<ListSize, List<String>> {

    /**
     * The maximum length constraint value specified by the {@link ListSize} annotation.
     */
    private int max;

    /**
     * Initializes the validator with the maximum length from the {@link ListSize} annotation.
     *
     * @param constraintAnnotation the annotation instance for a given constraint declaration
     */
    @Override
    public void initialize(ListSize constraintAnnotation) {
        this.max = constraintAnnotation.max();
    }

    /**
     * Validates that each string in the provided list does not exceed the specified maximum length.
     *
     * @param list the list of strings to validate
     * @param context context in which the constraint is evaluated
     * @return {@code true} if the list is valid (null or all strings within the maximum length), {@code false} otherwise
     */
    @Override
    public boolean isValid(List<String> list, ConstraintValidatorContext context) {
        if (list == null) {
            return true;
        }
        for (String item : list) {
            if (item == null || item.length() > max) {
                return false;
            }
        }
        return true;
    }
}


