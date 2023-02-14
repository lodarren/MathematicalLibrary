package tests;

import model.Entry;
import model.entryTypes.Theorem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TheoremTest {

    Entry testEntryTheorem;

    Theorem testTheorem;

    @BeforeEach

    public void setup() {
        testEntryTheorem = new Theorem("name", "theorem", "course", "proof",
                "explaination");
        testTheorem = new Theorem("name", "theorem", "course", "proof",
                "explaination");
    }

    @Test
    public void getNameTest() {
        assertEquals("name", testEntryTheorem.getName());
    }

    @Test
    public void getTheoremTest() {
        assertEquals("theorem", testEntryTheorem.getTheorem());
    }

    @Test
    public void getCourseTest() {
        assertEquals("course", testEntryTheorem.getCourse());
    }

    @Test
    public void getProofTest() {
        assertEquals("proof", testEntryTheorem.getProof());
    }

    @Test
    public void getExplainationTest() {
        assertEquals("explaination", testEntryTheorem.getExplainations());
    }

    @Test
    public void changeNameTest() {
        testEntryTheorem.changeName("new name");
        assertEquals("new name", testEntryTheorem.getName());
    }

    @Test
    public void changeTheoremTest() {
        testEntryTheorem.changeTheorem("new theorem");
        assertEquals("new theorem", testEntryTheorem.getTheorem());
    }

    @Test
    public void changeCourseTest() {
        testEntryTheorem.changeCourse("new course");
        assertEquals("new course", testEntryTheorem.getCourse());
    }

    @Test
    public void changeProofTest() {
        testEntryTheorem.changeProof("new proof blah blah");
        assertEquals("new proof blah blah", testEntryTheorem.getProof());
    }

    @Test
    public void changeExplainationTest() {
        testEntryTheorem.changeExplaination("this is something");
        assertEquals("this is something", testEntryTheorem.getExplainations());
    }

}
