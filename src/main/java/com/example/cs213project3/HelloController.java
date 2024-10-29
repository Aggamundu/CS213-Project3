package com.example.cs213project3;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.Scanner;


public class HelloController implements Initializable {
    private static List<Provider> providers = new List<>(); // Single list for all providers
    private static List<Appointment> appointments = new List<>(); // Single list for both office and imaging appointments
    private static List<Technician> technicians = new List<>();
    @FXML
    private TextArea output;
    @FXML
    private TextField fnameSchedule;
    @FXML
    private TextField lnameSchedule;
    @FXML
    private TextField fnameReschedule;
    @FXML
    private TextField lnameReschedule;
    @FXML
    private DatePicker date;
    @FXML
    private DatePicker rescheduleDate;
    @FXML
    private DatePicker dob;
    @FXML
    private DatePicker dobReschedule;
    @FXML
    private ChoiceBox timeslotBox;
    @FXML
    private ChoiceBox rescheduletimeslotBox;
    @FXML
    private ChoiceBox newtimeslotBox;
    @FXML
    private ChoiceBox providerBox;
    @FXML
    private RadioButton imagingButton, officeButton;
    @FXML
    private Button scheduleButton;
    @FXML
    private ChoiceBox imagingBox;
    @FXML
    private Button cancelButton;

    private final String[] timeslots = {"Pick Timeslot","9:00 AM", "9:30 AM", "10:00 AM", "10:30 AM", "11:00 AM", "11:30 AM", "2:00 PM", "2:30 PM", "3:00 PM", "3:30 PM", "4:00 PM", "4:30 PM"};
    private final String[] providersArr = {"Choose Provider","JUSTIN CERAVOLO (09)", "JOHN HARPER (32)","BEN JERRY (77)","GARY JOHNSON (85)","TOM KAUR (54)", "RACHAEL LIM (23)" ,"ANDREW PATEL (01)","BEN RAMESH (39)","ERIC TAYLOR (91)","MONICA ZIMNES (11)"};
    private final String[] imagingServ = {"Choose Imaging Service", "catscan", "ultrasound", "xray"};

    private int technicianRotationIndex = 0;


    /**
     * Method to set up U.I. components before they are displayed to user
     * @param url a reference location to the FXML file
     * @param resourceBundle a bundle of resources, such as localized strings
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timeslotBox.getItems().addAll(timeslots);
        timeslotBox.getSelectionModel().selectFirst();
        providerBox.getItems().addAll(providersArr);
        providerBox.getSelectionModel().selectFirst();
        imagingBox.getItems().addAll(imagingServ);
        imagingBox.getSelectionModel().selectFirst();
    }

    /**
     * When a box is selected, we turn one off and make sure the other is off.
     */
    @FXML
    void disableProviders(){
        if(imagingButton.isSelected()){
            providerBox.setDisable(true);
        }
        if(officeButton.isSelected()){
            providerBox.setDisable(false);
        }
    }

    /**
     * This class loads all the providers from the providers.txt list and sorts them.
     */
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

    /**
     * Display the loaded providers sorted by profile and maintain the rotation list for technicians.
     */
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
    private void handleSchedule() {
        if(imagingButton.isSelected()){
            scheduleImagingAppointment();
        }
        if(officeButton.isSelected()){
            scheduleDoctorAppointment();;
        }
    }

    /**
     * Schedules a doctor appointment.
     */
    @FXML
    private void scheduleDoctorAppointment() {
        String dateString = date.getValue().toString();
        String timeSlotString = String.valueOf(timeslotBox.getSelectionModel().getSelectedIndex());
        String firstName = fnameSchedule.getText();
        String lastName = lnameSchedule.getText();
        String dobString = dob.getValue().toString();
        String prov = (String) providerBox.getValue();
        String[] sArr = prov.split(" ");
        String npiString = sArr[2].substring(1,3);
        Date date = dateisValid(dateString);
        if (date == null) return;
        Timeslot timeslot = getTimeslotFromString(timeSlotString);
        if (timeslot == null) {output.appendText(timeSlotString + " is not a valid time slot.\n");return;}
        Date dob = birthDateisValid(dobString);
        if (dob == null) return;
        Patient patient = new Patient(firstName, lastName, dob);
        Doctor doctor = null;
        for (Provider provider : providers) {
            if (provider instanceof Doctor && ((Doctor) provider).getNpi().equals(npiString)) {doctor = (Doctor) provider;break;}}
        if (doctor == null) {output.appendText(npiString + " - provider doesn't exist.\n");return;}
        // Check for existing appointments
        if (containsSamePerson(appointments, patient, date, timeslot) != null) {
            Person person = containsSamePerson(appointments, patient, date, timeslot);
            output.appendText(person.getFirstName() + " " + person.getLastName() + " " + person.getDob() + " has an existing appointment at the same time slot.\n");
            return;}
        // Check doctor's availability
        for (Appointment appointment : appointments) {
            if (appointment.getDate().equals(date) && appointment.getProvider().equals(doctor) && appointment.getTimeslot().equals(timeslot)) {output.appendText(doctor.toString() + " is not available at slot " + timeSlotString + ".\n");return;}
        }
        // No conflicts, create the new appointment
        Appointment officeAppointment = new Appointment(date, timeslot, patient, doctor);
        appointments.add(officeAppointment);
        output.appendText(date + " " + timeslot + " " + firstName + " " + lastName + " " + dob + " " + doctor.toString() + " booked.\n");
    }

