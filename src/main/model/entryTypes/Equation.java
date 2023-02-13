package model.entryTypes;

import model.Entry;
import model.exceptions.IndexNotThere;

import java.util.ArrayList;

// Represents math concepts that can be represented as an Equation, contains its name, its mathematical expression,
// the class it is for, the explaination for it, the proof, comments, and practice questions.
public class Equation extends Entry {
    ArrayList<String> practiceProblems;
    ArrayList<String> practiceProblemsAnswer;

    public Equation(String name, String theorem, String course, String proof, String explainations) {
        super(name, theorem, course, proof, explainations);
        practiceProblems = new ArrayList<>();
        practiceProblemsAnswer = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: change the practice problem on that entry, if there is no entry to change then leave the method leaves
    // the practice problem or the answer the same.

    public void changePracticeProblem(String practiceProblem, String answer, int i) {
        if (practiceProblem != "") {
            practiceProblems.set(i, practiceProblem);
        }
        if (answer != "") {
            practiceProblemsAnswer.set(i, answer);
        }
    }

    //MODIFIES: this
    //EFFECTS: add practice problem and its solution to both problems and the answers list

    public void addPracticeProblem(String problem, String answer) {
        practiceProblems.add(problem);
        practiceProblemsAnswer.add(answer);
    }

    //MODIFIES: this
    //EFFECTS: delete practice problem and its associated solution

    public void removePracticeProblem(int i)  {
        practiceProblems.remove(i);
        practiceProblemsAnswer.remove(i);
    }

    //MODIFIES: this
    //EFFECTS: gets the following practice problem in that index in the form of Question: _  Answer: _:

    public String getPracticeProblem(int i)  {
        String output;
        output = "Question: " + practiceProblems.get(i);
        output = output + "\n";
        output = output + "Answer: ";
        output = output + practiceProblemsAnswer.get(i);
        return output;
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

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: Shows the entire Equation in the system
    public void viewEquation() {
        System.out.println("Name:" + this.getName());
        System.out.println("Theorem:" + this.getTheorem());
        System.out.println("Course this is most relevant to:" + this.getCourse());
        System.out.println("Description: " + this.getExplainations());
        showNumberOfPracticeProblems();
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: Shows the number of practice problems in the entry:

    public void showNumberOfPracticeProblems() {
        int counter = 1;
        for (String p : practiceProblems) {
            System.out.print("Practice Problem" + counter);
            counter++;
        }
    }
}
