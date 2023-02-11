package model.EntryTypes;

// Represents math concepts that users want to be covered, subject to updates before put into main library. Contains
// the name wanted to be covered, the equation requested, the class information, the pending explaination, the
// pending proof, the pending practice problems, and its estimated time for completion.

import model.Entry;
import model.Exceptions.NotValidCompletion;
import model.Exceptions.NotValidType;

import java.util.ArrayList;

public class Request extends Entry {
    int estimatedCompletion;
    String type;

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: Creates a new request

    public Request(String name, String theorem, String type, String course, String proof, String explainations) {
        super(name, theorem, course, proof, explainations);
        this.type = type;
    }

    public changeType(String type) throws NotValidType {
        if (type != "Equation" & type != "Theorem") {
            throw new NotValidType;
        }

        this.type = type;
    }



    //MODIFIES: this
    //EFFECTS: returns estimated completion of the request

    public int getEstimatedCompletion() {
        return estimatedCompletion;
    }


    //MODIFIES: this
    //EFFECTS: updates completion

    public void updateEstimatedCompletion(int completion) throws NotValidCompletion {
        if (completion > 100) {
            throw new NotValidCompletion;
        } else if (completion < 0) {
            throw new NotValidCompletion;
        }
        this.estimatedCompletion = completion;
    }


    //MODIFIES: this
    //EFFECTS: checks if the request is completed

    public Boolean isRequestCompleted() {
        return estimatedCompletion == 100;
    }


    //MODIFIES: this
    //EFFECTS: checks if the following request is a Theorem

    public Boolean isItATheorem() {
        return type == "Theorem";
    }

    //MODIFIES: this
    //EFFECTS: checks the following request if it is an equation

    public Boolean isItAnEquation() {
        return type == "Equation";
    }

    //MODIFIES: this
    //EFFECTS: turns request into equation with all its relevant fields transferred

    public Equation requestToEquation() {
        Equation newEquation;
        newEquation = new Equation(this.getName(), this.getTheorem(), this.getCourse(), this.getProof(),
                this.getExplainations());

        for (String c: this.getCommentsRaw()) {
            newEquation.addComment(c);
        }

        return newEquation;
    }


    //MODIFIES: this
    //EFFECTS: turns request into Theorem with all its relevant fields transferred
    public Theorem requestToTheorem() {
        Theorem newTheorem;
        newTheorem = new Theorem(this.getName(), this.getTheorem(), this.getCourse(), this.getProof(),
                this.getExplainations());

        for (String c: this.getCommentsRaw()) {
            newTheorem.addComment(c);
        }
        return newTheorem;
    }

}
