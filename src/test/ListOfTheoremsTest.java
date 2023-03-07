import model.ListOfTheorems;
import model.Theorem;
import model.exceptions.NameAlreadyExists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListOfTheoremsTest {
    ListOfTheorems listOfTheoremsTest;
    Theorem theoremEntry1;
    Theorem theoremEntry2;
    Theorem theoremEntry3;


    @BeforeEach
    void setup() {
        listOfTheoremsTest = new ListOfTheorems();
        theoremEntry1 = new Theorem("A", "B", "C", "D", "E");
        theoremEntry2 = new Theorem("F", "G", "H", "I", "J");
        theoremEntry3 = new Theorem("K", "L", "M", "N", "O");
    }

    @Test
    void newListOfTheoremTest() {
        assertTrue(listOfTheoremsTest.isListEmpty());
    }

    @Test
    void addTheoremTest() {
        listOfTheoremsTest.addTheorem(theoremEntry1);
        listOfTheoremsTest.addTheorem(theoremEntry2);
        listOfTheoremsTest.addTheorem(theoremEntry3);

        assertEquals(theoremEntry1, listOfTheoremsTest.getTheorem(0));
        assertEquals(theoremEntry2, listOfTheoremsTest.getTheorem(1));
        assertEquals(theoremEntry3, listOfTheoremsTest.getTheorem(2));
    }

    @Test
    void removeTheoremTest() {
        listOfTheoremsTest.addTheorem(theoremEntry1);
        listOfTheoremsTest.addTheorem(theoremEntry2);
        listOfTheoremsTest.addTheorem(theoremEntry3);
        listOfTheoremsTest.removeTheorem(theoremEntry2);

        assertEquals(theoremEntry1, listOfTheoremsTest.getTheorem(0));
        assertEquals(-1, listOfTheoremsTest.doesTheoremExist("F"));
        assertEquals(theoremEntry3, listOfTheoremsTest.getTheorem(1));
    }

    @Test
    void doesTheoremExistTest() {
        listOfTheoremsTest.addTheorem(theoremEntry1);
        listOfTheoremsTest.addTheorem(theoremEntry2);

        assertEquals(0, listOfTheoremsTest.doesTheoremExist("A"));
        assertEquals(1, listOfTheoremsTest.doesTheoremExist("F"));
        assertEquals(-1, listOfTheoremsTest.doesTheoremExist("K"));
    }

    @Test
    void checkIfTheoremExists() {
        listOfTheoremsTest.addTheorem(theoremEntry1);
        listOfTheoremsTest.addTheorem(theoremEntry2);
        assertTrue(listOfTheoremsTest.checkIfTheoremExists("A"));
        assertTrue(listOfTheoremsTest.checkIfTheoremExists("F"));
        assertFalse(listOfTheoremsTest.checkIfTheoremExists("K"));
    }

    @Test
    void printAllTheoremsTest() {
        listOfTheoremsTest.addTheorem(theoremEntry1);
        listOfTheoremsTest.addTheorem(theoremEntry2);
        assertEquals("\n1.F\n" + "2.A",listOfTheoremsTest.printAllTheorems());
    }

    @Test
    void addTheoremAndCheckExistenceTest() {
        try {
            listOfTheoremsTest.addTheoremAndCheckExistence(theoremEntry1);
            assertTrue(listOfTheoremsTest.checkIfTheoremExists("A"));
        } catch (NameAlreadyExists e) {
            fail();
        }
    }

    @Test
    void addTheoremAndCheckExistenceFailTest() {
        try {
            listOfTheoremsTest.addTheoremAndCheckExistence(theoremEntry1);
            listOfTheoremsTest.addTheoremAndCheckExistence(theoremEntry1);
            fail();
        } catch (NameAlreadyExists e) {
            //Pass!
        }
    }

    @Test
    void changeTheoremAndCheckExistenceTest() {
        try {
            listOfTheoremsTest.addTheorem(theoremEntry1);
            listOfTheoremsTest.addTheorem(theoremEntry2);
            listOfTheoremsTest.changeTheoremNameAndCheckExistence(theoremEntry1,"newname");
            assertEquals("newname", listOfTheoremsTest.getTheorem(0).getName());
        } catch (NameAlreadyExists e) {
            fail();
        }
    }

    @Test
    void changeTheoremAndCheckExistenceFailTest() {
        try {
            listOfTheoremsTest.addTheorem(theoremEntry1);
            listOfTheoremsTest.addTheorem(theoremEntry2);
            listOfTheoremsTest.changeTheoremNameAndCheckExistence(theoremEntry1, "F");
            fail();
        } catch (NameAlreadyExists e) {
            //Pass!
        }
    }

    @Test
    void numberOfTheoremsTest() {
        assertEquals(0, listOfTheoremsTest.numberOfTheorems());
        listOfTheoremsTest.addTheorem(theoremEntry1);
        assertEquals(1, listOfTheoremsTest.numberOfTheorems());
        listOfTheoremsTest.addTheorem(theoremEntry2);
        assertEquals(2, listOfTheoremsTest.numberOfTheorems());
    }
}
