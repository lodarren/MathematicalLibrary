package model.EntryTypes;

import model.Entry;
import model.Exceptions.IndexNotThere;

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

    public void changePracticeProblem(String practiceProblem, String answer, int i)  throws IndexNotThere {
        if (i > practiceProblems.size()) {
            throw new IndexNotThere();
        }
        if (practiceProblem != null) {
            practiceProblems.add(i, practiceProblem);
        }
        if (answer != null) {
            practiceProblemsAnswer.add(i, answer);
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

    public void removePracticeProblem(int i) throws IndexNotThere {
        if (i > practiceProblems.size()) {
            throw new IndexNotThere();
        }
        practiceProblems.remove(i);
        practiceProblemsAnswer.remove(i);
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

}
