package model;

// Represents math concepts that users want to be covered, subject to updates before put into main library. Contains
// the name wanted to be covered, the equation requested, the class information, the pending explanation, the
// pending proof, the pending practice problems, and its estimated time for completion. The type of the equation can
// only be "Equation" or "Theorem". estimatedCompletion can only be an integer between 0 and 100.

import org.json.JSONObject;

public class Request extends Entry {
    int estimatedCompletion;        //An integer between 0 and 100 that signifies how much percent of the request
                                    //is completed.
    String type;                    //The type of request this request is for. Can either be "Theorem" or "Equation"


    //EFFECTS: Creates a new request object
    public Request(String name, String theorem, String type, String course, String proof, String explanation) {
        super(name, theorem, course, proof, explanation);
        this.type = type;
    }

    //MODIFIES: this
    //EFFECTS: returns the type of the request.
    public String getType() {
        return type;
    }


    //MODIFIES: this
    //EFFECTS: returns estimated completion of the request
    public int getEstimatedCompletion() {
        return estimatedCompletion;
    }


    //REQUIRES: completion must be bewteen "0" and "100" in string format.
    //MODIFIES: this
    //EFFECTS: updates completion
    public void updateEstimatedCompletion(String completion) {
        int newCompletion = Integer.parseInt(completion);
        this.estimatedCompletion = newCompletion;
    }

    //MODIFIES: this
    //EFFECTS: checks if the estimated completion is equal to 100
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
    //EFFECTS: turns request into equation with all its relevant fields (name, type, course, proof, explanation)
    //         transferred
    public Equation requestToEquation() {
        Equation newEquation;
        newEquation = new Equation(this.getName(), this.getTheorem(), this.getCourse(), this.getProof(),
                this.getExplanations());
        return newEquation;
    }

    //MODIFIES: this
    //EFFECTS: turns request into theorem with all its relevant fields (name, type, course, proof, explanation)
    //         transferred
    public Theorem requestToTheorem() {
        Theorem newTheorem;
        newTheorem = new Theorem(this.getName(), this.getTheorem(), this.getCourse(), this.getProof(),
                this.getExplanations());
        return newTheorem;
    }

    //MODIFIES: this
    //EFFECTS: returns the fields of the request in text format in the form Field: field.
    public String viewRequest() {
        String text;
        text = "Name: " + this.getName() + "\n";
        text = text + "Type: " + this.getType() + "\n";
        text = text + "Theorem: " + this.getTheorem() + "\n";
        text = text + "Course this is most relevant to: " + this.getCourse() + "\n";
        text = text + "Description: " + this.getExplanations() + "\n";
        text = text + "Completion: " + this.getEstimatedCompletion() + "\n";
        text = text + "Proof: " + this.getProof() + "\n";
        return text;
    }

    //MODIFIES: this
    //EFFECTS: turns the following request into a JSON file with all its specified fields.
    public JSONObject requestToJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("theorem", theorem);
        json.put("type", type);
        json.put("request estimated completion", estimatedCompletion);
        json.put("course", course);
        json.put("proof", proof);
        json.put("explanation", explanations);
        return json;
    }
}
