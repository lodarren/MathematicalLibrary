package ui;

import model.*;
import model.exceptions.IndexNotThere;
import model.exceptions.NameAlreadyExists;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Represents the full list of finished mathematical entries in the library. There are three sections of the library
// That the user can explore, one containing equations, one containing theorems and the last one containing requests.

public class TemporaryGUIMain extends JFrame {
    ListOfTheorems listOfTheorems;
    ListOfEquations listOfEquations;
    ListOfRequests listOfRequests;
    private JsonWriter jsonWriterTheorem;
    private JsonReader jsonReaderTheorem;
    private JsonWriter jsonWriterEquation;
    private JsonReader jsonReaderEquation;
    private JsonWriter jsonWriterRequest;
    private JsonReader jsonReaderRequest;
    private static final String JSON_STORE_THEOREM = "./data/ListOfTheoremSave.json";
    private static final String JSON_STORE_EQUATION = "./data/ListOfEquationSave.json";
    private static final String JSON_STORE_REQUEST = "./data/ListOfRequestSave.json";
    private Scanner input;

    //Related to GUI (SOME MAY STiLL NEED TO BE DELETED)
    JFrame mainFrame;

    //Delete this
    JPanel loadPanel;
    JPanel loadPanelButtons;
    JLabel loadPanelInstructions;
    JButton loadButton;
    JButton declineLoadButton;

    //Welcome Screen
    JPanel welcomeScreen;
    JPanel buttons;
    Border border;
    JButton openListOfTheorem;
    JButton openListOfEquation;
    JButton openListOfRequest;
    JButton leaveButton;
    JLabel welcomeText;
    JLabel instructionsText;

    //ListOfTheoremScreen
    JPanel theoremLibrary;
    JComboBox theoremEntries;
    JButton showProofButton;
    JLabel theoremText;
    JButton editTheorem;
    JPanel theoremButtons;
    JButton returnToMainMenuButton;
    //Then from here editing the theorem will be a bunch of prompts

    //EFFECTS: Creates a new library object, throws the FileNotFoundException if it occurs
    public TemporaryGUIMain() throws FileNotFoundException {
        runLibrary();
    }

    //EFFECTS: Runs the library and boots up the main menu for the user
    private void runLibrary() {
        boolean running = true;
        String command;
        setup();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        doYouWantToLoadLibrary();
        while (running) {
            mainMenu();
            command = input.next();

            if (command.equals("0")) {
                doYouWantToSaveLibrary();
                running = false;
            } else {
                mainDoCommand(command);
            }
        }
        System.out.println("\nSee You Next Time!\n");
    }

    //EFFECTS: used to instantiate listOfEquation, listOfTheorem and listOfRequests
    public void setup() {
        mainFrame = new JFrame();
        makeScreen();
        listOfEquations = new ListOfEquations();
        listOfTheorems = new ListOfTheorems();
        listOfRequests = new ListOfRequests();
        jsonWriterTheorem = new JsonWriter(JSON_STORE_THEOREM);
        jsonReaderTheorem = new JsonReader(JSON_STORE_THEOREM);
        jsonWriterEquation = new JsonWriter(JSON_STORE_EQUATION);
        jsonReaderEquation = new JsonReader(JSON_STORE_EQUATION);
        jsonWriterRequest = new JsonWriter(JSON_STORE_REQUEST);
        jsonReaderRequest = new JsonReader(JSON_STORE_REQUEST);

        instantiateGUI();
    }

    void instantiateGUI() {
        welcomeScreen = new JPanel();
        buttons = new JPanel();
        border = BorderFactory.createLineBorder(Color.black,3);
        openListOfTheorem = new JButton();
        openListOfEquation = new JButton();
        openListOfRequest = new JButton();
        welcomeText = new JLabel();
        instructionsText = new JLabel();
        loadPanel = new JPanel();
        loadPanelButtons = new JPanel();
        loadButton = new JButton();
        declineLoadButton = new JButton();
        loadPanelInstructions = new JLabel();
        leaveButton = new JButton();
        returnToMainMenuButton = new JButton();
    }

    //EFFECTS: Displays the options Theorem, Equation and Request and the option to leave for the user:
    public void mainMenu() {
        System.out.print("Welcome to the Library! \n");
        System.out.print("Press 1 to enter the Theorem section of the library! \n");
        System.out.print("Press 2 to enter the Equation section of the library! \n");
        System.out.print("Press 3 to enter the Requests section of the library! \n");
        System.out.print("Press 0 to leave! \n");
    }

    //EFFECTS: loads the JSON file if the user selects a key other than n
    private void doYouWantToLoadLibrary() {
        //Code quote
        String[] buttonTexts = {"Load save", "don't load save"};
        int userDecision = JOptionPane.showOptionDialog(null, "Do you want to load the save?",
                "Do you want to load the save file?", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, buttonTexts,
                buttonTexts[0]);
        if (userDecision == 0) {
            loadButtonAction();
        } else if (userDecision == 1) {
            instantiateMainMenu();
        } else {
            instantiateMainMenu();
        }
    }

