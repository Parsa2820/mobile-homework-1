package ir.parsa2820.terminator.ui.courses;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ir.parsa2820.terminator.R;
import ir.parsa2820.terminator.model.Course;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    private final ArrayList<Course> localDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView courseNameTextView;
        private final TextView instructorTextView;

        public ViewHolder(View view) {
            super(view);
            courseNameTextView = (TextView) view.findViewById(R.id.courseName);
            instructorTextView = (TextView) view.findViewById(R.id.instructor);
        }

        public TextView getCourseNameTextView() {
            return courseNameTextView;
        }

        public TextView getInstructorTextView() {
            return instructorTextView;
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
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}

