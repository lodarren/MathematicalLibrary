import model.ListOfEquations;
import model.ListOfRequests;
import model.ListOfTheorems;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {
    @Test
    void readerNoListOfRequestTest() {
        JsonReader reader = new JsonReader("./data/doesnotexist.json");
        try {
            ListOfRequests listOfRequests = reader.readRequests();
            fail("IOException expcted");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void readerNoListOfTheoremTest() {
        JsonReader reader = new JsonReader("./data/doesnotexist.json");
        try {
            ListOfTheorems listOfTheorems = reader.readTheorems();
            fail("IOException expcted");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void readerNoListOfEquationTest() {
        JsonReader reader = new JsonReader("./data/doesnotexist.json");
        try {
            ListOfEquations listOfEquations = reader.readEquations();
            fail("IOException expcted");
        } catch (IOException e) {
            //pass
        }
    }
}
