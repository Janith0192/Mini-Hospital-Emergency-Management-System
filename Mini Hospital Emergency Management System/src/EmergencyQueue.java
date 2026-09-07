/**
 * FIFO Queue that manages patients waiting in the emergency unit.
 * Implemented manually with linked nodes (front/rear pointers) rather than
 * using java.util.LinkedList, to demonstrate understanding of the structure.
 *
 * Supported operations:
 *  - enqueue(patient)  -> add a patient to the back of the queue
 *  - dequeue()         -> remove and return the patient at the front
 *  - displayQueue()    -> show all waiting patients, front to back
 *  - isEmpty()         -> check whether the queue has anyone waiting
 */
public class EmergencyQueue {

    private class QueueNode {
        Patient patient;
        QueueNode next;

        QueueNode(Patient patient) {
            this.patient = patient;
            this.next = null;
        }
    }

    private QueueNode front; // next patient to be treated
    private QueueNode rear;  // most recently added patient
    private int size;

    public EmergencyQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    /** Add a patient to the back of the waiting queue. */
    public void enqueue(Patient patient) {
        QueueNode newNode = new QueueNode(patient);

        if (rear == null) {
            // queue was empty
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
        System.out.println("Patient " + patient.patientId + " (" + patient.name + ") added to emergency queue.");
    }

    /** Remove and return the patient at the front of the queue (next to be treated). */
    public Patient dequeue() {
        if (isEmpty()) {
            System.out.println("Emergency queue is empty. No patients waiting.");
            return null;
        }

        Patient treated = front.patient;
        front = front.next;

        if (front == null) {
            // queue became empty
            rear = null;
        }
        size--;
        return treated;
    }

    /** Display every patient currently waiting, in FIFO order (front first). */
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("  No patients currently waiting.");
            return;
        }

        QueueNode current = front;
        int position = 1;
        while (current != null) {
            System.out.println("  " + position + ". " + current.patient);
            current = current.next;
            position++;
        }
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int getSize() {
        return size;
    }
}
