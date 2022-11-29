package ir.parsa2820.terminator.ui.courses;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ir.parsa2820.terminator.R;
import ir.parsa2820.terminator.model.Course;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    private final ArrayList<Course> localDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout itemCourse;
        private final TextView courseNameTextView;
        private final TextView instructorTextView;

        public ViewHolder(View view) {
            super(view);
            itemCourse = (LinearLayout) view.findViewById(R.id.itemCourse);
            courseNameTextView = (TextView) view.findViewById(R.id.courseName);
            instructorTextView = (TextView) view.findViewById(R.id.instructor);
        }

        public TextView getCourseNameTextView() {
            return courseNameTextView;
        }

        public TextView getInstructorTextView() {
            return instructorTextView;
        }

        public LinearLayout getItemCourse() {
            return itemCourse;
        }
    }

    public CourseAdapter(ArrayList<Course> dataSet) {
        localDataSet = dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_course, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getCourseNameTextView().setText(localDataSet.get(position).getName());
        viewHolder.getInstructorTextView().setText(localDataSet.get(position).getInstructor());
        viewHolder.getItemCourse().setOnClickListener(v -> {
            Log.e("CourseAdapter", "Clicked on " + localDataSet.get(position).getName());
        });
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}

