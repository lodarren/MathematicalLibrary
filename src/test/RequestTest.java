import model.Entry;
import model.Equation;
import model.Request;
import model.Theorem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RequestTest {
    Entry testEntryRequest;
    Request testRequest;
    Request testRequestLetters;

    @BeforeEach
    void setup() {
        testEntryRequest = new Request("name", "theorem", "Theorem", "course",
                "proof", "explanation");

        testRequest = new Request("name", "theorem", "Theorem", "course",
                "proof", "explanation");

        testRequestLetters = new Request("A","B", "Equation", "C", "D", "E");
    }

    @Test
    void getNameTest() {
        assertEquals("name", testEntryRequest.getName());
    }

    @Test
    void getTheoremTest() {
        assertEquals("theorem", testEntryRequest.getTheorem());
    }

    @Test
    void getCourseTest() {
        assertEquals("course", testEntryRequest.getCourse());
    }

    @Test
    void getProofTest() {
        assertEquals("proof", testEntryRequest.getProof());
    }

    @Test
    void getExplanationTest() {
        assertEquals("explanation", testEntryRequest.getExplanations());
    }

    @Test
    void changeNameTest() {
        testEntryRequest.changeName("new name");
        assertEquals("new name", testEntryRequest.getName());
    }

    @Test
    void changeTheoremTest() {
        testEntryRequest.changeTheorem("new theorem");
        assertEquals("new theorem", testEntryRequest.getTheorem());
    }

    @Test
    void changeCourseTest() {
        testEntryRequest.changeCourse("new course");
        assertEquals("new course", testEntryRequest.getCourse());
    }

    @Test
    void changeProofTest() {
        testEntryRequest.changeProof("new proof blah blah");
        assertEquals("new proof blah blah", testEntryRequest.getProof());
    }

    @Test
    void changeExplanationTest() {
        testEntryRequest.changeExplanation("this is something");
        assertEquals("this is something", testEntryRequest.getExplanations());
    }

    @Test
    void isItATheoremTest() {
        assertTrue(testRequest.isItATheorem());
        assertFalse(testRequestLetters.isItATheorem());
    }

    @Test
    void isItAnEquationTest() {
        assertFalse(testRequest.isItAnEquation());
        assertTrue(testRequestLetters.isItAnEquation());
    }

    @Test
    void isRequestCompletedTest() {
        assertFalse(testRequest.isRequestCompleted());
        testRequest.updateEstimatedCompletion("99");
        assertFalse(testRequest.isRequestCompleted());
        testRequest.updateEstimatedCompletion("100");
        assertTrue(testRequest.isRequestCompleted());
    }


    @Test
    void requestToTheoremTest() {
        Theorem compareTheorem;
        Theorem convertedTheorem = testRequestLetters.requestToTheorem();
        compareTheorem = new Theorem("A", "B", "C",
                "D", "E");
        assertEquals(convertedTheorem.getName(), compareTheorem.getName());
        assertEquals(convertedTheorem.getTheorem(), compareTheorem.getTheorem());
        assertEquals(convertedTheorem.getCourse(), compareTheorem.getCourse());
        assertEquals(convertedTheorem.getProof(), compareTheorem.getProof());
        assertEquals(convertedTheorem.getExplanations(), compareTheorem.getExplanations());

    }

    @Test
    void requestToEquationTest() {
        Equation compareEquation;
        Equation convertedEquation = testRequestLetters.requestToEquation();
        compareEquation = new Equation("A", "B", "C",
                "D", "E");
        assertEquals(convertedEquation.getName(), compareEquation.getName());
        assertEquals(convertedEquation.getTheorem(), compareEquation.getTheorem());
        assertEquals(convertedEquation.getCourse(), compareEquation.getCourse());
        assertEquals(convertedEquation.getProof(), compareEquation.getProof());
        assertEquals(convertedEquation.getExplanations(), compareEquation.getExplanations());
    }

    @Test
    void viewRequestTest() {
        assertEquals("Name: name\nType: Theorem\nTheorem: theorem\nCourse this is most relevant to: course\n"
                + "Description: explanation\nCompletion: 0\nProof: proof\n", testRequest.viewRequest());
    }
}
