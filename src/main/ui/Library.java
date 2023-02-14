package ui;

import model.entryTypes.Equation;
import model.entryTypes.Request;
import model.entryTypes.Theorem;
import model.exceptions.IndexNotThere;

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
    Equation mockEquation1;
    Equation mockEquation2;

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
        mockEquation1 = new Equation("a", "B", "C", "D", "E");
        mockEquation2 = new Equation("a1", "B", "C", "D", "E");

        mockEquation1.addPracticeProblem("what is sine of x", "its x you dummy");
        listOfTheorems.add(0, mockEntry);
        listOfTheorems.add(1, mockEntry2);
        listOfEquations.add(0, mockEquation1);
        listOfEquations.add(1, mockEquation2);


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
                            "the Proof.\nPress 6 to delete this entry.");
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
        } else if (command.equals("6")) {
            deleteTheoremPrompt(theorem);
        } else {
            System.out.print("Not one of the selected options! \n");
        }
    }

    private void deleteTheoremPrompt(Theorem theorem) {
        System.out.print("Are you sure you want to delete this entry? Press y to delete this entry.");
        if (yesOrNo()) {
            listOfTheorems.remove(theorem);
            System.out.print("Entry deleted!");
        } else {
            System.out.print("Entry will remain in the library.");
        }
    }

    private String whatIsTheChange() {
        String command = input.next();
        return command;
    }

// Equation section



    //REQUIRES:
    //MODIFIES:
    //EFFECTS: Does the command in the Equation section of the library:
    public void mainListOfEquation() {
        welcomeToEquationSection();

        String command = input.next();
        command = command.toLowerCase();
        equationMakeSelection(command);
    }


    private void welcomeToEquationSection() {
        System.out.print("Here are our Equation Entries So far! \n");
        System.out.print(printAllEquations() + "\n");
        System.out.print("Type in the name of the entry you want to view! \n");
    }

    private void equationMakeSelection(String command) {
        if (doesEquationExist(command) != -1) {
            listOfEquations.get(doesEquationExist(command)).viewEntry();
            System.out.print("\nPress p if you want to view the practice problems. Press c to change the entry.\n");
            viewPracticeOrChangeEntry(command);
        } else {
            System.out.println("That entry doesn't exist!");
        }
    }

    private void viewPracticeOrChangeEntry(String previousCommand) {
        String nextCommand = input.next();
        if (nextCommand.equalsIgnoreCase("p")) {
            showPracticeProblems(listOfEquations.get(doesEquationExist(previousCommand)));
        } else if (nextCommand.equalsIgnoreCase("c")) {
            System.out.print("Press 1 to change the name of the entry.\nPress 2 to change the theorem.\nPress 3" +
                    " to change the course it is most useful for.\nPress 4 to change the description.\nPress 5 to change" +
                    "the Proof.\nPress 6 to delete specific practice problems.\nPress 7 to delete this entry.");
            changeEquationEntry(listOfEquations.get(doesEquationExist(previousCommand)));
        } else {
            System.out.print("\nNot a valid entry!\n");
        }
    }

    private void changeEquationEntry(Equation equation) {
        String command = input.next();
        if (command.equals("1")) {
            System.out.print("What were you planning to change the field to? Please type it in below.\n");
            equation.changeName(whatIsTheChange());
        } else if (command.equals("2")) {
            System.out.print("What were you planning to change the field to? Please type it in below.\n");
            equation.changeTheorem(whatIsTheChange());
        } else if (command.equals("3")) {
            System.out.print("What were you planning to change the field to? Please type it in below.\n");
            equation.changeCourse(whatIsTheChange());
        } else if (command.equals("4")) {
            System.out.print("What were you planning to change the field to? Please type it in below.\n");
            equation.changeExplaination(whatIsTheChange());
        } else if (command.equals("5")) {
            System.out.print("What were you planning to change the field to? Please type it in below.\n");
            equation.changeProof(whatIsTheChange());
        } else if (command.equals("6")) {
            deleteQuestionsPrompt(equation);
        } else if (command.equals("7")) {
            deleteEntryPrompt(equation);
        } else {
            System.out.print("Not one of the selected options! \n");
        }
    }

    public void deleteEntryPrompt(Equation equation) {
        System.out.print("You are about to delete this entry. Press y to delete the entry.\n");
        if (yesOrNo()) {
            listOfEquations.remove(equation);
        } else {
            System.out.print("Entry will remain in the library.\n");
        }
    }


    public void showPracticeProblems(Equation equation) throws IndexOutOfBoundsException {
        System.out.print("Which practice problems do you want to view?\n");
        try {
            System.out.print(equation.showNumberOfPracticeProblems());
            String command = input.next();
            int newInput;
            newInput = Integer.parseInt(command);
            newInput = Integer.parseInt(command) - 1;
            System.out.print(equation.getThePracticeProblem(newInput) + "\n");
            System.out.print("After you're done finishing the problem, press any key to view the answer!\n");

            String viewAnswerInput = input.next();
            if (viewAnswerInput != null) {
                System.out.print(equation.getThePracticeProblemAnswer(newInput) + "\n" + "\n");
                doYouWantToChangeTheProblem(equation, newInput);
            }
        } catch (IndexNotThere e) {
            System.out.print("There are no questions to show!");
        }
    }

    private void deleteQuestionsPrompt(Equation equation) {
        try {
            System.out.print("What problems would you like to delete? Type the number below.\n");
            System.out.print(equation.showNumberOfPracticeProblems() + "\n");
            String command = input.next();
            int newInput;
            newInput = Integer.parseInt(command);
            newInput = Integer.parseInt(command) - 1;
            equation.removePracticeProblem(newInput);
            System.out.print("Removed!\n");
        } catch (IndexNotThere e) {
            System.out.print("There are no questions to delete!");
        }
    }

    private void doYouWantToChangeTheProblem(Equation equation, int index) {
        System.out.print("Do you want to change the question? Press c to change the practice problem.\n");
        String command = input.next();
        if (command.equals("c")) {
            System.out.print("What should the question be instead?\n");
            String suggestedQuestion = input.next();
            System.out.print("What is the answer to this question?\n");
            String suggestedAnswer = input.next();
            equation.changePracticeProblem(suggestedQuestion, suggestedAnswer, index);
            System.out.print("Thank you for your feedback!\n");
        }
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
            for (Equation e : listOfEquations) {
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
        int counter = listOfEquations.size();
        String text = "";
        for (Equation t: listOfEquations) {
            text = t.getName() + text;
            text = "\n" + counter + "." + text;
            counter--;
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
