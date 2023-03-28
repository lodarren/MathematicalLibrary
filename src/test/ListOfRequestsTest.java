import model.ListOfRequests;
import model.Request;
import model.exceptions.NameAlreadyExists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListOfRequestsTest {
    ListOfRequests listOfRequestsTest;
    Request requestEntry1;
    Request requestEntry2;
    Request requestEntry3;


    @BeforeEach
    void setup() {
        listOfRequestsTest = new ListOfRequests();
        requestEntry1 = new Request("A", "B","Theorem", "C", "D", "E");
        requestEntry2 = new Request("F", "G", "Theorem", "H", "I", "J");
        requestEntry3 = new Request("K", "L", "Equation", "M", "N", "O");
    }

    @Test
    void newListOfRequestTest() {
        assertTrue(listOfRequestsTest.isListEmpty());
    }

    @Test
    void addRequestTest() {
        listOfRequestsTest.addRequest(requestEntry1);
        listOfRequestsTest.addRequest(requestEntry2);
        listOfRequestsTest.addRequest(requestEntry3);

        assertEquals(requestEntry1, listOfRequestsTest.getRequest(0));
        assertEquals(requestEntry2, listOfRequestsTest.getRequest(1));
        assertEquals(requestEntry3, listOfRequestsTest.getRequest(2));
    }

    @Test
    void removeRequestTest() {
        listOfRequestsTest.addRequest(requestEntry1);
        listOfRequestsTest.addRequest(requestEntry2);
        listOfRequestsTest.addRequest(requestEntry3);
        listOfRequestsTest.removeRequest(requestEntry2);

        assertEquals(requestEntry1, listOfRequestsTest.getRequest(0));
        assertEquals(-1, listOfRequestsTest.doesRequestExist("F"));
        assertEquals(requestEntry3, listOfRequestsTest.getRequest(1));
    }

    @Test
    void doesRequestExistTest() {
        listOfRequestsTest.addRequest(requestEntry1);
        listOfRequestsTest.addRequest(requestEntry2);

        assertEquals(0, listOfRequestsTest.doesRequestExist("A"));
        assertEquals(1, listOfRequestsTest.doesRequestExist("F"));
        assertEquals(-1, listOfRequestsTest.doesRequestExist("K"));
    }

    @Test
    void checkIfRequestExistsTest() {
        listOfRequestsTest.addRequest(requestEntry1);
        listOfRequestsTest.addRequest(requestEntry2);

        assertTrue(listOfRequestsTest.checkIfRequestExists("A"));
        assertTrue(listOfRequestsTest.checkIfRequestExists("F"));
        assertFalse(listOfRequestsTest.checkIfRequestExists("K"));
    }

    @Test
    void printAllRequestsTest() {
        listOfRequestsTest.addRequest(requestEntry1);
        listOfRequestsTest.addRequest(requestEntry2);

        assertEquals("\n1. F: Completion Status:  0  Type:Theorem\n"
                       + "2. A: Completion Status:  0  Type:Theorem",listOfRequestsTest.printAllRequests());
    }

    @Test
    void addRequestAndCheckExistenceTest() {
        try {
            listOfRequestsTest.addRequestAndCheckExistence(requestEntry1);
            assertTrue(listOfRequestsTest.checkIfRequestExists("A"));
        } catch (NameAlreadyExists e) {
            fail();
        }
    }

    @Test
    void addRequestAndCheckExistenceFailTest() {
        try {
            listOfRequestsTest.addRequestAndCheckExistence(requestEntry1);
            listOfRequestsTest.addRequestAndCheckExistence(requestEntry1);
            fail();
        } catch (NameAlreadyExists e) {
            //Pass!
        }
    }

    @Test
    void changeRequestAndCheckExistenceTest() {
        try {
            listOfRequestsTest.addRequest(requestEntry1);
            listOfRequestsTest.addRequest(requestEntry2);
            listOfRequestsTest.changeRequestNameAndCheckExistence(requestEntry1,"newname");
            assertEquals("newname", listOfRequestsTest.getRequest(0).getName());
        } catch (NameAlreadyExists e) {
            fail();
        }
    }

    @Test
    void changeRequestAndCheckExistenceFailTest() {
        try {
            listOfRequestsTest.addRequest(requestEntry1);
            listOfRequestsTest.addRequest(requestEntry2);
            listOfRequestsTest.changeRequestNameAndCheckExistence(requestEntry1, "F");
            fail();
        } catch (NameAlreadyExists e) {
            //Pass!
        }
    }

    @Test
    void numberOfRequestsTest() {
        assertEquals(0, listOfRequestsTest.numberOfRequests());
        listOfRequestsTest.addRequest(requestEntry1);
        assertEquals(1, listOfRequestsTest.numberOfRequests());
        listOfRequestsTest.addRequest(requestEntry2);
        assertEquals(2, listOfRequestsTest.numberOfRequests());
    }

    @Test
    void requestsToStringTest() {
        ArrayList<String> list = new ArrayList<>();
        listOfRequestsTest.addRequest(requestEntry1);
        listOfRequestsTest.addRequest(requestEntry2);
        list.add("A");
        list.add("F");
        assertEquals(listOfRequestsTest.requestsToString(), list);
    }
}
