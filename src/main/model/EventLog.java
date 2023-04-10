package ca.ubc.cpsc210.alarm.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Represents a log of alarm system events.
 * We use the Singleton Design Pattern to ensure that there is only
 * one EventLog in the system and that the system has global access
 * to the single instance of the EventLog.
 */
public class EventLog implements Iterable<ca.ubc.cpsc210.alarm.model.Event> {
    /** the only EventLog in the system (Singleton Design Pattern) */
    private static EventLog theLog;
    private Collection<ca.ubc.cpsc210.alarm.model.Event> events;

    /**
     * Prevent external construction.
     * (Singleton Design Pattern).
     */
    private EventLog() {
        events = new ArrayList<ca.ubc.cpsc210.alarm.model.Event>();
    }

    /**
     * Gets instance of EventLog - creates it
     * if it doesn't already exist.
     * (Singleton Design Pattern)
     * @return  instance of EventLog
     */
    public static EventLog getInstance() {
        if (theLog == null)
            theLog = new EventLog();

        return theLog;
    }

    /**
     * Adds an event to the event log.
     * @param e the event to be added
     */
    public void logEvent(ca.ubc.cpsc210.alarm.model.Event e) {
        events.add(e);
    }

    /**
     * Clears the event log and logs the event.
     */
    public void clear() {
        events.clear();
        logEvent(new ca.ubc.cpsc210.alarm.model.Event("Event log cleared."));
    }

    @Override
    public Iterator<ca.ubc.cpsc210.alarm.model.Event> iterator() {
        return events.iterator();
    }
}

/*
make calls to the logEvent method of the EventLog class from code in your model package but from nowhere else.
In particular, you must not call this method from anywhere in your ui package.
 */