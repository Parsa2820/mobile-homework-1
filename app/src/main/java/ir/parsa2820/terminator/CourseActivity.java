package ir.parsa2820.terminator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.ArrayList;

import ir.parsa2820.terminator.model.Course;
import ir.parsa2820.terminator.storage.InMemoryStorage;

public class CourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        Gson gson = new Gson();
        Course course = gson.fromJson(getIntent().getStringExtra("course"), Course.class);
        TextView courseTitle = findViewById(R.id.activityCourseName);
        TextView instructor = findViewById(R.id.activityInstructor);
        TextView id = findViewById(R.id.activityId);
        TextView courseId = findViewById(R.id.activityCourseId);
        TextView courseNumber = findViewById(R.id.activityCourseNumber);
        TextView courseUnits = findViewById(R.id.activityCourseUnits);
        TextView courseTimes = findViewById(R.id.activityCourseTimes);
        TextView courseExamTime = findViewById(R.id.activityCourseExamTime);
        TextView courseInfo = findViewById(R.id.activityInfo);
        courseTitle.setText(course.getName());
        instructor.setText(course.getInstructor());
        id.setText("ID: " + course.getId());
        courseId.setText("Course ID: " + course.getCourseId());
        courseNumber.setText("Course Number: " + course.getCourseNumber());
        courseUnits.setText("Units: " + String.valueOf(course.getUnits()));
        courseTimes.setText("Times: " + course.getTimes());
        courseExamTime.setText("Exam Time: " + course.getExamTime());
        courseInfo.setText("Info: " + course.getInfo());

        Spinner spinner = findViewById(R.id.agendaSpinner);
        ArrayList<String> agendaNames = InMemoryStorage.getInstance().getAgendaNames();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, agendaNames);
        spinner.setAdapter(adapter);

        Button addToAgenda = findViewById(R.id.addToAgendaButton);
        addToAgenda.setOnClickListener(v -> {
            Log.e("inja", "to agenda");
            Object selectedItem = spinner.getSelectedItem();
            if (selectedItem == null) {
                return;
            }
            String agendaName = selectedItem.toString();
            if (InMemoryStorage.getInstance().getAgenda(agendaName) == null) {
                return;
            }
            InMemoryStorage.getInstance().addToAgenda(agendaName, course.getCourseId());
        });
    }
}