package com.example.cs213project3;

/**
 * This class models a Technician by extending all the attributes in the Provider class while also including the doctor's rate per visit
 * @author Christopher Zhuo
 */

public class Technician extends Provider{
    private int ratePerVisit;

    /**
     * A constructor for the Technician class
     * @param firstName is the first name of the doctor.
     * @param lastName is the last name of the doctor.
     * @param dob is the date of birth of the doctor.
     * @param location is the location of the doctor.
     * @param ratePerVisit is in an integer which shows the cost of each visit.
     */
    public Technician(String firstName, String lastName, Date dob, Location location, int ratePerVisit) {
        super(firstName, lastName, dob, location);
        this.ratePerVisit = ratePerVisit;
    }

    /**
     * A getter for rate that the technician charges which is due to the specialty. In code, will get this after Doctor is constructed.
     * @return an integer which is the cost of the doctor's charge.
     */
    @Override
    public int rate() {
        return ratePerVisit; // Technician's rate per visit
    }

    /**
     * Returns a String representation of the Technician.
     * @return String representation of the Technician.
     */
    @Override
    public String toString() {
        return "[" + super.toString() + "][rate: $" + ratePerVisit + ".00]";
    }
}