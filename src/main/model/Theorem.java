package model;

import org.json.JSONObject;

// Represents math concepts that can be represented as a theorem, contains its name, its mathematical expression,
// the class it is for, the explanation for it, the proof, comments and practice questions.
public class Theorem extends Entry {

    //EFFECTS: creates a new theorem object.
    public Theorem(String name, String theorem, String course, String proof, String explanation) {
        super(name, theorem, course, proof, explanation);
    }

    //MODIFIES: this
    //EFFECTS: returns the fields of the equation in text format in the form Field: field.
    public String viewTheorem() {
        String text;
        text = "<html><font size = '5'>Name: " + this.getName();
        text = text +  "<br><br>Theorem: " + this.getTheorem();
        text = text + "<br><br>Course this is most relevant to: " + this.getCourse();
        text = text + "<br><br>Description: " + this.getExplanations();
        text = text + "<br><br>Proof: " + this.getProof() + "<br></font></html>";
        return text;
    }

    //MODIFIES: this
    //EFFECTS: returns the fields of the equation in text format in the form Field: field. without the proof
    public String viewTheoremLessInfo() {
        String text;
        text = "<html><font size = '5'>Name: " + this.getName();
        text = text +  "<br><br>Theorem: " + this.getTheorem();
        text = text + "<br><br>Course this is most relevant to: " + this.getCourse();
        text = text + "<br><br>Description: " + this.getExplanations() + "<br></font></html>";
        return text;
    }

    //MODIFIES: this
    //EFFECTS: turns the theorem into a JSONObject, with all its fields specified.
    public JSONObject theoremToJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("theorem", theorem);
        json.put("course", course);
        json.put("proof", proof);
        json.put("explanation", explanations);
        return json;
    }
}
