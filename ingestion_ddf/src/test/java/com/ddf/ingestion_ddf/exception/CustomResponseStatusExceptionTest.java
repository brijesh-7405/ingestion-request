package com.ddf.ingestion_ddf.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for {@link CustomResponseStatusException}.
 */
public class CustomResponseStatusExceptionTest {

    /**
     * Test constructor with a message provided and the exception thrown with the message.
     */
    @Test
    void testConstructorWithMessageProvidedAndExceptionThrownWithMessage() {
        // Arrange
        String errorMessage = "Error Message";

        // Act
        CustomResponseStatusException exception = assertThrows(CustomResponseStatusException.class,
                () -> {
                    throw new CustomResponseStatusException(errorMessage);
                });

        // Assert
        assertEquals(errorMessage, exception.getMessage());
    }

    /**
     * Test constructor with a null message provided and the exception thrown with a null message.
     */
    @Test
    void testConstructorWithNullMessageProvidedAndExceptionThrownWithNullMessage() {
        // Act & Assert
        assertThrows(CustomResponseStatusException.class,
                () -> {
                    throw new CustomResponseStatusException(null);
                });
    }

    /**
     * Test getMessage() method with an exception having a message, and the same message should be returned.
     */
    @Test
    void testGetMessageWithExceptionWithMessageAndExceptionMessageReturned() {
        // Arrange
        String errorMessage = "Error Message";
        CustomResponseStatusException exception = new CustomResponseStatusException(errorMessage);

        // Act
        String message = exception.getMessage();

        // Assert
        assertEquals(errorMessage, message);
    }
}

