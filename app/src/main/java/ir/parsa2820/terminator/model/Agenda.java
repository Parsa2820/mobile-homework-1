package ir.parsa2820.terminator.model;

import java.util.ArrayList;

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
}
