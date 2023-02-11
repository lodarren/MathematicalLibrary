package model;

import model.exceptions.EmptyText;
import model.exceptions.IndexNotThere;

import java.util.ArrayList;

public abstract class Entry {
    String name;
    String theorem;
    String course;
    String proof;
    String explainations;
    ArrayList<String>  comments;

    //EFFECTS: Creates a new entry class
    public Entry(String name, String theorem, String course, String proof, String explainations){
        this.name = name;
        this.theorem = theorem;
        this.course = course;
        this.proof = proof;
        this.explainations = explainations;
        this.comments = new ArrayList<>();
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

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: Changes the comment found on that index in the list.

    public void changeComment(String comment, int i) {
        comments.add(i, comment);
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


    //MODIFIES: this
    //EFFECTS: returns the entire list of comments in ArrayList form

    public ArrayList<String> getCommentsRaw() {
        return comments;
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: retrieves a specific comment from comments

    public String getComment(int i) {

        return comments.get(i);
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: adds a comment to comment

    public void addComment(String comment) {
        comments.add(comment);
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: Removes a comment from the entry

    public void removeComment(int i) {

        comments.remove(i);
    }


    //MODIFIES: this
    //EFFECTS: Returns list of all comments in text form in the form 1. text \\ 2. text \\

    public String getAllComments() {
        String fullListOfComments = "";

        for (String c: comments) {
            fullListOfComments = fullListOfComments + c + "\n";
        }

        return fullListOfComments;
    }


}
