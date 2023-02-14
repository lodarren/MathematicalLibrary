package ui;

import model.ListOfEquations;
import model.ListOfRequests;
import model.ListOfTheorems;
import model.entryTypes.Equation;
import model.entryTypes.Request;
import model.entryTypes.Theorem;
import model.exceptions.IndexNotThere;
import model.exceptions.NameAlreadyExists;
import model.exceptions.NotValidCompletion;

import java.util.Scanner;

// Represents the full list of finished mathematical entries in the library, can either contain Equations or Theorems,
// and has a section for Requests
public class Library {
    ListOfTheorems listOfTheorems;
    ListOfEquations listOfEquations;
    ListOfRequests listOfRequests;

    Theorem mockEntry;
    Theorem mockEntry2;
    Equation mockEquation1;
    Equation mockEquation2;
    Request mockRequest1;
    Request mockRequest2;

    private Scanner input;

    public Library() {
        runLibrary();
    }

    private void runLibrary() {
        boolean running = true;
        String command = null;

        listOfEquations = new ListOfEquations();
        listOfTheorems = new ListOfTheorems();
        listOfRequests = new ListOfRequests();

        // MOCK lists, to be removed later
        mockEntry = new Theorem("a", "B", "C", "D", "E");
        mockEntry2 = new Theorem("a1", "B", "C", "D", "E");
        mockEquation1 = new Equation("a", "B", "C", "D", "E");
        mockEquation2 = new Equation("a1", "B", "C", "D", "E");
        mockRequest1 = new Request("a", "Theorem", "C", "D", "E", "F");
        mockRequest2 = new Request("a1", "Equation", "C", "D", "E", "F");

        mockEquation1.addPracticeProblem("what is sine of x", "its x you dummy");
        listOfTheorems.addTheorem(mockEntry);
        listOfTheorems.addTheorem(mockEntry2);
        listOfEquations.addEquation(mockEquation1);
        listOfEquations.addEquation(mockEquation2);
        listOfRequests.addRequest(mockRequest1);
        listOfRequests.addRequest(mockRequest2);


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
        System.out.print("Welcome to the Library! \n");
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
        System.out.print(listOfTheorems.printAllTheorems() + "\n");
        System.out.print("Type in the name of the entry you want to view! \n");

    }

