package tests;

import model.Entry;
import model.entryTypes.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EquationTest {

    Entry testEntryRequest;

    Equation testRequest;

    @BeforeEach

    public void setup() {
        testEntryRequest = new Equation("name", "theorem", "course", "proof",
                "explaination");
        testRequest = new Equation("name", "theorem", "course", "proof",
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
        testRequest.addPracticeProblem("question1", "answer1");
        assertEquals("Question: question1\nAnswer: answer1", testRequest.getPracticeProblem(0));
        assertEquals("question1", testRequest.getThePracticeProblem(0));
        assertEquals("answer1", testRequest.getThePracticeProblemAnswer(0));
        testRequest.addPracticeProblem("question2", "answer3");
        assertEquals("Question: question2\nAnswer: answer3", testRequest.getPracticeProblem(1));
        assertEquals("question2", testRequest.getThePracticeProblem(1));
        assertEquals("answer3", testRequest.getThePracticeProblemAnswer(1));
    }

    @Test
    public void removePracticeProblemsTest() {
        testRequest.addPracticeProblem("question1", "answer1");
        testRequest.addPracticeProblem("question2", "answer2");
        testRequest.addPracticeProblem("question3", "answer3");
        testRequest.removePracticeProblem(1);
        assertEquals("question3", testRequest.getThePracticeProblem(1));
        assertEquals("answer3", testRequest.getThePracticeProblemAnswer(1));
    }

    @Test
    public void changePracticeProblemsTest() {
        testRequest.addPracticeProblem("question1", "answer1");
        testRequest.addPracticeProblem("question2", "answer2");
        testRequest.changePracticeProblem("Changed Question 1", "Changed Answer 1", 1);
        testRequest.changePracticeProblem("Changed Question 2", "Changed Answer 2", 0);
        assertEquals("Changed Question 1", testRequest.getThePracticeProblem(1));
        assertEquals("Changed Question 2", testRequest.getThePracticeProblem(0));
        assertEquals("Changed Answer 1", testRequest.getThePracticeProblemAnswer(1));
        assertEquals("Changed Answer 2", testRequest.getThePracticeProblemAnswer(0));
    }

    @Test
    public void changePracticeProblemsProblemVoidTest() {
        testRequest.addPracticeProblem("question1", "answer1");
        testRequest.addPracticeProblem("question2", "answer2");
        testRequest.changePracticeProblem("", "Changed Answer 1", 1);
        testRequest.changePracticeProblem("Changed Question 2", "Changed Answer 2", 0);
        assertEquals("question2", testRequest.getThePracticeProblem(1));
        assertEquals("Changed Question 2", testRequest.getThePracticeProblem(0));
        assertEquals("Changed Answer 1", testRequest.getThePracticeProblemAnswer(1));
        assertEquals("Changed Answer 2", testRequest.getThePracticeProblemAnswer(0));
    }

    @Test
    public void changePracticeProblemsProblemAnswerVoidTest() {
        testRequest.addPracticeProblem("question1", "answer1");
        testRequest.addPracticeProblem("question2", "answer2");
        testRequest.changePracticeProblem("Changed Question 1", "" , 1);
        testRequest.changePracticeProblem("Changed Question 2", "Changed Answer 2", 0);
        assertEquals("Changed Question 1", testRequest.getThePracticeProblem(1));
        assertEquals("Changed Question 2", testRequest.getThePracticeProblem(0));
        assertEquals("answer2", testRequest.getThePracticeProblemAnswer(1));
        assertEquals("Changed Answer 2", testRequest.getThePracticeProblemAnswer(0));
    }

    @Test
    public void changePracticeProblemsBothVoidTest() {
        testRequest.addPracticeProblem("question1", "answer1");
        testRequest.addPracticeProblem("question2", "answer2");
        testRequest.changePracticeProblem("", "" , 1);
        testRequest.changePracticeProblem("Changed Question 2", "Changed Answer 2", 0);
        assertEquals("question2", testRequest.getThePracticeProblem(1));
        assertEquals("Changed Question 2", testRequest.getThePracticeProblem(0));
        assertEquals("answer2", testRequest.getThePracticeProblemAnswer(1));
        assertEquals("Changed Answer 2", testRequest.getThePracticeProblemAnswer(0));
    }

    @Test
    public void getPracticeProblemsTest() {
        ArrayList<String> mockPracticeProblemList;
        ArrayList<String> mockPracticeProblemAnswerList;
        mockPracticeProblemList = new ArrayList<>();
        mockPracticeProblemAnswerList = new ArrayList<>();
        mockPracticeProblemList.add("question1");
        mockPracticeProblemAnswerList.add("answer1");

        assertTrue(testRequest.getPracticeProblemsRaw().isEmpty());
        assertTrue(testRequest.getPracticeProblemsAnswerRaw().isEmpty());

        testRequest.addPracticeProblem("question1", "answer1");
        assertEquals(mockPracticeProblemList, testRequest.getPracticeProblemsRaw());
        assertEquals(mockPracticeProblemAnswerList, testRequest.getPracticeProblemsAnswerRaw());
    }








}
