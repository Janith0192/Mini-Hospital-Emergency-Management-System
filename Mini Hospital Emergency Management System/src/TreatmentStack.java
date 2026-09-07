/**
 * LIFO Stack that stores completed treatment records.
 * Implemented manually with a linked structure (top pointer) rather than
 * using java.util.Stack, to demonstrate understanding of the structure.
 *
 * Supported operations:
 *  - push(record)     -> add a newly completed treatment on top
 *  - pop()            -> remove and return the most recently completed treatment
 *  - displayStack()   -> show all records, most recent first
 *  - isEmpty()        -> check whether any records exist
 */
public class TreatmentStack {

    private class StackNode {
        TreatmentRecord record;
        StackNode next;

        StackNode(TreatmentRecord record) {
            this.record = record;
            this.next = null;
        }
    }

    private StackNode top;
    private int size;

    public TreatmentStack() {
        top = null;
        size = 0;
    }

    /** Push a newly completed treatment record onto the stack. */
    public void push(TreatmentRecord record) {
        StackNode newNode = new StackNode(record);
        newNode.next = top;
        top = newNode;
        size++;
        System.out.println("Treatment record for patient " + record.patientId + " pushed onto history stack.");
    }

    /** Pop (remove and return) the most recently completed treatment record. */
    public TreatmentRecord pop() {
        if (isEmpty()) {
            System.out.println("Treatment history is empty. Nothing to pop.");
            return null;
        }
        TreatmentRecord popped = top.record;
        top = top.next;
        size--;
        return popped;
    }

    /** Peek at the most recent record without removing it. */
    public TreatmentRecord peek() {
        if (isEmpty()) {
            System.out.println("Treatment history is empty.");
            return null;
        }
        return top.record;
    }

    /** Display all treatment records, most recently completed first. */
    public void displayStack() {
        if (isEmpty()) {
            System.out.println("  No treatment records available.");
            return;
        }
        StackNode current = top;
        int position = 1;
        while (current != null) {
            System.out.println("  " + position + ". " + current.record);
            current = current.next;
            position++;
        }
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int getSize() {
        return size;
    }
}
