package model;

// Represents math concepts that users want to be covered, subject to updates before put into main library. Contains
// the name wanted to be covered, the equation requested, the class information, the pending explanation, the
// pending proof, the pending practice problems, and its estimated time for completion. The type of the equation can
// only be "Equation" or "Theorem". estimatedCompletion can only be an integer between 0 and 100.

import model.exceptions.NotValidCompletion;

public class Request extends Entry {
    int estimatedCompletion;
    String type;

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: Creates a new request

    public Request(String name, String theorem, String type, String course, String proof, String explanation) {
        super(name, theorem, course, proof, explanation);
        this.type = type;
    }

    public void changeType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }



    //MODIFIES: this
    //EFFECTS: returns estimated completion of the request

    public int getEstimatedCompletion() {
        return estimatedCompletion;
    }


    //MODIFIES: this
    //EFFECTS: updates completion

    public void updateEstimatedCompletion(String completion) throws NotValidCompletion {
        int newCompletion = Integer.parseInt(completion);
        if (newCompletion > 100) {
            throw new NotValidCompletion();
        }
        this.estimatedCompletion = newCompletion;
    }


    //MODIFIES: this
    //EFFECTS: checks if the request is completed

    public Boolean isRequestCompleted() {
        return estimatedCompletion == 100;
    }


    //MODIFIES: this
    //EFFECTS: checks if the following request is a Theorem

    public Boolean isItATheorem() {
        return type.equals("Theorem");
    }

    //MODIFIES: this
    //EFFECTS: checks the following request if it is an equation

    public Boolean isItAnEquation() {
        return type.equals("Equation");
    }

    //MODIFIES: this
    //EFFECTS: turns request into equation with all its relevant fields transferred

    public Equation requestToEquation() {
        Equation newEquation;
        newEquation = new Equation(this.getName(), this.getTheorem(), this.getCourse(), this.getProof(),
                this.getExplanations());
        return newEquation;
    }


    //MODIFIES: this
    //EFFECTS: turns request into Theorem with all its relevant fields transferred
    public Theorem requestToTheorem() {
        Theorem newTheorem;
        newTheorem = new Theorem(this.getName(), this.getTheorem(), this.getCourse(), this.getProof(),
                this.getExplanations());
        return newTheorem;
    }

    public String viewRequest() {
        String text;
        text = "Name: " + this.getName() + "\n";
        text = text + "Type" + this.getType() + "\n";
        text = text + "Theorem: " + this.getTheorem() + "\n";
        text = text + "Course this is most relevant to: " + this.getCourse() + "\n";
        text = text + "Description: " + this.getExplanations() + "\n";
        text = text + "Completion: " + this.getEstimatedCompletion() + "\n";
        return text;
    }

}
