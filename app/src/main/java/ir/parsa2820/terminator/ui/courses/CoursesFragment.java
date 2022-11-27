package ir.parsa2820.terminator.ui.courses;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

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
        Log.e("kir", courses.get(0).getName());
        recyclerView.setAdapter(new CourseAdapter(courses));
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}