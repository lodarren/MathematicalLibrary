package model.EntryTypes*;
package model;

import java.util.ArrayList;

// Represents the full list of finished mathematical entries in the library, can either contain Equations or Theorems,
// and has a section for Requests
public class Library {
    ArrayList<Theorem> listOfTheorems;
    ArrayList<Equation> listOfEquations;
    ArrayList<Request> listofRequests;

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: creates a new constructor

    public Library() {
        listOfTheorems = new ArrayList<>();
        listOfEquations = new ArrayList<>();
        listofRequests = new ArrayList<>();
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: adds an entry to the listofEquations

    public void addEquation(Equation equation) {
        listOfEquations.add(equation);
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: adds an entry to the listofTheorems

    public void addTheorem(Theorem theorem) {
        listOfTheorems.add(theorem);
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: adds a request to the main library and checks which list it belongs to

    public void turnRequestIntoEntry(Request request) {
        if (request.isRequestCompleted()) {
            if (request.isItAnEquation()) {
                listOfEquations.add(request.requestToEquation());
            } else {
                listOfTheorems.add(request.requestToTheorem());
            }
        }
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: Searches if a certain entry exists in listofEquations and returns its index (and should open it up after)

    public int doesEquationExist(String nameOfEquation) {
        int counter = 0;
        for (Equation e: listOfEquations) {
            if (nameOfEquation.equals(e.getName())) {
                return counter;
            } else {
                counter++;
            }
        }

        return -1;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: Searches if a certain entry exists in listofTheorems

    public int doesTheoremExist(String nameOfTheorem) {
        int counter = 0;
        for (Theorem t: listOfTheorems) {
            if (nameOfTheorem.equals(t.getName())) {
                return counter;
            } else {
                counter++;
            }
        }

        return "Theorem does not exist!";
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: lists the names of the Equations so far

    public String printAllEquations() {
        int counter = 1;
        String text = "";
        for (Equation e: listOfEquations) {
            text = counter + e.getName() + text;
        }
        return text;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: lists the names of the Theorems so far

    public String printAllTheorems() {
        int counter = 1;
        String text = "";
        for (Theorem t: listOfTheorems) {
            text = counter + t.getName() + text;
        }
        return text;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: lists the names of the Requests so far

    public String printAllRequests() {
        int counter = 1;
        String text = "";
        for (Request r: listofRequests) {
            text = Integer.toString(counter) + r.getName() + text;
        }
        return text;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: shows all current theorems and lists in alphabetical order

    public
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: shows all current Equations and lists them in alphabetical order

    public void sortEquations() {
        listOfEquations.sort();
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: Should let you add as many requests at once



}
