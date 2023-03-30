package ui;

import javax.swing.*;
import java.io.FileNotFoundException;

//Runs the program
public class Main extends JFrame {

    public static void main(String[] args) {
        try {
            new LibraryGraphicalInterface();
        } catch (FileNotFoundException e) {
            System.out.print("Unable to load application, JSON file cannot be found!");
        }
        /*
        This is the previous working program, one can uncomment this to return to the old UI
        (which is still functional)

        try {
            new Library();
        } catch (FileNotFoundException e) {
            System.out.print("Unable to load application, JSON file cannot be found!");
        }
        */
    }
}
