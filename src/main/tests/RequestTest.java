package tests;

import model.Entry;
import model.entryTypes.*;

import model.exceptions.NotValidCompletion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

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
        try {
            testRequest.updateEstimatedCompletion("76");
            assertEquals(76, testRequest.getEstimatedCompletion());
        }
        catch (NotValidCompletion e) {
            fail();
        }
    }

    @Test
    public void isRequestCompletedTest() {
        try {
            assertFalse(testRequest.isRequestCompleted());
            testRequest.updateEstimatedCompletion("99");
            assertFalse(testRequest.isRequestCompleted());
            testRequest.updateEstimatedCompletion("100");
            assertTrue(testRequest.isRequestCompleted());
        }
        catch (NotValidCompletion e) {
            fail();
        }
    }

    @Test
    public void requestToTheoremTest() {
        Theorem compareTheorem;
        Theorem convertedTheorem = testRequestLetters.requestToTheorem();
        compareTheorem = new Theorem("A", "B", "C",
                "D", "E");


        assertEquals(convertedTheorem.getName(), compareTheorem.getName());
        assertEquals(convertedTheorem.getTheorem(), compareTheorem.getTheorem());
        assertEquals(convertedTheorem.getCourse(), compareTheorem.getCourse());
        assertEquals(convertedTheorem.getProof(), compareTheorem.getProof());
        assertEquals(convertedTheorem.getExplainations(), compareTheorem.getExplainations());

    }

    @Test
    public void requestToEquationTest() {
        Equation compareEquation;
        Equation convertedEquation = testRequestLetters.requestToEquation();
        compareEquation = new Equation("A", "B", "C",
                "D", "E");


        assertEquals(convertedEquation.getName(), compareEquation.getName());
        assertEquals(convertedEquation.getTheorem(), compareEquation.getTheorem());
        assertEquals(convertedEquation.getCourse(), compareEquation.getCourse());
        assertEquals(convertedEquation.getProof(), compareEquation.getProof());
        assertEquals(convertedEquation.getExplainations(), compareEquation.getExplainations());

    }

}