    //MIGHT NOT NEED THIS!
    private void instantiateLoadScreen() {
        loadPanel.setBackground(Color.gray);
        loadPanel.setBounds(0, 0, 1024,768);
        loadPanel.setLayout(new GridLayout(2,1,0,0));

        loadPanelInstructions.setFont(new Font("Computer Modern", Font.PLAIN, 60));
        loadPanelInstructions.setBorder(border);
        loadPanelInstructions.setVerticalTextPosition(JLabel.TOP);
        loadPanelInstructions.setHorizontalAlignment(JLabel.CENTER);
        loadPanelInstructions.setBackground(Color.white);
        loadPanelInstructions.setOpaque(true);
        loadPanelInstructions.setText("Do you want to load the library save? Click any key for yes, and n for no.\n");

        loadButton.setText("Load the save!");
        declineLoadButton.setText("No thanks.");

        loadPanelButtons.setLayout(new GridLayout(1, 2));
        loadPanelButtons.add(loadButton);
        loadPanelButtons.add(declineLoadButton);

        loadPanel.add(loadPanelInstructions);
        loadPanel.add(loadPanelButtons);
    }

    //EFFECTS: saves the JSON file if the user selects a key other than n
    private void doYouWantToSaveLibrary() {
        //Code quote
        String[] buttonTexts = {"Save Entries"};
        int userDecision = JOptionPane.showOptionDialog(null,
                "Before you leave, do you want to save the library?",
                "Do you want to save?", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, buttonTexts,
                buttonTexts[0]);
        if (userDecision == 0) {
            try {
                jsonWriterTheorem.open();
                jsonWriterEquation.open();
                jsonWriterRequest.open();
                jsonWriterTheorem.writeListOfTheorem(listOfTheorems);
                jsonWriterEquation.writeListOfEquation(listOfEquations);
                jsonWriterRequest.writeListOfRequests(listOfRequests);
                jsonWriterTheorem.close();
                jsonWriterEquation.close();
                jsonWriterRequest.close();
                JOptionPane.showMessageDialog(mainFrame, "Library has been saved!");
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(mainFrame, "Unable to write to file");
            }
        } else {
            //Close
        }
    }

    //EFFECTS: If 1 is pressed, the theorem section is opened. If 2, the Equation section. If 3, the request
    //         of the library, and finally 0 is used to leave the library. Other inputs will result in nothing.
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

    //EFFECTS: Displays text welcoming readers into the theorem section and prompts them to decide what to do next.
    public void mainListOfTheorem() {
        welcomeToTheoremSection();

        String command = input.next();
        command = command.toLowerCase();
        theoremMakeSelection(command);
    }

    //EFFECTS: Displays lists of theorems so far and prompts user to type in the one they want to look at

    private void welcomeToTheoremSection() {
        System.out.print("Here are our Theorem Entries So far! \n");
        System.out.print(listOfTheorems.printAllTheorems() + "\n");
        System.out.print("Type in the name of the entry you want to view! \n");
    }

    //EFFECTS: First checks if the theorem exists. If it does exist, it asks the reader if they want to see additional
    //         info. If yes, it then asks the user if they want to change the entry, and finally if yes is selected
    //         it prompts the reader if it wants to change the entry.
    public void theoremMakeSelection(String command) {
        if (listOfTheorems.doesTheoremExist(command) != -1) {
            System.out.print(listOfTheorems.getTheorem(listOfTheorems.doesTheoremExist(command)).viewTheoremLessInfo());
            System.out.print("\nPress y if you want to display extra info. Press another key to return to the main "
                    + "menu.\n");
            if (yesOrNo()) {
                System.out.print(listOfTheorems.getTheorem(listOfTheorems.doesTheoremExist(command)).viewTheorem());
                System.out.print("\nPress y if you want to change this entry. Press another key to return to the "
                            + "main menu.\n");
                if (yesOrNo()) {
                    System.out.print("Press 1 to change the name of the entry.\nPress 2 to change the theorem.\nPress 3"
                            + " to change the course it is most useful for.\nPress 4 to change the description."
                            + "\nPress 5 to change the Proof.\nPress 6 to delete this entry.\n");
                    doYouWantToChangeTheTheorem(listOfTheorems.getTheorem(listOfTheorems.doesTheoremExist(command)));
                }
            }
        } else {
            System.out.println("That entry doesn't exist!\n");
        }
    }

    //EFFECTS: returns yes if the user types a lowercase or uppercase y.
    public boolean yesOrNo() {
        String command = input.next();
        return command.equalsIgnoreCase("y");
    }


    //EFFECTS: Prompts the user to select the field that they want to change. Afterwards, it allows the user to
    //         change it. If the name shows up in listOfTheorem, it denies the suggestion.
    public void doYouWantToChangeTheTheorem(Theorem theorem) {
        String command = input.next();
        if (command.equals("1")) {
            System.out.print("What were you planning to change the field to? Please type it in below.\n");
            String name = input.next();
            try {
                listOfTheorems.changeTheoremNameAndCheckExistence(theorem, name);
            } catch (NameAlreadyExists e) {
                System.out.print("Name already exists in theorem entries!\n");
            }
        } else if (command.equals("2")) {
            theorem.changeTheorem(whatIsTheChange());
        } else if (command.equals("3")) {
            theorem.changeCourse(whatIsTheChange());
        } else if (command.equals("4")) {
            theorem.changeExplanation(whatIsTheChange());
        } else if (command.equals("5")) {
            theorem.changeProof(whatIsTheChange());
        } else if (command.equals("6")) {
            deleteTheoremPrompt(theorem);
        } else {
            System.out.print("Not one of the selected options! \n");
        }
    }

