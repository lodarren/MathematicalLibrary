package persistence;

import model.*;

import model.exceptions.JsonNotFound;
import org.json.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// This class represents a reader that reads library data from JSON data stored in file
public class JsonReader {
    private String location;

    public JsonReader(String location) {
        this.location = location;
    }

    public ListOfTheorems read() throws JsonNotFound {
        String jsonData = readFile(location);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseListOfTheorems(jsonObject);
    }

    private String readFile (String location) throws JsonNotFound {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(location), StandardCharsets.UTF_8)) {
            stream.forEach (s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    private void parseListOfTheorems(ListOfTheorems lot, JSONObject jsonObject) {
    }
}
