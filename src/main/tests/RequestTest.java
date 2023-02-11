package tests;

import model.Entry;
import model.entryTypes.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RequestTest {

    Entry testEntryRequest;

    Request testRequest;
    Request testRequestLetters;

    @BeforeEach

    public void setup() {
        testEntryRequest = new Request("name", "theorem", "Theorem", "course",
                "proof", "explaination");

        testRequest = new Request("name", "theorem", "Theorem", "course",
                "proof", "explaination");

        testRequestLetters = new Request ("A","B", "Theorem", "C", "D", "E");
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
    public void getTypeTest() {
        assertEquals("Theorem", testRequest.getType());
        testRequest.changeType("Equation");
        assertEquals("Equation", testRequest.getType());

    }

    @Test
    public void isItAnEquationTest() {
        assertFalse(testRequest.isItAnEquation());
        testRequest.changeType("Equation");
        assertTrue(testRequest.isItAnEquation());
    }

    @Test
    public void isItATheoremTest() {
        assertTrue(testRequest.isItATheorem());
        testRequest.changeType("Equation");
        assertFalse(testRequest.isItATheorem());
    }

    @Test
    public void estimatedCompletionTest() {
        assertEquals(0,testRequest.getEstimatedCompletion());
        testRequest.updateEstimatedCompletion(76);
        assertEquals(76, testRequest.getEstimatedCompletion());
    }

    @Test
    public void isRequestCompletedTest() {
        assertFalse(testRequest.isRequestCompleted());
        testRequest.updateEstimatedCompletion(99);
        assertFalse(testRequest.isRequestCompleted());
        testRequest.updateEstimatedCompletion(100);
        assertTrue(testRequest.isRequestCompleted());
    }

    @Test
    public void requestToTheoremTest() {
        Theorem compareTheorem;
        compareTheorem = new Theorem("name", "theorem", "course",
                "proof", "explaination");
        assertEquals(compareTheorem, testRequestLetters.requestToTheorem());

    }

    @Test
    public void requestToEquationTest() {
        Equation compareEquation;
        compareEquation = new Equation("A", "B", "C",
                "D", "E");
        assertEquals(compareEquation, testRequestLetters.requestToTheorem());

    }

    //Fix these two
    //Start on library
    //Proofread most of the methods and stuff
    //Double check styling
    //UI



}
