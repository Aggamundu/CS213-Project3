package com.example.cs213project3;

/**
 * This Enum class defines the locations of RU clinic locations in New Jersey.
 * @author Christopher Zhuo
 */
public enum Location {
    /**
     * A Location with the County and zip
     */
    BRIDGEWATER("Somerset", "08807"),
    /**
     * A Location with the County and zip
     */
    EDISON("Middlesex", "08817"),
    /**
     * A Location with the County and zip
     */
    PISCATAWAY("Middlesex", "08854"),
    /**
     * A Location with the County and zip
     */
    PRINCETON("Mercer", "08542"),
    /**
     * A Location with the County and zip
     */
    MORRISTOWN("Morris", "07960"),
    /**
     * A Location with the County and zip
     */
    CLARK("Union", "07066");

    private final String county;
    private final String zip;
    /**
     * Parameterized Constructor with 2 parameters.
     * Parameters are "local" variables. Ues the same name with the associated
     * instance variables. Use "this" keyword to differentiate between the two.
     * Overloading constructor.
     * @param county the county of the RU cinic location.
     * @param zip the zip of the RU clinic location.
     */
    Location(String county, String zip) {
        this.county = county;
        this.zip = zip;
    }

    /**
     * A getter for the county of the RU clinic location.
     * @return the county.
     */
    public String getCounty() {
        return county;
    }
    /**
     * A getter for the zip  of the RU clinic location.
     * @return the zip.
     */
    public String getZip() {
        return zip;
    }

    /**
     * Turns the location data into string format.
     * @return the location format in string format.
     */
    @Override
    public String toString() {
        return String.format("%s, %s %s", name(), county, zip);
    }

}