package com.example.cs213project3;

/**
 * This class models a Doctor by extending all the attributes in the Provider class while also including the doctor's specialty and National Provider Identification.
 * @author Christopher Zhuo
 */
public class Doctor extends Provider{
    private Specialty specialty; //encapsulate the rate per visit based on specialty
    private String npi; //National Provider Identification unique to the doctor

    /**
     * A constructor for the Doctor class
     * @param firstName is the first name of the doctor.
     * @param lastName is the last name of the doctor.
     * @param dob is the date of birth of the doctor.
     * @param location is the location of the doctor.
     * @param specialty is the specialty of the doctor.
     * @param npi is the National Provider Identification which is unique to the doctor
     */
    public Doctor(String firstName, String lastName, Date dob, Location location, Specialty specialty, String npi) {
        super(firstName, lastName, dob, location);
        this.specialty = specialty;
        this.npi = npi;
    }

    /**
     * A getter for the specialty of the doctor
     * @return the specialty.
     */
    public Specialty getSpecialty() {
        return specialty;
    }

    /**
     * A getter for the npi of the doctor.
     * @return the npi.
     */
    public String getNpi() {
        return npi;
    }

    /**
     * A getter for rate that the doctor charges which is due to the specialty
     * @return an integer which is the cost of the doctor's charge.
     */
    public int rate() {
        // Return the rate based on the doctor's specialty
        return specialty.getRate();
    }

    /**
     * Returns a String representation of the doctor.
     * @return String representation of the doctor.
     */
    @Override
    public String toString() {
        return "[" + super.toString() + "] [" + specialty.name() + ", #"+npi+"]";
    }
}