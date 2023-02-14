package tests;

import model.Entry;
import model.entryTypes.*;

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
                "explaination");
        testEquation = new Equation("name", "theorem", "course", "proof",
                "explaination");
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
    public void getExplainationTest() {
        assertEquals("explaination", testEntryRequest.getExplainations());
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
    public void changeExplainationTest() {
        testEntryRequest.changeExplaination("this is something");
        assertEquals("this is something", testEntryRequest.getExplainations());
    }

    @Test
    public void addCommentTest() {
        testEntryRequest.addComment("comment1");
        testEntryRequest.addComment("comment2");
        assertEquals("comment1", testEntryRequest.getComment(0));
        assertEquals("comment2", testEntryRequest.getComment(1));
    }

    @Test
    public void removeCommentTest() {
        testEntryRequest.addComment("comment1");
        testEntryRequest.removeComment(0);
        testEntryRequest.addComment("newcomment1");
        assertEquals("newcomment1", testEntryRequest.getComment(0));
    }

    @Test
    public void addPracticeProblemTest() {
        testEquation.addPracticeProblem("question1", "answer1");
        assertEquals("Question: question1\nAnswer: answer1", testEquation.getPracticeProblem(0));
        assertEquals("question1", testEquation.getThePracticeProblem(0));
        assertEquals("answer1", testEquation.getThePracticeProblemAnswer(0));
        testEquation.addPracticeProblem("question2", "answer3");
        assertEquals("Question: question2\nAnswer: answer3", testEquation.getPracticeProblem(1));
        assertEquals("question2", testEquation.getThePracticeProblem(1));
        assertEquals("answer3", testEquation.getThePracticeProblemAnswer(1));
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
    public void changePracticeProblemsProblemVoidTest() {
        testEquation.addPracticeProblem("question1", "answer1");
        testEquation.addPracticeProblem("question2", "answer2");
        testEquation.changePracticeProblem("", "Changed Answer 1", 1);
        testEquation.changePracticeProblem("Changed Question 2", "Changed Answer 2", 0);
        assertEquals("question2", testEquation.getThePracticeProblem(1));
        assertEquals("Changed Question 2", testEquation.getThePracticeProblem(0));
        assertEquals("Changed Answer 1", testEquation.getThePracticeProblemAnswer(1));
        assertEquals("Changed Answer 2", testEquation.getThePracticeProblemAnswer(0));
    }

    @Test
    public void changePracticeProblemsProblemAnswerVoidTest() {
        testEquation.addPracticeProblem("question1", "answer1");
        testEquation.addPracticeProblem("question2", "answer2");
        testEquation.changePracticeProblem("Changed Question 1", "" , 1);
        testEquation.changePracticeProblem("Changed Question 2", "Changed Answer 2", 0);
        assertEquals("Changed Question 1", testEquation.getThePracticeProblem(1));
        assertEquals("Changed Question 2", testEquation.getThePracticeProblem(0));
        assertEquals("answer2", testEquation.getThePracticeProblemAnswer(1));
        assertEquals("Changed Answer 2", testEquation.getThePracticeProblemAnswer(0));
    }

    @Test
    public void changePracticeProblemsBothVoidTest() {
        testEquation.addPracticeProblem("question1", "answer1");
        testEquation.addPracticeProblem("question2", "answer2");
        testEquation.changePracticeProblem("", "" , 1);
        testEquation.changePracticeProblem("Changed Question 2", "Changed Answer 2", 0);
        assertEquals("question2", testEquation.getThePracticeProblem(1));
        assertEquals("Changed Question 2", testEquation.getThePracticeProblem(0));
        assertEquals("answer2", testEquation.getThePracticeProblemAnswer(1));
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
    public void showNumberOfPracticeProblemsTest() {
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

}
