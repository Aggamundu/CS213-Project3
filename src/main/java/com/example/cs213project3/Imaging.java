package com.example.cs213project3;

/**
 * The Imaging class extends the Appointment class to hold additional information for imaging appointments,
 * such as the assigned Radiology room.
 * @author Ryan Cepeda
 */
public class Imaging extends Appointment {
    private Radiology room; // The Radiology room for the imaging appointment

    /**
     * Constructor to create an Imaging appointment.
     * @param date the date of the appointment
     * @param timeslot the timeslot of the appointment
     * @param patient the patient for whom the appointment is scheduled
     * @param provider the provider overseeing the appointment
     * @param room the Radiology room assigned for the imaging appointment
     */
    public Imaging(Date date, Timeslot timeslot, Patient patient, Provider provider, Radiology room) {
        super(date, timeslot, patient, provider); // Call the parent class constructor
        this.room = room;
    }

    /**
     * Getter method to retrieve the Radiology room for the imaging appointment.
     * @return the Radiology room assigned to the appointment.
     */
    public Radiology getRoom() {
        return room;
    }

    /**
     * Setter method to assign a Radiology room for the imaging appointment.
     * @param room the Radiology room to assign to the appointment.
     */
    public void setRoom(Radiology room) {
        this.room = room;
    }

    /**
     * Overrides the toString method to include room information in the output for imaging appointments.
     * @return a string representation of the imaging appointment with room details.
     */
    @Override
    public String toString() {
        return super.toString() + String.format(" | Room: %s", room); // Include the room details
    }

    /**
     * Overrides the equals method to check equality of Imaging objects.
     * @param obj the object to compare with.
     * @return true if both Imaging appointments are the same, including room details.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false; // Check the parent class fields first
        }
        if (!(obj instanceof Imaging)) {
            return false;
        }
        Imaging other = (Imaging) obj;
        return room.equals(other.room); // Compare the room fields
    }
    /**
     * Getter for provider
     * @return Provider
     */
    @Override
    public Provider getProvider(){
        return (Provider) super.getProvider();
    }

    /**
     * Overrides the compareTo method to include comparison based on room, if necessary.
     * @param other the other Imaging appointment to compare with.
     * @return a comparison result based on the inherited fields and the room.
     */
    @Override
    public int compareTo(Appointment other) {
        if (!(other instanceof Imaging)) {
            return super.compareTo(other); // Use the parent class compareTo for non-Imaging appointments
        }
        int parentComparison = super.compareTo(other); // Compare based on date, timeslot, etc.
        if (parentComparison != 0) {
            return parentComparison;
        }
        Imaging otherImaging = (Imaging) other;
        return this.room.compareTo(otherImaging.room); // Compare rooms if all else is equal
    }

}