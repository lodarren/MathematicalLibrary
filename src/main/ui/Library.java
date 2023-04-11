package ui;

import model.*;
import model.exceptions.IndexNotThere;
import model.exceptions.NameAlreadyExists;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represents the full list of finished mathematical entries in the library. There are three sections of the library
// That the user can explore, one containing equations, one containing theorems and the last one containing requests.

public class Library {
    //These fields represent the libraries one can enter and view entries from.
    ListOfTheorems listOfTheorems;
    ListOfEquations listOfEquations;
    ListOfRequests listOfRequests;

    //These fields are all related to saving and loading the library
    private JsonWriter jsonWriterTheorem;
    private JsonReader jsonReaderTheorem;
    private JsonWriter jsonWriterEquation;
    private JsonReader jsonReaderEquation;
    private JsonWriter jsonWriterRequest;
    private JsonReader jsonReaderRequest;
    private static final String JSON_STORE_THEOREM = "./data/ListOfTheoremSave.json";
    private static final String JSON_STORE_EQUATION = "./data/ListOfEquationSave.json";
    private static final String JSON_STORE_REQUEST = "./data/ListOfRequestSave.json";

    //This is the main window where the application takes place
    JFrame mainFrame;

    //These fields are all related to the welcome screen that appears when the user starts the application
    JPanel welcomeScreen;
    JPanel welcomeButtons;
    Border border;
    JButton openListOfTheorem;
    JButton openListOfEquation;
    JButton openListOfRequest;
    JButton leaveButton;
    JLabel welcomeText;
    JLabel instructionsText;

    //These fields are all related to the Theorem Library.
    JPanel theoremLibrary;
    JComboBox<String> theoremEntries;
    JButton showProofButton;
    JLabel theoremText;
    JButton editTheorem;
    JPanel theoremButtons;
    JButton returnToMainMenuButton;

    //These fields are all related to the Equation Library.
    JComboBox<String> equationEntries;
    JPanel equationLibrary;
    JPanel equationButtons;
    JLabel equationText;
    JButton equationToMainMenu;
    JButton editEquation;
    JButton viewQuestionButton;

    //These fields are all related to the request Library.
    JPanel requestPanel;
    JLabel requestText;
    JComboBox<String> requestEntries;
    JButton updateRequestButton;
    JButton submitRequestButton;
    JButton requestToMainMenuButton;
    JPanel requestButtons;

    //These fields are all related to the Practice Problem section of an Equation.
    JPanel questionPanel;
    JPanel questionAnswerArea;
    JLabel questionText;
    JLabel answerText;
    JComboBox<String> listOfQuestions;
    JButton showAnswerButton;
    JButton returnToEquationsButton;
    JPanel questionButtons;


    //EFFECTS: Creates a new library object, throws the FileNotFoundException if it occurs
    public Library() throws FileNotFoundException {
        runLibrary();
    }

    //EFFECTS: Runs the library and boots up the main menu for the user
    private void runLibrary() {
        setup();
        doYouWantToLoadLibrary();
        System.out.println("\nSee You Next Time!\n");
    }

    //EFFECTS: used to instantiate listOfEquation, listOfTheorem and listOfRequests and the GUI
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

    //Instantiates the GUI components that are related to the welcome screen.
    void instantiateGUI() {
        welcomeScreen = new JPanel();
        welcomeButtons = new JPanel();
        border = BorderFactory.createLineBorder(Color.black, 3);
        openListOfTheorem = new JButton();
        openListOfEquation = new JButton();
        openListOfRequest = new JButton();
        welcomeText = new JLabel();
        instructionsText = new JLabel();
        leaveButton = new JButton();
        returnToMainMenuButton = new JButton();
    }


    //EFFECTS: loads the JSON file if the user selects yes on the window pop up
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

