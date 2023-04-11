package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

//This code was borrowed from https://github.students.cs.ubc.ca/CPSC210/AlarmSystem
public class EventLog implements Iterable<Event> {
    // The only Eventlog in the system (using the Singleton Design Pattern)
    private static EventLog theLog;
    private Collection<Event> events;

    //EFFECTS: Constructor for the EventLog, does not allow external construction.
    private EventLog() {
        events = new ArrayList<Event>();
    }

    //EFFECTS: Returns the instance of EventLog if it exists, creates an Eventlog if it does not exist.
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }

        return theLog;
    }

    //EFFECTS: Adds an Event to Eventlog.
    public void logEvent(Event e) {
        events.add(e);
    }

    //EFFECTS: Clears the Eventlog and logs the event.
    public void clear() {
        events.clear();
        logEvent(new Event("Event log cleared."));
    }

    //Custom Iterator
    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}

/*
make calls to the logEvent method of the EventLog class from code in your model package but from nowhere else.
In particular, you must not call this method from anywhere in your ui package.

To complete:
- Implement the classes in model
- Create UML diagram
- Create proper name for the commit "Phase 4 task 2"
- Put UML into project
 */