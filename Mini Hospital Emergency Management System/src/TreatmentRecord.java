/**
 * Represents a single completed treatment record.
 * These are the items stored in the TreatmentStack.
 */
public class TreatmentRecord {
    int patientId;
    String patientName;
    String treatmentDetails;
    String completionTime;

    public TreatmentRecord(int patientId, String patientName, String treatmentDetails, String completionTime) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.treatmentDetails = treatmentDetails;
        this.completionTime = completionTime;
    }

    @Override
    public String toString() {
        return "Patient ID: " + patientId +
                " | Name: " + patientName +
                " | Treatment: " + treatmentDetails +
                " | Completed: " + completionTime;
    }
}
