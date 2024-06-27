package com.ddf.ingestion_ddf.constants;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the {@link ApiConstants} class.
 * This class contains tests to verify the behavior of the ApiConstants class, particularly its private constructor.
 */
public class ApiConstantsTest {

    /**
     * Tests that the private constructor of the ApiConstants class throws an InvocationTargetException.
     * This ensures that the class cannot be instantiated via reflection.
     */
    @Test
    void testPrivateConstructorThrowsException() {
        // Arrange: Get the constructor of the ApiConstants class
        Class<ApiConstants> clazz = ApiConstants.class;
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();

        // Act & Assert: Verify that invoking the private constructor throws an InvocationTargetException
        assertThrows(InvocationTargetException.class, () -> {
            constructors[0].setAccessible(true);
            constructors[0].newInstance();
        });
    }
}
