package model;

import model.exceptions.NameAlreadyExists;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

// A list of Requests that is to be put in the library. No Request with the same name can show up
// twice on this list.

public class ListOfRequests {
    ArrayList<Request> entries;     //A list of Request entries that are in the library.

    // EFFECTS: Constructs a new ListOfRequests with an empty list.
    public ListOfRequests() {
        entries = new ArrayList();
    }

    //MODIFIES: this
    //EFFECTS: returns -1 if the string inputted does not match the name of the Requests,
    //         otherwise it returns the index it is located.
    public int doesRequestExist(String nameOfRequest) {
        int counter = 0;
        for (Request r: entries) {
            if (nameOfRequest.equalsIgnoreCase(r.getName())) {
                return counter;
            } else {
                counter++;
            }
        }
        return -1;
    }

    //MODIFIES: this
    //EFFECTS: prints all the equations in the format: #. (name of request)
    public String printAllRequests() {
        int counter = entries.size();
        String text = "";
        for (Request r: entries) {
            text = r.getName() + ": " + "Completion Status:  " + r.getEstimatedCompletion() + "  Type:" + r.getType()
                    + text;
            text = "\n" + counter + ". " + text;
            counter--;
        }
        return text;
    }

    //MODIFIES: this, request
    //EFFECTS: Checks if request exists in entries, if so it changes the name of the request. If the entry exists, it
    //         throws a NameAlreadyExists exception.
    public void changeRequestNameAndCheckExistence(Request request, String name) throws NameAlreadyExists {
        if (checkIfRequestExists(name)) {
            throw new NameAlreadyExists();
        }
        request.changeName(name);
    }

    //MODIFIES: this, request
    //EFFECTS: Checks if request exists in entries, if so it adds request to entries. If the entry exists, it
    //         throws a NameAlreadyExists exception.
    public void addRequestAndCheckExistence(Request request) throws NameAlreadyExists {
        if (checkIfRequestExists(request.getName())) {
            throw new NameAlreadyExists();
        }
        entries.add(request);
    }

    //MODIFIES: this
    //EFFECTS: returns true if the request exists. Otherwise, false.
    public Boolean checkIfRequestExists(String name) {
        return -1 != doesRequestExist(name);
    }

    //MODIFIES: this
    //EFFECTS: adds the request to the last index of the entries list.
    public void addRequest(Request request) {
        entries.add(request);
    }

    //MODIFIES: this
    //EFFECTS: removes the request whose identity matches that in the list.
    public void removeRequest(Request request) {
        entries.remove(request);
    }

    //MODIFIES this:
    //EFFECTS: returns the Request located on the index in entries.
    public Request getRequest(int i) {
        return entries.get(i);
    }

    //MODIFIES: this
    //EFFECTS: returns true if there are no requests in entries.
    public Boolean isListEmpty() {
        return entries.isEmpty();
    }

    //MODIFIES: this
    //EFFECTS: returns the number of entries in this list
    public int numberOfRequests() {
        return entries.size();
    }

    public JSONObject listOfRequestsToJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("requests", requestsToJson());
        return jsonObject;
    }

    public JSONArray requestsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Request e : entries) {
            jsonArray.put(e.requestToJson());
        }

        return jsonArray;
    }
}
