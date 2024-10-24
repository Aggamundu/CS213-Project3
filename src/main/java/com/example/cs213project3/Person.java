package com.example.cs213project3;

/**
 * This class models a person's profile, which holds the patient's first name, last name, and date of birth
 * This class implements/overrides the equals(), toString(), and the compareTo() methods.
 * @author Christopher Zhuo
 */
public class Person implements Comparable<Person>{
    private String fname;
    private String lname;
    private Date dob;

    /**
     * A constructor for the profile class
     * @param fname is the first name
     * @param lname is the last name
     * @param dob is the date of birth
     */
    public Person(String fname, String lname, Date dob){
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    /**
     * Checks if two profiles have the same fname, lname, and dob
     * @param other the object being checked with the current profile instance
     * @return true if the two profiles have the same fname, lname, and dob, false otherwise
     */
    @Override
    public boolean equals(Object other){
        if(this==other) return true;
        if(other==null || getClass()!=other.getClass()) return false;
        Person profile = (Person) other;
        if(fname.equals(profile.fname) && lname.equals(profile.lname) && dob.equals(profile.dob)){
            return true;
        }
        return false;
    }

    /**
     * Returns a String representation of a profile
     * @return String representation of a profile
     */
    @Override
    public String toString() {
        return fname + " " + lname + " " + dob.toString();
    }
    /**
     * Allows comparison by sorting com.Profile objects based on lname, fname, the dob.
     * @param other the object to be compared.
     * @return an Integer value based on the com.Profile's value compared to another com.Profile
     */
    @Override
    public int compareTo(Person other){
        int lnameComparison = lname.compareTo(other.lname);
        if(lnameComparison!=0) return lnameComparison;
        int fnameComparison = fname.compareTo(other.fname);
        if(fnameComparison!=0) return fnameComparison;
        return dob.compareTo(other.dob);
    }

    /**
     * Getter for name of Profile
     * @return first and last name of profile
     */
    public String getName(){
        return fname + " " + lname;
    }

    /**
     * Getter for last name of Profile
     * @return last name of profile
     */
    public String getLastName() {
        return lname;
    }

    /**
     * Getter for first name of Profile
     * @return first name of profile
     */
    public String getFirstName() {
        return fname;
    }

    /**
     * Getter for dob
     * @return dob
     */
    public Date getDob(){
        return dob;
    }
}