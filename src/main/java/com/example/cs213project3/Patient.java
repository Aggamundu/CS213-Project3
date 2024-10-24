package com.example.cs213project3;

/**
 * This class models a patient by extending all the attributes in the Person class while also adding a Visit variable as a linked list of visits.
 * @author Christopher Zhuo
 */
public class Patient extends Person {
    private Visit visit; // Head of the visit linked list

     /**
      * A constructor for the patient class
      * @param firstName is the first name of the patient.
      * @param lastName is the last name of the patient.
      * @param dateOfBirth is the date of birth of the patient
      * the list of Visits for each patient is initialized to null.
      */
    public Patient(String firstName, String lastName, Date dateOfBirth) {
        super(firstName, lastName, dateOfBirth);
        this.visit = null; // Initialize with no visits
    }

     /**
      * Getter for the head of the list of visits.
      * @return the head of the list of visits.
      */
    public Visit getVisit() {
        return visit;
    }

     /**
      * Adds a visit
      * @param visit to be addded
      */
    public void addVisit(Visit visit) {
        if (this.visit == null) {
            this.visit = visit;
        } else {
            // Logic to append new visit to the linked list of visits
            Visit current = this.visit;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(visit);
        }
    }

     /**
      * Returns a String representation of the Patient.
      * @return String representation of a Patient.
      */
    @Override
    public String toString() {
        return "Patient: " + super.toString(); // Can add more details if necessary
    }

}