/**
 * Binary Search Tree that stores Patient records keyed on patientId.
 *
 * Supported operations:
 *  - insert(patient)      -> add a new patient, positioned by patientId
 *  - search(id)           -> find a patient by patientId
 *  - delete(id)            -> remove a patient by patientId (handles all 3 BST delete cases)
 *  - inorderTraversal()   -> print patients in ascending order of patientId
 */
public class PatientBST {

    /** Internal node class. Each node holds one Patient plus left/right child pointers. */
    private class Node {
        Patient patient;
        Node left, right;

        Node(Patient patient) {
            this.patient = patient;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    public PatientBST() {
        this.root = null;
    }

    // ---------------------- INSERT ----------------------

    public void insert(Patient patient) {
        root = insertRec(root, patient);
    }

    private Node insertRec(Node node, Patient patient) {
        if (node == null) {
            return new Node(patient);
        }

        if (patient.patientId < node.patient.patientId) {
            node.left = insertRec(node.left, patient);
        } else if (patient.patientId > node.patient.patientId) {
            node.right = insertRec(node.right, patient);
        } else {
            // Duplicate ID - reject the insert to keep IDs unique
            System.out.println("Patient ID " + patient.patientId + " already exists. Insert rejected.");
        }
        return node;
    }

    // ---------------------- SEARCH ----------------------

    public Patient search(int patientId) {
        Node result = searchRec(root, patientId);
        return (result != null) ? result.patient : null;
    }

    private Node searchRec(Node node, int patientId) {
        if (node == null || node.patient.patientId == patientId) {
            return node;
        }
        if (patientId < node.patient.patientId) {
            return searchRec(node.left, patientId);
        }
        return searchRec(node.right, patientId);
    }

    // ---------------------- DELETE ----------------------

    public boolean delete(int patientId) {
        if (search(patientId) == null) {
            return false; // nothing to delete
        }
        root = deleteRec(root, patientId);
        return true;
    }

    private Node deleteRec(Node node, int patientId) {
        if (node == null) {
            return null;
        }

        if (patientId < node.patient.patientId) {
            node.left = deleteRec(node.left, patientId);
        } else if (patientId > node.patient.patientId) {
            node.right = deleteRec(node.right, patientId);
        } else {
            // Found the node to delete - handle the 3 classic BST cases

            // Case 1: no children (leaf node)
            if (node.left == null && node.right == null) {
                return null;
            }

            // Case 2: one child - replace node with its only child
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }

            // Case 3: two children
            // Find the in-order successor (smallest value in the right subtree)
            Node successor = findMin(node.right);
            // Copy the successor's data into this node
            node.patient = successor.patient;
            // Delete the successor from the right subtree (it now has a duplicate to remove)
            node.right = deleteRec(node.right, successor.patient.patientId);
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // ---------------------- IN-ORDER TRAVERSAL ----------------------

    /** Displays all patients in ascending order of patientId. */
    public void inorderTraversal() {
        if (root == null) {
            System.out.println("No patient records found.");
            return;
        }
        inorderRec(root);
    }

    private void inorderRec(Node node) {
        if (node == null) {
            return;
        }
        inorderRec(node.left);
        System.out.println("  " + node.patient);
        inorderRec(node.right);
    }

    public boolean isEmpty() {
        return root == null;
    }
}
