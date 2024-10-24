package com.example.cs213project3;

/**
 * This class creates a Timeslot
 * @author Ryan Cepeda
 */
public class Timeslot implements Comparable<Timeslot> {
    private int hour;   // The hour of the timeslot (24-hour format)
    private int minute; // The minute of the timeslot

    /**
     * Constructor to set the hour and minute given the slot number. If invalid slot number, make all fields -1
     * @param slotNumber the hour of the timeslot (24-hour format)
     */
    public Timeslot(int slotNumber) {
        switch(slotNumber){
            case 1: this.hour = 9; this.minute = 0; break;
            case 2: this.hour = 9; this.minute = 30; break;
            case 3: this.hour = 10; this.minute = 0; break;
            case 4: this.hour = 10; this.minute = 30; break;
            case 5: this.hour = 11; this.minute = 0; break;
            case 6: this.hour = 11; this.minute = 30; break;
            case 7: this.hour = 14; this.minute = 0; break;
            case 8: this.hour = 14; this.minute = 30; break;
            case 9: this.hour = 15; this.minute = 0; break;
            case 10: this.hour = 15; this.minute = 30; break;
            case 11: this.hour = 16; this.minute = 0; break;
            case 12: this.hour = 16; this.minute = 30; break;
            default: this.hour = -1; this.minute = -1; break;
        }
    }

    /**
     * Getter for the hour of the timeslot.
     * @return the hour of the timeslot.
     */
    public int getHour() {
        return hour;
    }

    /**
     * Getter for the minute of the timeslot.
     * @return the minute of the timeslot.
     */
    public int getMinute() {
        return minute;
    }

    /**
     * Method which determines whether the timeslot is in "AM" or "PM".
     * @return "AM" if the hour is less than 12, "PM" otherwise.
     */
    private String getAmPm() {
        return hour < 12 ? "AM" : "PM";
    }

    /**
     * Converts the hour to a 12-hour format for display.
     * @return the hour in 12-hour format.
     */
    private int get12HourFormat() {
        if (hour == 0) {
            return 12; // Midnight case
        } else if (hour > 12) {
            return hour - 12; // Convert from 24-hour to 12-hour format
        } else {
            return hour; // Already in 12-hour format
        }
    }

    /**
     * Returns a textual representation of the timeslot
     * @return a string representing the timeslot.
     */
    @Override
    public String toString() {
        return String.format("%d:%02d %s", get12HourFormat(), minute, getAmPm());
    }

    /**
     * Compares this timeslot to another timeslot based on the time (hour and minute).
     * @param other the timeslot to compare with.
     * @return a negative integer, zero, or a positive integer as this timeslot is earlier, same, or later than the specified timeslot.
     */
    @Override
    public int compareTo(Timeslot other) {
        int hourComparison = Integer.compare(this.hour, other.hour);
        if (hourComparison != 0) {
            return hourComparison;
        }
        return Integer.compare(this.minute, other.minute);
    }

    /**
     * Compares this timeslot to another object for equality based on the hour and minute.
     * @param obj the object to compare with.
     * @return true if the timeslots are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Timeslot that = (Timeslot) obj;
        return this.hour == that.hour && this.minute == that.minute;
    }

    /**
     * Method to check if a timeslot is valid
     * @return true if valid, false otherwise
     */
    public boolean validateTimeslot(){
        return hour != -1 || minute != -1;
    }

    /**
     * Method to get the integer index of the timeslot
     * @return an integer representing the timeslot order.
     */
    public int getSlotNumber() {
        if (hour == 9 && minute == 0) return 1;
        if (hour == 9 && minute == 30) return 2;
        if (hour == 10 && minute == 0) return 3;
        if (hour == 10 && minute == 30) return 4;
        if (hour == 11 && minute == 0) return 5;
        if (hour == 11 && minute == 30) return 6;
        if (hour == 16 && minute == 30) return 7;
        if (hour == 17 && minute == 0) return 8;
        if (hour == 17 && minute == 30) return 9;
        if (hour == 18 && minute == 0) return 10;
        if (hour == 18 && minute == 30) return 11;
        if (hour == 19 && minute == 0) return 12;

        return -1; // Invalid timeslot
    }

}