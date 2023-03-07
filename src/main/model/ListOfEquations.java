package model;

import model.exceptions.NameAlreadyExists;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListOfEquations {
    // A list of equations that is to be put in the library. No Equation with the same name can show up
    // twice on this list.

    ArrayList<Equation> entries;        //A list of equation entries that are in the library.

    //EFFECTS: constructs a new listOFEquations with an empty list.
    public ListOfEquations() {
        entries = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: returns -1 if the string inputted does not match the name of the Equations,
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

    //MODIFIES: this, equation
    //EFFECTS: Checks if Equation exists in entries, if so it changes the name of the request. If the entry exists, it
    //         throws a NameAlreadyExists exception.
    public void changeEquationNameAndCheckExistence(Equation equation, String name) throws NameAlreadyExists {
        if (checkIfEquationExists(name)) {
            throw new NameAlreadyExists();
        }
        equation.changeName(name);
    }

    //MODIFIES: this, equation
    //EFFECTS: Checks if Equation exists in entries, if so it adds request to entries. If the entry exists, it
    //         throws a NameAlreadyExists exception.

    public void addEquationAndCheckExistence(Equation equation) throws NameAlreadyExists {
        if (checkIfEquationExists(equation.getName())) {
            throw new NameAlreadyExists();
        }
        entries.add(equation);
    }

    //MODIFIES: this
    //EFFECTS: returns true if there are no equations in entries.
    public Boolean isListEmpty() {
        return entries.isEmpty();
    }

    //MODIFIES: this
    //EFFECTS: returns true if the equation exists. Otherwise, false.
    public Boolean checkIfEquationExists(String name) {
        return -1 != doesEquationExist(name);
    }

    //MODIFIES: this
    //EFFECTS: adds the equation to the last index of the entries list.
    public void addEquation(Equation equation) {
        entries.add(equation);
    }

    //MODIFIES: this
    //EFFECTS: removes the equation whose identity matches that in the list.
    public void removeEquation(Equation equation) {
        entries.remove(equation);
    }

    //MODIFIES: this
    //EFFECTS: returns the Equation that is located on that index.
    public Equation getEquation(int i) {
        return entries.get(i);
    }

    //MODIFIES: this
    //EFFECTS: returns the number of entries in this list
    public int numberOfEquations() {
        return entries.size();
    }


    //MODIFIES: this
    //EFFECTS: turns the ListOfEquations class into a JSONObject
    public JSONObject listOfEquationsToJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("equations", equationsToJson());
        return jsonObject;
    }

    //MODIFIES: this
    //EFFECTS: turns all the equations in entries into a JSONArraylist
    public JSONArray equationsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Equation e : entries) {
            jsonArray.put(e.equationToJson());
        }

        return jsonArray;
    }

}
