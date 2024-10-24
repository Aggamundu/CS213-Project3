package com.example.cs213project3;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;


public class HelloController {
    private static List<Provider> providers = new List<>(); // Single list for all providers
    private static List<Appointment> appointments = new List<>(); // Single list for both office and imaging appointments
    private static List<Technician> technicians = new List<>();
    @FXML
    private TextArea output;
    @FXML
    private Button loadProvidersButton;
    @FXML
    void loadProviders() {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/providers.txt");
            if(inputStream==null){
                throw new FileNotFoundException("File not found: providers.txt");
            }
            Scanner fileScanner = new Scanner(inputStream);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] data = line.split("  ");
                String firstName = data[1];
                String lastName = data[2];
                String[] dobData = data[3].split("/");
                Date dob = new Date(Integer.parseInt(dobData[2]), Integer.parseInt(dobData[0]), Integer.parseInt(dobData[1]));
                Location location = Location.valueOf(data[4]);
                if (data[0].equals("D")) {
                    Specialty specialty = Specialty.valueOf(data[5]);
                    String npi = data[6];
                    Provider doctor = new Doctor(firstName, lastName, dob, location, specialty, npi);
                    providers.add(doctor);
                } else {
                    int rate = Integer.parseInt(data[5]);
                    Provider technician = new Technician(firstName, lastName, dob, location, rate);
                    providers.add(technician);
                }
            }
            fileScanner.close();
        } catch (Exception e) {
            output.appendText("An error occurred while reading the file: " + e.getMessage());
        }
        // Sort and display providers
        Sort.sortAppointmentByProvider(providers);
        output.appendText("Providers loaded to the list.\n");
        for (int i = 0; i < providers.size(); i++) {
            output.appendText(providers.get(i)+"\n");
        }
        displayRotations();
    }
    public void displayRotations() {
        output.appendText("\nRotation list for the technicians.\n");
        try {
            // Create a Scanner object to read from the file
            InputStream inputStream = getClass().getResourceAsStream("/providers.txt");
            if(inputStream==null){
                throw new FileNotFoundException("File not found: providers.txt");
            }
            Scanner fileScanner = new Scanner(inputStream);
            // Read the file line by line
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] data = line.split("  "); // Assuming data is space-delimited
                if (data[0].equals("T")) { // 'T' signifies a Technician
                    String firstName = data[1];
                    String lastName = data[2];
                    String[] dobData = data[3].split("/");
                    int month = Integer.parseInt(dobData[0]);
                    int day = Integer.parseInt(dobData[1]);
                    int year = Integer.parseInt(dobData[2]);
                    Date dob = new Date(year, month, day);
                    Location location = Location.valueOf(data[4]);
                    int rate = Integer.parseInt(data[5]);
                    // Create a technician and add it to the list
                    Technician technician = new Technician(firstName, lastName, dob, location, rate);
                    technicians.add(technician);
                }
            }
            // Close the file scanner
            fileScanner.close();
        } catch (Exception e) {
            output.appendText("An error occurred while reading the file: " + e.getMessage());
        }
        // Reverse the list using recursion (if needed)
        reverseWithRecursion(technicians, 0, technicians.size() - 1);
        // Display the technicians in rotation
        for (int i = 0; i < technicians.size(); i++) {
            if (i < technicians.size() - 1) {
                output.appendText(technicians.get(i).getName() + " (" + technicians.get(i).getLocationName() + ") --> ");
            } else {
                output.appendText(technicians.get(i).getName() + " (" + technicians.get(i).getLocationName() + ") ");
            }
        }
        output.appendText("\n"); // Add a new line at the end
    }
    /**
     * Helper method to reverse the list using recursion.
     */
    private void reverseWithRecursion(List<Technician> list, int start, int end) {
        if (start >= end) {
            return;
        }
        // Swap the elements
        Technician temp = list.get(start);
        list.set(start, list.get(end));
        list.set(end, temp);

        // Recursive call for the next pair
        reverseWithRecursion(list, start + 1, end - 1);
    }


    @FXML
    void clear(){
        output.clear();
    }
}