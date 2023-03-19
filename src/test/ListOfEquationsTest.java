import model.ListOfEquations;
import model.Equation;
import model.exceptions.NameAlreadyExists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListOfEquationsTest {
    ListOfEquations listOfEquationsTest;
    Equation equationEntry1;
    Equation equationEntry2;
    Equation equationEntry3;


    @BeforeEach
    void setup() {
        listOfEquationsTest = new ListOfEquations();
        equationEntry1 = new Equation("A", "B", "C", "D", "E");
        equationEntry2 = new Equation("F", "G", "H", "I", "J");
        equationEntry3 = new Equation("K", "L", "M", "N", "O");
    }

    @Test
    void newListOfTheoremTest() {
        assertTrue(listOfEquationsTest.isListEmpty());
    }

    @Test
    void addTheoremTest() {
        listOfEquationsTest.addEquation(equationEntry1);
        listOfEquationsTest.addEquation(equationEntry2);
        listOfEquationsTest.addEquation(equationEntry3);

        assertEquals(equationEntry1, listOfEquationsTest.getEquation(0));
        assertEquals(equationEntry2, listOfEquationsTest.getEquation(1));
        assertEquals(equationEntry3, listOfEquationsTest.getEquation(2));
    }

    @Test
    void removeTheoremTest() {
        listOfEquationsTest.addEquation(equationEntry1);
        listOfEquationsTest.addEquation(equationEntry2);
        listOfEquationsTest.addEquation(equationEntry3);
        listOfEquationsTest.removeEquation(equationEntry2);

        assertEquals(equationEntry1, listOfEquationsTest.getEquation(0));
        assertEquals(-1, listOfEquationsTest.doesEquationExist("F"));
        assertEquals(equationEntry3, listOfEquationsTest.getEquation(1));
    }

    @Test
    void doesTheoremExistTest() {
        listOfEquationsTest.addEquation(equationEntry1);
        listOfEquationsTest.addEquation(equationEntry2);

        assertEquals(0, listOfEquationsTest.doesEquationExist("A"));
        assertEquals(1, listOfEquationsTest.doesEquationExist("F"));
        assertEquals(-1, listOfEquationsTest.doesEquationExist("K"));
    }

    @Test
    void checkIfTheoremExists() {
        listOfEquationsTest.addEquation(equationEntry1);
        listOfEquationsTest.addEquation(equationEntry2);

        assertTrue(listOfEquationsTest.checkIfEquationExists("A"));
        assertTrue(listOfEquationsTest.checkIfEquationExists("F"));
        assertFalse(listOfEquationsTest.checkIfEquationExists("K"));
    }

    @Test
    void printAllTheoremsTest() {
        listOfEquationsTest.addEquation(equationEntry1);
        listOfEquationsTest.addEquation(equationEntry2);

        assertEquals("\n1.F\n" + "2.A",listOfEquationsTest.printAllEquations());
    }

    @Test
    void addEquationAndCheckExistenceTest() {
        try {
            listOfEquationsTest.addEquationAndCheckExistence(equationEntry1);
            assertTrue(listOfEquationsTest.checkIfEquationExists("A"));
        } catch (NameAlreadyExists e) {
            fail();
        }
    }

    @Test
    void addEquationAndCheckExistenceFailTest() {
        try {
            listOfEquationsTest.addEquationAndCheckExistence(equationEntry1);
            listOfEquationsTest.addEquationAndCheckExistence(equationEntry1);
            fail();
        } catch (NameAlreadyExists e) {
            //Pass!
        }
    }

    @Test
    void changeEquationAndCheckExistenceTest() {
        try {
            listOfEquationsTest.addEquation(equationEntry1);
            listOfEquationsTest.addEquation(equationEntry2);
            listOfEquationsTest.changeEquationNameAndCheckExistence(equationEntry1,"newname");
            assertEquals("newname", listOfEquationsTest.getEquation(0).getName());
        } catch (NameAlreadyExists e) {
            fail();
        }
    }

    @Test
    void changeEquationAndCheckExistenceFailTest() {
        try {
            listOfEquationsTest.addEquation(equationEntry1);
            listOfEquationsTest.addEquation(equationEntry2);
            listOfEquationsTest.changeEquationNameAndCheckExistence(equationEntry1,"F");
            fail();
        } catch (NameAlreadyExists e) {
            //Pass!
        }
    }

    @Test
    void numberOfEquationsTest() {
        assertEquals(0, listOfEquationsTest.numberOfEquations());
        listOfEquationsTest.addEquation(equationEntry1);
        assertEquals(1, listOfEquationsTest.numberOfEquations());
        listOfEquationsTest.addEquation(equationEntry2);
        assertEquals(2, listOfEquationsTest.numberOfEquations());
    }

}
