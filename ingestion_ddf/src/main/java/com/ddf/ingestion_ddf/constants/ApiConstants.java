package com.ddf.ingestion_ddf.constants;

/**
 * A utility class that contains various constants used across the application.
 * These constants include user-related details, restriction types, roles, email templates, and request details.
 * The class is not meant to be instantiated.
 */
public class ApiConstants {

    // User-related constants
    /** The email of the user who created the resource. */
    public static final String CREATED_BY = "created_by@example.com";
    /** The email of the user who last modified the resource. */
    public static final String MODIFIED_BY = "modified_by@example.com";

    // Restriction type references
    /** The constant for usage restrictions type. */
    public static final String RESTRICTION_TYPE_USAGE = "usage_restrictions";
    /** The constant for user restrictions type. */
    public static final String RESTRICTION_TYPE_USER = "user_restrictions";

    // Role constants
    /** The role constant for data owner. */
    public static final String ROLE_DATA_OWNER = "data owner";
    /** The role constant for data steward. */
    public static final String ROLE_DATA_STEWARD = "data steward";
    /** The role constant for data SME. */
    public static final String ROLE_DATA_SME = "data sme";

    // Email template code constants
    /** The code for the email template. */
    public static final String EMAIL_TEMPLATE_CODE = "INGESTION_REQUEST_STATUS_UPDATE";

    // Requested by constants
    /** The name of the person who requested the resource. */
    public static final String REQUESTED_BY_NAME = "John Doe";
    /** The MUDID of the person who requested the resource. */
    public static final String REQUESTED_BY_MUDID = "JD123";
    /** The email of the person who requested the resource. */
    public static final String REQUESTED_BY_EMAIL = "john.doe@example.com";

    /**
     * Private constructor to prevent instantiation of this utility class.
     * Throws UnsupportedOperationException if an attempt is made to instantiate the class.
     */
    private ApiConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
