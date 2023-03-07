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
        text = "Name: " + this.getName();
        text = text +  "\nTheorem: " + this.getTheorem();
        text = text + "\nCourse this is most relevant to: " + this.getCourse();
        text = text + "\nDescription: " + this.getExplanations();
        text = text + "\nProof: " + this.getProof() + "\n";
        return text;
    }

    //MODIFIES: this
    //EFFECTS: returns the fields of the equation in text format in the form Field: field. without the proof
    public String viewTheoremLessInfo() {
        String text;
        text = "Name: " + this.getName();
        text = text +  "\nTheorem: " + this.getTheorem();
        text = text + "\nCourse this is most relevant to: " + this.getCourse();
        text = text + "\nDescription: " + this.getExplanations() + "\n";
        return text;
    }

    //MODIFIES: this
    //EFFECTS: turns the theorem into a JSONObject, with all its fields specified.
    public JSONObject theoremToJson() {
        JSONObject json = new JSONObject();
        json.put("theoremname", name);
        json.put("theoremtheorem", theorem);
        json.put("theoremcourse", course);
        json.put("theoremproof", proof);
        json.put("theoremexplanation", explanations);
        return json;
    }
}
