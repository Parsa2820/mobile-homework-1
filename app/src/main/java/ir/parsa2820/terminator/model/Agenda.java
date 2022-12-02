package ir.parsa2820.terminator.model;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

import ir.parsa2820.terminator.ui.agenda.CourseEvent;
import ir.parsa2820.terminator.ui.agenda.WeekDay;
import ir.parsa2820.terminator.ui.agenda.WeekDayEnum;

public class Agenda {
    private String name;
    private ArrayList<Course> courses;

    public Agenda(String name, ArrayList<Course> courses) {
        this.name = name;
        this.courses = courses;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public ArrayList<WeekDay> getWeekDays(){
        ArrayList<WeekDay> weekDays = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            weekDays.add(new WeekDay(WeekDayEnum.values()[i].toString(), new ArrayList<>()));
        }
        for (Course course : courses) {
            ArrayList<CourseEvent> courseEvents = course.getCourseEvents();

            for (CourseEvent courseEvent : courseEvents) {
            }
        }
        return weekDays;
    }
}
