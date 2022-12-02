package ir.parsa2820.terminator;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import ir.parsa2820.terminator.databinding.ActivityMainBinding;
import ir.parsa2820.terminator.model.Course;
import ir.parsa2820.terminator.model.Department;
import ir.parsa2820.terminator.storage.InMemoryStorage;

public class MainActivity extends AppCompatActivity {

    final static String COURSE_DATA_DIRECTORY = "courses";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            InMemoryStorage.getInstance().setDepartments(loadDepartments());
            InMemoryStorage.getInstance().setContext(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_courses, R.id.navigation_agenda)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


        // try {
        //     ArrayList<Department> departments = loadDepartment();
        //     for (Department department : departments) {
        //         Log.d("Department", department.getName());
        //         for (Course course : department.getCourses()) {
        //             Log.d("Course", course.getName());
        //         }
        //     }
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
    }

    private ArrayList<Department> loadDepartments() throws IOException {
        Gson gson = new Gson();
        String[] files = getAssets().list(COURSE_DATA_DIRECTORY);
        ArrayList<Department> departments = new ArrayList<>();

        for (String file : files) {
            InputStream is = getAssets().open(COURSE_DATA_DIRECTORY + "/" + file);
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            String json = new String(buffer, StandardCharsets.UTF_8);
            Course[] courses = gson.fromJson(json, Course[].class);
            String departmentName = file.substring(0, file.lastIndexOf("."));
            Department department = new Department(departmentName, courses);
            departments.add(department);
        }

        return departments;
    }
}