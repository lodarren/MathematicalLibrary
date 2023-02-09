package model.EntryTypes;

// Represents math concepts that users want to be covered, subject to updates before put into main library. Contains
// the name wanted to be covered, the equation requested, the class information, the pending explaination, the
// pending proof, the pending practice problems, and its estimated time for completion.

import model.Entry;

import java.util.ArrayList;

public class Request extends Entry {
    int estimatedCompletion;
    String type;

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: Creates a new request

    public Request(String name, String theorem, String type, String course, String proof, String explainations) {
        super(name, theorem, type, course, proof, explainations);
    }




    //REQUIRES:
    //MODIFIES:
    //EFFECTS: returns estimated completion

    public int getEstimatedCompletion() {
        return estimatedCompletion;
    }

    //REQUIRES: has to be between 0 and 100
    //MODIFIES:
    //EFFECTS: updates completion

    public void updateEstimatedCompletion(int completion) {
        this.estimatedCompletion = completion;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: checks if the request is completed

    public Boolean isRequestCompleted() {
        return estimatedCompletion == 100;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: checks if it is a Theorem

    public Boolean isItATheorem() {
        return type == "Theorem";
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: checks if request is an equation

    public Boolean isItAnEquation() {
        return type == "Equation";
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: turns request into equation

    public Equation requestToEquation() {
        Equation newEquation;
        newEquation = new Equation(this.getName(), this.getTheorem(), this.getCourse(), this.getProof(), this.getExplainations());

        for (String p: this.getPracticeProblemsRaw()) {
            newEquation.addPracticeProblem(p);
        }
        for (String c: this.getCommentsRaw()) {
            newEquation.addPracticeProblem(c);
        }
        return newEquation;
    }


    //REQUIRES:
    //MODIFIES:
    //EFFECTS: turns request into theorem
    public Theorem requestToTheorem() {
        Theorem newTheorem;
        newTheorem = new Theorem(this.getName(), this.getTheorem(), this.getCourse(), this.getProof(), this.getExplainations());

        for (String p: this.getPracticeProblemsRaw()) {
            newTheorem.addPracticeProblem(p);
        }
        for (String c: this.getCommentsRaw()) {
            newTheorem.addPracticeProblem(c);
        }
        return newTheorem;
    }


}
