package com.example.cs213project3;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;
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
            Scanner fileScanner = new Scanner(new File("providers.txt"));
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
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
        // Sort and display providers
        Sort.sortAppointmentByProvider(providers);
        System.out.println("Providers loaded to the list.");
        for (int i = 0; i < providers.size(); i++) {
            System.out.println(providers.get(i));
        }
    }

}