package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;

// This class represents a writer translates project objects into JSONObjects to introduce a save function in the
// program
public class JsonWriter {
    private static final int TAB = 4; //The buffer between objects in the JSON file
    private PrintWriter writer; //A class that updates the JSONfile
    private String location; //The location in which the JSON file is stored

    //MODFIIES: this
    //EFFECTS: Creates a new JSONWriter at its specified location
    public JsonWriter(String location) {
        this.location = location;
    }

    //EFFECTS: Attempts to open the JSON file, throws a FileNotFound Exception if it doesn't exist.
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(location));
    }

    //MODFIIES: this
    //EFFECTS: Turns the ListOfTheorems object into a JSONObject.
    public void writeListOfTheorem(ListOfTheorems lot) {
        JSONObject json = lot.listOfTheoremsToJson();
        saveToFile(json.toString(TAB));
    }

    //MODFIIES: this
    //EFFECTS: Turns the ListOfEquations object into a JSONObject.
    public void writeListOfEquation(ListOfEquations loe) {
        JSONObject json = loe.listOfEquationsToJson();
        saveToFile(json.toString(TAB));
    }

    //MODFIIES: this
    //EFFECTS: Turns the ListOfRequests object into a JSONObject.
    public void writeListOfRequests(ListOfRequests lor) {
        JSONObject json = lor.listOfRequestsToJson();
        saveToFile(json.toString(TAB));
    }

    //EFFECTS: closes the writer to signify no more changes are to be made
    public void close() {
        writer.close();
    }

    //EFFECTS: Prints the file to the JSONFile
    private void saveToFile(String json) {
        writer.print(json);
    }

}
