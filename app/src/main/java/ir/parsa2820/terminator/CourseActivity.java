package ir.parsa2820.terminator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.ArrayList;

import ir.parsa2820.terminator.model.Agenda;
import ir.parsa2820.terminator.model.Course;
import ir.parsa2820.terminator.storage.InMemoryStorage;
import ir.parsa2820.terminator.ui.agenda.CourseEvent;
import ir.parsa2820.terminator.ui.agenda.WeekDayAdapter;
import ir.parsa2820.terminator.ui.agenda.WeekDayEnum;

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
        StringBuilder times = new StringBuilder();
        times.append('\n');
        for (CourseEvent event : course.getCourseEvents()) {
            times.append('\t');
            times.append(WeekDayEnum.values()[event.getDay()]);
            times.append(" ");
            times.append(event.getStart());
            times.append(" - ");
            times.append(event.getEnd());
            times.append('\n');
        }
        times.setLength(times.length() - 1);
        courseTimes.setText("Times: " + times);
        courseExamTime.setText("Exam Time: " + course.getExamTime());
        courseInfo.setText("Info: " + course.getInfo());

        Spinner spinner = findViewById(R.id.agendaSpinner);
        ArrayList<String> agendaNames = InMemoryStorage.getInstance().getAgendaNames();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, agendaNames);
        spinner.setAdapter(adapter);

        Button addToAgenda = findViewById(R.id.addToAgendaButton);
        addToAgenda.setOnClickListener(v -> {
            Object selectedItem = spinner.getSelectedItem();
            if (selectedItem == null) {
                Toast.makeText(this, "No agenda selected", Toast.LENGTH_LONG).show();
                return;
            }
            String agendaName = selectedItem.toString();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Confirmation");
            builder.setMessage("Are you sure you want to add this course to " + agendaName + "?");
            builder.setPositiveButton("Yes", (dialog, which) -> {
                Agenda agenda = InMemoryStorage.getInstance().getAgenda(agendaName);
                assert agenda != null;

                if (agenda.hasOverlap(course)) {
                    Toast.makeText(this, "Course overlaps with another course in agenda", Toast.LENGTH_LONG).show();
                    return;
                }

                InMemoryStorage.getInstance().addToAgenda(agendaName, course.getCourseId());
                Toast.makeText(this, "Course added to agenda", Toast.LENGTH_LONG).show();
                finish();
            });
            builder.setNegativeButton("No", (dialog, which) -> {
                // do nothing
            });
            builder.show();
        });

        Button deleteFromAgenda = findViewById(R.id.deleteFromAgendaButton);
        deleteFromAgenda.setOnClickListener(v -> {
            Object selectedItem = spinner.getSelectedItem();
            if (selectedItem == null) {
                Toast.makeText(this, "No agenda selected", Toast.LENGTH_LONG).show();
                return;
            }
            String agendaName = selectedItem.toString();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Confirmation");
            builder.setMessage("Are you sure you want to delete this course from " + agendaName + "?");
            builder.setPositiveButton("Yes", (dialog, which) -> {
                InMemoryStorage.getInstance().deleteFromAgenda(agendaName, course.getCourseId());
                Toast.makeText(this, "Course deleted from agenda", Toast.LENGTH_LONG).show();
                finish();
            });
            builder.setNegativeButton("No", (dialog, which) -> {
                // do nothing
            });
            builder.show();
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String agendaName = parent.getItemAtPosition(position).toString();
                Agenda agenda = InMemoryStorage.getInstance().getAgenda(agendaName);
                if (agenda == null) {
                    return;
                }
                if (agenda.hasCourse(course.getCourseId())) {
                    addToAgenda.setEnabled(false);
                    deleteFromAgenda.setEnabled(true);
                } else {
                    addToAgenda.setEnabled(true);
                    deleteFromAgenda.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                addToAgenda.setEnabled(false);
                deleteFromAgenda.setEnabled(false);
            }
        });
    }
}