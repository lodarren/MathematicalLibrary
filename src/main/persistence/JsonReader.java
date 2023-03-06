package persistence;

import model.*;

import model.exceptions.JsonNotFound;
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
    private String location;

    public JsonReader(String location) {
        this.location = location;
    }




    //Cite this piece of code
    private String readFile(String location) throws JsonNotFound, IOException {
        StringBuilder contentBuilder = new StringBuilder();
        Stream<String> stream = Files.lines(Paths.get(location), StandardCharsets.UTF_8);

        try {
            stream.forEach((s) -> {
                contentBuilder.append(s);
            });
        } catch (Throwable var7) {
            if (stream != null) {
                try {
                    stream.close();
                } catch (Throwable var6) {
                    var7.addSuppressed(var6);
                }
            }
            throw var7;
        }

        if (stream != null) {
            stream.close();
        }

        return contentBuilder.toString();
    }

    public ListOfTheorems readTheorems() throws JsonNotFound, IOException {
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

    public ListOfEquations readEquations() throws JsonNotFound, IOException {
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
        String name = jsonObject.getString("equationname");
        String theoremstatement = jsonObject.getString("equationtheorem");
        String proof = jsonObject.getString("equationproof");
        String explanation = jsonObject.getString("equationexplanation");
        String course = jsonObject.getString("equationcourse");
        Equation equation;
        equation = new Equation(name, theoremstatement, course, proof, explanation);

        ArrayList<String> practiceProblems;
        ArrayList<String> practiceProblemAnswers;

        practiceProblems = parsePracticeProblems(jsonObject);
        practiceProblemAnswers = parsePracticeProblemAnswers(jsonObject);
        equation.addAllPracticeAndAnswers(practiceProblems, practiceProblemAnswers);

        loe.addEquation(equation);
    }

    private ArrayList<String> parsePracticeProblems(JSONObject jsonObject) {
        ArrayList<String> practiceProblems;
        practiceProblems = new ArrayList<>();
        this.addPracticeProblems(practiceProblems, jsonObject);
        return practiceProblems;
    }


    private void addPracticeProblems(ArrayList<String> practiceProblems, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("practice problems");
        Iterator var4 = jsonArray.iterator();

        while (var4.hasNext()) {
            Object json = var4.next();
            JSONObject nextProblem = (JSONObject)json;
            this.addPracticeProblem(practiceProblems, nextProblem);
        }
    }

    private void addPracticeProblem(ArrayList<String> practiceProblems, JSONObject jsonObject) {
        String practiceProblem = jsonObject.getString("practice problem");
        practiceProblems.add(practiceProblem);
    }

    private ArrayList<String> parsePracticeProblemAnswers(JSONObject jsonObject) {
        ArrayList<String> practiceProblemsAnswers;
        practiceProblemsAnswers = new ArrayList<>();
        this.addPracticeProblemAnswers(practiceProblemsAnswers, jsonObject);
        return practiceProblemsAnswers;
    }

    private void addPracticeProblemAnswers(ArrayList<String> practiceProblemsAnswers, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("practice problems answers");
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


    public ListOfRequests readRequests() throws JsonNotFound, IOException {
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
        String name = jsonObject.getString("requestname");
        String theoremstatement = jsonObject.getString("requesttheorem");
        String type = jsonObject.getString("requesttype");
        String proof = jsonObject.getString("requestproof");
        String explanation = jsonObject.getString("requestexplanation");
        String course = jsonObject.getString("requestcourse");
        Request request;
        request = new Request(name, theoremstatement, type, course, proof, explanation);
        request.updateEstimatedCompletion(jsonObject.getString("request estimated completion"));
        lor.addRequest(request);
    }

}
