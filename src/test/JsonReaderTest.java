import model.ListOfEquations;
import model.ListOfRequests;
import model.ListOfTheorems;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest{
    @Test
    void readerNoListOfRequestTest() {
        JsonReader reader = new JsonReader("./data/doesnotexist.json");
        try {
            ListOfRequests listOfRequests = reader.readRequests();
            fail("IOException expcted");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void readerNoListOfTheoremTest() {
        JsonReader reader = new JsonReader("./data/doesnotexist.json");
        try {
            ListOfTheorems listOfTheorems = reader.readTheorems();
            fail("IOException expcted");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void readerNoListOfEquationTest() {
        JsonReader reader = new JsonReader("./data/doesnotexist.json");
        try {
            ListOfEquations listOfEquations = reader.readEquations();
            fail("IOException expcted");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testReaderEmptyListOfRequestTest() {
        JsonReader reader = new JsonReader("./data/testEmptyListOfRequest.json");
        try {
            ListOfRequests listOfRequests = reader.readRequests();
            assertTrue(listOfRequests.isListEmpty());
        } catch (IOException e) {
            fail("File couldn't be read!");
        }
    }

    @Test
    void testReaderEmptyListOfTheoremTest() {
        JsonReader reader = new JsonReader("./data/testEmptyListOfTheorem.json");
        try {
            ListOfTheorems listOfTheorems = reader.readTheorems();
            assertTrue(listOfTheorems.isListEmpty());
        } catch (IOException e) {
            fail("File couldn't be read!");
        }
    }

    @Test
    void testReaderEmptyListOfEquationTest() {
        JsonReader reader = new JsonReader("./data/testEmptyListOfEquation.json");
        try {
            ListOfEquations listOfEquations = reader.readEquations();
            assertTrue(listOfEquations.isListEmpty());
        } catch (IOException e) {
            fail("File couldn't be read!");
        }
    }

    @Test
    void testReaderNotEmptyRequests() {
        JsonReader reader = new JsonReader("./data/testNotEmptyListOfRequest.json");
        try {
            ListOfRequests listOfRequests = reader.readRequests();
            assertEquals(2, listOfRequests.numberOfRequests());
            checkRequest("Riemann Hypothesis",  "Theorem",  "Something about prime numbers",
                    "???", "I would like to know this too. ", "Relationship between the zeta "
                            + "function and the prime numbers",
                    0, listOfRequests.getRequest(0));
            checkRequest("The REAL law of cosines",  "Equation",  "c^2 = a^2 + b^2 - 2abcos(C)",
                    "Grade 11", "Use geometry.", "Relationship between sides of a triangle that "
                            +"are not always right.",
                    100, listOfRequests.getRequest(1));

        } catch (IOException e) {
            fail("File couldn't be read!");
        }
    }

    @Test
    void testReaderNotEmptyTheorems() {
        JsonReader reader = new JsonReader("./data/testNotEmptyListOfTheorem.json");
        try {
            ListOfTheorems listOfTheorems = reader.readTheorems();
            assertEquals(2, listOfTheorems.numberOfTheorems());
            checkTheorem("Green's theorem",  "The grass is green",
                    "Calculus 4", "Grass = green", "The grass looks green to me",
                    listOfTheorems.getTheorem(0));
            checkTheorem("Pythagorean theorem",  "a^2+b^2=c^2",  "Pre-school",
                    "Proof by contradiction", "There is a relationship between the sides of a triangle",
                    listOfTheorems.getTheorem(1));
        } catch (IOException e) {
            fail("File couldn't be read!");
        }
    }

    @Test
    void testReaderNotEmptyEquations() {
        JsonReader reader = new JsonReader("./data/testNotEmptyListOfEquation.json");
        try {
            ListOfEquations listOfEquations = reader.readEquations();
            assertEquals(2, listOfEquations.numberOfEquations());
            checkEquation("Fundamental theorem of engineering",  "sin(x) = x",
                "All of university", "sin(0) = 0 QED", "The most useful formula in engineering",
                2, listOfEquations.getEquation(0));
            checkEquation("The cosine law",  "cos(x) = 1",  "Grade school",
                "cos(0) = 1", "All cosines are equal to 1. ", 0,
                listOfEquations.getEquation(1));
            ArrayList<String> practiceProblemTest;
            practiceProblemTest = listOfEquations.getEquation(0).getPracticeProblemsRaw();
            ArrayList<String> practiceProblemAnswerTest;
            practiceProblemAnswerTest = listOfEquations.getEquation(0).getPracticeProblemsAnswerRaw();
            assertEquals("What is sine of 11", practiceProblemTest.get(0));
            assertEquals("What is sine of 30", practiceProblemTest.get(1));
            assertEquals("11", practiceProblemAnswerTest.get(0));
            assertEquals("30", practiceProblemAnswerTest.get(1));
        } catch (IOException e) {
        fail("File couldn't be read!");
        }
    }
}