    //EFFECTS: Attempts to load the JSON file. Returns an error if there is no save file found. Loads the program after.
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
            instantiateMainMenu();
        }
    }

    //EFFECTS: Saves to the JSON file when the user clicks yes, afterwards closes the program.
    private void doYouWantToSaveLibrary() {
        String[] buttonTexts = {"Save Entries"};
        int userDecision = JOptionPane.showOptionDialog(null, "Before you leave, do you want to save the library?",
                "Do you want to save?",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttonTexts, buttonTexts[0]);
        if (userDecision == 0) {
            try {
                attemptSave();
                JOptionPane.showMessageDialog(mainFrame, "Library has been saved!");
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(mainFrame, "Unable to write to file");
            } finally {
                closeProgram();
            }
        } else {
            closeProgram();
        }
    }

    //EFFECTS: Closes the program and prints the EventLog to the system.
    private void closeProgram() {
        mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
        for (Event e : EventLog.getInstance()) {
            System.out.println(e.toString());
        }
    }

    //EFFECTS: Attempts to save the file and throws a FileNotFoundException if not successful.
    private void attemptSave() throws FileNotFoundException {
        jsonWriterTheorem.open();
        jsonWriterEquation.open();
        jsonWriterRequest.open();
        jsonWriterTheorem.writeListOfTheorem(listOfTheorems);
        jsonWriterEquation.writeListOfEquation(listOfEquations);
        jsonWriterRequest.writeListOfRequests(listOfRequests);
        jsonWriterTheorem.close();
        jsonWriterEquation.close();
        jsonWriterRequest.close();
    }


    //EFFECTS: Displays a pop-up window and returns the input that the user puts in. If the input is cancelled then it
    //         returns the old input.
    private String whatIsTheChange(String oldInput) {
        String change = JOptionPane.showInputDialog("What is the proposed change to the field?");
        if (change == null) {
            return oldInput;
        }
        JOptionPane.showMessageDialog(
                null, "Field Successfully Changed!", "Success!", JOptionPane.PLAIN_MESSAGE);
        return change;
    }


    //EFFECTS: checks if the request is a Theorem, if it is, then it adds it to listOfTheorem, otherwise adds it
    //         to ListOfEquation.
    private void convertingRequest(Request request) {
        if (request.isItATheorem()) {
            listOfTheorems.addTheorem(request.requestToTheorem());
        } else {
            listOfEquations.addEquation(request.requestToEquation());
        }
        listOfRequests.removeRequest(request);
    }

    //EFFECTS: Creates the screen for the library application.
    void makeScreen() {
        mainFrame.setTitle("LibraryApplication");
        mainFrame.setResizable(false);
        mainFrame.setSize(1024, 768);
        mainFrame.setVisible(true);
    }

    //EFFECTS: Sets up the welcome text for the welcome screen of the library.
    void makeWelcomeText() {
        welcomeText.setText("Welcome To The Library!");
        welcomeText.setFont(new Font("Computer Modern", Font.PLAIN, 60));
        welcomeText.setBorder(border);
        welcomeText.setVerticalAlignment(JLabel.CENTER);
        welcomeText.setHorizontalAlignment(JLabel.CENTER);
        welcomeText.setBackground(Color.white);
        welcomeText.setOpaque(true);
    }

    //EFFECTS: Creates the welcome buttons for the library and sets their required actions.
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

    //EFFECTS: tells the user to make some requests.
    private void populateLibraryErrorMessage() {
        JOptionPane.showMessageDialog(null, "Hey! It seems like there's nothing in this library"
                + " yet, try adding some entries in the requests!", "ERROR!", JOptionPane.PLAIN_MESSAGE);
    }

    //EFFECTS: Opens the theorem library. If there are no entries in the theorem library then it prompts the user to
    //         make some requests.
    void openListOfTheoremAction() {
        try {
            makeTheoremMenu();
        } catch (IndexNotThere e) {
            populateLibraryErrorMessage();
        }
    }

    //EFFECTS: Opens the equation library. If there are no entries in the theorem library then it prompts the user to
    //         make some requests.
    void openListOfEquationAction() {
        try {
            makeEquationMenu();
        } catch (IndexNotThere e) {
            populateLibraryErrorMessage();
        }
    }

    //EFFECTS: Sets up the welcome buttons panel and adds the buttons to the panel.
    void buttonsToWelcomeButtonsPanel() {
        welcomeButtons.setBackground(Color.gray);
        welcomeButtons.setLayout(new GridLayout(1, 4, 0, 0));
        welcomeButtons.add(openListOfTheorem);
        welcomeButtons.add(openListOfEquation);
        welcomeButtons.add(openListOfRequest);
        welcomeButtons.add(leaveButton);
    }

    //EFFECTS: Sets up the text for the instruction text in the library.
    void instantiateInstructionText() {
        ImageIcon image = new ImageIcon("./data/Main Menu Picture.png");
        instructionsText.setText("Please click on the following library you would like to enter!");
        instructionsText.setIcon(image);
        instructionsText.setFont(new Font("Computer Modern", Font.BOLD, 30));
        instructionsText.setVerticalTextPosition(JLabel.CENTER);
        instructionsText.setHorizontalTextPosition(JLabel.CENTER);
        instructionsText.setBorder(border);
        instructionsText.setVerticalAlignment(JLabel.CENTER);
        instructionsText.setHorizontalAlignment(JLabel.CENTER);
        instructionsText.setBackground(Color.white);
        instructionsText.setOpaque(true);
    }

    //EFFECTS: Adds the panels related to the welcome panel to the welcome panel
    void addingToWelcomeScreen() {
        welcomeScreen.setLayout(new GridLayout(3, 1, 0, 0));
        welcomeScreen.setBackground(Color.gray);
        welcomeScreen.add(welcomeText);
        welcomeScreen.add(instructionsText);
        welcomeScreen.add(welcomeButtons);
        welcomeScreen.setVisible(true);
    }

    //EFFECTS: Instantiates the welcome panel and then adds it to the mainFrame.
    private void instantiateMainMenu() {
        makeWelcomeText();
        makeWelcomeButtons();
        instantiateInstructionText();
        buttonsToWelcomeButtonsPanel();
        addingToWelcomeScreen();
        mainFrame.add(welcomeScreen);
        mainFrame.setVisible(true);
    }

    // EFFECTS: Tells the user to add requests if the theorem library is empty, otherwise instantiates the theorem
    //          library.
    private void makeTheoremMenu() throws IndexNotThere {
        if (listOfTheorems.isListEmpty()) {
            throw new IndexNotThere();
        }

        instantiateTheoremMenu();
        theoremLibrary.setLayout(new GridLayout(1, 2, 0, 0));

        addingTheoremButtons();
        theoremLibrary.add(theoremText);
        theoremLibrary.add(theoremButtons);

        welcomeScreen.setVisible(false);
        mainFrame.add(theoremLibrary);
    }

    //EFFECTS: Instantiates all the buttons related to the theorem library.
    private void instantiateTheoremMenu() {
        theoremLibrary = new JPanel();
        theoremButtons = new JPanel();
        theoremEntries = new JComboBox(listOfTheorems.theoremsToString().toArray());
        theoremEntries.setSelectedIndex(0);
        instantiateTheoremEntries();

        showProofButton = new JButton();
        showProofButton.setText("Display Additional Info");
        showProofButton.addActionListener(e -> showAdditionalInfoMethod());
        showProofButton.setFocusable(true);

        theoremText = new JLabel();
        theoremText.setBounds(0, 0, 768, 576);
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

    //EFFECTS: Adds the buttons related to the theoremButtons Panel to theoremButtons. It also sets its background
    //         colour.
    private void addingTheoremButtons() {
        theoremButtons.add(theoremEntries);
        theoremButtons.add(showProofButton);
        theoremButtons.add(editTheorem);
        theoremButtons.add(returnToMainMenuButton);
        theoremButtons.setBackground(new java.awt.Color(51, 204, 255));
        theoremButtons.setOpaque(true);
    }

    //EFFECTS: Switches from the welcome panel to the theoremLibrary panel.
    private void theoremToMainMenu() {
        theoremLibrary.setVisible(false);
        welcomeScreen.setVisible(true);
    }

    //EFFECTS: Sets the text of the theoremText to show its other fields minus the proof.
    private void theoremEntriesAction() {
        theoremText.setText(listOfTheorems.getTheorem(theoremEntries.getSelectedIndex()).viewTheoremLessInfo());
    }

    //EFFECTS: Sets the text of theoremText to show the proof as well as its other fields in the theorem.
    private void showAdditionalInfoMethod() {
        theoremText.setText(listOfTheorems.getTheorem(theoremEntries.getSelectedIndex()).viewTheorem());
    }

    //EFFECTS: sets up the ComboBox related to viewing the theorem entries.
    private void instantiateTheoremEntries() {
        theoremEntries.addActionListener(e -> theoremEntriesAction());
        theoremEntries.setSize(40, 20);
    }

    // EFFECTS: Prompts the user to choose which field of the theorem they would like to change. It then asks for the
    //          suggested change, and then adds it if it is a valid change.
    private void changeTheoremPrompt(Theorem theorem) {
        String[] buttonTexts = {"Change Name", "Change theorem", "Change course", "Change description", "Change proof",
                "Delete entry"};
        int userDecision = JOptionPane.showOptionDialog(null, "Choose the theorem field you would"
                        + "like to change. ", "Change Theorem?", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, buttonTexts, buttonTexts[0]);
        if (userDecision == 0) {
            changeTheoremNamePrompt(theorem);
        } else if (userDecision == 1) {
            theorem.changeTheorem(whatIsTheChange(theorem.getTheorem()));
        } else if (userDecision == 2) {
            theorem.changeCourse(whatIsTheChange(theorem.getCourse()));
        } else if (userDecision == 3) {
            theorem.changeExplanation(whatIsTheChange(theorem.getExplanations()));
        } else if (userDecision == 4) {
            theorem.changeProof(whatIsTheChange(theorem.getProof()));
        } else if (userDecision == 5) {
            deleteTheoremPrompt(theorem);
        }
    }

    //EFFECTS: prompts the user to change the name of the theorem. If the change is not null, it then checks if the name
    //         is already taken. If it is already taken, it tells the user the name already exists.
    private void changeTheoremNamePrompt(Theorem theorem) {
        String change = JOptionPane.showInputDialog("What is the proposed change to the field?");
        if (change != null) {
            try {
                listOfTheorems.changeTheoremNameAndCheckExistence(theorem, change);
                theoremEntries.setSelectedItem(change);
                JOptionPane.showMessageDialog(null, "Field Successfully Changed!", "Success!",
                        JOptionPane.PLAIN_MESSAGE);
            } catch (NameAlreadyExists e) {
                JOptionPane.showMessageDialog(null, "Name already exists!", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    //EFFECTS: Asks the user if they would like to delete the theorem if it is not the last one left. If yes, it tells
    //         the user that the deletion was successful.
    private void deleteTheoremPrompt(Theorem theorem) {
        String[] deleteButton = {"Yes", "No"};
        if (listOfTheorems.theoremsToString().size() == 1) {
            JOptionPane.showMessageDialog(null, "There is only one entry left!",
                    "Error!", JOptionPane.PLAIN_MESSAGE);
        } else {
            int deleteOption = JOptionPane.showOptionDialog(null, "Are you sure you want to delete the theorem?",
                    "Delete Theorem?", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                    deleteButton, deleteButton[0]);
            if (deleteOption == 0) {
                theoremEntries.removeItemAt(theoremEntries.getSelectedIndex());
                listOfTheorems.removeTheorem(theorem);
                JOptionPane.showMessageDialog(null, "Theorem Deleted", "Success!", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    //Starting here is all the code related to the EquationLibrary GUI.

    //EFFECTS: Sets up the equationPanel screen if listOfRequests is not empty. If it is empty, then it tells the user
    //         to make some requests.
    private void makeEquationMenu() throws IndexNotThere {
        if (listOfEquations.isListEmpty()) {
            throw new IndexNotThere();
        }

        instantiateEquationMenu();
        equationLibrary.setBackground(new java.awt.Color(102, 255, 102));
        equationLibrary.setLayout(new GridLayout(1, 2, 0, 0));

        addingEquationButtons();
        equationLibrary.add(equationText);
        equationLibrary.add(equationButtons);

        welcomeScreen.setVisible(false);
        mainFrame.add(equationLibrary);
    }

    // EFFECTS: instantiates the equationMenu elements.
    private void instantiateEquationMenu() {
        equationLibrary = new JPanel();
        equationButtons = new JPanel();
        equationEntries = new JComboBox(listOfEquations.equationsToString().toArray());
        equationEntries.setSelectedIndex(0);
        instantiateEquationEntries();

        equationText = new JLabel();
        equationText.setBounds(0, 0, 768, 576);
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
        viewQuestionButton.addActionListener(e -> viewQuestionButtonAction());
        viewQuestionButton.setText("View Practice Problems");
        viewQuestionButton.setFocusable(true);
    }


    //EFFECTS: instantiates and sets up the equationEntries ComboBox.
    private void instantiateEquationEntries() {
        equationEntries.addActionListener(e -> equationEntriesAction());
        equationEntries.setSize(40, 20);
    }

    //EFFECTS: sets the text of the equationText to the fields of the equation currently selected by equationEntries.
    private void equationEntriesAction() {
        equationText.setText(listOfEquations.getEquation(equationEntries.getSelectedIndex()).viewEquation());
    }

    //EFFECTS: adds the buttons related to the equation screen to equationButtons.
    private void addingEquationButtons() {
        equationButtons.add(equationEntries);
        equationButtons.add(editEquation);
        equationButtons.add(viewQuestionButton);
        equationButtons.add(equationToMainMenu);
        equationButtons.setBackground(new java.awt.Color(102, 255, 102));
        equationButtons.setOpaque(true);
    }

    //EFFECTS: returns from the equation library panel to the welcome panel.
    private void equationToMainMenu() {
        equationLibrary.setVisible(false);
        welcomeScreen.setVisible(true);
    }

    // EFFECTS: Prompts the user what field of the equation they would like to change, it prompts the user the change
    //          was successful if the change was a valid change.
    private void changeEquationPrompt(Equation equation) {
        String[] buttonTexts = {"Change Name", "Change theorem", "Change course", "Change description",
                "Change Derivation", "Add practice problems", "Delete practice problems", "Delete entry"};
        int userDecision = JOptionPane.showOptionDialog(null, "Choose the equation field you would"
                        + "like to change. ", "Change Equation?", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, buttonTexts, buttonTexts[0]);
        if (userDecision == 0) {
            changeEquationNamePrompt(equation);
        } else if (userDecision == 1) {
            equation.changeTheorem(whatIsTheChange(equation.getTheorem()));
        } else if (userDecision == 2) {
            equation.changeCourse(whatIsTheChange(equation.getCourse()));
        } else if (userDecision == 3) {
            equation.changeExplanation(whatIsTheChange(equation.getExplanations()));
        } else if (userDecision == 4) {
            equation.changeProof(whatIsTheChange(equation.getProof()));
        } else if (userDecision == 5) {
            addPracticeProblemPrompt(equation);
        } else if (userDecision == 6) {
            deletePracticeProblemPrompt(equation);
        } else if (userDecision == 7) {
            deleteEquationPrompt(equation);
        }
    }

    //EFFECTS: prompts the user to change the name of the equation. If the change is not null, then it changes the name
    //         of the equation if it already hasn't been used by another equation.
    private void changeEquationNamePrompt(Equation equation) {
        String change = JOptionPane.showInputDialog("What is the proposed change to the field?");
        if (change != null) {
            try {
                listOfEquations.changeEquationNameAndCheckExistence(equation, change);
                equationEntries.setSelectedItem(change);
                JOptionPane.showMessageDialog(null, "Field Successfully Changed!", "Success!",
                        JOptionPane.PLAIN_MESSAGE);
            } catch (NameAlreadyExists e) {
                JOptionPane.showMessageDialog(null, "Name already exists!", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    //EFFECTS: prompts the user to delete the equation if it is not the last one, if "yes" is selected, then it tells
    //         the user that the deletion was successful.
    private void deleteEquationPrompt(Equation equation) {
        String[] deleteButton = {"Yes", "No"};
        if (listOfEquations.equationsToString().size() == 1) {
            JOptionPane.showMessageDialog(null, "There is only one entry left!",
                    "Error!", JOptionPane.PLAIN_MESSAGE);
        } else {
            int deleteOption = JOptionPane.showOptionDialog(null, "Are you sure you want to delete the Equation?",
                    "Delete Equation?", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE, null, deleteButton, deleteButton[0]);
            if (deleteOption == 0) {
                equationEntries.removeItemAt(equationEntries.getSelectedIndex());
                listOfEquations.removeEquation(equation);
                JOptionPane.showMessageDialog(null, "Equation Deleted", "Success!", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    //EFFECTS: prompts the user to delete the practice problem.
    private void deletePracticeProblemPrompt(Equation equation) {
        try {
            String answer = JOptionPane.showInputDialog("What problems would you like to delete? Type the number "
                    + "below.\n" + equation.showNumberOfPracticeProblems() + "\n");
            int newInput;
            newInput = Integer.parseInt(answer) - 1;
            equation.removePracticeProblem(newInput);
            JOptionPane.showMessageDialog(null, "Removed the practice problem!",
                    "Success!", JOptionPane.PLAIN_MESSAGE);
        } catch (IndexNotThere e) {
            JOptionPane.showMessageDialog(null, "There is nothing to delete!",
                    "Error!", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Not a valid number!",
                    "Error!", JOptionPane.ERROR_MESSAGE);
        } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "That is not one of the numbers you can delete!",
                    "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    //EFFECTS: prompts the user to add the question and the answer of their suggested practice problem. Tells the user
    //         the addition was successful upon completion.
    private void addPracticeProblemPrompt(Equation equation) {
        String question = JOptionPane.showInputDialog("What is your example practice problem?");
        String answer = JOptionPane.showInputDialog("What is the answer to your practice problem?");
        equation.addPracticeProblem(question, answer);
        JOptionPane.showMessageDialog(null, "Thank You! Question Added!", "Success!", JOptionPane.PLAIN_MESSAGE);
    }

    //Starting here is all the code related to the QuestionsScreen section of the GUI.

    //EFFECTS: Opens the question panel. If there are no entries in the question panel, it prompts the user to make
    //         questions.
    private void viewQuestionButtonAction() {
        try {
            startQuestionPanel();
        } catch (IndexNotThere e) {
            JOptionPane.showMessageDialog(null, "There are currently no practice problems, why"
                    + " don't you add some?", "No practice problems!", JOptionPane.PLAIN_MESSAGE);
        }
    }

    //EFFECTS: sets up the elements that are related to questionPanel.
    private void startQuestionPanel() throws IndexNotThere {
        if (listOfEquations.getEquation(equationEntries.getSelectedIndex()).getPracticeProblemsRaw().size() == 0) {
            throw new IndexNotThere();
        }
        instantiateQuestionPanel();
        questionPanel.setLayout(new GridLayout(1, 2, 0, 0));

        addingQuestionButtons();
        addingQuestionAnswerTextArea();
        questionPanel.add(questionAnswerArea);
        questionPanel.add(questionButtons);

        equationLibrary.setVisible(false);
        mainFrame.add(questionPanel);
    }

    //EFFECTS: instantiates the questionPanel elements.
    private void instantiateQuestionPanel() {
        questionPanel = new JPanel();
        questionText = new JLabel();
        answerText = new JLabel();
        listOfQuestions = new
                JComboBox(listOfEquations.getEquation(equationEntries.getSelectedIndex()).questionsToList().toArray());
        listOfQuestions.addActionListener(e -> selectQuestionAction());
        showAnswerButton = new JButton();
        returnToEquationsButton = new JButton();
        questionButtons = new JPanel();
        questionAnswerArea = new JPanel();
    }

    //EFFECTS: sets up the fields related to the panel displaying the practice problem and answer.
    private void addingQuestionAnswerTextArea() {
        questionAnswerArea.setLayout(new GridLayout(2, 1, 0, 0));
        questionAnswerArea.add(questionText);
        questionText.setVerticalTextPosition(JLabel.TOP);
        questionText.setHorizontalAlignment(JLabel.CENTER);
        questionAnswerArea.add(answerText);
        answerText.setVerticalTextPosition(JLabel.TOP);
        answerText.setHorizontalAlignment(JLabel.CENTER);
    }

    //EFFECTS: adds all the buttons related to the questionPanel to questionPanel and sets up their fields.
    private void addingQuestionButtons() {
        showAnswerButton.setText("Show Answer");
        showAnswerButton.addActionListener(e -> revealAnswerToViewer());

        returnToEquationsButton.setText("Return to equations library");
        returnToEquationsButton.addActionListener(e -> returnToEquationsAction());

        questionButtons.setBackground(new java.awt.Color(255, 255, 104));
        questionButtons.add(listOfQuestions);
        questionButtons.add(showAnswerButton);
        questionButtons.add(returnToEquationsButton);
    }

    //EFFECTS: returns from the questionPanel to the equationLibraryPanel.
    private void returnToEquationsAction() {
        questionPanel.setVisible(false);
        equationLibrary.setVisible(true);
    }

    //EFFECTS: makes the text of answerText the answer to the selected equation in the listOfQuestions ComboBox.
    private void revealAnswerToViewer() {
        int index = equationEntries.getSelectedIndex();
        answerText.setText("Answer: "
                + listOfEquations.getEquation(index).getThePracticeProblemAnswer(listOfQuestions.getSelectedIndex()));
    }

    //EFFECTS: makes the text of questionText display the question that is selected by listOfQuestions.
    private void selectQuestionAction() {
        int indexEquation = equationEntries.getSelectedIndex();
        int indexQuestion = listOfQuestions.getSelectedIndex();
        answerText.setText(null);
        questionText.setText("Question: "
                + listOfEquations.getEquation(indexEquation).getThePracticeProblem(indexQuestion));
    }

    //Starting here is all the code related to the GUI of the RequestsLibrary GUI.

    //EFFECTS: prompts the user if they would like to view or make requests. If ListOfRequests is empty then it prompts
    //         tells the user to make more requests.
    private void enterRequests() {
        String[] buttonTexts = {"View Requests", "Make Requests"};
        int userDecision = JOptionPane.showOptionDialog(null, "Would you like to view or "
                        + "make a request?", "View or make requests?", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, buttonTexts, buttonTexts[0]);
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

    //EFFECTS: sets up and adds all the elements of the requestPanel to requestPanel. It throws an IndexNotThere
    //         exception if there are no requests in ListOfRequests.
    private void openViewRequests() throws IndexNotThere {
        if (listOfRequests.isListEmpty()) {
            throw new IndexNotThere();
        }

        instantiateRequestPanel();
        requestPanel.setLayout(new GridLayout(1, 2, 0, 0));
        requestEntries.setSelectedIndex(0);
        requestEntries.addActionListener(e -> requestEntriesAction());

        addingRequestButtons();
        requestPanel.add(requestText);
        requestPanel.add(requestButtons);

        welcomeScreen.setVisible(false);
        mainFrame.add(requestPanel);
    }

    //EFFECTS: instantiates the elements in the RequestPanel.
    private void instantiateRequestPanel() {
        requestPanel = new JPanel();
        requestText = new JLabel();
        requestEntries = new JComboBox(listOfRequests.requestsToString().toArray());
        updateRequestButton = new JButton();
        submitRequestButton = new JButton();
        requestToMainMenuButton = new JButton();
        requestButtons = new JPanel();
    }

    //EFFECTS: makes the text of requestText display the properties of the request selected by the listOfRequests
    //         ComboBox.
    private void requestEntriesAction() {
        if (requestEntries.getSelectedIndex() > -1) {
            requestText.setText(listOfRequests.getRequest(requestEntries.getSelectedIndex()).viewRequest());
        }
    }

    //EFFECTS: sets up the fields to addingRequestButtons.
    private void addingRequestButtons() {
        requestButtons.setBackground(new java.awt.Color(153, 102, 0));

        updateRequestButton.setText("Update Request");
        updateRequestButton.addActionListener(e
                -> updateRequestButtonAction(listOfRequests.getRequest(requestEntries.getSelectedIndex())));
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

    //EFFECTS: Returns from the requestsPanel to the welcomeScreen Panel.
    private void requestToMainMenu() {
        requestPanel.setVisible(false);
        welcomeScreen.setVisible(true);
    }

    //EFFECTS: Asks the user which field of the request they would like to change. Tells the user the change was
    //         successful up on completion.
    private void updateRequestButtonAction(Request request) {
        int userDecision = getChangeRequested();
        if (userDecision == 0) {
            changeRequestNamePrompt(request);
        } else if (userDecision == 1) {
            request.changeTheorem(whatIsTheChange(request.getTheorem()));
        } else if (userDecision == 2) {
            request.changeCourse(whatIsTheChange(request.getCourse()));
        } else if (userDecision == 3) {
            request.changeExplanation(whatIsTheChange(request.getExplanations()));
        } else if (userDecision == 4) {
            request.changeProof(whatIsTheChange(request.getProof()));
        } else if (userDecision == 5) {
            changeRequestCompletion(request);
        } else if (userDecision == 6) {
            deleteRequestPrompt(request);
        }
    }

    //EFFECTS: prompts the user to pick the field they would like to change in the request.
    private int getChangeRequested() {
        String[] buttonTexts = {"Change Name", "Change theorem", "Change course", "Change description",
                "Change proof", "Change estimated completion", "Delete entry"};
        return JOptionPane.showOptionDialog(null, "Choose the action you would like "
                        + "to perform.", "Change Request?", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, buttonTexts, buttonTexts[0]);
    }

    //EFFECTS: First prompts the user if they would like to delete the request if it is not the last one, if yes then
    //         it removes it from requestEntries and also from the listOfRequests. It displays a message telling the
    //         user the deletion was successful.
    private void deleteRequestPrompt(Request request) {
        if (listOfRequests.requestsToString().size() == 1) {
            JOptionPane.showMessageDialog(null, "There is only one entry left!",
                    "Error!", JOptionPane.PLAIN_MESSAGE);
        } else {
            if (areYouSureYouWantToDeleteRequest()) {
                listOfRequests.removeRequest(request);
                requestEntries.removeItemAt(requestEntries.getSelectedIndex());
                JOptionPane.showMessageDialog(null, "Successfully removed the request.",
                        "Success!", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    //EFFECTS: Prompts the user to change the completion of the request. If the input is not an integer between 0 and
    //         100 then it tells the user that the input is not valid.
    private void changeRequestCompletion(Request request) {
        String completion = JOptionPane.showInputDialog("How far along is this entry completed? "
                + "(Pick a number between 0 and 100)");
        try {
            request.updateEstimatedCompletion(completion);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Not between 0 and 100!", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    //EFFECTS: Prompts the user to change the name of the field. If the change is not null, and the name is not already
    //         taken then it tells the user the change was successful. If the name is already taken, it displays an
    //         error message.
    private void changeRequestNamePrompt(Request request) {
        String change = JOptionPane.showInputDialog("What is the proposed change to the field?");
        if (change != null) {
            try {
                checkIfNameExistsRequest(request, change);
                requestEntries.setSelectedItem(change);
                JOptionPane.showMessageDialog(null, "Field Successfully Changed!", "Success!",
                        JOptionPane.PLAIN_MESSAGE);
            } catch (NameAlreadyExists e) {
                JOptionPane.showMessageDialog(null, "Name already exists!", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    //EFFECTS: Checks if the name exists in ListOfRequests. In addition, if the request is a Theorem then it checks if
    //         it is in ListOfTheorem and if it is an Equation then it checks if the name is in ListOfEquation.
    private void checkIfNameExistsRequest(Request request, String change) throws NameAlreadyExists {
        listOfRequests.changeRequestNameAndCheckExistence(request, change);
        if (request.isItATheorem()) {
            if (listOfTheorems.doesTheoremExist(change) != -1) {
                throw new NameAlreadyExists();
            }
        } else {
            if (listOfEquations.doesEquationExist(change) != -1) {
                throw new NameAlreadyExists();
            }
        }
    }

    //EFFECTS: prompts the user if they would like to delete the request and returns true if the value is yes.
    private Boolean areYouSureYouWantToDeleteRequest() {
        String[] buttonTexts = {"Yes", "No"};
        int userDecision = JOptionPane.showOptionDialog(null, "Are you sure you want to delete "
                        + "this request entry?", "Delete Request?", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,null, buttonTexts,buttonTexts[0]);
        return userDecision == 0;
    }

    //EFFECTS: Checks if the request is completed before submitting the entry to its designated library. If it is not
    //         complete then it prompts the user with another prompt.
    private void submitRequestPrompt() {
        Request request = listOfRequests.getRequest(requestEntries.getSelectedIndex());
        if (request.getEstimatedCompletion() != 100) {
            if (submitIfNotCompletePrompt()) {
                submitRequestAction(request);
            }
        } else {
            submitRequestAction(request);
        }
    }

    //EFFECTS: Removes the request from listOfRequests and adds the entry to the designated library.
    //         Returns you back to the main menu if this is the last entry in ListOfRequests.
    private void submitRequestAction(Request request) {
        requestEntries.removeItem(request.getName());
        convertingRequest(request);
        JOptionPane.showMessageDialog(null, "Request submitted to main library!!!",
                "Success!", JOptionPane.PLAIN_MESSAGE);
        if (listOfRequests.isListEmpty()) {
            JOptionPane.showMessageDialog(null, "There are no more requests, returning to "
                    + "Main Menu!", "Thank you for clearing the requests!", JOptionPane.PLAIN_MESSAGE);
            requestToMainMenu();
        }
    }

    //EFFECTS: Asks the user if they are willing to submit the request even though it is not complete. Returns true if
    //         the user selects "yes".
    private boolean submitIfNotCompletePrompt() {
        String[] buttonTexts = {"Submit", "Don't submit"};
        int userDecision = JOptionPane.showOptionDialog(null, "The request is currently not "
                        + "complete, would you still like to submit it?", "Warning!", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,null, buttonTexts,buttonTexts[0]);
        return userDecision == 0;
    }

    //EFFECTS: Prompts the user if they would like to make an equation or a theorem.
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

    //EFFECTS: Prompts the user to enter the fields of the requested theorem entry. If the name already exists in
    //         ListOfTheorems, then it tells the user the name is already taken.
    private void promptToMakeTheorem() {
        Request newRequest;
        String name = JOptionPane.showInputDialog("What is the name of the Theorem?");
        String theorem = JOptionPane.showInputDialog("What does the Theorem state?");
        String course = JOptionPane.showInputDialog("What course is this Theorem most applicable for? (You can leave "
                + "it blank if you are not sure");
        String explanation = JOptionPane.showInputDialog("You can leave any extra explanations if you so desire.");
        String proof = JOptionPane.showInputDialog("What is the derivation of this Theorem? "
                + "(You can leave this blank if unknown");
        if (name == null) {
            JOptionPane.showMessageDialog(null, "Name cannot be null!", "Error!", JOptionPane.PLAIN_MESSAGE);
        } else {
            try {
                newRequest = new Request(name, theorem, "Theorem", course, explanation, proof);
                listOfRequests.addRequestAndCheckExistence(newRequest);
                JOptionPane.showMessageDialog(null, "Request added!", "Success!", JOptionPane.PLAIN_MESSAGE);
            } catch (NameAlreadyExists e) {
                JOptionPane.showMessageDialog(null, "Name of theorem already exists!", "Error!",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //EFFECTS: Prompts the user to enter the fields of the requested equation entry. If the name already exists in
    //         ListOfEquations, then it tells the user the name is already taken.
    public void promptToMakeEquation() {
        Request newRequest;
        String name = JOptionPane.showInputDialog("What is the name of the Equation?");
        String theorem = JOptionPane.showInputDialog("What does the equation state?");
        String course = JOptionPane.showInputDialog("What course is this equation most applicable for? (You can leave "
                + "it blank if you are not sure");
        String explanation = JOptionPane.showInputDialog("You can leave any extra explanations if you so desire.");
        String proof = JOptionPane.showInputDialog("What is the derivation of this Equation? "
                + "(You can leave this blank if unknown");
        if (name == null) {
            JOptionPane.showMessageDialog(null, "Name cannot be null!", "Error!", JOptionPane.PLAIN_MESSAGE);
        } else {
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
}