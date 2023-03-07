package persistence;

import model.*;

import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;

// This class represents a reader that reads library data from JSON data stored in file
public class JsonReader {
    private String location;

    public JsonReader(String location) {
        this.location = location;
    }

    //Cite this piece of code
    private String readFile(String location) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(location), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    public ListOfTheorems readTheorems() throws IOException {
        String jsonData = readFile(location);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseListOfTheorems(jsonObject);
    }


    private ListOfTheorems parseListOfTheorems(JSONObject jsonObject) {
        ListOfTheorems lot = new ListOfTheorems();
        this.addTheorems(lot, jsonObject);
        return lot;
    }

    private void addTheorems(ListOfTheorems lot, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("theorems");
        Iterator var4 = jsonArray.iterator();

        while (var4.hasNext()) {
            Object json = var4.next();
            JSONObject nextTheorem = (JSONObject)json;
            this.addTheorem(lot, nextTheorem);
        }
    }

    private void addTheorem(ListOfTheorems lot, JSONObject jsonObject) {
        String name = jsonObject.getString("theoremname");
        String theoremstatement = jsonObject.getString("theoremtheorem");
        String proof = jsonObject.getString("theoremproof");
        String explanation = jsonObject.getString("theoremexplanation");
        String course = jsonObject.getString("theoremcourse");
        Theorem theorem;
        theorem = new Theorem(name, theoremstatement, course, proof, explanation);
        lot.addTheorem(theorem);
    }

    public ListOfEquations readEquations() throws IOException {
        String jsonData = readFile(location);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseListOfEquations(jsonObject);
    }


    private ListOfEquations parseListOfEquations(JSONObject jsonObject) {
        ListOfEquations loe = new ListOfEquations();
        this.addEquations(loe, jsonObject);
        return loe;
    }

    private void addEquations(ListOfEquations loe, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("equations");
        Iterator var4 = jsonArray.iterator();

        while (var4.hasNext()) {
            Object json = var4.next();
            JSONObject nextEquation = (JSONObject)json;
            this.addEquation(loe, nextEquation);
        }
    }

    private void addEquation(ListOfEquations loe, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String theoremstatement = jsonObject.getString("theorem");
        String proof = jsonObject.getString("proof");
        String explanation = jsonObject.getString("explanation");
        String course = jsonObject.getString("course");
        Equation equation;
        equation = new Equation(name, theoremstatement, course, proof, explanation);

        ArrayList<String> practiceProblems;
        ArrayList<String> practiceProblemAnswers;
        JSONArray jsonArray = new JSONArray();
        //might duplicate problems and answers

        practiceProblems = parsePracticeProblems(jsonArray);
        practiceProblemAnswers = parsePracticeProblemAnswers(jsonArray);
        equation.addAllPracticeAndAnswers(practiceProblems, practiceProblemAnswers);

        loe.addEquation(equation);
    }

    private ArrayList<String> parsePracticeProblems(JSONArray jsonArray) {
        ArrayList<String> practiceProblems;
        practiceProblems = new ArrayList<>();
        this.addPracticeProblems(practiceProblems, jsonArray);
        return practiceProblems;
    }


    private void addPracticeProblems(ArrayList<String> practiceProblems, JSONArray jsonArray) {
        Iterator var4 = jsonArray.iterator();

        while (var4.hasNext()) {
            Object json = var4.next();
            JSONObject nextProblem = (JSONObject)json;
            this.addPracticeProblem(practiceProblems, nextProblem);
        }
    }

    private void addPracticeProblem(ArrayList<String> practiceProblems, JSONObject jsonObject) {
        String practiceProblem = jsonObject.getString("practice problems");
        practiceProblems.add(practiceProblem);
    }

    private ArrayList<String> parsePracticeProblemAnswers(JSONArray jsonArray) {
        ArrayList<String> practiceProblemsAnswers;
        practiceProblemsAnswers = new ArrayList<>();
        this.addPracticeProblemAnswers(practiceProblemsAnswers, jsonArray);
        return practiceProblemsAnswers;
    }

    private void addPracticeProblemAnswers(ArrayList<String> practiceProblemsAnswers, JSONArray jsonArray) {
        Iterator var4 = jsonArray.iterator();

        while (var4.hasNext()) {
            Object json = var4.next();
            JSONObject nextProblem = (JSONObject)json;
            this.addPracticeProblemAnswer(practiceProblemsAnswers, nextProblem);
        }
    }

    private void addPracticeProblemAnswer(ArrayList<String> practiceProblemsAnswers, JSONObject jsonObject) {
        String practiceProblemAnswer = jsonObject.getString("practice problem answers");
        practiceProblemsAnswers.add(practiceProblemAnswer);
    }


    public ListOfRequests readRequests() throws IOException {
        String jsonData = readFile(location);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseListOfRequests(jsonObject);
    }


    private ListOfRequests parseListOfRequests(JSONObject jsonObject) {
        ListOfRequests lor = new ListOfRequests();
        this.addRequests(lor, jsonObject);
        return lor;
    }

    private void addRequests(ListOfRequests lor, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("requests");
        Iterator var4 = jsonArray.iterator();

        while (var4.hasNext()) {
            Object json = var4.next();
            JSONObject nextRequest = (JSONObject)json;
            this.addRequest(lor, nextRequest);
        }
    }

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