    //EFFECTS: prompts the user if they want to delete the entry.
    private void deleteTheoremPrompt(Theorem theorem) {
        System.out.print("Are you sure you want to delete this entry? Press y to delete this entry.\n");
        if (yesOrNo()) {
            listOfTheorems.removeTheorem(theorem);
            System.out.print("Entry deleted!\n");
        } else {
            System.out.print("Entry will remain in the library.\n");
        }
    }

    //EFFECTS: Takes in a user input and returns it. CHANGE
    private String whatIsTheChange() {
        String change = JOptionPane.showInputDialog("What is the proposed change to the field?");
        return change;
    }

    //EFFECTS: Introduces the user to the Equation section of the library and prompts them to select a choice.
    public void mainListOfEquation() {
        welcomeToEquationSection();

        String command = input.next();
        command = command.toLowerCase();
        equationMakeSelection(command);
    }


    //EFFECTS: Prints all the equations in a 1. name: style.
    private void welcomeToEquationSection() {
        System.out.print("Here are our Equation Entries So far! \n");
        System.out.print(listOfEquations.printAllEquations() + "\n");
        System.out.print("Type in the name of the entry you want to view! \n");
    }

    //EFFECTS: prompts the user if they would like to view practice problems. If yes, the user can type in a number
    //         to view. If the entry does not exist ten it returns "That entry doesn't exist".
    private void equationMakeSelection(String command) {
        if (listOfEquations.doesEquationExist(command) != -1) {
            System.out.print(listOfEquations.getEquation(listOfEquations.doesEquationExist(command)).viewEquation());
            System.out.print("\nPress p if you want to view the practice problems. Press c to change the entry.\n");
            viewPracticeOrChangeEntry(command);
        } else {
            System.out.println("That entry doesn't exist!\n");
        }
    }

    //EFFECTS: Prompts the user if they would like to view practice problems. It then prompts them to either change
    //         the entry or to change the practice problems. Returns a NameAlreadyExists exception if the name of the
    //         suggested name change already exists.
    private void viewPracticeOrChangeEntry(String previousCommand) {
        String nextCommand = input.next();
        if (nextCommand.equalsIgnoreCase("p")) {
            showPracticeProblems(listOfEquations.getEquation(listOfEquations.doesEquationExist(previousCommand)));
        } else if (nextCommand.equalsIgnoreCase("c")) {
            System.out.print("Press 1 to change the name of the entry.\nPress 2 to change the theorem.\nPress 3"
                    + " to change the course it is most useful for.\nPress 4 to change the description.\nPress 5 to"
                    + " change the Proof.\nPress 6 to add practice problems.\nPress 7 to delete specific practice "
                    + "problems.\nPress 8 to delete this entry.\n");
            changeEquationEntry(listOfEquations.getEquation(listOfEquations.doesEquationExist(previousCommand)));
        } else {
            System.out.print("\nNot a valid entry!\n");
        }
    }

    //EFFECTS: Prompts the user to either change the fields of the equation or remove it from liftOfEquation.
    //         Does nothing if the user does not choose a number from 1-7.
    public void changeEquationEntry(Equation equation) {
        String command = input.next();
        if (command.equals("1")) {
            changeEquationNamePrompt(equation);
        } else if (command.equals("2")) {
            equation.changeTheorem(whatIsTheChange());
        } else if (command.equals("3")) {
            equation.changeCourse(whatIsTheChange());
        } else if (command.equals("4")) {
            equation.changeExplanation(whatIsTheChange());
        } else if (command.equals("5")) {
            equation.changeProof(whatIsTheChange());
        } else if (command.equals("6")) {
            addQuestionPrompt(equation);
        } else if (command.equals("7")) {
            deleteQuestionsPrompt(equation);
        } else if (command.equals("8")) {
            deleteEntryPrompt(equation);
        } else {
            System.out.print("Not one of the selected options! \n");
        }
    }

    //EFFECTS: Prompts the user to change the name of the equation. If name already exists it prints a message informing
    //         the user it already exists.
    public void changeEquationNamePrompt(Equation equation) {
        System.out.print("What were you planning to change the field to? Please type it in below.\n");
        String newName = input.next();
        try {
            listOfEquations.changeEquationNameAndCheckExistence(equation, newName);
        } catch (NameAlreadyExists e) {
            System.out.print("Name already exists in equation entries!\n");
        }
    }

    //EFFECTS: prompts the user if they would like to delete the equation.
    public void deleteEntryPrompt(Equation equation) {
        System.out.print("You are about to delete this entry. Press y to delete the entry.\n");
        if (yesOrNo()) {
            listOfEquations.removeEquation(equation);
        } else {
            System.out.print("Entry will remain in the library.\n");
        }
    }

    //EFFECTS: Prompts the user to select a practice problem they would like to view. Returns
    //         "There are no questions to show" if the list of practice problems is empty.
    public void showPracticeProblems(Equation equation) {
        try {
            String text = equation.showNumberOfPracticeProblems();
            System.out.print("Which practice problems do you want to view? Type the number of the practice problem that"
                    + " you would like to view.\n");
            System.out.print(text);
            String command = input.next();
            int newInput;
            newInput = Integer.parseInt(command) - 1;
            System.out.print(equation.getThePracticeProblem(newInput) + "\n");
            System.out.print("After you're done finishing the problem, press any key to view the answer!\n");

            String viewAnswerInput = input.next();
            if (viewAnswerInput != null) {
                System.out.print(equation.getThePracticeProblemAnswer(newInput) + "\n" + "\n");
                doYouWantToChangeTheProblem(equation, newInput);
            }
        } catch (IndexNotThere e) {
            System.out.print("There are no questions to show!\n");
        }
    }

