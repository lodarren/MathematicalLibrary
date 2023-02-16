import model.Entry;
import model.Theorem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TheoremTest {
    Entry testEntryTheorem;
    Theorem testTheorem;

    @BeforeEach
    void setup() {
        testEntryTheorem = new Theorem("name", "theorem", "course", "proof",
                "explanation");
        testTheorem = new Theorem("name", "theorem", "course", "proof",
                "explanation");
    }

    @Test
    void getNameTest() {
        assertEquals("name", testEntryTheorem.getName());
    }

    @Test
    void getTheoremTest() {
        assertEquals("theorem", testEntryTheorem.getTheorem());
    }

    @Test
    void getCourseTest() {
        assertEquals("course", testEntryTheorem.getCourse());
    }

    @Test
    void getProofTest() {
        assertEquals("proof", testEntryTheorem.getProof());
    }

    @Test
    void getExplanationTest() {
        assertEquals("explanation", testEntryTheorem.getExplanations());
    }

    @Test
    void changeNameTest() {
        testEntryTheorem.changeName("new name");
        assertEquals("new name", testEntryTheorem.getName());
    }

    @Test
    void changeTheoremTest() {
        testEntryTheorem.changeTheorem("new theorem");
        assertEquals("new theorem", testEntryTheorem.getTheorem());
    }

    @Test
    void changeCourseTest() {
        testEntryTheorem.changeCourse("new course");
        assertEquals("new course", testEntryTheorem.getCourse());
    }

    @Test
    void changeProofTest() {
        testEntryTheorem.changeProof("new proof blah blah");
        assertEquals("new proof blah blah", testEntryTheorem.getProof());
    }

    @Test
    void changeExplanationTest() {
        testEntryTheorem.changeExplanation("this is something");
        assertEquals("this is something", testEntryTheorem.getExplanations());
    }

    @Test
    void viewTheorem() {
        assertEquals("Name: name\nTheorem: theorem\nCourse this is most relevant to: course\n"
                + "Description: explanation\nProof: proof\n", testTheorem.viewTheorem());
    }

    @Test
    void viewTheoremLessInfoProof() {
        assertEquals("Name: name\nTheorem: theorem\nCourse this is most relevant to: course\n"
                + "Description: explanation\n", testTheorem.viewTheoremLessInfo());
    }
}
