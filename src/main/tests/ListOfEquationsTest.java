package tests;

import model.ListOfEquations;
import model.entryTypes.Equation;
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
    public void setup() {
        listOfEquationsTest = new ListOfEquations();
        equationEntry1 = new Equation("A", "B", "C", "D", "E");
        equationEntry2 = new Equation("F", "G", "H", "I", "J");
        equationEntry3 = new Equation("K", "L", "M", "N", "O");
    }

    @Test
    public void newListOfTheoremTest() {
        assertTrue(listOfEquationsTest.isListEmpty());
    }

    @Test
    public void addTheoremTest() {
        listOfEquationsTest.addEquation(equationEntry1);
        listOfEquationsTest.addEquation(equationEntry2);
        listOfEquationsTest.addEquation(equationEntry3);

        assertEquals(equationEntry1, listOfEquationsTest.getEquation(0));
        assertEquals(equationEntry2, listOfEquationsTest.getEquation(1));
        assertEquals(equationEntry3, listOfEquationsTest.getEquation(2));
    }

    @Test
    public void removeTheoremTest() {
        listOfEquationsTest.addEquation(equationEntry1);
        listOfEquationsTest.addEquation(equationEntry2);
        listOfEquationsTest.addEquation(equationEntry3);
        listOfEquationsTest.removeEquation(equationEntry2);

        assertEquals(equationEntry1, listOfEquationsTest.getEquation(0));
        assertEquals(-1 , listOfEquationsTest.doesEquationExist("F"));
        assertEquals(equationEntry3, listOfEquationsTest.getEquation(1));
    }

    @Test
    public void doesTheoremExistTest() {
        listOfEquationsTest.addEquation(equationEntry1);
        listOfEquationsTest.addEquation(equationEntry2);

        assertEquals(0, listOfEquationsTest.doesEquationExist("A"));
        assertEquals(1, listOfEquationsTest.doesEquationExist("F"));
        assertEquals(-1, listOfEquationsTest.doesEquationExist("K"));
    }

    @Test
    public void checkIfTheoremExists() {
        listOfEquationsTest.addEquation(equationEntry1);
        listOfEquationsTest.addEquation(equationEntry2);

        assertTrue(listOfEquationsTest.checkIfEquationExists("A"));
        assertTrue(listOfEquationsTest.checkIfEquationExists("F"));
        assertFalse(listOfEquationsTest.checkIfEquationExists("K"));
    }

    @Test
    public void printAllTheoremsTest() {
        listOfEquationsTest.addEquation(equationEntry1);
        listOfEquationsTest.addEquation(equationEntry2);

        assertEquals("\n1.F\n" + "2.A",listOfEquationsTest.printAllEquations());
    }
}
