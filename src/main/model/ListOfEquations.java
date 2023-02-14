package model;

import model.entryTypes.Equation;
import model.entryTypes.Request;

import java.util.ArrayList;

public class ListOfEquations {
    ArrayList<Equation> entries;

    public ListOfEquations() {
        entries = new ArrayList();
    }

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

    public Boolean isListEmpty() {
        return entries.isEmpty();
    }

    public Boolean checkIfEquationExists(String name) {
        return -1 != doesEquationExist(name);
    }

    public void addEquation(Equation equation) {
        entries.add(equation);
    }

    public void removeEquation(Equation equation) {
        entries.remove(equation);
    }

    public Equation getEquation(int i) {
        return entries.get(i);
    }
}
