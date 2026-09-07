import java.util.Scanner;

/**
 * Mini Hospital Emergency Management System
 *
 * Ties together four data structures:
 *  - PatientBST      : stores patient records, keyed by Patient ID
 *  - EmergencyQueue  : FIFO queue of patients waiting for treatment
 *  - TreatmentStack  : LIFO stack of completed treatment records
 *  - VisitLinkedList : singly linked list of each patient's visit history (owned per-Patient)
 */
public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final PatientBST patientTree = new PatientBST();
    private static final EmergencyQueue emergencyQueue = new EmergencyQueue();
    private static final TreatmentStack treatmentHistory = new TreatmentStack();

    public static void main(String[] args) {
        try (sc) {
            boolean running = true;
            
            System.out.println("=================================================");
            System.out.println(" Welcome to the Mini Hospital Emergency Management System");
            System.out.println("=================================================");
            
            while (running) {
                printMenu();
                int choice = readInt("Enter your choice: ");
                
                switch (choice) {
                    case 1 -> registerPatient();
                    case 2 -> searchPatient();
                    case 3 -> deletePatient();
                    case 4 -> viewAllPatients();
                    case 5 -> addToQueue();
                    case 6 -> treatNextPatient();
                    case 7 -> viewQueue();
                    case 8 -> completeTreatment();
                    case 9 -> viewTreatmentHistory();
                    case 10 -> undoLastTreatment();
                    case 11 -> addVisit();
                    case 12 -> viewVisitHistory();
                    case 13 -> removeVisit();
                    case 14 -> searchVisit();
                    case 0 -> {
                        running = false;
                        System.out.println("Exiting system. Goodbye!");
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--------------------- MENU ---------------------");
        System.out.println(" Patient Records (BST)");
        System.out.println("  1. Register New Patient");
        System.out.println("  2. Search Patient by ID");
        System.out.println("  3. Delete Patient");
        System.out.println("  4. View All Patients (In-Order)");
        System.out.println(" Emergency Queue");
        System.out.println("  5. Add Patient to Emergency Queue");
        System.out.println("  6. Treat Next Patient (Dequeue)");
        System.out.println("  7. View Waiting Queue");
        System.out.println(" Treatment History (Stack)");
        System.out.println("  8. Complete Treatment (Push)");
        System.out.println("  9. View Treatment History");
        System.out.println(" 10. Undo Last Treatment (Pop)");
        System.out.println(" Patient Visit History (Linked List)");
        System.out.println(" 11. Add Visit to Patient History");
        System.out.println(" 12. View Patient Visit History");
        System.out.println(" 13. Remove a Visit");
        System.out.println(" 14. Search for a Visit");
        System.out.println("  0. Exit");
        System.out.println("--------------------------------------------------");
    }

    // ==================== BST: PATIENT RECORDS ====================

    private static void registerPatient() {
        System.out.println("\n-- Register New Patient --");
        int id = readInt("Patient ID: ");

        if (patientTree.search(id) != null) {
            System.out.println("A patient with ID " + id + " already exists.");
            return;
        }

        String name = readString("Patient Name: ");
        int age = readInt("Age: ");
        String contact = readString("Contact Number: ");
        String condition = readString("Medical Condition: ");

        Patient patient = new Patient(id, name, age, contact, condition);
        patientTree.insert(patient);
        System.out.println("Patient registered successfully.");
    }

    private static void searchPatient() {
        System.out.println("\n-- Search Patient --");
        int id = readInt("Enter Patient ID to search: ");
        Patient found = patientTree.search(id);

        if (found == null) {
            System.out.println("No patient found with ID " + id + ".");
        } else {
            System.out.println("Patient found: " + found);
        }
    }

    private static void deletePatient() {
        System.out.println("\n-- Delete Patient --");
        int id = readInt("Enter Patient ID to delete: ");
        boolean removed = patientTree.delete(id);

        if (removed) {
            System.out.println("Patient " + id + " deleted successfully.");
        } else {
            System.out.println("No patient found with ID " + id + ".");
        }
    }

    private static void viewAllPatients() {
        System.out.println("\n-- All Patients (Ascending Order of ID) --");
        patientTree.inorderTraversal();
    }

    // ==================== QUEUE: EMERGENCY WAITING LIST ====================

    private static void addToQueue() {
        System.out.println("\n-- Add Patient to Emergency Queue --");
        int id = readInt("Enter Patient ID (must already be registered): ");
        Patient patient = patientTree.search(id);

        if (patient == null) {
            System.out.println("No patient found with ID " + id + ". Please register them first.");
            return;
        }
        emergencyQueue.enqueue(patient);
    }

    private static void treatNextPatient() {
        System.out.println("\n-- Treat Next Patient --");
        Patient next = emergencyQueue.dequeue();
        if (next != null) {
            System.out.println("Now treating: " + next);
        }
    }

    private static void viewQueue() {
        System.out.println("\n-- Patients Currently Waiting --");
        emergencyQueue.displayQueue();
    }

    // ==================== STACK: TREATMENT HISTORY ====================

    private static void completeTreatment() {
        System.out.println("\n-- Complete Treatment --");
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientTree.search(id);

        if (patient == null) {
            System.out.println("No patient found with ID " + id + ".");
            return;
        }

        String details = readString("Enter treatment details: ");
        String time = readString("Enter completion time/date: ");

        TreatmentRecord record = new TreatmentRecord(id, patient.name, details, time);
        treatmentHistory.push(record);
    }

    private static void viewTreatmentHistory() {
        System.out.println("\n-- Treatment History (Most Recent First) --");
        treatmentHistory.displayStack();
    }

    private static void undoLastTreatment() {
        System.out.println("\n-- Undo Last Treatment --");
        TreatmentRecord popped = treatmentHistory.pop();
        if (popped != null) {
            System.out.println("Removed most recent treatment record: " + popped);
        }
    }

    // ==================== LINKED LIST: PATIENT VISIT HISTORY ====================

    private static void addVisit() {
        System.out.println("\n-- Add Visit to Patient History --");
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientTree.search(id);

        if (patient == null) {
            System.out.println("No patient found with ID " + id + ".");
            return;
        }

        int visitId = readInt("Visit ID: ");
        String date = readString("Visit Date: ");
        String doctor = readString("Doctor Name: ");
        String diagnosis = readString("Diagnosis: ");
        String treatment = readString("Treatment: ");

        patient.visitHistory.addVisit(visitId, date, doctor, diagnosis, treatment);
    }

    private static void viewVisitHistory() {
        System.out.println("\n-- View Patient Visit History --");
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientTree.search(id);

        if (patient == null) {
            System.out.println("No patient found with ID " + id + ".");
            return;
        }

        System.out.println("Visit history for " + patient.name + ":");
        patient.visitHistory.displayVisits();
    }

    private static void removeVisit() {
        System.out.println("\n-- Remove a Visit --");
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientTree.search(id);

        if (patient == null) {
            System.out.println("No patient found with ID " + id + ".");
            return;
        }

        int visitId = readInt("Enter Visit ID to remove: ");
        patient.visitHistory.removeVisit(visitId);
    }

    private static void searchVisit() {
        System.out.println("\n-- Search for a Visit --");
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientTree.search(id);

        if (patient == null) {
            System.out.println("No patient found with ID " + id + ".");
            return;
        }

        int visitId = readInt("Enter Visit ID to search: ");
        Visit found = patient.visitHistory.searchVisit(visitId);

        if (found == null) {
            System.out.println("Visit #" + visitId + " not found for this patient.");
        } else {
            System.out.println("Visit found: " + found);
        }
    }

    // ==================== INPUT HELPERS ====================

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }
}
