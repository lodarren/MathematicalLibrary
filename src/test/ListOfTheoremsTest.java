import model.ListOfTheorems;
import model.Theorem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListOfTheoremsTest {
    ListOfTheorems listOfTheoremsTest;
    Theorem theoremEntry1;
    Theorem theoremEntry2;
    Theorem theoremEntry3;


    @BeforeEach
    public void setup() {
        listOfTheoremsTest = new ListOfTheorems();
        theoremEntry1 = new Theorem("A", "B", "C", "D", "E");
        theoremEntry2 = new Theorem("F", "G", "H", "I", "J");
        theoremEntry3 = new Theorem("K", "L", "M", "N", "O");
    }

    @Test
    public void newListOfTheoremTest() {
        assertTrue(listOfTheoremsTest.isListEmpty());
    }

    @Test
    public void addTheoremTest() {
        listOfTheoremsTest.addTheorem(theoremEntry1);
        listOfTheoremsTest.addTheorem(theoremEntry2);
        listOfTheoremsTest.addTheorem(theoremEntry3);

        assertEquals(theoremEntry1, listOfTheoremsTest.getTheorem(0));
        assertEquals(theoremEntry2, listOfTheoremsTest.getTheorem(1));
        assertEquals(theoremEntry3, listOfTheoremsTest.getTheorem(2));
    }

    @Test
    public void removeTheoremTest() {
        listOfTheoremsTest.addTheorem(theoremEntry1);
        listOfTheoremsTest.addTheorem(theoremEntry2);
        listOfTheoremsTest.addTheorem(theoremEntry3);
        listOfTheoremsTest.removeTheorem(theoremEntry2);

        assertEquals(theoremEntry1, listOfTheoremsTest.getTheorem(0));
        assertEquals(-1, listOfTheoremsTest.doesTheoremExist("F"));
        assertEquals(theoremEntry3, listOfTheoremsTest.getTheorem(1));
    }

    @Test
    public void doesTheoremExistTest() {
        listOfTheoremsTest.addTheorem(theoremEntry1);
        listOfTheoremsTest.addTheorem(theoremEntry2);

        assertEquals(0, listOfTheoremsTest.doesTheoremExist("A"));
        assertEquals(1, listOfTheoremsTest.doesTheoremExist("F"));
        assertEquals(-1, listOfTheoremsTest.doesTheoremExist("K"));
    }

    @Test
    public void checkIfTheoremExists() {
        listOfTheoremsTest.addTheorem(theoremEntry1);
        listOfTheoremsTest.addTheorem(theoremEntry2);

        assertTrue(listOfTheoremsTest.checkIfTheoremExists("A"));
        assertTrue(listOfTheoremsTest.checkIfTheoremExists("F"));
        assertFalse(listOfTheoremsTest.checkIfTheoremExists("K"));
    }

    @Test
    public void printAllTheoremsTest() {
        listOfTheoremsTest.addTheorem(theoremEntry1);
        listOfTheoremsTest.addTheorem(theoremEntry2);

        assertEquals("\n1.F\n" + "2.A",listOfTheoremsTest.printAllTheorems());
    }
}
