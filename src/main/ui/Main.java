package ui;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new Library();
        } catch (FileNotFoundException e) {
            System.out.print("Unable to load application, JSON file cannot be found!");
        }
    }
}
