package ir.parsa2820.terminator.ui.agenda;

import com.google.gson.annotations.Expose;

import ir.parsa2820.terminator.model.Course;

public class CourseEvent {
    private float start;
    private float end;
    private int day;
    @Expose(serialize = false, deserialize = false)
    private Course course;

    public float getStart() {
        return start;
    }

    public float getEnd() {
        return end;
    }

    public int getDay() {
        return day;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }
}
