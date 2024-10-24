package com.example.cs213project3;

/**
 * This class models a patient visit node, which holds an Appointment, as well as the next Visit in a singly linked list
 * @author Ryan Cepeda
 */
public class Visit {
    private Appointment appointment;
    private Visit next;


    /**
     * A constructor for the Visit class
     * @param appointment is the appointment object for the current Visit.
     * @param next is the link to the next visit in the list.
     */
    public Visit(Appointment appointment, Visit next) {
        this.appointment = appointment;
        this.next = next;
    }

    /**
     * Getter for the appointment of the visit.
     * @return the appointment object of the visit.
     */
    public Appointment getAppointment() {
        return appointment;
    }

    /**
     * Getter for the next appointment of the visit.
     * @return the next appointment of the visit.
     */
    public Visit getNext() {
        return next;
    }

    /**
     * Sets next visit
     * @param nextVisit to be set
     */
    public void setNext(Visit nextVisit) {
        this.next = nextVisit;
    }

    /**
     * Gets the last appointment of the visit.
     * @return the last appointment of the visit.
     */
    public Visit getLast() {
        Visit ptr = this;
        while (ptr.next != null) {
            ptr = ptr.next;
        }
        return ptr;
    }
}