    //EFFECTS: prompts the user if they would like to add a practice problem to the following equation:
    private void addQuestionPrompt(Equation equation) {
        System.out.print("What is your example practice problem?\n");
        String question = input.next();
        System.out.print("What is the answer to your example practice problem?\n");
        String answer = input.next();
        equation.addPracticeProblem(question, answer);
        System.out.print("Added question and answer, thank you!\n");
    }


    // EFFECTS: prompts the user if they would like to delete the following practice problems.
    private void deleteQuestionsPrompt(Equation equation) {
        try {
            System.out.print("What problems would you like to delete? Type the number below.\n");
            System.out.print(equation.showNumberOfPracticeProblems() + "\n");
            String command = input.next();
            int newInput;
            newInput = Integer.parseInt(command) - 1;
            equation.removePracticeProblem(newInput);
            System.out.print("Removed!\n");
        } catch (IndexNotThere e) {
            System.out.print("There are no questions to delete!\n");
        }
    }

    //EFFECTS: prompts the user if they would like to change the practice problem.
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


    //EFFECTS: Welcomes the user in the requests section of the library. Prompts them to make a selection to either
    //         view requests or to make a request.
    public void mainListOfRequests() {
        System.out.print("Welcome to the requests section!\nPress A to make a request.\nPress B to view made requests."
                + "\n");
        String command = input.next();

        if (command.equalsIgnoreCase("A")) {
            promptToMakeRequest();
        } else if (command.equalsIgnoreCase("B")) {
            viewAllRequests();
        } else {
            System.out.print("Not one of the options!\n");
        }
    }

    //EFFECTS: Shows the requests so far in text form. If "u" is selected, the user is then prompted to change requests.
    private void viewAllRequests() {
        System.out.print("Here are all the requests so far:\n");
        System.out.print(listOfRequests.printAllRequests() + "\n");
        System.out.print("If you would like to update any of the requests, press u, otherwise press another key to "
                + "return.\n");
        String command = input.next();
        if (command.equals("u")) {
            promptToUpdateRequest();
        }
    }

    //EFFECTS: prompts the user to select the request they would like to change. If that request does not exist,
    //         prints "That entry doesn't exist!"
    private void promptToUpdateRequest() {
        System.out.print("Please put in the name of the request you would like to change.\n");
        String command = input.next();
        if (listOfRequests.doesRequestExist(command) != -1) {
            System.out.print(listOfRequests.getRequest(listOfRequests.doesRequestExist(command)).viewRequest());
            updateDeleteOrSubmitRequest(listOfRequests.getRequest(listOfRequests.doesRequestExist(command)));
        } else {
            System.out.println("That entry doesn't exist!\n");
        }
    }

    //EFFECTS: Prompts the user if they would like to update the request or delete the request with "u" and "d"
    //         respectively. If the name is changed and the name already exists, the request is rejected.
    //         Does nothing if none of the options are selected.
    public void updateDeleteOrSubmitRequest(Request request) {
        System.out.print("Press u to update this request, press d to delete this request.\n");
        String newCommand = input.next();
        if (newCommand.equalsIgnoreCase("u")) {
            System.out.print("Press 1 to change the name of the entry.\nPress 2 to change the theorem.\nPress 3"
                        + " to change the course it is most useful for.\nPress 4 to change the description.\nPress 5 to"
                        + " change the Proof.\nPress 6 to update the estimated completion\nPress 7 to submit the"
                        + " entry to its designated library\n");
            updateRequest(request);
        } else if (newCommand.equalsIgnoreCase("d")) {
            deleteRequestPrompt(request);
        } else {
            System.out.println("Not one of the following options!\n");
        }
    }

    //EFFECTS: prompts the user if they would like to update the request. If the user decides to change the completion
    //         value to a value over 100, then it returns "Not a value between 0 and 100!". If a value between 1-7
    //         is not selected, the method does nothing.
    public void updateRequest(Request request) {
        String command = input.next();
        if (command.equals("1")) {
            changeRequestName(request);
        } else if (command.equals("2")) {
            request.changeTheorem(whatIsTheChange());
        } else if (command.equals("3")) {
            request.changeCourse(whatIsTheChange());
        } else if (command.equals("4")) {
            request.changeExplanation(whatIsTheChange());
        } else if (command.equals("5")) {
            request.changeProof(whatIsTheChange());
        } else if (command.equals("6")) {
            updateEstimatedCompletionPrompt(request);
        } else if (command.equals("7")) {
            prepareToSubmitRequest(request);
        } else {
            System.out.print("Not one of the options!\n");
        }
    }

    //EFFECTS: prompts the user if they would like to change the name of the request. If name is changed to a request
    //         that already exists, the method prints a message informing the user of that error.
    public void changeRequestName(Request request) {
        System.out.print("What were you planning to change the field to? Please type it in below.\n");
        String name = input.next();
        try {
            listOfRequests.changeRequestNameAndCheckExistence(request, name);
        } catch (NameAlreadyExists e) {
            System.out.print("Name already exists in request entries!\n");
        }
        request.changeName(name);
    }

    //MODIFIES: this, request
    //EFFECTS: Updates estimatedCompletion with the integer inputted.
    public void updateEstimatedCompletionPrompt(Request request) {
        System.out.print("Please input a value between 0 and 100 to signify its completion status.\n");
        String newEstimatedCompletion = input.next();
        request.updateEstimatedCompletion(newEstimatedCompletion);
    }


