package com.example.cs213project3;

/**
 * Utility class for sorting.
 * Contains only static methods for sorting appointments and providers.
 * @author Christopher Zhuo
 */

public class Sort {
    /**
     * default constructor
     */
    public Sort(){

    }


//    /**
//     * Sort appointments by county name, then date and time.
//     * @param list the list of appointments to sort.
//     */
//    public static void sortAppointmentsByCounty(List<Appointment> list) {
//        for (int i = 0; i < list.size() - 1; i++) {
//            for (int j = 0; j < list.size() - i - 1; j++) {
//                Provider p1 = (Provider) list.get(j).getProvider();
//                Provider p2 = (Provider) list.get(j + 1).getProvider();
//                int countyComparison = p1.getLocation().getCounty().compareTo(p2.getLocation().getCounty());
//                if (countyComparison > 0 ||
//                        (countyComparison == 0 && list.get(j).getDate().compareTo(list.get(j + 1).getDate()) > 0) ||
//                        (countyComparison == 0 && list.get(j).getDate().equals(list.get(j + 1).getDate()) &&
//                                list.get(j).getTimeslot().compareTo(list.get(j + 1).getTimeslot()) > 0)) {
//                    swap(list, j, j + 1);
//                }
//            }
//        }
//    }

    /**
     * Sort appointments by county name, then date and time.
     * @param list the list of appointments to sort.
     */
    public static void sortAppointmentsByCounty(List<Appointment> list) {
        int n = list.size();
        // Bubble sort implementation
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Provider p1 = (Provider) list.get(j).getProvider();
                Provider p2 = (Provider) list.get(j + 1).getProvider();

                // Compare by county name first
                int countyComparison = p1.getLocation().getCounty().compareTo(p2.getLocation().getCounty());

                // If countyComparison > 0, swap appointments
                if (countyComparison > 0 ||
                        (countyComparison == 0 && list.get(j).getDate().compareTo(list.get(j + 1).getDate()) > 0) ||
                        (countyComparison == 0 && list.get(j).getDate().equals(list.get(j + 1).getDate()) &&
                                list.get(j).getTimeslot().compareTo(list.get(j + 1).getTimeslot()) > 0)) {
                    // Swap appointments
                    swap(list, j, j + 1);
                }
            }
        }
    }

    /**
     * Sort appointments by patient last name, first name, date of birth, and then appointment date and time.
     * @param list the list of appointments to sort.
     */
    public static void sortAppointmentsByPatient(List<Appointment> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                Patient p1 = (Patient) list.get(j).getPatient();
                Patient p2 = (Patient) list.get(j + 1).getPatient();

                int lastNameComparison = p1.getLastName().compareTo(p2.getLastName());
                if (lastNameComparison > 0 ||
                        (lastNameComparison == 0 && p1.getFirstName().compareTo(p2.getFirstName()) > 0) ||
                        (lastNameComparison == 0 && p1.getFirstName().equals(p2.getFirstName()) &&
                                p1.getDob().compareTo(p2.getDob()) > 0) ||
                        (lastNameComparison == 0 && p1.getFirstName().equals(p2.getFirstName()) &&
                                p1.getDob().equals(p2.getDob()) &&
                                list.get(j).getDate().compareTo(list.get(j + 1).getDate()) > 0) ) {
                    swap(list, j, j + 1);
                }
            }
        }
    }

    /**
     * Sort appointments by appointment date, then time, and then provider's name.
     * @param list the list of appointments to sort.
     */
    public static void sortAppointmentsByDateAndProvider(List<Appointment> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                Appointment a1 = list.get(j);
                Appointment a2 = list.get(j + 1);

                int dateComparison = a1.getDate().compareTo(a2.getDate());
                if (dateComparison > 0 ||
                        (dateComparison == 0 && a1.getTimeslot().compareTo(a2.getTimeslot()) > 0) ||
                        (dateComparison == 0 && a1.getTimeslot().equals(a2.getTimeslot()) &&
                                a1.getProvider().getLastName().compareTo(a2.getProvider().getLastName()) > 0) ||
                        (dateComparison == 0 && a1.getTimeslot().equals(a2.getTimeslot()) &&
                                a1.getProvider().getLastName().equals(a2.getProvider().getLastName()) &&
                                a1.getProvider().getFirstName().compareTo(a2.getProvider().getFirstName()) > 0) ) {
                    swap(list, j, j + 1);
                }
            }
        }
    }
    /**
     * Sorts the list of appointments based on the given key.
     * @param list the list of appointments to be sorted.
     * @param key the sorting key: 'D' for date, 'T' for timeslot, 'P' for provider.
     */
    public static void appointmentController(List<Appointment> list, char key) {
        switch (key) {
            case 'D': // Sort by date
                sortAppointmentsByDate(list);
                break;
            case 'T': // Sort by timeslot
                sortAppointmentsByTimeslot(list);
                break;
            case 'P': // Sort by provider name
                System.out.println("hi");
                sortAppointmentsByProvider(list);
                break;
            default:
                throw new IllegalArgumentException("Invalid sorting key: " + key);
        }
    }

    /**
     * Sorts the list of providers by their name.
     * @param list the list of providers to be sorted.
     */
    public static void sortAppointmentByProvider(List<Provider> list) {
        // Bubble sort by provider's name (last name then first name)
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                Provider p1 = list.get(j);
                Provider p2 = list.get(j + 1);
                if (p1.getLastName().compareTo(p2.getLastName()) > 0 ||
                        (p1.getLastName().equals(p2.getLastName()) && p1.getFirstName().compareTo(p2.getFirstName()) > 0)) {
                    swap(list, j, j + 1);
                }
            }
        }
    }

    // Helper Methods for Appointment Sorting

    /**
     * Sort appointments by date. Meant to be used by the PA.
     * @param list the list of appointments to sort.
     */
    public static void sortAppointmentsByDate(List<Appointment> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j).getDate().compareTo(list.get(j + 1).getDate()) > 0) {
                    swap(list, j, j + 1);
                }
            }
        }
    }

    /**
     * Sort appointments by timeslot.
     * @param list the list of appointments to sort.
     */
    public static void sortAppointmentsByTimeslot(List<Appointment> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j).getTimeslot().getSlotNumber() > list.get(j + 1).getTimeslot().getSlotNumber()) {
                    swap(list, j, j + 1);
                }
            }
        }
    }

    /**
     * Sort appointments by provider name.
     * @param list the list of appointments to sort.
     */
    public static void sortAppointmentsByProvider(List<Appointment> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                //System.out.println("Made it into thee loop. ");
                Provider p1 = (Doctor) list.get(j).getProvider();
                Provider p2 = (Doctor) list.get(j + 1).getProvider();
                if (p1.getLastName().compareTo(p2.getLastName()) > 0 ||
                        (p1.getLastName().equals(p2.getLastName()) && p1.getFirstName().compareTo(p2.getFirstName()) > 0)) {
                    System.out.println("Swapping has occured");
                    swap(list, j, j + 1);
                }
            }
        }
    }

    // Helper Method to Swap Elements

    /**
     * Swaps two items in a list
     * @param list of items
     * @param i to be swapped
     * @param j to be swapped
     * @param <E> generic
     */
    public static <E> void swap(List<E> list, int i, int j) {
        E temp = list.get(i);

        list.set(i, list.get(j));
        list.set(j, temp);
    }

}
