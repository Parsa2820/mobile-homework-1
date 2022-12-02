package ir.parsa2820.terminator.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;

import ir.parsa2820.terminator.ui.agenda.CourseEvent;

public class Course {
    @SerializedName("info")
    private String info;
    @SerializedName("course_id")
    private String courseId;
    @SerializedName("course_number")
    private String courseNumber;
    @SerializedName("name")
    private String name;
    @SerializedName("units")
    private int units;
    @SerializedName("instructor")
    private String instructor;
    @SerializedName("class_times")
    private String times;
    @SerializedName("id")
    private String id;
    @SerializedName("exam_time")
    private String examTime;

    public String getInfo() {
        return info;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public String getName() {
        return name;
    }

    public int getUnits() {
        return units;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getTimes() {
        return times;
    }

    public String getId() {
        return id;
    }

    public String getExamTime() {
        return examTime;
    }

    public ArrayList<CourseEvent> getCourseEvents() {
        Gson gson = new Gson();
        CourseEvent[] courseEvents = gson.fromJson(times, CourseEvent[].class);
        for (CourseEvent event : courseEvents) {
            event.setCourse(this);
        }
        return new ArrayList<>(Arrays.asList(courseEvents));
    }
}
