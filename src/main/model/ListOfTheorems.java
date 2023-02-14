package model;

import model.entryTypes.Theorem;

import java.util.ArrayList;

public class ListOfTheorems {
    ArrayList<Theorem> entries;

    public ListOfTheorems() {
        entries = new ArrayList();
    }

    public int doesTheoremExist(String nameOfTheorem) {
        int counter = 0;
        for (Theorem t: entries) {
            if (nameOfTheorem.equalsIgnoreCase(t.getName())) {
                return counter;
            } else {
                counter++;
            }
        }
        return -1;
    }

    public String printAllTheorems() {
        int counter = entries.size();
        String text = "";
        for (Theorem t: entries) {
            text = t.getName() + text;
            text = "\n" + counter + "." + text;
            counter--;
        }
        return text;
    }

    public Boolean checkIfTheoremExists(String name) {
        return -1 != doesTheoremExist(name);
    }

    public void addTheorem(Theorem theorem) {
        entries.add(theorem);
    }

    public void removeTheorem(Theorem theorem) {
        entries.remove(theorem);
    }

    public Theorem getTheorem(int i) {
        return entries.get(i);
    }

    public Boolean isListEmpty() {
        return entries.isEmpty();
    }
}
