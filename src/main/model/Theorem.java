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

    public void viewTheorem() {
        System.out.println("Name: " + this.getName());
        System.out.println("Theorem: " + this.getTheorem());
        System.out.println("Course this is most relevant to: " + this.getCourse());
        System.out.println("Description: " + this.getExplanations());
        System.out.println("Proof: " + this.getProof() + "\n");
    }


}
