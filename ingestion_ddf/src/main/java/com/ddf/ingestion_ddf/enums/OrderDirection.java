package com.ddf.ingestion_ddf.enums;

/**
 * Enum representing the direction for ordering.
 */
public enum OrderDirection {
    /**
     * Represents the ascending order direction.
     */
    asc("asc"),

    /**
     * Represents the descending order direction.
     */
    desc("desc");

    /**
     * The order of direction.
     */
    private final String direction;

    /**
     * Constructs an {@code OrderDirection} enum with the specified direction.
     *
     * @param direction the direction for ordering
     */
    OrderDirection(String direction) {
        this.direction = direction;
    }

    /**
     * Returns the direction for ordering.
     *
     * @return the direction for ordering
     */
    public String getDirection() {
        return direction;
    }
}
