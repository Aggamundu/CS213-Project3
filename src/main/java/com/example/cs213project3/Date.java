package com.example.cs213project3;

import java.util.Calendar;

/**
 * This class reads in the date and also checks if the dates are valid.
 * This class must implement/override the equals(), toString(), and the compareTo() methods.
 * @author Christopher Zhuo
 */
public class Date implements Comparable <Date>{
    private int year;
    private int month;
    private int day;

    //Constants for the amount of days in month
    /**
     * A month with 30 days
     */
    public static final int MONTH_WITH_30_DAYS = 30;
    /**
     * A month with 31 days
     */
    public static final int MONTH_WITH_31_DAYS = 31;
    /**
     * A regular month in February has 28 days
     */
    public static final int FEBRUARY_DAYS = 28;
    /**
     * The number of days in February during a leap year.
     */
    public static final int FEB_LEAP_YEAR_DAYS = 29;

    /**
     * Parameterized Constructor for the com.Date Class with 3 parameters.
     * @param year the year of the new date object.
     * @param month the month of the new date object.
     * @param day the day of the new date object.
     */
    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
    /**
     * Checks whether the date object is equal to another date.
     * @param obj which is the appointment object.
     * @return an integer based on the com.Appointment value compared to other appointments.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Date that = (Date) obj;
        return this.year == that.year && this.month == that.month && this.day == that.day; //uses == since it deals with integer
    }
    /**
     * Overrides the toString() function and returns a textual representation of the date.
     * @return a textual representation of the date.
     */
    @Override
    public String toString() {
        return String.format("%d/%d/%d", month, day, year); //"\%02d" means two integers that are padded with 0's.
    }

    /**
     * Allows sorting com.Date objects by year, month, and day.
     * @param other which is the appointment object.
     * @return an integer based on the com.Date value compared to other date values.
     */
    @Override
    public int compareTo(Date other) {
        if (this.year != other.year) {
            return this.year - other.year;
        }
        if (this.month != other.month) {
            return this.month - other.month;
        }
        return this.day - other.day;
    }

    /**
     * Checks if the year is a leap year
     * @param year which is an integer
     * @return true if it is a leap year and false otherwise.
     */
    public static boolean isLeapYear(int year) {
        // A year is a leap year if it is divisible by 4
        // But if it is divisible by 100, it must also be divisible by 400 to be a leap year
        return (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0);
    }

    /**
     * Tells if the date is valid by checking if the date is valid calendar date.
     * @return true if the date is valid and false otherwise
     */
    public boolean isValid() {
        if (year < 0) {
            return false; // Invalid year or month
        }
        if (month < 1 || month > 12) {
            return false;
        }
        // Determine the number of days in the given month
        int daysInMonth = 0;
        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                daysInMonth = MONTH_WITH_31_DAYS;
                break;
            case 4: case 6: case 9: case 11:
                daysInMonth = MONTH_WITH_30_DAYS;
                break;
            case 2:
                if (isLeapYear(year)) {
                    daysInMonth = FEB_LEAP_YEAR_DAYS;
                }
                else {
                    daysInMonth = FEBRUARY_DAYS;
                }
                break;
            default:
                return false; // Invalid month
        }
        return day > 0 && day <= daysInMonth;
    }

    /**
     * Tells if the date is a weekday and not a weekend.
     * @return true if the date is a weekday and false otherwise
     */
    public boolean isWeekday() {
        Calendar chosenDate = Calendar.getInstance();
        chosenDate.set(year, month - 1, day);
        int dayOfWeek = chosenDate.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY;
    }

    /**
     * Tells if the date is within 6 months of today.
     * @return true if the date is within 6 months of today and false otherwise
     */
    public boolean isWithinSixMonths() {
        Calendar today = Calendar.getInstance();
        Calendar chosenDate = Calendar.getInstance();
        chosenDate.set(year, month - 1, day);
        Calendar sixMonthsLater = (Calendar) today.clone();
        sixMonthsLater.add(Calendar.MONTH, 6);
        return !chosenDate.before(today) && !chosenDate.after(sixMonthsLater); // Check if within the 6-month window
    }

    /**
     * Tells if the date has already passed
     * @return true if the date has already passed and false otherwise.
     */
    public boolean dateAlreadyPassed() {
        Calendar today = Calendar.getInstance();
        Calendar chosenDate = Calendar.getInstance();
        chosenDate.set(year, month - 1, day);
        return !chosenDate.after(today);
    }

}