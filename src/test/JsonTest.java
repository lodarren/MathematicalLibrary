import model.Equation;
import model.Request;
import model.Theorem;

import static org.junit.jupiter.api.Assertions.assertEquals;

//A class that exists to help the aiding of testing the JSONReaderTest and JSONWriterTest classes
public class JsonTest {
    protected void checkTheorem(String name, String theorem, String course, String proof, String explanation, Theorem t) {
        assertEquals(name, t.getName());
        assertEquals(theorem, t.getTheorem());
        assertEquals(course, t.getCourse());
        assertEquals(proof, t.getProof());
        assertEquals(explanation, t.getExplanations());
    }

    protected void checkEquation(String name, String theorem, String course, String proof, String explanation,
                                 int practiceProblemNum, Equation e) {
        assertEquals(name, e.getName());
        assertEquals(theorem, e.getTheorem());
        assertEquals(course, e.getCourse());
        assertEquals(proof, e.getProof());
        assertEquals(explanation, e.getExplanations());
        assertEquals(practiceProblemNum, e.numberOfPracticeProblems());
        assertEquals(practiceProblemNum, e.numberOfAnswers());
    }

    protected void checkRequest(String name,  String type,  String theorem, String course, String proof,
                                String explanation, int estimatedCompletion, Request r) {
        assertEquals(name, r.getName());
        assertEquals(theorem, r.getTheorem());
        assertEquals(course, r.getCourse());
        assertEquals(proof, r.getProof());
        assertEquals(type, r.getType());
        assertEquals(estimatedCompletion, r.getEstimatedCompletion());
        assertEquals(explanation, r.getExplanations());
    }
}
