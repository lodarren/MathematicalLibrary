package model;

public abstract class Entry {
    String name;
    String theorem;
    String course;
    String proof;
    String explainations;

    //EFFECTS: Creates a new entry class
    public Entry(String name, String theorem, String course, String proof, String explainations){
        this.name = name;
        this.theorem = theorem;
        this.course = course;
        this.proof = proof;
        this.explainations = explainations;
    }

    //MODIFIES: this
    //EFFECTS: Changes name of the entry, returns exception if name is empty.

    public void changeName(String name) {
        this.name = name;
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: Changes name of the theorem, returns exception if theorem text is empty.

    public void changeTheorem(String theorem) {
        this.theorem = theorem;
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: changes the course that this entry is most relevant to

    public void changeCourse(String course) {
        this.course = course;
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: changes name of the proof, returns exception if proof text is empty.

    public void changeProof(String proof) {
        this.proof = proof;
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: changes explaination of the math concept, returns exception if changes are empty

    public void changeExplaination(String explaination) {
        this.explainations = explaination;
    }

    //MODIFIES: this
    //EFFECTS: returns name of the entry

    public String getName() {
        return name;
    }


    //MODIFIES: this
    //EFFECTS: returns the theorem text

    public String getTheorem() {
        return theorem;
    }


    //MODIFIES: this
    //EFFECTS: returns the course that this entry is most relevant to

    public String getCourse(){
        return course;
    }



    //MODIFIES: this
    //EFFECTS: returns the proof for the entry

    public String getProof() {
        return proof;
    }


    //MODIFIES: this
    //EFFECTS: returns the explaination for the entry

    public String getExplainations() {
        return explainations;
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: Shows the entire theorem in the system
    public void viewEntry() {
        System.out.println("Name: " + this.getName());
        System.out.println("Statement: " + this.getTheorem());
        System.out.println("Course this is most relevant to: " + this.getCourse());
        System.out.println("Description: " + this.getExplainations());
    }



}