    //EFFECTS: prompts the user if they would like to submit to the main library.
    private void prepareToSubmitRequest(Request request) {
        if (checkToSubmitRequest(request)) {
            convertingRequest(request);
            System.out.print("Submitted to main library!\n");
        } else {
            System.out.print("Returning to main menu\n");
        }
    }

    //EFFECTS: checks if the request is a Theorem, if it is, then it adds it to theorem, otherwise adds it
    //         to equation.
    private void convertingRequest(Request request) {
        listOfRequests.removeRequest(request);
        if (request.getType().equals("Theorem")) {
            listOfTheorems.addTheorem(request.requestToTheorem());
        } else {
            listOfEquations.addEquation(request.requestToEquation());
        }
    }

    // EFFECTS: if the estimated completion is less than 100, then it prompts the user to click "y" again before
    //          moving the request to either listOfTheorem or listOfEquation.
    private Boolean checkToSubmitRequest(Request request) {
        if (request.getEstimatedCompletion() != 100) {
            System.out.print("This request is currently still not complete. Submit anyway (press y to accept)?\n");
            return yesOrNo();
        }
        return true;
    }

    //EFFECTS: prompts the user if they would like to delete the request. If "y" is pressed then the request is deleted.
    //         Otherwise nothing changes.
    public void deleteRequestPrompt(Request request) {
        System.out.print("You are about to delete this request. Press y to delete this request. Press any other key "
                + "to go back. \n");
        if (yesOrNo()) {
            listOfRequests.removeRequest(request);
            System.out.print("Request removed!\n");
        } else {
            System.out.print("System remains in the request catalog. \n");
        }
    }

    //EFFECTS: prompts the user if they would like to make a request. If the request already exists in the
    //         listOfRequest, then the suggestion is denied. Requests are also denied if it is neither "equation" or
    //         "theorem".
    public void promptToMakeRequest() {
        System.out.print("You are now making a request. What is this request for an equation or theorem?\n");
        String command = input.next();
        if (command.equalsIgnoreCase("theorem")) {
            userMakeTheoremRequest();
        } else if (command.equalsIgnoreCase("equation")) {
            userMakeEquationRequest();
        } else {
            System.out.print("Not one of the options!\n");
        }
    }

    //EFFECTS: prompts the user to make a new Theorem Request.
    public void userMakeTheoremRequest() {
        Request newRequest;
        System.out.print("What is the name of this theorem?\n");
        String name = input.next();
        System.out.print("What does this theorem state? (you can leave this blank if you are not sure)\n");
        String theorem = input.next();
        System.out.print("What course is this theorem most applicable for? (you can leave this blank if you are not "
                + "sure) \n");
        String course = input.next();
        System.out.print("You can leave any explanations if you so desire. \n");
        String explanation = input.next();
        System.out.print("What is the proof for this theorem? (you can leave this blank if you are not sure)\n");
        String proof = input.next();
        newRequest = new Request(name, theorem, "Theorem", course, explanation, proof);
        try {
            listOfRequests.addRequestAndCheckExistence(newRequest);
        } catch (NameAlreadyExists e) {
            System.out.print("Name Already Exists!\n");
        }
        System.out.print("Submitted request!\n");
    }

    //EFFECTS: prompts the user to make a new Equation Request.
    public void userMakeEquationRequest() {
        Request newRequest;
        System.out.print("What is the name of this Equation?\n");
        String name = input.next();
        System.out.print("What does this Equation state? (you can leave this blank if you are not sure)\n");
        String theorem = input.next();
        System.out.print("What course is this Equation most applicable for? (you can leave this blank if you are not"
                + " sure) \n");
        String course = input.next();
        System.out.print("You can leave any extra explanations if you so desire. \n");
        String explanation = input.next();
        System.out.print("What is the derivation for this Equation? (you can leave this blank if you are not sure)\n");
        String proof = input.next();
        newRequest = new Request(name, theorem, "Equation", course, explanation, proof);
        try {
            listOfRequests.addRequestAndCheckExistence(newRequest);
        } catch (NameAlreadyExists e) {
            System.out.print("Name already exists!\n");
        }
        System.out.print("Submitted request!\n");
    }

    //Starting here is the new piece of code I'm going to add

    void makeScreen() {
        mainFrame.setTitle("LibraryApplication");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.setSize(1024,768);
        mainFrame.setVisible(true);
    }

    void makeWelcomeText() {
        welcomeText.setText("Welcome To The Library!");
        welcomeText.setFont(new Font("Computer Modern", Font.PLAIN, 60));
        welcomeText.setBorder(border);
        welcomeText.setVerticalAlignment(JLabel.CENTER);
        welcomeText.setHorizontalAlignment(JLabel.CENTER);
        welcomeText.setBackground(Color.white);
        welcomeText.setOpaque(true);
    }

    void makeWelcomeButtons() {
        openListOfTheorem = new JButton();
        openListOfTheorem.setText("Open Theorem Library!");
        openListOfTheorem.addActionListener(e -> makeTheoremMenu());
        openListOfTheorem.setFocusable(true);

        openListOfEquation = new JButton();
        openListOfEquation.setText("Open Equation Library!");
        openListOfEquation.addActionListener(e -> makeEquationMenu());
        openListOfEquation.setFocusable(true);

        openListOfRequest = new JButton();
        openListOfRequest.setText("Open Request Library!");
        openListOfRequest.addActionListener(e -> System.out.print("Opening List Of Theorems"));
        openListOfRequest.setFocusable(true);

        leaveButton = new JButton();
        leaveButton.setText("Goodbye!");
        leaveButton.addActionListener(e -> doYouWantToSaveLibrary());
        leaveButton.setFocusable(true);
    }

