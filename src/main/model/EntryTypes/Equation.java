package model.EntryTypes;

import model.Entry;

import java.util.ArrayList;

// Represents math concepts that can be represented as an Equation, contains its name, its mathematical expression,
// the class it is for, the explaination for it, the proof, comments, and practice questions.
public class Equation extends Entry {


    public Equation(String name, String theorem, String type, String course, String proof, String explainations) {
        super(name, theorem, type, course, proof, explainations);
    }
}
