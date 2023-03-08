package persistence;

import model.*;

import org.json.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;

// This class represents a reader that reads library data from JSON data stored in file
public class JsonReader {
    private String location; //Represents where the json file is located

    //MODFIIES: this
    //EFFECTS: Creates a new JSONReader at its specified location
    public JsonReader(String location) {
        this.location = location;
    }

    //EFFECTS: attempts to read the JSON file, if no such file exists then an IOException is thrown.
    //         Note: this code is modified from the CPSC210 JsonSerializationDemo
    private String readFile(String location) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(location), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    //EFFECTS: reads the JSON file for listOfTheorems, and returns a ListOfTheorems object with all its fields filled in
    //         as instructed by the JSON file. Throws an IOException if the file does not exist.
    public ListOfTheorems readTheorems() throws IOException {
        String jsonData = readFile(location);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseListOfTheorems(jsonObject);
    }

    //EFFECTS: Creates a ListOfTheorems and adds the theorems in the JSONArraylist into the entries list.
    private ListOfTheorems parseListOfTheorems(JSONObject jsonObject) {
        ListOfTheorems lot = new ListOfTheorems();
        this.addTheorems(lot, jsonObject);
        return lot;
    }

    //EFFECTS: Adds a theorem to the ListOfTheorem's entry for each element in the ArrayList.
    private void addTheorems(ListOfTheorems lot, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("theorems");
        Iterator var4 = jsonArray.iterator();

        while (var4.hasNext()) {
            Object json = var4.next();
            JSONObject nextTheorem = (JSONObject)json;
            this.addTheorem(lot, nextTheorem);
        }
    }

    //EFFECTS: Creates a Theorem object as per the JSON file's instructions.
    private void addTheorem(ListOfTheorems lot, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String theoremstatement = jsonObject.getString("theorem");
        String proof = jsonObject.getString("proof");
        String explanation = jsonObject.getString("explanation");
        String course = jsonObject.getString("course");
        Theorem theorem;
        theorem = new Theorem(name, theoremstatement, course, proof, explanation);
        lot.addTheorem(theorem);
    }

    //EFFECTS: reads the JSON file for ListOfEquations, and returns a ListOfEquations object with all its fields filled
    //         in as instructed by the JSON file. Throws an IOException if the file does not exist.
    public ListOfEquations readEquations() throws IOException {
        String jsonData = readFile(location);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseListOfEquations(jsonObject);
    }

    //EFFECTS: Creates a ListOfEquations and adds the theorems in the JSONArraylist into the entries list.
    private ListOfEquations parseListOfEquations(JSONObject jsonObject) {
        ListOfEquations loe = new ListOfEquations();
        this.addEquations(loe, jsonObject);
        return loe;
    }

    //EFFECTS: Adds an Equation to the ListOfEquation's entry for each element in the ArrayList.
    private void addEquations(ListOfEquations loe, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("equations");
        Iterator var4 = jsonArray.iterator();

        while (var4.hasNext()) {
            Object json = var4.next();
            JSONObject nextEquation = (JSONObject)json;
            this.addEquation(loe, nextEquation, jsonObject);
        }
    }

    //EFFECTS: Creates an Equation object as per the JSON file's instructions. It also adds the practice problems for
    //         each Equation if it exists.
    private void addEquation(ListOfEquations loe, JSONObject nextEquation, JSONObject jsonObject) {
        ArrayList<String> practiceProblems;
        ArrayList<String> practiceProblemAnswers;

        String name = nextEquation.getString("name");
        String theoremstatement = nextEquation.getString("theorem");
        String proof = nextEquation.getString("proof");
        String explanation = nextEquation.getString("explanation");
        String course = nextEquation.getString("course");
        Equation equation;
        equation = new Equation(name, theoremstatement, course, proof, explanation);

        practiceProblems = parsePracticeProblems(nextEquation);
        practiceProblemAnswers = parsePracticeProblemAnswers(nextEquation);
        equation.addAllPracticeAndAnswers(practiceProblems, practiceProblemAnswers);

        loe.addEquation(equation);
    }

    //EFFECTS: Creates a list with all the practice problems as specified by the JSON file.
    private ArrayList<String> parsePracticeProblems(JSONObject equation) {
        JSONArray jsonArray = equation.getJSONArray("practice problems");
        ArrayList<String> practiceProblems = new ArrayList<>();
        Iterator var4 = jsonArray.iterator();

        while (var4.hasNext()) {
            Object json = var4.next();
            JSONObject nextQuestion = (JSONObject)json;
            practiceProblems.add(nextQuestion.getString("question"));
        }
        return practiceProblems;
    }

    //EFFECTS: Creates a list with all the practice problem answers as specified by the JSON file.
    private ArrayList<String> parsePracticeProblemAnswers(JSONObject equation) {
        JSONArray jsonArray = equation.getJSONArray("practice problem answers");
        ArrayList<String> practiceProblemsAnswers = new ArrayList<>();
        Iterator var4 = jsonArray.iterator();

        while (var4.hasNext()) {
            Object json = var4.next();
            JSONObject nextQuestion = (JSONObject)json;
            practiceProblemsAnswers.add(nextQuestion.getString("answer"));
        }
        return practiceProblemsAnswers;
    }

    //EFFECTS: reads the JSON file for ListOfRequests, and returns a ListOfRequests object with all its fields filled
    //         in as instructed by the JSON file. Throws an IOException if the file does not exist.
    public ListOfRequests readRequests() throws IOException {
        String jsonData = readFile(location);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseListOfRequests(jsonObject);
    }

    //EFFECTS: Creates a ListOfRequests and adds the theorems in the JSONArraylist into the entries list.
    private ListOfRequests parseListOfRequests(JSONObject jsonObject) {
        ListOfRequests lor = new ListOfRequests();
        this.addRequests(lor, jsonObject);
        return lor;
    }

    //EFFECTS: Adds a request to the ListOfRequest's entry for each element in the ArrayList.
    private void addRequests(ListOfRequests lor, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("requests");
        Iterator var4 = jsonArray.iterator();

        while (var4.hasNext()) {
            Object json = var4.next();
            JSONObject nextRequest = (JSONObject)json;
            this.addRequest(lor, nextRequest);
        }
    }

    //EFFECTS: Creates a Request object as per the JSON file's instructions. It also updates the estimated completion
    //         time if specified.
    private void addRequest(ListOfRequests lor, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String theoremstatement = jsonObject.getString("theorem");
        String type = jsonObject.getString("type");
        String proof = jsonObject.getString("proof");
        String explanation = jsonObject.getString("explanation");
        String course = jsonObject.getString("course");
        Request request;
        request = new Request(name, theoremstatement, type, course, proof, explanation);
        request.updateEstimatedCompletion(Integer.toString(jsonObject.getInt("request estimated completion")));
        lor.addRequest(request);
    }

}
