package tests;

import model.ListOfRequests;
import model.entryTypes.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListOfRequestsTest {
    ListOfRequests listOfRequestsTest;
    Request requestEntry1;
    Request requestEntry2;
    Request requestEntry3;


    @BeforeEach
    public void setup() {
        listOfRequestsTest = new ListOfRequests();
        requestEntry1 = new Request("A", "B","Theorem", "C", "D", "E");
        requestEntry2 = new Request("F", "G", "Theorem", "H", "I", "J");
        requestEntry3 = new Request("K", "L", "Equation", "M", "N", "O");
    }

    @Test
    public void newListOfRequestTest() {
        assertTrue(listOfRequestsTest.isListEmpty());
    }

    @Test
    public void addRequestTest() {
        listOfRequestsTest.addRequest(requestEntry1);
        listOfRequestsTest.addRequest(requestEntry2);
        listOfRequestsTest.addRequest(requestEntry3);

        assertEquals(requestEntry1, listOfRequestsTest.getRequest(0));
        assertEquals(requestEntry2, listOfRequestsTest.getRequest(1));
        assertEquals(requestEntry3, listOfRequestsTest.getRequest(2));
    }

    @Test
    public void removeRequestTest() {
        listOfRequestsTest.addRequest(requestEntry1);
        listOfRequestsTest.addRequest(requestEntry2);
        listOfRequestsTest.addRequest(requestEntry3);
        listOfRequestsTest.removeRequest(requestEntry2);

        assertEquals(requestEntry1, listOfRequestsTest.getRequest(0));
        assertEquals(-1 , listOfRequestsTest.doesRequestExist("F"));
        assertEquals(requestEntry3, listOfRequestsTest.getRequest(1));
    }

    @Test
    public void doesRequestExistTest() {
        listOfRequestsTest.addRequest(requestEntry1);
        listOfRequestsTest.addRequest(requestEntry2);

        assertEquals(0, listOfRequestsTest.doesRequestExist("A"));
        assertEquals(1, listOfRequestsTest.doesRequestExist("F"));
        assertEquals(-1, listOfRequestsTest.doesRequestExist("K"));
    }

    @Test
    public void checkIfRequestExistsTest() {
        listOfRequestsTest.addRequest(requestEntry1);
        listOfRequestsTest.addRequest(requestEntry2);

        assertTrue(listOfRequestsTest.checkIfRequestExists("A"));
        assertTrue(listOfRequestsTest.checkIfRequestExists("F"));
        assertFalse(listOfRequestsTest.checkIfRequestExists("K"));
    }

    @Test
    public void printAllRequestsTest() {
        listOfRequestsTest.addRequest(requestEntry1);
        listOfRequestsTest.addRequest(requestEntry2);

        assertEquals("\n1. F: Completion Status:  0  Type:Theorem\n" +
                        "2. A: Completion Status:  0  Type:Theorem",listOfRequestsTest.printAllRequests());
    }
}
