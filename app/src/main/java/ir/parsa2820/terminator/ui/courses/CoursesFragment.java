package ir.parsa2820.terminator.ui.courses;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import ir.parsa2820.terminator.databinding.FragmentCoursesBinding;
import ir.parsa2820.terminator.model.Course;
import ir.parsa2820.terminator.model.Department;
import ir.parsa2820.terminator.storage.InMemoryStorage;

public class CoursesFragment extends Fragment {

    private FragmentCoursesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CoursesViewModel coursesViewModel =
                new ViewModelProvider(this).get(CoursesViewModel.class);

        binding = FragmentCoursesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // final TextView textView = binding.textHome;
        // homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);


        final RecyclerView recyclerView = binding.recyclerviewCourses;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Course[] coursesArray = InMemoryStorage.getInstance().getDepartments().get(0).getCourses();
        ArrayList<Course> courses = new ArrayList<>(Arrays.asList(coursesArray));
        recyclerView.setAdapter(new CourseAdapter(courses));


        final Spinner spinner = binding.spinnerDepartments;
        ArrayList<String> departments = new ArrayList<>(Collections.singletonList("All"));
        for (Department d : InMemoryStorage.getInstance().getDepartments()) {
            departments.add(d.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, departments);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String chosenDepartment = parent.getItemAtPosition(position).toString();
                ArrayList<Course> courses = new ArrayList<>();
                for (Department d : InMemoryStorage.getInstance().getDepartments()) {
                    if (chosenDepartment.equals("All") || d.getName().equals(chosenDepartment)) {
                        courses.addAll(Arrays.asList(d.getCourses()));
                    }
                }
                recyclerView.setAdapter(new CourseAdapter(courses));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}