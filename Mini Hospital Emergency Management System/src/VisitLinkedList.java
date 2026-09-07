/**
 * A Singly Linked List that stores a patient's visit history.
 * Each Patient object owns exactly one of these.
 *
 * Supported operations:
 *  - addVisit(...)      -> append a new visit to the end of the list
 *  - removeVisit(id)    -> remove a visit by its visitId
 *  - searchVisit(id)    -> find and return a visit by its visitId
 *  - displayVisits()    -> print all visits in order (oldest -> newest)
 */
public class VisitLinkedList {
    private Visit head; // first node in the list
    private int size;

    public VisitLinkedList() {
        this.head = null;
        this.size = 0;
    }

    /** Add a new visit to the end of the list. */
    public void addVisit(int visitId, String visitDate, String doctorName, String diagnosis, String treatment) {
        Visit newVisit = new Visit(visitId, visitDate, doctorName, diagnosis, treatment);

        if (head == null) {
            head = newVisit;
        } else {
            Visit current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newVisit;
        }
        size++;
        System.out.println("Visit #" + visitId + " added to history.");
    }

    /** Remove a visit by its visitId. Returns true if removed, false if not found. */
    public boolean removeVisit(int visitId) {
        if (head == null) {
            System.out.println("Visit history is empty. Nothing to remove.");
            return false;
        }

        // Case 1: the node to remove is the head
        if (head.visitId == visitId) {
            head = head.next;
            size--;
            System.out.println("Visit #" + visitId + " removed.");
            return true;
        }

        // Case 2: the node to remove is somewhere after the head
        Visit current = head;
        while (current.next != null && current.next.visitId != visitId) {
            current = current.next;
        }

        if (current.next == null) {
            System.out.println("Visit #" + visitId + " not found.");
            return false;
        }

        // current.next is the node to remove -> skip over it
        current.next = current.next.next;
        size--;
        System.out.println("Visit #" + visitId + " removed.");
        return true;
    }

    /** Search for a visit by its visitId. Returns the Visit if found, otherwise null. */
    public Visit searchVisit(int visitId) {
        Visit current = head;
        while (current != null) {
            if (current.visitId == visitId) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    /** Display all visits in the list, from oldest to newest. */
    public void displayVisits() {
        if (head == null) {
            System.out.println("  No visit history recorded.");
            return;
        }
        Visit current = head;
        while (current != null) {
            System.out.println("  " + current);
            current = current.next;
        }
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }
}
