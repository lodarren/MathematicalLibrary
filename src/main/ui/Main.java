package ui;

import javax.swing.*;
import java.io.FileNotFoundException;

//Runs the program
public class Main extends JFrame {

    public static void main(String[] args) {
        /*
        try {
            new Library();
        } catch (FileNotFoundException e) {
            System.out.print("Unable to load application, JSON file cannot be found!");
        }

    This is the previous working program, we are going to change this to LibraryGUI for now for testing
         */

        JFrame mainMenu = new LibraryGUI();


    }
}
