package model;

import model.exceptions.IndexNotThere;
import org.json.JSONObject;

import java.util.ArrayList;

// Represents math concepts that can be represented as an Equation, contains its name, its mathematical expression,
// the class it is for, the description for it, the derivation for the equation, comments, and a
// list of practice questions. The list of practice problem answers are in tandem with the practice problems.

public class Equation extends Entry {
    ArrayList<String> practiceProblems;          //A list of practice problems for the user to use
    ArrayList<String> practiceProblemsAnswer;    //A list of practice problems answers in tandem with the practice
                                                 // problems list

    //EFFECTS: creates a new equation object.
    public Equation(String name, String theorem, String course, String proof, String explanation) {
        super(name, theorem, course, proof, explanation);
        practiceProblems = new ArrayList<>();
        practiceProblemsAnswer = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: change the practice problem and the answer on the specified index.
    public void changePracticeProblem(String practiceProblem, String answer, int i) {
        practiceProblems.set(i, practiceProblem);
        practiceProblemsAnswer.set(i, answer);
    }

    //MODIFIES: this
    //EFFECTS: add practice problem and its solution to both problems and the answers list
    public void addPracticeProblem(String problem, String answer) {
        practiceProblems.add(problem);
        practiceProblemsAnswer.add(answer);
    }

    //MODIFIES: this
    //EFFECTS: deletes practice problem and its associated solution
    public void removePracticeProblem(int i)  {
        practiceProblems.remove(i);
        practiceProblemsAnswer.remove(i);
    }

    //MODIFIES: this
    //EFFECTS: gets the practice problem given the index
    public String getThePracticeProblem(int i) {
        return practiceProblems.get(i);
    }

    //MODIFIES: this
    //EFFECTS: gets the practice problem answer given the index
    public String getThePracticeProblemAnswer(int i) {
        return practiceProblemsAnswer.get(i);
    }

    //MODIFIES: this
    //EFFECTS: get Practice Problems in a list
    public ArrayList<String> getPracticeProblemsRaw() {
        return practiceProblems;
    }

    //MODIFIES: this
    //EFFECTS: gets Practice Problem solutions in a list
    public ArrayList<String> getPracticeProblemsAnswerRaw() {
        return practiceProblemsAnswer;
    }

    //MODIFIES: this
    //EFFECTS: Shows the number of practice problems in the entry:
    public String showNumberOfPracticeProblems() throws IndexNotThere {
        if (practiceProblems.isEmpty()) {
            throw new IndexNotThere();
        } else {
            int counter = practiceProblems.size();
            String text = "";
            for (String p : practiceProblems) {
                text = "Practice Problem " + counter + "\n" + text;
                counter--;
            }
            return text;
        }
    }

    //MODIFIES: this
    //EFFECTS: Checks if the index of the practice problem is within the list of practice problems
    public Boolean doesPracticeProblemExist(String i) {
        int number = Integer.parseInt(i);
        int size = practiceProblems.size() + 1;
        return number <= size;
    }


    //MODIFIES: this
    //EFFECTS: returns the fields of the equation in text format in the form Field: field.
    public String viewEquation() {
        String text;
        text = "\nName: " + this.getName();
        text = text + "\nStatement: " + this.getTheorem();
        text = text + "\nCourse this is most relevant to: " + this.getCourse();
        text = text + "\nDescription: " + this.getExplanations();
        text = text + "\nDerivation: " + this.getProof() + "\n";
        return text;
    }

    public JSONObject equationToJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("theorem", theorem);
        json.put("course", course);
        json.put("proof", proof);
        json.put("explanation", explanations);
        json.put("practice problems", practiceProblems.practiceProblemsToJson);
        json.put("practice problem answers", practiceProblemsAnswer.practiceProblemAnswersToJson);
        return json;
    }
}