    public void theoremMakeSelection(String command) {
        if (listOfTheorems.doesTheoremExist(command) != -1) {
            listOfTheorems.getTheorem(listOfTheorems.doesTheoremExist(command)).viewEntry();
            System.out.print("\nPress y if you want to display extra info. Press another key to return to the main menu.\n");
            if (yesOrNo()) {
                listOfTheorems.getTheorem(listOfTheorems.doesTheoremExist(command)).viewTheorem();
                System.out.print("\nPress y if you want to change this entry. Press another key to return to the main menu.\n");
                if (yesOrNo()) {
                    System.out.print("Press 1 to change the name of the entry.\nPress 2 to change the theorem.\nPress 3" +
                            " to change the course it is most useful for.\nPress 4 to change the description.\nPress 5 to change" +
                            "the Proof.\nPress 6 to delete this entry.");
                    try {
                        doYouWantToChangeTheTheorem(listOfTheorems.getTheorem(listOfTheorems.doesTheoremExist(command)));
                    } catch (NameAlreadyExists e) {
                        System.out.print("Name already exists!");
                    }
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


    public void doYouWantToChangeTheTheorem(Theorem theorem) throws NameAlreadyExists {
        String command = input.next();
        if (command.equals("1")) {
            System.out.print("What were you planning to change the field to? Please type it in below.\n");
            String name = input.next();
            if (listOfTheorems.checkIfTheoremExists(name)) {
                throw new NameAlreadyExists();
            }
            theorem.changeName(name);
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
            listOfTheorems.removeTheorem(theorem);
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
        System.out.print(listOfEquations.printAllEquations() + "\n");
        System.out.print("Type in the name of the entry you want to view! \n");
    }

    private void equationMakeSelection(String command) {
        if (listOfEquations.doesEquationExist(command) != -1) {
            listOfEquations.getEquation(listOfEquations.doesEquationExist(command)).viewEntry();
            System.out.print("\nPress p if you want to view the practice problems. Press c to change the entry.\n");
            viewPracticeOrChangeEntry(command);
        } else {
            System.out.println("That entry doesn't exist!");
        }
    }

    private void viewPracticeOrChangeEntry(String previousCommand) {
        String nextCommand = input.next();
        if (nextCommand.equalsIgnoreCase("p")) {
            showPracticeProblems(listOfEquations.getEquation(listOfEquations.doesEquationExist(previousCommand)));
        } else if (nextCommand.equalsIgnoreCase("c")) {
            System.out.print("Press 1 to change the name of the entry.\nPress 2 to change the theorem.\nPress 3" +
                    " to change the course it is most useful for.\nPress 4 to change the description.\nPress 5 to change" +
                    "the Proof.\nPress 6 to delete specific practice problems.\nPress 7 to delete this entry.");
            try {
                changeEquationEntry(listOfEquations.getEquation(listOfEquations.doesEquationExist(previousCommand)));
            } catch (NameAlreadyExists e) {
                System.out.print("Name Already exists!");
            }
        } else {
            System.out.print("\nNot a valid entry!\n");
        }
    }

    public void changeEquationEntry(Equation equation) throws NameAlreadyExists {
        String command = input.next();
        if (command.equals("1")) {
            System.out.print("What were you planning to change the field to? Please type it in below.\n");
            String newName = input.next();
            if (listOfEquations.checkIfEquationExists(newName)) {
                throw new NameAlreadyExists();
            }
            equation.changeName(newName);
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
            listOfEquations.removeEquation(equation);
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
        System.out.print("Welcome to the requests section!\nPress A to make a request.\nPress B to view made requests.\n");
        String command = input.next();

        if (command.equalsIgnoreCase("A")) {
            promptToMakeRequest();
        } else if (command.equalsIgnoreCase("B")) {
            viewAllRequests();
        } else {
            System.out.print("Not one of the options!\n");
        }
    }

    private void viewAllRequests() {
        System.out.print("Here are all the requests so far:\n");
        System.out.print(listOfRequests.printAllRequests() + "\n");
        System.out.print("If you would like to update any of the requests, press u, otherwise press another key to return.\n");
        String command = input.next();
        if (command.equals("u")) {
            promptToUpdateRequest();
        }
    }

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

    public void updateDeleteOrSubmitRequest(Request request) {
        System.out.print("Press u to update this request, press d to delete this request.\n");
        String newcommand = input.next();
        if (newcommand.equalsIgnoreCase("u")) {
            try {
                updateRequest(request);
            } catch (NameAlreadyExists e) {
                System.out.print("Name already exists!\n");
            }
        } else if (newcommand.equalsIgnoreCase("d")) {
            deleteRequestPrompt(request);
        } else {
            System.out.println("Not one of the following options!\n");
        }
    }

    public void updateRequest(Request request) throws NameAlreadyExists {
        String command;
        System.out.print("Press 1 to change the name of the entry.\nPress 2 to change the theorem.\nPress 3" +
                " to change the course it is most useful for.\nPress 4 to change the description.\nPress 5 to change" +
                "the Proof.\n Press 6 to update the estimated completion \nPress 7 to submit the entry to its designated library\n");
        command = input.next();
        if (command.equals("1")) {
            System.out.print("What were you planning to change the field to? Please type it in below.\n");
            String name = input.next();
            if (listOfRequests.checkIfRequestExists(name)) {
                throw new NameAlreadyExists();
            }
            request.changeName(name);
        } else if (command.equals("2")) {
            System.out.print("What were you planning to change the field to? Please type it in below.\n");
            request.changeTheorem(whatIsTheChange());
        } else if (command.equals("3")) {
            System.out.print("What were you planning to change the field to? Please type it in below.\n");
            request.changeCourse(whatIsTheChange());
        } else if (command.equals("4")) {
            System.out.print("What were you planning to change the field to? Please type it in below.\n");
            request.changeExplaination(whatIsTheChange());
        } else if (command.equals("5")) {
            System.out.print("What were you planning to change the field to? Please type it in below.\n");
            request.changeProof(whatIsTheChange());
        } else if (command.equals("6")) {
            System.out.print("What were you planning to change the field to? Please type it in below.\n");
            String newEstimatedCompletion = input.next();
            try {
                request.updateEstimatedCompletion(newEstimatedCompletion);
            } catch (NotValidCompletion e) {
                System.out.print("Not a value between 1 and 100!\n");
            }
        } else if (command.equals("7")) {
            prepareToSubmitRequest(request);
        } else {
            System.out.print("Not one of the options!");
        }

    }

    private void prepareToSubmitRequest(Request request) {
        if (checkToSubmitRequest(request)) {
            convertingRequest(request);
            System.out.print("Submitted to main library!");
        } else {
            System.out.print("Returning to main menu");
        }
    }

    private void convertingRequest(Request request) {
        if (request.getType().equals("Theorem")) {
            listOfTheorems.addTheorem(request.requestToTheorem());
        } else {
            listOfEquations.addEquation(request.requestToEquation());
        }
    }

    private Boolean checkToSubmitRequest(Request request) {
        if (request.getEstimatedCompletion() != 100) {
            System.out.print("This request is currently still not complete. Submit anyway (press y to accept)?\n");
            return yesOrNo();
        }
        return true;
    }


    public void deleteRequestPrompt(Request request) {
        System.out.print("You are about to delete this request. Press y to delete this request. Press any other key to go back. \n");
        if (yesOrNo()) {
            listOfRequests.removeRequest(request);
            System.out.print("Request removed!\n");
        } else {
            System.out.print("System remains in the request catalog. \n");
        }
    }

    public void promptToMakeRequest() {
        System.out.print("You are now making a request. What is this request for an equation or theorem?\n");
        String command = input.next();
        if (command.equalsIgnoreCase("theorem")) {
            try {
                userMakeTheoremRequest();
            } catch (NameAlreadyExists e) {
                System.out.print("Name already exists!\n");
            }
        } else if (command.equalsIgnoreCase("equation")) {
            try {
                userMakeEquationRequest();
            } catch (NameAlreadyExists e) {
                System.out.print("Name already exists!\n");
            }
        } else {
            System.out.print("Not one of the options!\n");
        }

    }

    public void userMakeTheoremRequest() throws NameAlreadyExists {
        Request newRequest;
        System.out.print("What is the name of this theorem?\n");
        String name = input.next();
        if (listOfTheorems.checkIfTheoremExists(name)) {
            throw new NameAlreadyExists();
        }
        System.out.print("What does this theorem state? (you can leave this blank if you are not sure)\n");
        String theorem = input.next();
        System.out.print("What course is this theorem most applicable for? (you can leave this blank if you are not sure) \n");
        String course = input.next();
        System.out.print("What does this theorem state? (you can leave this blank if you are not sure)\n");
        String explaination = input.next();
        System.out.print("What is the proof for this theorem? (you can leave this blank if you are not sure)\n");
        String proof = input.next();
        newRequest = new Request(name, theorem, "Theorem", course, explaination, proof);
        listOfRequests.addRequest(newRequest);
        System.out.print("Submitted request!\n");
    }

    public void userMakeEquationRequest() throws NameAlreadyExists {
        Request newRequest;
        System.out.print("What is the name of this Equation?\n");
        String name = input.next();
        if (listOfEquations.checkIfEquationExists(name)) {
            throw new NameAlreadyExists();
        }
        System.out.print("What does this Equation state? (you can leave this blank if you are not sure)\n");
        String theorem = input.next();
        System.out.print("What course is this Equation most applicable for? (you can leave this blank if you are not sure) \n");
        String course = input.next();
        System.out.print("What does this Equation state? (you can leave this blank if you are not sure)\n");
        String explaination = input.next();
        System.out.print("What is the derivation for this Equation? (you can leave this blank if you are not sure)\n");
        String proof = input.next();
        newRequest = new Request(name, theorem, "Equation", course, explaination, proof);
        listOfRequests.addRequest(newRequest);
        System.out.print("Submitted request!\n");
    }
}


    //Starting down here is where i have to make the new classes


//LEFT TO DO
// Proper documentation, Remove comments list, add requires methods etc. Double check styling, Check if Lists have to be in its own class
// Double check tests, I believe some of the ones involving print are not tested properly, fix \n typos, update the readme to reflect these changes.
//
//
//