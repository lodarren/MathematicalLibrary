package model;

import java.util.ArrayList;

// A list of Requests that is to be put in the library. No Request with the same name can show up
// twice on this list.

public class ListOfRequests {
    ArrayList<Request> entries;

    // EFFECTS: Constructs a new ListOfRequests with an empty list.
    public ListOfRequests() {
        entries = new ArrayList();
    }

    //MODIFIES: this
    //EFFECTS: returns -1 if the string inputted does not match the name of any of the Requests,
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
            text = r.getName() + ": " + "Completion Status:  " + r.getEstimatedCompletion() + "  Type:" + r.getType() + text;
            text = "\n" + counter + ". " + text;
            counter--;
        }
        return text;
    }

    //MODIFIES: this
    //EFFECTS: returns true if the request exists. Otherwise false.
    public Boolean checkIfRequestExists(String name) {
        return -1 != doesRequestExist(name);
    }

    //MODFIIES: this
    //EFFECTS: adds the request to the last index of the entries list.
    public void addRequest(Request request) {
        entries.add(request);
    }

    //MODFIIES: this
    //EFFECTS: removes the request whose identity matches that in the list.
    public void removeRequest(Request request) {
        entries.remove(request);
    }

    //MODFIIES this:
    //EFFECTS: returns the Request located on the index in entries.
    public Request getRequest(int i) {
        return entries.get(i);
    }

    //MODIFIES: this
    //EFFECTS: returns true if there are no requests in entries.
    public Boolean isListEmpty() {
        return entries.isEmpty();
    }
}
