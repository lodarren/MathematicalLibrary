package model;

public abstract class Entry {
    // A class that represents the general representation of an entry in the Library. Contains its name, its theorem,
    // the course it is most relevant for, the proof of the course and the description for the entry.

    String name;            //Name of the entry
    String theorem;         //What the expression states
    String course;          //What course this is most relevant to
    String proof;           //The proof of the following entry
    String explanations;    //A description to help the user understand the entry.

    //EFFECTS: Creates a new entry class
    public Entry(String name, String theorem, String course, String proof, String explanations) {
        this.name = name;
        this.theorem = theorem;
        this.course = course;
        this.proof = proof;
        this.explanations = explanations;
    }

    //MODIFIES: this
    //EFFECTS: Changes name of the entry.

    public void changeName(String name) {
        this.name = name;
    }

    //MODIFIES: this
    //EFFECTS: Changes name of the theorem.

    public void changeTheorem(String theorem) {
        this.theorem = theorem;
    }

    //MODIFIES: this
    //EFFECTS: changes the course that this entry is most relevant to.

    public void changeCourse(String course) {
        this.course = course;
    }

    //MODIFIES: this
    //EFFECTS: changes name of the proof, returns exception if proof text is empty.

    public void changeProof(String proof) {
        this.proof = proof;
    }

    //MODIFIES: this
    //EFFECTS: changes explanation of the math concept, returns exception if changes are empty

    public void changeExplanation(String explanation) {
        this.explanations = explanation;
    }

    //MODIFIES: this
    //EFFECTS: returns name of the entry.
    public String getName() {
        return name;
    }

    //MODIFIES: this
    //EFFECTS: returns the theorem text.
    public String getTheorem() {
        return theorem;
    }

    //MODIFIES: this
    //EFFECTS: returns the course that this entry is most relevant to.
    public String getCourse() {
        return course;
    }

    //MODIFIES: this
    //EFFECTS: returns the proof for the entry.
    public String getProof() {
        return proof;
    }

    //MODIFIES: this
    //EFFECTS: returns the explanation for the entry
    public String getExplanations() {
        return explanations;
    }

}