    /**
     * Schedules a imaging appointment.
     */
  @FXML
  private void scheduleImagingAppointment() {
      String dateString = date.getValue().toString();
      String timeSlotString = String.valueOf(timeslotBox.getSelectionModel().getSelectedIndex());
      String firstName = fnameSchedule.getText();
      String lastName = lnameSchedule.getText();
      String dobString = dob.getValue().toString();
      String imagingString = (String) imagingBox.getValue();
      Date date = dateisValid(dateString);
      if (date == null) return;
      Timeslot timeslot = getTimeslotFromString(timeSlotString);
      if (timeslot == null) {output.appendText(timeSlotString + " is not a valid time slot.\n");return;}
      Date dob = birthDateisValid(dobString);
      if (dob == null) return;
      Patient patient = new Patient(firstName, lastName, dob);
      //Validate the imaging Service
      Radiology imagingService;
      try {imagingService = Radiology.valueOf(imagingString.toUpperCase());}
      catch (IllegalArgumentException e) {
          output.appendText(imagingString + " - imaging service not provided.");
          return;
      }
      // Check for existing appointments for the patient
      if (containsSamePerson(appointments, patient, date, timeslot) != null) {
          output.appendText(firstName + " " + lastName + " " + dobString + " has an existing appointment at the same time slot.");
          return;
      }
      // Circular rotation logic to find the next available technician
      int checkedTechnicians = 0;
      Technician selectedTechnician = null;
      while (checkedTechnicians < technicians.size()) {
          Technician currentTechnician = technicians.get(technicianRotationIndex);
          // Check if the technician is available at the requested timeslot and location
          boolean isAvailable = checkTechnicianAvailability(currentTechnician, date, timeslot, imagingService);
          if (isAvailable) {selectedTechnician = currentTechnician;break;} // Found an available technician
          // Move to the next technician in the circular rotation
          technicianRotationIndex = (technicianRotationIndex + 1) % technicians.size();
          checkedTechnicians++;
      }
      // If no technician is found, output a message
      if (selectedTechnician == null) {
          output.appendText("Cannot find an available technician at all locations for " + imagingService + " at slot " + timeSlotString + ".");
          return;
      }
      // Create the imaging appointment
      Imaging imagingAppointment = new Imaging(date, timeslot, patient, selectedTechnician, imagingService);
      appointments.add(imagingAppointment);
      output.appendText(dateString + " " + timeslot + " " + firstName + " " + lastName + " " + dobString + " " + selectedTechnician.toString() + "[" + imagingString.toUpperCase() +  "] booked.");
      // Move the rotation to the next technician for future appointments
      technicianRotationIndex = (technicianRotationIndex + 1) % technicians.size();
  }

    /**
     * Checks if a technician is available for a given date, timeslot, and imaging service.
     * @param technician the technician to check.
     * @param date the date of the appointment.
     * @param timeslot the timeslot of the appointment.
     * @param imagingService the requested imaging service (XRAY, CATSCAN, ULTRASOUND).
     * @return true if the technician is available, false otherwise.
     */
    private boolean checkTechnicianAvailability(Technician technician, Date date, Timeslot timeslot, Radiology imagingService) {
        Location technicianLocation = technician.getLocation();

        // Iterate through the appointments to check if there's a conflict with the same timeslot, location, and imaging service
        for (Appointment appointment : appointments) {
            if (appointment instanceof Imaging) {
                Imaging imagingAppointment = (Imaging) appointment;
                // Check if the technician is already busy at that timeslot and date
                if (imagingAppointment.getProvider().equals(technician) &&
                        imagingAppointment.getDate().equals(date) &&
                        imagingAppointment.getTimeslot().equals(timeslot)) {
                    // Conflict: The technician already has an appointment with the same service at the same timeslot
                    return false;
                }
                // Check if the imaging room for the requested service is already booked at the same location
                if (imagingAppointment.getRoom().equals(imagingService) &&
                        imagingAppointment.getProvider().getLocation().equals(technicianLocation) &&
                        imagingAppointment.getDate().equals(date) &&
                        imagingAppointment.getTimeslot().equals(timeslot)) {
                    // Conflict: The room for the imaging service is already booked at the same location and timeslot
                    return false;
                }
            }
        }
        // If no conflicts were found, the technician is available
        return true;
    }

