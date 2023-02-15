package tests;

import model.Entry;
import model.Equation;

import model.exceptions.IndexNotThere;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class EquationTest {

    Entry testEntryRequest;

    Equation testEquation;

    @BeforeEach

    public void setup() {
        testEntryRequest = new Equation("name", "theorem", "course", "proof",
                "explanation");
        testEquation = new Equation("name", "theorem", "course", "proof",
                "explanation");
    }

    @Test
    public void getNameTest() {
        assertEquals("name", testEntryRequest.getName());
    }

    @Test
    public void getTheoremTest() {
        assertEquals("theorem", testEntryRequest.getTheorem());
    }

    @Test
    public void getCourseTest() {
        assertEquals("course", testEntryRequest.getCourse());
    }

    @Test
    public void getProofTest() {
        assertEquals("proof", testEntryRequest.getProof());
    }

    @Test
    public void getExplanationTest() {
        assertEquals("explanation", testEntryRequest.getExplanations());
    }

    @Test
    public void changeNameTest() {
        testEntryRequest.changeName("new name");
        assertEquals("new name", testEntryRequest.getName());
    }

    @Test
    public void changeTheoremTest() {
        testEntryRequest.changeTheorem("new theorem");
        assertEquals("new theorem", testEntryRequest.getTheorem());
    }

    @Test
    public void changeCourseTest() {
        testEntryRequest.changeCourse("new course");
        assertEquals("new course", testEntryRequest.getCourse());
    }

    @Test
    public void changeProofTest() {
        testEntryRequest.changeProof("new proof blah blah");
        assertEquals("new proof blah blah", testEntryRequest.getProof());
    }

    @Test
    public void changeExplanationTest() {
        testEntryRequest.changeExplanation("this is something");
        assertEquals("this is something", testEntryRequest.getExplanations());
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
    public void showNumberOfPracticeProblemsTest() throws IndexNotThere {
        testEquation.addPracticeProblem("question1", "answer1");
        testEquation.addPracticeProblem("question1", "answer1");
        testEquation.addPracticeProblem("question1", "answer1");
        assertEquals(testEquation.showNumberOfPracticeProblems(),
                "Practice Problem 1\nPractice Problem 2\nPractice Problem 3\n");
    }

    @Test
    public void doesPracticeProblemExist() {
        testEquation.addPracticeProblem("question1", "answer1");

        assertTrue(testEquation.doesPracticeProblemExist(String.valueOf(1)));
        assertTrue(testEquation.doesPracticeProblemExist(String.valueOf(2)));
        assertFalse(testEquation.doesPracticeProblemExist(String.valueOf(3)));
    }

}
