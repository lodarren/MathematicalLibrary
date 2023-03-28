package ui;

import model.*;
import model.exceptions.IndexNotThere;
import model.exceptions.NameAlreadyExists;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Represents the full list of finished mathematical entries in the library. There are three sections of the library
// That the user can explore, one containing equations, one containing theorems and the last one containing requests.

public class LibraryGraphicalInterface extends JFrame {
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

    JFrame mainFrame;

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
    JComboBox<String> theoremEntries;
    JButton showProofButton;
    JLabel theoremText;
    JButton editTheorem;
    JPanel theoremButtons;
    JButton returnToMainMenuButton;

    //ListOfEquationScreen
    JComboBox<String> equationEntries;
    JPanel equationLibrary;
    JPanel equationButtons;
    JLabel equationText;
    JButton equationToMainMenu;
    JButton editEquation;
    JButton viewQuestionButton;

    //ListOfRequest Screen
    JPanel requestPanel;
    JLabel requestText;
    JComboBox<String> requestEntries;
    JButton updateRequestButton;
    JButton submitRequestButton;
    JButton requestToMainMenuButton;
    JPanel requestButtons;

    //Questions Screen
    JPanel questionPanel;
    JPanel questionAnswerArea;
    JLabel questionText;
    JLabel answerText;
    JComboBox<String> listOfQuestions;
    JButton showAnswerButton;
    JButton returnToEquationsButton;
    JPanel questionButtons;



    //EFFECTS: Creates a new library object, throws the FileNotFoundException if it occurs
    public LibraryGraphicalInterface() throws FileNotFoundException {
        runLibrary();
    }

