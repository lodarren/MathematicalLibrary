package model;

import model.entryTypes.Request;
import model.entryTypes.Theorem;

import java.util.ArrayList;

public class ListOfRequests {
    ArrayList<Request> entries;

    public ListOfRequests() {
        entries = new ArrayList();
    }
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

    public Boolean checkIfRequestExists(String name) {
        return -1 != doesRequestExist(name);
    }

    public void addRequest(Request request) {
        entries.add(request);
    }

    public void removeRequest(Request request) {
        entries.remove(request);
    }


    public Request getRequest(int i) {
        return entries.get(i);
    }

    public Boolean isListEmpty() {
        return entries.isEmpty();
    }
}
