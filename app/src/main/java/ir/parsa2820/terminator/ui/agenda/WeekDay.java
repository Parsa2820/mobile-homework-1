package ir.parsa2820.terminator.ui.agenda;

import java.util.ArrayList;

public class WeekDay {
    private String dayName;
    private ArrayList<CourseEvent> courseEvents;

    public WeekDay(String dayName, ArrayList<CourseEvent> courseEvents) {
        this.dayName = dayName;
        this.courseEvents = courseEvents;
    }

    public String getDayName() {
        return dayName;
    }

    public ArrayList<CourseEvent> getCourseEvents() {
        return courseEvents;
    }

    public void setCourseEvents(ArrayList<CourseEvent> courseEvents) {
        this.courseEvents = courseEvents;
    }
}

