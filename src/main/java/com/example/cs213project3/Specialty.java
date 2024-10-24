package com.example.cs213project3;

/**
 * This Enum class defines the Specialty of each doctor and the cost of their services
 * @author Christopher Zhuo
 */

public enum Specialty {
    /**
     * Family specialty
     */
    FAMILY(250),
    /**
     * pediactrician specialty
     */
    PEDIATRICIAN(300),
    /**
     * allergist specialty
     */
    ALLERGIST(350);

    private final int charge;
    /**
     * Parameterized Constructor with 1 parameter.
     * Parameters are "local" variables. Ues the same name with the associated
     * instance variables. Use "this" keyword to differentiate between the two.
     * Overloading constructor.
     * @param charge is the cost that the service costs.
     */
    Specialty(int charge) {
        this.charge = charge;
    }

    /**
     * A getter for the rate of the providers' specialties.
     * @return the location.
     */
    public int getRate() {
        return charge;
    }

    /**
     * Turns the specialty data into string format.
     * @return the specialty format in string format.
     */
    @Override
    public String toString() {
        return String.format("%s ($%d)", name(), charge);
    }
}