package model;

import model.exceptions.NameAlreadyExists;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

// A list of Theorems that is to be put in the library. No Theorems with the same name can show up
// twice on this list.

public class ListOfTheorems {
    ArrayList<Theorem> entries;     //A list of Theorem entries that are in the library.

    // EFFECTS: Constructs a new ListOfTheorems with an empty list.
    public ListOfTheorems() {
        entries = new ArrayList();
    }

    //MODIFIES: this
    //EFFECTS: returns -1 if the string inputted does not match the name of the Theorems,
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

    //MODIFIES: this, theorem
    //EFFECTS: Checks if Theorem exists in entries, if so it changes the name of the request. If the entry exists, it
    //         throws a NameAlreadyExists exception.
    public void changeTheoremNameAndCheckExistence(Theorem theorem, String name) throws NameAlreadyExists {
        if (checkIfTheoremExists(name)) {
            throw new NameAlreadyExists();
        }
        theorem.changeName(name);
    }

    //MODIFIES: this, theorem
    //EFFECTS: Checks if Theorem exists in entries, if so it adds request to entries. If the entry exists, it
    //         throws a NameAlreadyExists exception.
    public void addTheoremAndCheckExistence(Theorem theorem) throws NameAlreadyExists {
        if (checkIfTheoremExists(theorem.getName())) {
            throw new NameAlreadyExists();
        }
        entries.add(theorem);
    }

    //MODIFIES: this
    //EFFECTS: returns true if the Theorem exists. Otherwise,false.
    public Boolean checkIfTheoremExists(String name) {
        return -1 != doesTheoremExist(name);
    }

    //MODIFIES: this
    //EFFECTS: adds the Theorem to the last index of the entries list.
    public void addTheorem(Theorem theorem) {
        entries.add(theorem);
        EventLog.getInstance().logEvent(new Event("Added " + theorem.getName() + " to the Theorem library."));
    }

    //MODIFIES: this
    //EFFECTS: removes the Theorem whose identity matches that in the list.
    public void removeTheorem(Theorem theorem) {
        entries.remove(theorem);
        EventLog.getInstance().logEvent(new Event("Removed " + theorem.getName() + " from the Theorem library."));
    }

    //MODIFIES this:
    //EFFECTS: returns the Theorem located on the index in entries.
    public Theorem getTheorem(int i) {
        return entries.get(i);
    }

    //MODIFIES: this
    //EFFECTS: returns true if there are no Theorems in entries.
    public Boolean isListEmpty() {
        return entries.isEmpty();
    }

    //MODIFIES: this
    //EFFECTS: returns the number of entries in this list
    public int numberOfTheorems() {
        return entries.size();
    }

    //MODIFIES: this
    //EFFECTS: returns the number of practiceProblemAnswers in this list
    public JSONObject listOfTheoremsToJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("theorems", theoremsToJson());
        return jsonObject;
    }

    //MODIFIES: this
    //EFFECTS: turns the entries in ListOfRequests into a JSONArraylist
    public JSONArray theoremsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Theorem e : entries) {
            jsonArray.put(e.theoremToJson());
        }
        EventLog.getInstance().logEvent(new Event("Saved Theorem Library."));
        return jsonArray;
    }

    //MODIFIES: this
    //EFFECTS: returns the String list of all the theorem names:
    public ArrayList<String> theoremsToString() {
        ArrayList<String> listOfString = new ArrayList<>();
        for (Theorem t : entries) {
            listOfString.add(t.getName());
        }
        return listOfString;
    }
}