    void buttonsToPanel() {
        buttons.setBackground(Color.gray);
        buttons.setLayout(new GridLayout(1,4,0,0));
        buttons.add(openListOfTheorem);
        buttons.add(openListOfEquation);
        buttons.add(openListOfRequest);
        buttons.add(leaveButton);
    }

    void instantiateInstructionText() {
        instructionsText.setText("Please click on the following library you would like to enter!");
        instructionsText.setFont(new Font("Computer Modern", Font.PLAIN, 20));
        instructionsText.setVerticalTextPosition(JLabel.CENTER);
        instructionsText.setHorizontalTextPosition(JLabel.CENTER);
        instructionsText.setBorder(border);
        instructionsText.setVerticalAlignment(JLabel.CENTER);
        instructionsText.setHorizontalAlignment(JLabel.CENTER);
        instructionsText.setBackground(Color.white);
        instructionsText.setOpaque(true);
    }

    void addingToWelcomeScreen() {
        welcomeScreen.setLayout(new GridLayout(3,1,0,0));
        welcomeScreen.setBackground(Color.gray);
        welcomeScreen.add(welcomeText);
        welcomeScreen.add(instructionsText);
        welcomeScreen.add(buttons);
        welcomeScreen.setVisible(true);
    }

    public void loadButtonAction() {
        try {
            listOfTheorems = jsonReaderTheorem.readTheorems();
            listOfEquations = jsonReaderEquation.readEquations();
            listOfRequests = jsonReaderRequest.readRequests();
            System.out.print("Loaded the library!\n");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(mainFrame, "No save file found!",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            startMainMenu();
        }
    }

    public void startMainMenu() {
        mainFrame.remove(loadPanel);
        instantiateMainMenu();
    }

    private void instantiateMainMenu() {
        makeWelcomeText();
        makeWelcomeButtons();
        instantiateInstructionText();
        buttonsToPanel();
        addingToWelcomeScreen();
        mainFrame.add(welcomeScreen);
        mainFrame.setVisible(true);
    }
    //    JPanel theoremLibrary;
    //    JComboBox<String> theoremEntries;
    //    JButton showProofButton;
    //    JLabel theoremText;
    //    JButton editTheorem;
    //    //Then from here editing the theorem will be a bunch of prompts

    private void makeTheoremMenu() {
        instantiateTheoremMenu();
        theoremLibrary.setLayout(new GridLayout(1,2,0,0));

        theoremLibrary.add(theoremText);
        addingTheoremButtons();
        theoremLibrary.add(theoremButtons);

        welcomeScreen.setVisible(false);
        mainFrame.add(theoremLibrary);
    }

    JLabel theoremLibraryTextInstructions;

    private void addingTheoremButtons() {
        theoremButtons.add(theoremEntries);
        theoremButtons.add(showProofButton);
        theoremButtons.add(editTheorem);
        theoremButtons.add(returnToMainMenuButton);
        theoremButtons.setBackground(new java.awt.Color(51,204,255));
        theoremButtons.setOpaque(true);
    }


    private void instantiateTheoremMenu() {
        theoremLibrary = new JPanel();
        theoremButtons = new JPanel();
        theoremEntries = new JComboBox(listOfTheorems.theoremsToString().toArray());
        instantiateTheoremEntries();

        showProofButton = new JButton();
        showProofButton.setText("Display Additional Info");
        showProofButton.addActionListener(e -> showAdditionalInfoMethod());
        showProofButton.setFocusable(true);

        theoremText = new JLabel();
        theoremText.setBounds(0, 0, 768,576);
        theoremText.setBackground(Color.white);
        theoremText.setOpaque(true);

        editTheorem = new JButton();
        editTheorem.setText("Edit Theorem");
        editTheorem.addActionListener(e ->
                changeTheoremPrompt(listOfTheorems.getTheorem(theoremEntries.getSelectedIndex())));
        editTheorem.setFocusable(true);

        returnToMainMenuButton = new JButton();
        returnToMainMenuButton.addActionListener(e -> theoremToMainMenu());
        returnToMainMenuButton.setText("Return to main menu");
        returnToMainMenuButton.setFocusable(true);
    }

    private void theoremToMainMenu() {
        theoremLibrary.setVisible(false);
        welcomeScreen.setVisible(true);
    }

    //"Press 1 to change the name of the entry.\nPress 2 to change the theorem.\nPress 3"
    //        + " to change the course it is most useful for.\nPress 4 to change the description."
    //       + "\nPress 5 to change the Proof.\nPress 6 to delete this entry.\n"

    private void changeTheoremPrompt(Theorem theorem) {
        String[] buttonTexts = {"Change Name", "Change theorem", "Change course", "Change description", "Change proof",
                "Delete entry"};
        int userDecision = JOptionPane.showOptionDialog(null, "Choose the theorem field you would"
                        + "like to change. ", "Change Theorem?", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,null, buttonTexts,buttonTexts[0]);
        if (userDecision == 0) {
            String answer = whatIsTheChange();
            try {
                listOfTheorems.changeTheoremNameAndCheckExistence(theorem, answer);
                JOptionPane.showMessageDialog(null, "Field Successfully Changed!", "Success!", JOptionPane.PLAIN_MESSAGE);
            } catch (NameAlreadyExists e) {
                JOptionPane.showMessageDialog(null, "Name already exists!", "Error", JOptionPane.WARNING_MESSAGE);
            }
        } else if (userDecision == 1) {
            theorem.changeTheorem(whatIsTheChange());
            JOptionPane.showMessageDialog(null, "Field Successfully Changed!", "Success!", JOptionPane.PLAIN_MESSAGE);
        } else if (userDecision == 2) {
            theorem.changeCourse(whatIsTheChange());
            JOptionPane.showMessageDialog(null, "Field Successfully Changed!", "Success!", JOptionPane.PLAIN_MESSAGE);
        } else if (userDecision == 3) {
            theorem.changeExplanation(whatIsTheChange());
            JOptionPane.showMessageDialog(null, "Field Successfully Changed!", "Success!", JOptionPane.PLAIN_MESSAGE);
        } else if (userDecision == 4) {
            theorem.changeProof(whatIsTheChange());
            JOptionPane.showMessageDialog(null, "Field Successfully Changed!", "Success!", JOptionPane.PLAIN_MESSAGE);
        } else if (userDecision == 5) {
            String[] deleteButton = {"Yes", "No"};
            int deleteOption = JOptionPane.showOptionDialog(null, "Are you sure you want to delete the theorem?", "Delete Theorem?", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,null, deleteButton,deleteButton[0]);
            if (deleteOption == 0) {
                theoremEntries.removeItemAt(theoremEntries.getSelectedIndex());
                listOfTheorems.removeTheorem(theorem);
                JOptionPane.showMessageDialog(null, "Theorem Deleted", "Success!", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }


    private void showAdditionalInfoMethod() {
        theoremText.setText(listOfTheorems.getTheorem(theoremEntries.getSelectedIndex()).viewTheorem());
    }

    private void instantiateTheoremEntries() {
        theoremEntries.addActionListener(e -> theoremEntriesAction());
        theoremEntries.setSize(40, 20);
    }

    private void theoremEntriesAction() {
        theoremText.setText(listOfTheorems.getTheorem(theoremEntries.getSelectedIndex()).viewTheoremLessInfo());
    }


    //Starting here is the list of equations section

    JComboBox<String> equationEntries;
    JPanel equationLibrary;
    JPanel equationButtons;
    JLabel equationText;
    JButton equationToMainMenu;
    JButton editEquation;
    JButton viewQuestionButton;


    private void makeEquationMenu() {
        instantiateEquationMenu();
        equationLibrary.setBackground(new java.awt.Color(102,255,102));
        equationLibrary.setLayout(new GridLayout(1,2,0,0));

        addingEquationButtons();
        equationLibrary.add(equationText);
        equationLibrary.add(equationButtons);

        welcomeScreen.setVisible(false);
        mainFrame.add(equationLibrary);
    }

    private void addingEquationButtons() {
        equationButtons.add(equationEntries);
        equationButtons.add(editEquation);
        equationButtons.add(viewQuestionButton);
        equationButtons.add(equationToMainMenu);
        equationButtons.setBackground(new java.awt.Color(102,255,102));
        equationButtons.setOpaque(true);
    }

    private void equationToMainMenu() {
        equationLibrary.setVisible(false);
        welcomeScreen.setVisible(true);
    }


    private void instantiateEquationMenu() {
        equationLibrary = new JPanel();
        equationButtons = new JPanel();
        equationEntries = new JComboBox(listOfEquations.equationsToString().toArray());
        instantiateEquationEntries();

        equationText = new JLabel();
        equationText.setBounds(0, 0, 768,576);
        equationText.setBackground(Color.white);
        equationText.setOpaque(true);

        editEquation = new JButton();
        editEquation.setText("Edit Equation");
        editEquation.addActionListener(e ->
                changeEquationPrompt(listOfEquations.getEquation(equationEntries.getSelectedIndex())));
        editEquation.setFocusable(true);

        equationToMainMenu = new JButton();
        equationToMainMenu.addActionListener(e -> equationToMainMenu());
        equationToMainMenu.setText("Return to main menu");
        equationToMainMenu.setFocusable(true);

        viewQuestionButton = new JButton();
        viewQuestionButton.addActionListener(e -> startQuestionPanel());
        viewQuestionButton.setText("View Practice Problems");
        viewQuestionButton.setFocusable(true);
    }


    /*
    "Press 1 to change the name of the entry.\nPress 2 to change the theorem.\nPress 3"
            + " to change the course it is most useful for.\nPress 4 to change the description.\nPress 5 to"
            + " change the Proof.\nPress 6 to add practice problems.\nPress 7 to delete specific practice "
            + "problems.\nPress 8 to delete this entry.\n"

     */


    private void changeEquationPrompt(Equation equation) {
        String[] buttonTexts = {"Change Name", "Change theorem", "Change course", "Change description",
                "Change Derivation", "Add practice problems", "Delete practice problems", "Delete entry"};
        int userDecision = JOptionPane.showOptionDialog(null, "Choose the equation field you would"
                        + "like to change. ", "Change Equation?", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,null, buttonTexts,buttonTexts[0]);
        if (userDecision == 0) {
            String answer = whatIsTheChange();
            try {
                listOfEquations.changeEquationNameAndCheckExistence(equation, answer);
                JOptionPane.showMessageDialog(null, "Field Successfully Changed!", "Success!", JOptionPane.PLAIN_MESSAGE);
            } catch (NameAlreadyExists e) {
                JOptionPane.showMessageDialog(null, "Name already exists!", "Error", JOptionPane.WARNING_MESSAGE);
            }
        } else if (userDecision == 1) {
            equation.changeTheorem(whatIsTheChange());
            JOptionPane.showMessageDialog(null, "Field Successfully Changed!", "Success!", JOptionPane.PLAIN_MESSAGE);
        } else if (userDecision == 2) {
            equation.changeCourse(whatIsTheChange());
            JOptionPane.showMessageDialog(null, "Field Successfully Changed!", "Success!", JOptionPane.PLAIN_MESSAGE);
        } else if (userDecision == 3) {
            equation.changeExplanation(whatIsTheChange());
            JOptionPane.showMessageDialog(null, "Field Successfully Changed!", "Success!", JOptionPane.PLAIN_MESSAGE);
        } else if (userDecision == 4) {
            equation.changeProof(whatIsTheChange());
            JOptionPane.showMessageDialog(null, "Field Successfully Changed!", "Success!", JOptionPane.PLAIN_MESSAGE);
        } else if (userDecision == 5) {
            String question = JOptionPane.showInputDialog("What is your example practice problem?");
            String answer = JOptionPane.showInputDialog("What is the answer to your practice problem?");
            equation.addPracticeProblem(question, answer);
            JOptionPane.showMessageDialog(null, "Thank You! Question Added!", "Success!", JOptionPane.PLAIN_MESSAGE);
        } else if (userDecision == 6) {
            try {
                String answer = JOptionPane.showInputDialog("What problems would you like to delete? Type the number "
                        + "below.\n" + equation.showNumberOfPracticeProblems() + "\n");
                int newInput;
                newInput = Integer.parseInt(answer) - 1;
                equation.removePracticeProblem(newInput);
                JOptionPane.showMessageDialog(null, "Removed the practice problem!", "Success!", JOptionPane.PLAIN_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "There are no problems to delete!", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        } else if (userDecision == 7) {
            String[] deleteButton = {"Yes", "No"};
            int deleteOption = JOptionPane.showOptionDialog(null, "Are you sure you want to delete the Equation?", "Delete Equation?", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,null, deleteButton,deleteButton[0]);
            if (deleteOption == 0) {
                equationEntries.removeItemAt(equationEntries.getSelectedIndex());
                listOfEquations.removeEquation(equation);
                JOptionPane.showMessageDialog(null, "Equation Deleted", "Success!", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }



    private void instantiateEquationEntries() {
        equationEntries.addActionListener(e -> equationEntriesAction());
        equationEntries.setSize(40, 20);
    }

    private void equationEntriesAction() {
        equationText.setText(listOfEquations.getEquation(equationEntries.getSelectedIndex()).viewEquation());
    }

    //Quesitons Menu:

    JPanel questionPanel;
    JPanel questionAnswerArea;
    JLabel questionText;
    JLabel answerText;
    JComboBox<String> listOfQuestions;
    JButton showAnswerButton;
    JButton returnToEquationsButton;
    JPanel questionButtons;

    private void startQuestionPanel() {
        instantiateQuestionPanel();
        questionPanel.setBackground(new java.awt.Color(255,255,104));
        questionPanel.setLayout(new GridLayout(1,2,0,0));

        addingQuestionButtons();
        addingQuestionAnswerTextArea();
        questionPanel.add(questionAnswerArea);
        questionPanel.add(questionButtons);


        equationLibrary.setVisible(false);
        mainFrame.add(questionPanel);
    }

    private void addingQuestionAnswerTextArea() {
        questionPanel.setLayout(new GridLayout(2,1,0,0));
        questionPanel.add(questionText);
        questionPanel.add(answerText);
    }

    private void addingQuestionButtons() {
        showAnswerButton.setText("Show Answer");
        showAnswerButton.addActionListener(e -> revealAnswerToViewer());

        returnToEquationsButton.setText("Return to equations library");
        returnToEquationsButton.addActionListener(e -> returnToEquationsAction());

        questionButtons.add(listOfQuestions);
        questionButtons.add(showAnswerButton);
        questionButtons.add(returnToEquationsButton);
    }

    private void returnToEquationsAction() {
        questionPanel.setVisible(false);
        equationLibrary.setVisible(true);
    }

    private void revealAnswerToViewer() {
        answerText.setText(listOfEquations.getEquation(equationEntries.getSelectedIndex()).getThePracticeProblemAnswer(listOfQuestions.getSelectedIndex()));
    }

    private void instantiateQuestionPanel() {
        questionPanel = new JPanel();
        questionText = new JLabel();
        answerText = new JLabel();
        listOfQuestions = new JComboBox(listOfEquations.getEquation(equationEntries.getSelectedIndex()).questionsToList().toArray());
        listOfQuestions.addActionListener(e -> selectQuestionAction());
        showAnswerButton = new JButton();
        returnToEquationsButton = new JButton();
        questionButtons = new JPanel();
        questionAnswerArea = new JPanel();
    }

    private void selectQuestionAction() {
        answerText.setText(null);
        questionText.setText(listOfEquations.getEquation(equationEntries.getSelectedIndex()).getThePracticeProblem(listOfQuestions.getSelectedIndex()));
    }

}
