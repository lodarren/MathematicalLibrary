package model;

import java.util.ArrayList;

// A list of Theorems that is to be put in the library. No Theorems with the same name can show up
// twice on this list.

public class ListOfTheorems {
    ArrayList<Theorem> entries;

    // EFFECTS: Constructs a new ListOfTheorems with an empty list.
    public ListOfTheorems() {
        entries = new ArrayList();
    }

    //MODIFIES: this
    //EFFECTS: returns -1 if the string inputted does not match the name of any of the Theorems,
    //         otherwise it returns the index it is located.
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

    //MODIFIES: this
    //EFFECTS: prints all the equations in the format: #. (name of Theorem)
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

    //MODIFIES: this
    //EFFECTS: returns true if the Theorem exists. Otherwise false.
    public Boolean checkIfTheoremExists(String name) {
        return -1 != doesTheoremExist(name);
    }

    //MODFIIES: this
    //EFFECTS: adds the Theorem to the last index of the entries list.
    public void addTheorem(Theorem theorem) {
        entries.add(theorem);
    }

    //MODFIIES: this
    //EFFECTS: removes the Theorem whose identity matches that in the list.
    public void removeTheorem(Theorem theorem) {
        entries.remove(theorem);
    }

    //MODFIIES this:
    //EFFECTS: returns the Theorem located on the index in entries.
    public Theorem getTheorem(int i) {
        return entries.get(i);
    }

    //MODIFIES: this
    //EFFECTS: returns true if there are no Theorems in entries.
    public Boolean isListEmpty() {
        return entries.isEmpty();
    }
}
