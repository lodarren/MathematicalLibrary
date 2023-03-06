package persistence;

import model.*;
import model.exceptions.JsonNotFound;
import org.json.JSONArray;

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
        JSONArray json = lot.listOfTheoremsToJson();
        saveToFile(json.toString(TAB));
    }

    public void writeListOfEquation(ListOfEquations loe) {
        JSONArray json = loe.listOfEquationsToJson();
        saveToFile(json.toString(TAB));
    }

    public void writeListOfRequests(ListOfRequests lor) {
        JSONArray json = lor.listOfRequestsToJson();
        saveToFile(json.toString(TAB));
    }

    public void close() {
        writer.close();
    }

    private void saveToFile(String json) {
        writer.print(json);
    }

}
