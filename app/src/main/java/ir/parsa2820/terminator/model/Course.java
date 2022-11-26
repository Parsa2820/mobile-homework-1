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

    public String getName() {
        return name;
    }
}
