package ui;

import model.entryTypes.Equation;
import model.entryTypes.Request;
import model.entryTypes.Theorem;
import model.Entry;

import java.util.ArrayList;
import java.util.Scanner;

// Represents the full list of finished mathematical entries in the library, can either contain Equations or Theorems,
// and has a section for Requests
public class Library {
    ArrayList<Theorem> listOfTheorems;
    ArrayList<Equation> listOfEquations;
    ArrayList<Request> listOfRequests;

    Theorem mockEntry;
    Theorem mockEntry2;

    private Scanner input;

    public Library() {
        runLibrary();
    }

    private void runLibrary() {
        boolean running = true;
        String command = null;

        listOfEquations = new ArrayList<>();
        listOfTheorems = new ArrayList<>();
        listOfRequests = new ArrayList<>();

        // MOCK lists, to be removed later
        mockEntry = new Theorem("a", "B", "C", "D", "E");
        mockEntry2 = new Theorem("a1", "B", "C", "D", "E");
        listOfTheorems.add(0, mockEntry);
        listOfTheorems.add(1, mockEntry2);


        input = new Scanner(System.in);
        input.useDelimiter("\n");

        while (running) {
            mainMenu();
            command = input.next();

            if (command.equals("0")) {
                running = false;
            } else {
                mainDoCommand(command);
            }
        }

        System.out.println("\nSee You Next Time!");
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: Displays the options Theorem, Equation and Request:

    public void mainMenu() {
        System.out.print("Welcome to the Library! \n" );
        System.out.print("Press 1 to enter the Theorem section of the library! \n");
        System.out.print("Press 2 to enter the Equation section of the library! \n");
        System.out.print("Press 3 to enter the Requests section of the library! \n");
        System.out.print("Press 0 to leave! \n");
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: Does the command in the main section of the library:

    private void mainDoCommand(String command) {
        if (command.equals("1")) {
            mainListOfTheorem();
        } else if (command.equals("2")) {
            mainListOfEquation();
        } else if (command.equals("3")) {
            mainListOfRequests();
        } else {
            System.out.print("Not one of the selected options! \n");
        }
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: Does the command in the Theorem section of the library:
    public void mainListOfTheorem() {
        welcomeToTheoremSection();

        String command = input.next();
        command = command.toLowerCase();
        theoremMakeSelection(command);
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: Displays lists of theorems so far and prompts user to type in the one they want to look at

    private void welcomeToTheoremSection() {
        System.out.print("Here are our Theorem Entries So far! \n");
        System.out.print(printAllTheorems() + "\n");
        System.out.print("Type in the name of the entry you want to view! \n");

    }

    @SuppressWarnings("checkstyle:OperatorWrap")
    public void theoremMakeSelection(String command) {
        if (doesTheoremExist(command) != -1) {
            listOfTheorems.get(doesTheoremExist(command)).viewEntry();
            System.out.print("\nPress y if you want to display extra info. Press another key to return to the main menu.\n");
            if (yesOrNo()) {
                listOfTheorems.get(doesTheoremExist(command)).viewTheorem();
                System.out.print("\nPress y if you want to change this entry. Press another key to return to the main menu.\n");
                if (yesOrNo()) {
                    System.out.print("Press 1 to change the name of the entry.\nPress 2 to change the theorem.\nPress 3" +
                            " to change the course it is most useful for.\nPress 4 to change the description.\nPress 5 to change" +
                            "the Proof.\n");
                    doYouWantToChangeTheTheorem(listOfTheorems.get(doesTheoremExist(command)));
                }
            }
        } else {
            System.out.println("That entry doesn't exist!");
        }
    }

    public boolean yesOrNo() {
        String command = input.next();
        return command.equalsIgnoreCase("y");
    }

    public void doYouWantToChangeTheTheorem(Theorem theorem) {
        String command = input.next();
        if (command.equals("1")) {
            System.out.print("What were you planning to change the field to? Please type it in below.\n");
            theorem.changeName(whatIsTheChange());
        } else if (command.equals("2")) {
            System.out.print("What were you planning to change the field to? Please type it in below.\n");
            theorem.changeTheorem(whatIsTheChange());
        } else if (command.equals("3")) {
            System.out.print("What were you planning to change the field to? Please type it in below.\n");
            theorem.changeCourse(whatIsTheChange());
        } else if (command.equals("4")) {
            System.out.print("What were you planning to change the field to? Please type it in below.\n");
            theorem.changeExplaination(whatIsTheChange());
        } else if (command.equals("5")) {
            System.out.print("What were you planning to change the field to? Please type it in below.\n");
            theorem.changeProof(whatIsTheChange());
        } else {
            System.out.print("Not one of the selected options! \n");
        }
    }

    private String whatIsTheChange() {
        String command = input.next();
        return command;
    }






    //REQUIRES:
    //MODIFIES:
    //EFFECTS: Does the command in the Equation section of the library:
    public void mainListOfEquation() {
        //STUB
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: Does the command in the Request section of the library:
    public void mainListOfRequests() {
        //STUB
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: adds an entry to the listofEquations

    public void addEquation(Equation equation) {
        listOfEquations.add(equation);
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: adds an entry to the listofTheorems

    public void addTheorem(Theorem theorem) {
        listOfTheorems.add(theorem);
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: adds a request to the main library and checks which list it belongs to

    public void turnRequestIntoEntry(Request request) {
        if (request.isRequestCompleted()) {
            if (request.isItAnEquation()) {
                listOfEquations.add(request.requestToEquation());
            } else {
                listOfTheorems.add(request.requestToTheorem());
            }
        }
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: Searches if a certain entry exists in listofEquations and returns its index (and should open it up after)

    public int doesEquationExist(String nameOfEquation) {
        int counter = 0;
        for (Equation e: listOfEquations) {
            if (nameOfEquation.equalsIgnoreCase(e.getName())) {
                return counter;
            } else {
                counter++;
            }
        }

        return -1;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: Searches if a certain entry exists in listofTheorems

    public int doesTheoremExist(String nameOfTheorem) {
        int counter = 0;
        for (Theorem t: listOfTheorems) {
            if (nameOfTheorem.equalsIgnoreCase(t.getName())) {
                return counter;
            } else {
                counter++;
            }
        }
        return -1;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: lists the names of the Equations so far

    public String printAllEquations() {
        int counter = 1;
        String text = "";
        for (Equation e: listOfEquations) {
            text = counter + e.getName() + text;
        }
        return text;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: lists the names of the Theorems so far

    public String printAllTheorems() {
        int counter = listOfTheorems.size();
        String text = "";
        for (Theorem t: listOfTheorems) {
            text = t.getName() + text;
            text = "\n" + counter + "." + text;
            counter--;
        }
        return text;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: lists the names of the Requests so far

    public String printAllRequests() {
        int counter = 1;
        String text = "";
        for (Request r: listOfRequests) {
            text = Integer.toString(counter) + r.getName() + text;
        }
        return text;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: shows all current theorems and lists in alphabetical order

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: shows all current Equations and lists them in alphabetical order


    //REQUIRES:
    //MODIFIES:
    //EFFECTS: Should let you add as many requests at once



}
