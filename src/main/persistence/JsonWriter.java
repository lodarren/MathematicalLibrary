package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;

public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String location;

    public JsonWriter(String location) {
        this.location = location;
    }

    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(location));
    }

    public void writeListOfTheorem(ListOfTheorems lot) {
        JSONObject json = lot.listOfTheoremsToJson();
        saveToFile(json.toString(TAB));
    }

    public void writeListOfEquation(ListOfEquations loe) {
        JSONObject json = loe.listOfEquationsToJson();
        saveToFile(json.toString(TAB));
    }

    public void writeListOfRequests(ListOfRequests lor) {
        JSONObject json = lor.listOfRequestsToJson();
        saveToFile(json.toString(TAB));
    }

    public void close() {
        writer.close();
    }

    private void saveToFile(String json) {
        writer.print(json);
    }

}
