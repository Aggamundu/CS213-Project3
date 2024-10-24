package com.example.cs213project3;

/**
 * Enum representing different types of radiology imaging services.
 * Includes CATSCAN, ULTRASOUND, and XRAY.
 */
public enum Radiology {
    /**
     * CATSCAN imaging service
     */
    CATSCAN,
    /**
     * ULTRASOUND imaging service
     */
    ULTRASOUND,
    /**
     * XRAY imaging service
     */
    XRAY;

    /**
     * Returns a string representation of the imaging service type.
     * @return a string representation of the service
     */
    @Override
    public String toString() {
        // Customize the display format if necessary, else the enum name is returned
        return name();
    }
}