    /**
     * Cancels an appointment.
     */
    @FXML
    private void cancelAppointment(){
        String dateString = date.getValue().toString();
        String timeSlotString = String.valueOf(timeslotBox.getSelectionModel().getSelectedIndex());
        String firstName = fnameSchedule.getText();
        String lastName = lnameSchedule.getText();
        String dobString = dob.getValue().toString();
        Date date = dateisValid(dateString);
        if (date == null) return;
        Timeslot timeslot = getTimeslotFromString(timeSlotString);
        if (timeslot == null) {output.appendText(timeSlotString + " is not a valid time slot.\n");return;}
        Date dob = birthDateisValid(dobString);
        if (dob == null) return;
        Patient patient = new Patient(firstName, lastName, dob);
        // Find and remove appointment from the appointments list
        boolean cancelled = removeAppointment(appointments, date, patient, timeslot);
        if (!cancelled) {
            output.appendText(dateString + " " + timeslot + " " + patient.getName() + " " + dobString + " - appointment does not exist.");
        } else {
            output.appendText(dateString + " " + timeslot + " " + patient.getName().toUpperCase() + " " + dob + " - appointment has been cancelled.");
        }
    }

    /**
     * Removes an appointment from the given list.
     */
    private boolean removeAppointment(List<Appointment> appointments, Date date, Patient patient, Timeslot timeslot) {
        for (Appointment appointment : appointments) {
            if (appointment.getDate().equals(date) &&
                    appointment.getPatient().equals(patient) &&
                    appointment.getTimeslot().equals(timeslot)) {
                appointments.remove(appointment); // Remove the appointment directly
                return true; // Appointment removed
            }
        }
        return false; // Appointment not found
    }




    /**
     *  Determines whether date is valid
     * @param dateString input string
     * @return nall if not valid, Date object if valid
     */
    public Date dateisValid(String dateString) {
        String[] appointmentDate = dateString.split("-");
        Date thisDate = new Date(Integer.parseInt(appointmentDate[0]), Integer.parseInt(appointmentDate[1]), Integer.parseInt(appointmentDate[2]));
        Calendar inputDate = Calendar.getInstance();
        inputDate.set(Calendar.YEAR, Integer.parseInt(appointmentDate[0]));
        inputDate.set(Calendar.MONTH, Integer.parseInt(appointmentDate[1]) - 1); // Month is 0-indexed in Calendar
        inputDate.set(Calendar.DAY_OF_MONTH, Integer.parseInt(appointmentDate[2]));

        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        // Validations for date
        if (!thisDate.isValid()) {
            output.appendText("Appointment date: " + dateString + " is not a valid calendar date.\n");
            return null;
        }
        if (inputDate.before(today)) {
            output.appendText("Appointment date: " + dateString + " is today or a date before today.\n");
            return null;
        }

        if (!thisDate.isWeekday()) {
            output.appendText("Appointment date: " + dateString + " is Saturday or Sunday.");
            return null;
        }
        if (!thisDate.isWithinSixMonths()) {
            output.appendText("Appointment date: " + dateString + " is not within six months.");
            return null;
        }
        return thisDate;
    }

    /**
     * Searches list to find same person
     * @param appointments List to be serached
     * @param person person to be found
     * @param date date of person
     * @param timeslot of appointment
     * @return null if none found, else person of the person found
     */
    public Person containsSamePerson(List<Appointment> appointments, Person person, Date date, Timeslot timeslot) {
        for (Appointment currentAppointment : appointments) {
            if (currentAppointment.getDate().equals(date) &&
                    currentAppointment.getTimeslot().equals(timeslot) && person.equals(currentAppointment.getPatient())) {
                return currentAppointment.getPatient(); // Found a conflict with a different patient
            }
        }
        return null; // No conflicting appointment found
    }
    /**
     * Returns timeslot obj from string
     * @param timeSlotString input string
     * @return null if not found, else the timeslot obj from the string
     */
    private Timeslot getTimeslotFromString(String timeSlotString) {
        try {
            int slotNumber = Integer.parseInt(timeSlotString);
            Timeslot timeslot = new Timeslot(slotNumber);
            if (timeslot.getHour() == -1 && timeslot.getMinute() == -1) {
                return null;
            }
            return timeslot;
        } catch (NumberFormatException e) {
            return null;
        }
    }
    /**
     * Checks whether birthdate is valid
     * @param birthString input string
     * @return null if not valid, object if valid
     */
    public Date birthDateisValid(String birthString) {
        String[] birthDate = birthString.split("-");
        Date thisDate = new Date(Integer.parseInt(birthDate[0]), Integer.parseInt(birthDate[1]), Integer.parseInt(birthDate[2]));

        Calendar inputDate = Calendar.getInstance();
        inputDate.set(Calendar.YEAR, Integer.parseInt(birthDate[0]));
        inputDate.set(Calendar.MONTH, Integer.parseInt(birthDate[1]) - 1);
        inputDate.set(Calendar.DAY_OF_MONTH, Integer.parseInt(birthDate[2]));

        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        if (!thisDate.isValid()) {
            output.appendText("Patient dob: " + birthString + " is not a valid calendar date.\n");
            return null;
        }
        if (inputDate.after(today)) {
            output.appendText("Patient dob: " + birthString + " is today or a date after today.\n");
            return null;
        }
        return thisDate;
    }

    @FXML
    void clear(){
        output.clear();
    }

}