package model;

import java.sql.Array;
import java.util.ArrayList;

public abstract class Entry {
    String name;
    String theorem;
    String course;
    String proof;
    String explainations;
    String type;
    ArrayList<String>  comments;
    ArrayList<String> practiceProblems;

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: Makes new constructor
    public Entry(String name, String theorem, String type, String course, String proof, String explainations){
        this.name = name;
        this.theorem = theorem;
        this.course = course;
        this.proof = proof;
        this.explainations = explainations;
        this.comments = new ArrayList<>();
        this.practiceProblems = new ArrayList<>();
        this.type = type;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: change name

    public void changeName(String name) {
        this.name = name;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: change theorem

    public void changeTheorem(String name) {
        this.theorem = theorem;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: change proof

    public void changeProof(String name) {
        this.proof = proof;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: change explaination

    public void changeExplaination(String name) {
        this.proof = proof;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: change comment

    public void changeComment(String comment, int i) {
        practiceProblems.add(i, comment);
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: change practice problem

    public void changePracticeProblem(String practiceProblem, int i) {
        practiceProblems.add(i, practiceProblem);
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: get name

    public String getName() {
        return name;
    }


    //REQUIRES:
    //MODIFIES:
    //EFFECTS: get theorem

    public String getTheorem() {
        return theorem;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: get course

    public String getCourse(){
        return course;
    }


    //REQUIRES:
    //MODIFIES:
    //EFFECTS: get proof

    public String getProof() {
        return proof;
    }


    //REQUIRES:
    //MODIFIES:
    //EFFECTS: get explaination

    public String getExplainations() {
        return explainations;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: get Practice Problems in a list raw

    public ArrayList<String> getPracticeProblemsRaw() {
        return practiceProblems;
    }


    //REQUIRES:
    //MODIFIES:
    //EFFECTS: get comment in a list raw

    public ArrayList<String> getCommentsRaw() {
        return comments;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: get comment in a list

    public String getComments() {
        String fullListOfComments = "";
        for (String c: comments) {
            fullListOfComments = fullListOfComments + c;
        }
        return fullListOfComments;
    }


    //REQUIRES:
    //MODIFIES:
    //EFFECTS: add practice problem

    public void addPracticeProblem(String problem) {
        practiceProblems.add(problem);
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: delete practice problem

    public void removePracticeProblem(String problem) {
        practiceProblems.remove(problem);
    }

}
