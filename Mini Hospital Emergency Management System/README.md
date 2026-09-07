# Mini Hospital Emergency Management System

A console-based Java application developed for the **CIT300 - Data Structures and Algorithms** assignment.

The system manages patient records, emergency patients, completed treatments, and patient visit histories using four different data structures.

## Data Structures Used

| Feature                 | Data Structure           | Implementation         |
| ----------------------- | ------------------------ | ---------------------- |
| Patient Records         | Binary Search Tree (BST) | `PatientBST.java`      |
| Emergency Patient Queue | Queue (FIFO)             | `EmergencyQueue.java`  |
| Treatment History       | Stack (LIFO)             | `TreatmentStack.java`  |
| Patient Visit History   | Singly Linked List       | `VisitLinkedList.java` |

## Features

### Patient Records - BST

Patient records are stored using a Binary Search Tree with **Patient ID** as the key.

* Insert a new patient
* Search for a patient by Patient ID
* Delete a patient
* Display patients using in-order traversal

Patient details include:

* Patient ID
* Patient Name
* Age
* Contact Number
* Medical Condition

### Emergency Patient Queue

Emergency patients are managed using a **FIFO (First-In, First-Out)** queue.

* Enqueue a patient
* Dequeue the next patient
* Display patients currently waiting
* Handle an empty queue

### Treatment History - Stack

Completed treatment records are stored using a **LIFO (Last-In, First-Out)** stack.

* Push a completed treatment record
* Pop the most recently completed treatment
* Display treatment records
* Handle an empty stack

### Patient Visit History - Singly Linked List

Each patient has a singly linked list containing their previous hospital visits.

* Add a visit
* Remove a visit
* Search for a visit
* Display visit history

Each visit contains information such as:

* Visit ID
* Visit Date
* Doctor Name
* Diagnosis
* Treatment

## Project Structure

```text
Mini Hospital Emergency Management System/
│
├── README.md
│
└── src/
    ├── Main.java
    ├── Patient.java
    ├── PatientBST.java
    ├── EmergencyQueue.java
    ├── TreatmentRecord.java
    ├── TreatmentStack.java
    ├── Visit.java
    └── VisitLinkedList.java
```

## How to Run

Open the `src` folder in a Java development environment or use the command line.

Compile:

```bash
javac *.java
```

Run:

```bash
java Main
```

## Main Java Files

* `Main.java` - Main program and menu
* `Patient.java` - Patient information
* `PatientBST.java` - Patient Binary Search Tree
* `EmergencyQueue.java` - Emergency patient queue
* `TreatmentRecord.java` - Treatment record information
* `TreatmentStack.java` - Treatment history stack
* `Visit.java` - Patient visit information
* `VisitLinkedList.java` - Patient visit history linked list

## Author

CIT300 - Data Structures and Algorithms
