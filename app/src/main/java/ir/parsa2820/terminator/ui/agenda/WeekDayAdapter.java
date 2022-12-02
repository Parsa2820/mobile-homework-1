package ir.parsa2820.terminator.ui.agenda;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ir.parsa2820.terminator.R;
import ir.parsa2820.terminator.model.Agenda;

public class WeekDayAdapter extends RecyclerView.Adapter<WeekDayAdapter.ViewHolder> {

    private final ArrayList<WeekDay> localDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView dayNameTextView;
        private final LinearLayout coursesLinearLayout;

        public ViewHolder(View view) {
            super(view);
            dayNameTextView = (TextView) view.findViewById(R.id.dayNameTextView);
            coursesLinearLayout = (LinearLayout) view.findViewById(R.id.coursesLinearLayout);
        }
    }

    public WeekDayAdapter(ArrayList<WeekDay> localDataSet) {
        this.localDataSet = localDataSet;
    }

    @NonNull
    @Override
    public WeekDayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_week_day, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeekDayAdapter.ViewHolder holder, int position) {
        holder.dayNameTextView.setText(localDataSet.get(position).getDayName());
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
