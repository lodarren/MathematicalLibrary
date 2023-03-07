import model.Entry;
import model.Equation;

import model.exceptions.IndexNotThere;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class EquationTest {
    Entry testEntry;
    Equation testEquation;

    @BeforeEach
    public void setup() {
        testEntry = new Equation("name", "theorem", "course", "proof",
                "explanation");
        testEquation = new Equation("name", "theorem", "course", "proof",
                "explanation");
    }

    @Test
    public void getNameTest() {
        assertEquals("name", testEntry.getName());
    }

    @Test
    public void getTheoremTest() {
        assertEquals("theorem", testEntry.getTheorem());
    }

    @Test
    public void getCourseTest() {
        assertEquals("course", testEntry.getCourse());
    }

    @Test
    public void getProofTest() {
        assertEquals("proof", testEntry.getProof());
    }

    @Test
    public void getExplanationTest() {
        assertEquals("explanation", testEntry.getExplanations());
    }

    @Test
    public void changeNameTest() {
        testEntry.changeName("new name");
        assertEquals("new name", testEntry.getName());
    }

    @Test
    public void changeTheoremTest() {
        testEntry.changeTheorem("new theorem");
        assertEquals("new theorem", testEntry.getTheorem());
    }

    @Test
    public void changeCourseTest() {
        testEntry.changeCourse("new course");
        assertEquals("new course", testEntry.getCourse());
    }

    @Test
    public void changeProofTest() {
        testEntry.changeProof("new proof blah blah");
        assertEquals("new proof blah blah", testEntry.getProof());
    }

    @Test
    public void changeExplanationTest() {
        testEntry.changeExplanation("this is something");
        assertEquals("this is something", testEntry.getExplanations());
    }


    @Test
    public void removePracticeProblemsTest() {
        testEquation.addPracticeProblem("question1", "answer1");
        testEquation.addPracticeProblem("question2", "answer2");
        testEquation.addPracticeProblem("question3", "answer3");
        testEquation.removePracticeProblem(1);
        assertEquals("question3", testEquation.getThePracticeProblem(1));
        assertEquals("answer3", testEquation.getThePracticeProblemAnswer(1));
    }

    @Test
    public void changePracticeProblemsTest() {
        testEquation.addPracticeProblem("question1", "answer1");
        testEquation.addPracticeProblem("question2", "answer2");
        testEquation.changePracticeProblem("Changed Question 1", "Changed Answer 1", 1);
        testEquation.changePracticeProblem("Changed Question 2", "Changed Answer 2", 0);
        assertEquals("Changed Question 1", testEquation.getThePracticeProblem(1));
        assertEquals("Changed Question 2", testEquation.getThePracticeProblem(0));
        assertEquals("Changed Answer 1", testEquation.getThePracticeProblemAnswer(1));
        assertEquals("Changed Answer 2", testEquation.getThePracticeProblemAnswer(0));
    }

    @Test
    public void getPracticeProblemsTest() {
        ArrayList<String> mockPracticeProblemList;
        ArrayList<String> mockPracticeProblemAnswerList;
        mockPracticeProblemList = new ArrayList<>();
        mockPracticeProblemAnswerList = new ArrayList<>();
        mockPracticeProblemList.add("question1");
        mockPracticeProblemAnswerList.add("answer1");
        assertTrue(testEquation.getPracticeProblemsRaw().isEmpty());
        assertTrue(testEquation.getPracticeProblemsAnswerRaw().isEmpty());
        testEquation.addPracticeProblem("question1", "answer1");
        assertEquals(mockPracticeProblemList, testEquation.getPracticeProblemsRaw());
        assertEquals(mockPracticeProblemAnswerList, testEquation.getPracticeProblemsAnswerRaw());
    }

    @Test
    public void showNumberOfPracticeProblemsTest(){
        testEquation.addPracticeProblem("question1", "answer1");
        testEquation.addPracticeProblem("question1", "answer1");
        testEquation.addPracticeProblem("question1", "answer1");
        try {
            assertEquals(testEquation.showNumberOfPracticeProblems(),
                    "Practice Problem 1\nPractice Problem 2\nPractice Problem 3\n");
        } catch (IndexNotThere e) {
            fail();
        }
    }

    @Test
    public void showNumberOfPracticeProblemsFailTest(){
        try {
            assertEquals(testEquation.showNumberOfPracticeProblems(),
                    "Practice Problem 1\nPractice Problem 2\nPractice Problem 3\n");
            fail();
        } catch (IndexNotThere e) {
            //Pass!
        }
    }

    @Test
    public void doesPracticeProblemExist() {
        testEquation.addPracticeProblem("question1", "answer1");
        assertTrue(testEquation.doesPracticeProblemExist(String.valueOf(1)));
        assertTrue(testEquation.doesPracticeProblemExist(String.valueOf(2)));
        assertFalse(testEquation.doesPracticeProblemExist(String.valueOf(3)));
    }

    @Test
    void viewEquationTest() {
        assertEquals("\nName: name\nStatement: theorem\nCourse this is most relevant to: course\n"
               + "Description: explanation\nDerivation: proof\n", testEquation.viewEquation());
    }

    @Test
    void viewNumberOfProblems(){
        assertEquals(0, testEquation.numberOfPracticeProblems());
        assertEquals(0, testEquation.numberOfAnswers());
        testEquation.addPracticeProblem("A", "A");
        assertEquals(1, testEquation.numberOfPracticeProblems());
        assertEquals(1, testEquation.numberOfAnswers());
    }

    @Test
    void addAllThePracticeProblemsAndAnswersTest(){
        ArrayList<String> problems;
        ArrayList<String> answers;
        problems = new ArrayList<>();
        answers = new ArrayList<>();
        testEquation.addAllPracticeAndAnswers(problems, answers);
        assertEquals(0, testEquation.numberOfPracticeProblems());
        assertEquals(0, testEquation.numberOfAnswers());
        problems.add("A");
        problems.add("B");
        answers.add("C");
        answers.add("D");
        testEquation.addAllPracticeAndAnswers(problems, answers);
        assertEquals(2, testEquation.numberOfPracticeProblems());
        assertEquals(2, testEquation.numberOfAnswers());
    }


}