    //EFFECTS: Runs the library and boots up the main menu for the user
    private void runLibrary() {
        setup();
        doYouWantToLoadLibrary();
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
        leaveButton = new JButton();
        returnToMainMenuButton = new JButton();
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

    //EFFECTS: saves the JSON file if the user selects a key other than n
    private void doYouWantToSaveLibrary() {
        //Code quote
        String[] buttonTexts = {"Save Entries"};
        int userDecision = JOptionPane.showOptionDialog(null,"Before you leave, do you want to save the library?",
                "Do you want to save?",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttonTexts,
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
            mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
        }
    }


    //EFFECTS: Takes in a user input and returns it. CHANGE
    private String whatIsTheChange() {
        return JOptionPane.showInputDialog("What is the proposed change to the field?");
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

    //Starting here is the new piece of code I'm going to add

    void makeScreen() {
        mainFrame.setTitle("LibraryApplication");
        //mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        openListOfTheorem.addActionListener(e -> openListOfTheoremAction());
        openListOfTheorem.setFocusable(true);

        openListOfEquation = new JButton();
        openListOfEquation.setText("Open Equation Library!");
        openListOfEquation.addActionListener(e -> openListOfEquationAction());
        openListOfEquation.setFocusable(true);

        openListOfRequest = new JButton();
        openListOfRequest.setText("Open Request Library!");
        openListOfRequest.addActionListener(e -> enterRequests());
        openListOfRequest.setFocusable(true);

        leaveButton = new JButton();
        leaveButton.setText("Goodbye!");
        leaveButton.addActionListener(e -> doYouWantToSaveLibrary());
        leaveButton.setFocusable(true);
    }

    void openListOfTheoremAction() {
        try {
            makeTheoremMenu();
        } catch (IndexNotThere e) {
            populateLibraryErrorMessage();
        }
    }

    void openListOfEquationAction() {
        try {
            makeEquationMenu();
        } catch (IndexNotThere e) {
            populateLibraryErrorMessage();
        }
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

    private void makeTheoremMenu() throws IndexNotThere {
        if (listOfTheorems.isListEmpty()) {
            throw new IndexNotThere();
        }
        instantiateTheoremMenu();
        theoremLibrary.setLayout(new GridLayout(1,2,0,0));

        theoremLibrary.add(theoremText);
        addingTheoremButtons();
        theoremLibrary.add(theoremButtons);

        welcomeScreen.setVisible(false);
        mainFrame.add(theoremLibrary);
    }

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
                JOptionPane.showMessageDialog(null, "Field Successfully Changed!", "Success!",
                        JOptionPane.PLAIN_MESSAGE);
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
            int deleteOption = JOptionPane.showOptionDialog(null, "Are you sure you want to delete the theorem?",
                    "Delete Theorem?", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null,
                    deleteButton,deleteButton[0]);
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


    private void makeEquationMenu() throws IndexNotThere {
        if (listOfRequests.isListEmpty()) {
            throw new IndexNotThere();
        }

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
                JOptionPane.showMessageDialog(null, "Field Successfully Changed!",
                        "Success!", JOptionPane.PLAIN_MESSAGE);
            } catch (NameAlreadyExists e) {
                JOptionPane.showMessageDialog(null, "Name already exists!",
                        "Error", JOptionPane.WARNING_MESSAGE);
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
                JOptionPane.showMessageDialog(null, "Removed the practice problem!",
                        "Success!", JOptionPane.PLAIN_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "There are no problems to delete!",
                        "Error!", JOptionPane.ERROR_MESSAGE);
            }
        } else if (userDecision == 7) {
            String[] deleteButton = {"Yes", "No"};
            int deleteOption = JOptionPane.showOptionDialog(null, "Are you sure you want to delete the Equation?",
                    "Delete Equation?", JOptionPane.DEFAULT_OPTION,
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

    //Questions Menu:

    private void startQuestionPanel() {
        instantiateQuestionPanel();
        questionPanel.setLayout(new GridLayout(1,2,0,0));

        addingQuestionButtons();
        addingQuestionAnswerTextArea();
        questionPanel.add(questionAnswerArea);
        questionPanel.add(questionButtons);


        equationLibrary.setVisible(false);
        mainFrame.add(questionPanel);
    }

    private void addingQuestionAnswerTextArea() {
        questionAnswerArea.setLayout(new GridLayout(2,1,0,0));
        questionAnswerArea.add(questionText);
        questionText.setVerticalTextPosition(JLabel.TOP);
        questionText.setHorizontalAlignment(JLabel.CENTER);
        questionAnswerArea.add(answerText);
        answerText.setVerticalTextPosition(JLabel.TOP);
        answerText.setHorizontalAlignment(JLabel.CENTER);
    }

    private void addingQuestionButtons() {
        showAnswerButton.setText("Show Answer");
        showAnswerButton.addActionListener(e -> revealAnswerToViewer());

        returnToEquationsButton.setText("Return to equations library");
        returnToEquationsButton.addActionListener(e -> returnToEquationsAction());

        questionButtons.setBackground(new java.awt.Color(255,255,104));
        questionButtons.add(listOfQuestions);
        questionButtons.add(showAnswerButton);
        questionButtons.add(returnToEquationsButton);
    }

    private void returnToEquationsAction() {
        questionPanel.setVisible(false);
        equationLibrary.setVisible(true);
    }

    private void revealAnswerToViewer() {
        int index = equationEntries.getSelectedIndex();
        answerText.setText("Answer: "
                + listOfEquations.getEquation(index).getThePracticeProblemAnswer(listOfQuestions.getSelectedIndex()));
    }

    private void instantiateQuestionPanel() {
        int index = equationEntries.getSelectedIndex();
        questionPanel = new JPanel();
        questionText = new JLabel();
        answerText = new JLabel();
        listOfQuestions = new JComboBox(listOfEquations.getEquation(index).questionsToList().toArray());
        listOfQuestions.addActionListener(e -> selectQuestionAction());
        showAnswerButton = new JButton();
        returnToEquationsButton = new JButton();
        questionButtons = new JPanel();
        questionAnswerArea = new JPanel();
    }

    private void selectQuestionAction() {
        int indexEquation = equationEntries.getSelectedIndex();
        int indexQuestion = listOfQuestions.getSelectedIndex();
        answerText.setText(null);
        questionText.setText("Question: "
                + listOfEquations.getEquation(indexEquation).getThePracticeProblem(indexQuestion));
    }

    //Starting here is the GUI for requests


    private void enterRequests() {
        String[] buttonTexts = {"View Requests", "Make Requests"};
        int userDecision = JOptionPane.showOptionDialog(null, "Would you like to view or "
                        + "make a request?", "View or make requests?", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,null, buttonTexts,buttonTexts[0]);
        if (userDecision == 0) {
            try {
                openViewRequests();
            } catch (IndexNotThere e) {
                populateLibraryErrorMessage();
            }
        } else if (userDecision == 1) {
            openMakeRequest();
        }
    }

    private void populateLibraryErrorMessage() {
        JOptionPane.showMessageDialog(null, "Hey! It seems like there's nothing in this library"
                + " yet, try adding some entries in the requests!", "ERROR!", JOptionPane.PLAIN_MESSAGE);
    }

    private void openViewRequests() throws IndexNotThere {
        if (listOfRequests.isListEmpty()) {
            throw new IndexNotThere();
        }

        instantiateRequestPanel();
        requestPanel.setLayout(new GridLayout(1,2,0,0));
        requestEntries.addActionListener(e -> requestEntriesAction());

        addingRequestButtons();
        requestPanel.add(requestText);
        requestPanel.add(requestButtons);

        welcomeScreen.setVisible(false);
        mainFrame.add(requestPanel);
    }

    private void requestEntriesAction() {
        requestText.setText(listOfRequests.getRequest(requestEntries.getSelectedIndex()).viewRequest());
    }

    private void instantiateRequestPanel() {
        requestPanel = new JPanel();
        requestText = new JLabel();
        requestEntries = new JComboBox(listOfRequests.requestsToString().toArray());
        updateRequestButton = new JButton();
        submitRequestButton = new JButton();
        requestToMainMenuButton = new JButton();
        requestButtons = new JPanel();
    }

    private void addingRequestButtons() {
        int index = requestEntries.getSelectedIndex();
        requestButtons.setBackground(new java.awt.Color(153,102,0));

        updateRequestButton.setText("Update Request");
        updateRequestButton.addActionListener(e -> updateRequestButtonAction(listOfRequests.getRequest(index)));
        updateRequestButton.setFocusable(true);

        submitRequestButton.setText("Submit Request");
        submitRequestButton.addActionListener(e -> submitRequestPrompt());
        submitRequestButton.setFocusable(true);

        requestToMainMenuButton.setText("Return to main menu");
        requestToMainMenuButton.addActionListener(e -> requestToMainMenu());
        requestToMainMenuButton.setFocusable(true);

        requestButtons.add(requestEntries);
        requestButtons.add(updateRequestButton);
        requestButtons.add(submitRequestButton);
        requestButtons.add(requestToMainMenuButton);
    }

    /*
    "Press 1 to change the name of the entry.\nPress 2 to change the theorem.\nPress 3"
                        + " to change the course it is most useful for.\nPress 4 to change the description.\nPress 5 to"
                        + " change the Proof.\nPress 6 to update the estimated completion\nPress 7 to submit the"
                        + " entry to its designated library\n"
     */

    private void updateRequestButtonAction(Request request) {
        String[] buttonTexts = {"Change Name", "Change theorem", "Change course", "Change description",
                "Change proof", "Change estimated completion", "Delete entry"};
        int userDecision = JOptionPane.showOptionDialog(null, "Choose the action you would like "
                + "to perform.", "Change Request?", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,null, buttonTexts,buttonTexts[0]);
        if (userDecision == 0) {
            String answer = whatIsTheChange();
            try {
                listOfRequests.changeRequestNameAndCheckExistence(request, answer);
                JOptionPane.showMessageDialog(null, "Field Successfully Changed!", "Success!",
                        JOptionPane.PLAIN_MESSAGE);
            } catch (NameAlreadyExists e) {
                JOptionPane.showMessageDialog(null, "Name already exists!", "Error", JOptionPane.WARNING_MESSAGE);
            }
        } else if (userDecision == 1) {
            request.changeTheorem(whatIsTheChange());
            JOptionPane.showMessageDialog(null, "Field Successfully Changed!", "Success!", JOptionPane.PLAIN_MESSAGE);
        } else if (userDecision == 2) {
            request.changeCourse(whatIsTheChange());
            JOptionPane.showMessageDialog(null, "Field Successfully Changed!", "Success!", JOptionPane.PLAIN_MESSAGE);
        } else if (userDecision == 3) {
            request.changeExplanation(whatIsTheChange());
            JOptionPane.showMessageDialog(null, "Field Successfully Changed!", "Success!", JOptionPane.PLAIN_MESSAGE);
        } else if (userDecision == 4) {
            request.changeProof(whatIsTheChange());
            JOptionPane.showMessageDialog(null, "Field Successfully Changed!", "Success!", JOptionPane.PLAIN_MESSAGE);
        } else if (userDecision == 5) {
            String completion = JOptionPane.showInputDialog("How far along is this entry completed? "
                    + "(Pick a number between 0 and 100)");
            try {
                request.updateEstimatedCompletion(completion);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Not between 0 and 100!", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        } else if (userDecision == 6) {
            if (areYouSureYouWantToDeleteRequest()) {
                listOfRequests.removeRequest(request);
                requestEntries.removeItemAt(requestEntries.getSelectedIndex());
                JOptionPane.showMessageDialog(null, "Successfully removed the request.",
                        "Success!", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    private Boolean areYouSureYouWantToDeleteRequest() {
        String[] buttonTexts = {"Yes", "No"};
        int userDecision = JOptionPane.showOptionDialog(null, "Are you sure you want to delete "
                        + "this request entry?", "Delete Request?", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,null, buttonTexts,buttonTexts[0]);
        return userDecision == 0;
    }

    private void submitRequestPrompt() {
        Request request = listOfRequests.getRequest(requestEntries.getSelectedIndex());
        if (request.getEstimatedCompletion() != 100) {
            if (submitIfNotCompletePrompt()) {
                convertingRequest(request);
                requestEntries.removeItemAt(requestEntries.getSelectedIndex());
                JOptionPane.showMessageDialog(null, "Request submitted to main library!!!",
                        "Success!", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    private boolean submitIfNotCompletePrompt() {
        String[] buttonTexts = {"Submit", "Don't submit"};
        int userDecision = JOptionPane.showOptionDialog(null, "The request is currently not "
                        + "complete, would you still like to submit it?", "Warning!", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,null, buttonTexts,buttonTexts[0]);
        return userDecision == 0;
    }

    private void requestToMainMenu() {
        requestPanel.setVisible(false);
        welcomeScreen.setVisible(true);
    }

    private void openMakeRequest() {
        String[] buttonTexts = {"Theorem", "Equation"};
        int userDecision = JOptionPane.showOptionDialog(null, "You are now making a request. "
                + "Is this request for an equation or theorem?\n", "Is this a theorem or equation?",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttonTexts, buttonTexts[0]);
        if (userDecision == 0) {
            promptToMakeTheorem();
        } else if (userDecision == 1) {
            promptToMakeEquation();
        }
    }

    private void promptToMakeTheorem() {
        Request newRequest;
        String name = JOptionPane.showInputDialog("What is the name of the Theorem?");
        String theorem = JOptionPane.showInputDialog("What does the Theorem state?");
        String course = JOptionPane.showInputDialog("What course is this Theorem most applicable for? (You can leave "
                + "it blank if you are not sure");
        String explanation = JOptionPane.showInputDialog("You can leave any extra explanations if you so desire.");
        String proof = JOptionPane.showInputDialog("What is the derivation of this Theorem? "
                + "(You can leave this blank if unknown");
        newRequest = new Request(name, theorem, "Theorem", course, explanation, proof);
        try {
            listOfRequests.addRequestAndCheckExistence(newRequest);
            JOptionPane.showMessageDialog(null, "Request added!", "Success!", JOptionPane.PLAIN_MESSAGE);
        } catch (NameAlreadyExists e) {
            JOptionPane.showMessageDialog(null, "Name of theorem already exists!", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void promptToMakeEquation() {
        Request newRequest;
        String name = JOptionPane.showInputDialog("What is the name of the Equation?");
        String theorem = JOptionPane.showInputDialog("What does the equation state?");
        String course = JOptionPane.showInputDialog("What course is this equation most applicable for? (You can leave "
                + "it blank if you are not sure");
        String explanation = JOptionPane.showInputDialog("You can leave any extra explanations if you so desire.");
        String proof = JOptionPane.showInputDialog("What is the derivation of this Equation? "
                + "(You can leave this blank if unknown");
        newRequest = new Request(name, theorem, "Equation", course, explanation, proof);
        try {
            listOfRequests.addRequestAndCheckExistence(newRequest);
            JOptionPane.showMessageDialog(null,
                    "Request added!", "Success!", JOptionPane.PLAIN_MESSAGE);
        } catch (NameAlreadyExists e) {
            JOptionPane.showMessageDialog(null,
                    "Name of equation already exists!", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }
}

//Make an exception when there is nothing in a list DONE
//Fix tests DONE
//Fix styling
//Add documentation
//Update Readme
//Clean up some code
//Rigorous testing