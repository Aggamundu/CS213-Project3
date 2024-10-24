package com.example.cs213project3;

/**
 * This class models a real-world Appointment tracker that tracks the date, timeslot, patient (as a Person),
 * and provider (as a Person) of a person's appointment.
 * This class must implement/override the equals(), toString(), and the compareTo() methods.
 * @author Christopher
 */
public class Appointment implements Comparable<Appointment> {
    /**
     * Appointment date
     */
    protected Date date;
    /**
     * Appt timeslot
     */
    protected Timeslot timeslot;
    /**
     * appt patient
     */
    protected Person patient;
    /**
     * appt provider
     */
    protected Person provider;

    /**
     * Parameterized Constructor with 4 parameters.
     * @param dates the date of the new event object.
     * @param timeslots the timeslot of the new appointment object.
     * @param patients the patient of the new appointment, now as Person.
     * @param providers the provider of the new appointment, now as Person.
     */
    public Appointment(Date dates, Timeslot timeslots, Person patients, Person providers) {
        date = dates;
        timeslot = timeslots;
        patient = patients;
        provider = providers;
    }

    /**
     * Overrides the toString() method to return a textual representation of the appointment.
     * Format: MM/dd/yyyy timeslot patient provider
     * Example: "10/30/2024 10:45 AM John Doe 12/13/1989 [PATEL, BRIDGEWATER, Somerset 08807, FAMILY]"
     * @return a string representation of the appointment.
     */
    @Override
    public String toString() {
        return date.toString() + " " + timeslot.toString() + " " + patient.toString() + " " + provider.toString();
    }

    /**
     * Overrides compareTo to compare appointments based on date, timeslot, and patient.
     * @param other the appointment to compare with.
     * @return an integer indicating the comparison result.
     */
    @Override
    public int compareTo(Appointment other) {
        int dateComparison = date.compareTo(other.getDate());
        if (dateComparison != 0) {
            return dateComparison; // Compare by date first
        }
        int timeslotComparison = timeslot.compareTo(other.getTimeslot());
        if (timeslotComparison != 0) {
            return timeslotComparison; // Compare by timeslot second
        }
        return patient.toString().compareTo(other.patient.toString()); // Compare by patient's name last
    }

    /**
     * A new method to compare appointments based on date, timeslot, and provider.
     * @param other the appointment to compare with.
     * @return an integer indicating the comparison result.
     */
    public int compareToPA(Appointment other) {
        int dateComparison = date.compareTo(other.getDate());
        if (dateComparison != 0) {
            return dateComparison; // Compare by date first
        }
        int timeslotComparison = timeslot.compareTo(other.getTimeslot());
        if (timeslotComparison != 0) {
            return timeslotComparison; // Compare by timeslot second
        }
        return provider.toString().compareTo(other.provider.toString()); // Compare by provider's name
    }


    /**
     * Overrides equals to compare appointments based on date, timeslot, and patient.
     * @param obj the object to compare with.
     * @return true if the appointments are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Appointment that = (Appointment) obj;
        return date.equals(that.date) && timeslot.equals(that.timeslot) && patient.equals(that.patient);
    }

    /**
     * Getter for the date of the appointment.
     * @return the date of the appointment.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Getter for the timeslot of the appointment.
     * @return the timeslot of the appointment.
     */
    public Timeslot getTimeslot() {
        return timeslot;
    }

    /**
     * Getter for the patient of the appointment.
     * @return the patient of the appointment.
     */
    public Person getPatient() {
        return patient;
    }

    /**
     * Getter for the provider of the appointment.
     * @return the provider of the appointment.
     */
    public Person getProvider() {
        return provider;
    }


}