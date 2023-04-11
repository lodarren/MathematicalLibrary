package model;

import java.util.Calendar;
import java.util.Date;

//This code was borrowed from https://github.students.cs.ubc.ca/CPSC210/AlarmSystem
public class Event {
    private static final int HASH_CONSTANT = 13;
    private Date dateLogged;
    private String description;

    //EFFECTS: Creates a new Event with the given description.
    public Event(String description) {
        dateLogged = Calendar.getInstance().getTime();
        this.description = description;
    }

    //EFFECTS: Returns the date of the event.
    public Date getDate() {
        return dateLogged;
    }


    //EFFECTS: Returns the description of the event.
    public String getDescription() {
        return description;
    }

    //Custom equals() method
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (other.getClass() != this.getClass()) {
            return false;
        }

        Event otherEvent = (Event) other;

        return (this.dateLogged.equals(otherEvent.dateLogged)
                && this.description.equals(otherEvent.description));
    }

    //Custom hashcode() method.
    @Override
    public int hashCode() {
        return (HASH_CONSTANT * dateLogged.hashCode() + description.hashCode());
    }

    //Custom toString method.
    @Override
    public String toString() {
        return dateLogged.toString() + "\n" + description;
    }
}
