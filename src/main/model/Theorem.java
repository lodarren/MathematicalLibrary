package model;

// Represents math concepts that can be represented as a theorem, contains its name, its mathematical expression,
// the class it is for, the explanation for it, the proof, comments and practice questions.
public class Theorem extends Entry {

    public Theorem(String name, String theorem, String course, String proof, String explanation) {
        super(name, theorem, course, proof, explanation);
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: Shows the entire theorem in the system

    public String viewTheorem() {
        String text;
        text = "Name: " + this.getName();
        text = text +  "\nTheorem: " + this.getTheorem();
        text = text + "\nCourse this is most relevant to: " + this.getCourse();
        text = text + "\nDescription: " + this.getExplanations();
        text = text + "\nProof: " + this.getProof() + "\n";
        return text;
    }


}
