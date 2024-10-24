package com.example.cs213project3;

/**
 * This class keeps track of the providers and their lcation along with the cost of their services.
 * @author Christopher Zhuo
 */
public abstract class Provider extends Person {
    private Location location;

    /**
     * A constructor for the Provider class
     * @param firstName is the first name of the provider.
     * @param lastName is the last name of the provider.
     * @param dob is the date of birth of the provider.
     * @param location is the location of the provider.
     */
    public Provider(String firstName, String lastName, Date dob, Location location) {
        super(firstName, lastName, dob);
        this.location = location;
    }

    /**
     * A getter for the location of the providers.
     * @return the location.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * getter for county
     * @return county
     */
    public String getCounty() {
        return location.getCounty();
    }

    /**
     * getter for location name
     * @return location name
     */
    public String getLocationName(){
        return location.name();
    }

    /**
     * An abstract method to calculate the rate per visit
     * @return the cost per Visit.
     */
    public abstract int rate();

    /**
     * Returns a String representation of the provider.
     * @return String representation of a profile.
     */
    @Override
    public String toString() {
        return super.toString() + ", " + location;
    }
}