package model;

import java.util.ArrayList;

public class ListOfEquations {
    // A list of equations that is to be put in the library. No Equation with the same name can show up
    // twice on this list.
    ArrayList<Equation> entries;

    //EFFECTS: constructs a new listOFEquations with an empty list.
    public ListOfEquations() {
        entries = new ArrayList();
    }

    //MODIFIES: this
    //EFFECTS: returns -1 if the string inputted does not match the name of any of the Equations,
    //         otherwise it returns the index it is located.
    public int doesEquationExist(String nameOfEquation) {
        int counter = 0;
        for (Equation e : entries) {
            if (nameOfEquation.equalsIgnoreCase(e.getName())) {
                return counter;
            } else {
                counter++;
            }
        }
        return -1;
    }

    //MODIFIES: this
    //EFFECTS: prints all the equations in the format: #. (name of equation)
    public String printAllEquations() {
        int counter = entries.size();
        String text = "";
        for (Equation t: entries) {
            text = t.getName() + text;
            text = "\n" + counter + "." + text;
            counter--;
        }
        return text;
    }

    //MODIFIES: this
    //EFFECTS: returns true if there are no equations in entries.
    public Boolean isListEmpty() {
        return entries.isEmpty();
    }

    //MODIFIES: this
    //EFFECTS: returns true if the equation exists. Otherwise false.
    public Boolean checkIfEquationExists(String name) {
        return -1 != doesEquationExist(name);
    }

    //MODFIIES: this
    //EFFECTS: adds the equation to the last index of the entries list.
    public void addEquation(Equation equation) {
        entries.add(equation);
    }

    //MODFIIES: this
    //EFFECTS: removes the equation whose identity matches that in the list.
    public void removeEquation(Equation equation) {
        entries.remove(equation);
    }

    //MODFIIES: this
    //EFFECTS: returns the Equation that is located on that index.
    public Equation getEquation(int i) {
        return entries.get(i);
    }
}
