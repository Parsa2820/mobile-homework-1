package ir.parsa2820.terminator.model;

import com.google.gson.annotations.SerializedName;

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
    @SerializedName("times")
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
}
