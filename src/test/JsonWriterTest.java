import model.*;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {
    /*
            mockEntry = new Theorem("Green's theorem", "The grass is green",
                "Calculus 4", "Grass = green", "The grass looks green to me");
        mockEntry2 = new Theorem("Pythagorean theorem", "a^2+b^2=c^2", "Pre-school",
                "Proof by contradiction", "There is a relationship between the sides of a triangle");
        mockEquation1 = new Equation("Fundamental theorem of engineering", "sin(x) = x",
                "All of university", "sin(0) = 0 QED", "The most useful formula in engineering");
        mockEquation2 = new Equation("The cosine law", "cos(x) = 1", "Grade school",
                "cos(0) = 1", "All cosines are equal to 1. ");
        mockRequest1 = new Request("Riemann Hypothesis", "Something about prime numbers", "Theorem",
                "???", "I would like to know this too. ",
                "Relationship between the zeta function and the prime numbers");
        mockRequest2 = new Request("The REAL law of cosines", "c^2 = a^2 + b^2 - 2abcos(C)",
                "Equation", "Grade 11", "Use geometry.", "Relationship between sides of a "
                + "triangle that are not always right.");

        mockEquation1.addPracticeProblem("What is sine of 11", "11");
        mockEquation1.addPracticeProblem("What is sine of 30", "30");
     */

    @Test
    void testWriterTheoremInvalidFile() {
        try {
            Theorem mockEntry1 = new Theorem("Green's theorem", "The grass is green",
                    "Calculus 4", "Grass = green", "The grass looks green to me");
            Theorem mockEntry2= new Theorem("Pythagorean theorem", "a^2+b^2=c^2", "Pre-school",
                    "Proof by contradiction", "There is a relationship between the sides of a triangle");
            ListOfTheorems listOfTheorems = new ListOfTheorems();
            listOfTheorems.addTheorem(mockEntry1);
            listOfTheorems.addTheorem(mockEntry2);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEquationInvalidFile() {
        try {
            Equation mockEquation1 = new Equation("Fundamental theorem of engineering", "sin(x) = x",
                    "All of university", "sin(0) = 0 QED", "The most useful formula in "
                    + "engineering");
            Equation mockEquation2 = new Equation("The cosine law", "cos(x) = 1", "Grade school",
                    "cos(0) = 1", "All cosines are equal to 1. ");
            ListOfEquations listOfEquations = new ListOfEquations();
            listOfEquations.addEquation(mockEquation1);
            listOfEquations.addEquation(mockEquation2);
            mockEquation1.addPracticeProblem("What is sine of 11", "11");
            mockEquation1.addPracticeProblem("What is sine of 30", "30");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterRequestInvalidFile() {
        try {
            Request mockRequest1 = new Request("Riemann Hypothesis", "Something about prime numbers", "Theorem",
                    "???", "I would like to know this too. ",
                    "Relationship between the zeta function and the prime numbers");
            Request mockRequest2 = new Request("The REAL law of cosines", "c^2 = a^2 + b^2 - 2abcos(C)",
                    "Equation", "Grade 11", "Use geometry.", "Relationship between sides of a "
                    + "triangle that are not always right.");
            ListOfRequests listOfRequests = new ListOfRequests();
            listOfRequests.addRequest(mockRequest1);
            listOfRequests.addRequest(mockRequest2);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterTheoremEmptyWorkroom() {
        try {
            ListOfTheorems listOfTheorems = new ListOfTheorems();
            JsonWriter writer = new JsonWriter("./data/testEmptyListOfTheorem.json");
            writer.open();
            writer.writeListOfTheorem(listOfTheorems);
            writer.close();

            JsonReader reader = new JsonReader("./data/testEmptyListOfTheorem.json");
            listOfTheorems = reader.readTheorems();
            assertEquals(0, listOfTheorems.numberOfTheorems());

            writer.open();
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterEquationEmptyWorkroom() {
        try {
            ListOfEquations listOfEquations = new ListOfEquations();
            JsonWriter writer = new JsonWriter("./data/testEmptyListOfTheorem.json");
            writer.open();
            writer.writeListOfEquation(listOfEquations);
            writer.close();

            JsonReader reader = new JsonReader("./data/testEmptyListOfTheorem.json");
            listOfEquations = reader.readEquations();
            assertEquals(0, listOfEquations.numberOfEquations());

            writer.open();
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterRequestEmptyWorkroom() {
        try {
            ListOfRequests listOfRequests = new ListOfRequests();
            JsonWriter writer = new JsonWriter("./data/testEmptyListOfRequest.json");
            writer.open();
            writer.writeListOfRequests(listOfRequests);
            writer.close();

            JsonReader reader = new JsonReader("./data/testEmptyListOfRequest.json");
            listOfRequests = reader.readRequests();
            assertEquals(0, listOfRequests.numberOfRequests());

            writer.open();
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


    @Test
    void testWriterTheoremNotEmpty() {
        try {
            Theorem mockEntry1 = new Theorem("Green's theorem", "The grass is green",
                    "Calculus 4", "Grass = green", "The grass looks green to me");
            Theorem mockEntry2= new Theorem("Pythagorean theorem", "a^2+b^2=c^2", "Pre-school",
                    "Proof by contradiction", "There is a relationship between the sides of a triangle");
            ListOfTheorems listOfTheorems = new ListOfTheorems();
            listOfTheorems.addTheorem(mockEntry1);
            listOfTheorems.addTheorem(mockEntry2);
            JsonWriter writer = new JsonWriter("./data/testNotEmptyListOfTheorem.json");
            writer.open();
            writer.writeListOfTheorem(listOfTheorems);
            writer.close();

            JsonReader reader = new JsonReader("./data/testNotEmptyListOfTheorem.json");
            listOfTheorems = reader.readTheorems();
            assertEquals(listOfTheorems.numberOfTheorems(), 2);
            checkTheorem("Green's theorem",  "The grass is green",
                    "Calculus 4", "Grass = green", "The grass looks green to me",
                    listOfTheorems.getTheorem(0));
            checkTheorem("Pythagorean theorem",  "a^2+b^2=c^2",  "Pre-school",
                    "Proof by contradiction", "There is a relationship between the sides of a triangle",
                    listOfTheorems.getTheorem(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterEquationNotEmpty() {
        try {
            Equation mockEquation1 = new Equation("Fundamental theorem of engineering", "sin(x) = x",
                    "All of university", "sin(0) = 0 QED", "The most useful formula in "
                    + "engineering");
            Equation mockEquation2 = new Equation("The cosine law", "cos(x) = 1", "Grade school",
                    "cos(0) = 1", "All cosines are equal to 1. ");
            ListOfEquations listOfEquations = new ListOfEquations();
            listOfEquations.addEquation(mockEquation1);
            listOfEquations.addEquation(mockEquation2);
            mockEquation1.addPracticeProblem("What is sine of 11", "11");
            mockEquation1.addPracticeProblem("What is sine of 30", "30");
            JsonWriter writer = new JsonWriter("./data/testNotEmptyListOfEquation.json");
            writer.open();
            writer.writeListOfEquation(listOfEquations);
            writer.close();

            JsonReader reader = new JsonReader("./data/testNotEmptyListOfEquation.json");
            listOfEquations = reader.readEquations();
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
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterRequestNotEmpty() {
        try {
            Request mockRequest1 = new Request("Riemann Hypothesis", "Something about prime numbers", "Theorem",
                    "???", "I would like to know this too. ",
                    "Relationship between the zeta function and the prime numbers");
            Request mockRequest2 = new Request("The REAL law of cosines", "c^2 = a^2 + b^2 - 2abcos(C)",
                    "Equation", "Grade 11", "Use geometry.", "Relationship between sides of a "
                    + "triangle that are not always right.");
            mockRequest2.updateEstimatedCompletion("100");
            ListOfRequests listOfRequests = new ListOfRequests();
            listOfRequests.addRequest(mockRequest1);
            listOfRequests.addRequest(mockRequest2);
            JsonWriter writer = new JsonWriter("./data/testNotEmptyListOfRequest.json");
            writer.open();
            writer.writeListOfRequests(listOfRequests);
            writer.close();

            JsonReader reader = new JsonReader("./data/testNotEmptyListOfRequest.json");
            listOfRequests = reader.readRequests();
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
            fail("Exception should not have been thrown");
        }
    }

}
