package com.ddf.ingestion_ddf.annotation;

import com.ddf.ingestion_ddf.validators.ListSizeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Validates that each string in a list does not exceed a specified maximum length.
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * @ListSize(max = 255, message = "Each item must be at most 255 characters long")
 * private List<String> items;
 * }
 * </pre>
 *
 * <p>This annotation is validated by {@link ListSizeValidator}.</p>
 *
 * @see ListSizeValidator
 */
@Constraint(validatedBy = ListSizeValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ListSize {
    /**
     * The error message to be returned if validation fails.
     */
    String message() default "List contains elements longer than {max} characters";

    /**
     * The maximum length for each string in the list.
     */
    int max();

    /**
     * Allows specification of validation groups to which this constraint belongs.
     */
    Class<?>[] groups() default {};

    /**
     * Can be used by clients of the Jakarta Bean Validation API to assign custom payload objects to a constraint.
     */
    Class<? extends Payload>[] payload() default {};
}
