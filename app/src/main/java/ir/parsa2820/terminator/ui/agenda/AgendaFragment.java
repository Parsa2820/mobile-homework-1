package ir.parsa2820.terminator.ui.agenda;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

import ir.parsa2820.terminator.databinding.FragmentAgendaBinding;
import ir.parsa2820.terminator.model.Agenda;
import ir.parsa2820.terminator.storage.InMemoryStorage;

public class AgendaFragment extends Fragment {

    private FragmentAgendaBinding binding;
    private Spinner agendaSpinner;
    private InMemoryStorage storage;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AgendaViewModel dashboardViewModel =
                new ViewModelProvider(this).get(AgendaViewModel.class);

        binding = FragmentAgendaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final EditText agendaNameEditText = binding.editTextAgendaName;
        final Button createAgenda = binding.createAgendaButton;
        storage = InMemoryStorage.getInstance();
        agendaSpinner = binding.agendasSpinner;
        updateAgendaSpinner();
        final RecyclerView agendaRecyclerView = binding.agendaRecyclerView;
        agendaRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        agendaRecyclerView.setAdapter(new WeekDayAdapter(new ArrayList<>()));

        createAgenda.setOnClickListener(v -> {
            String agendaName = agendaNameEditText.getText().toString();
            InMemoryStorage storage = InMemoryStorage.getInstance();
            if (agendaName.isEmpty() || storage.getAgenda(agendaName) != null) {
                return;
            }
            storage.createAgenda(agendaName);
            agendaNameEditText.setText("");
            updateAgendaSpinner();
        });

        agendaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String agendaName = agendaSpinner.getSelectedItem().toString();
                Agenda agenda = storage.getAgenda(agendaName);
                if (agenda == null) {
                    return;
                }
                ArrayList<WeekDay> weekDays = agenda.getWeekDays();
                agendaRecyclerView.setAdapter(new WeekDayAdapter(weekDays));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return root;
    }

    private void updateAgendaSpinner() {
        ArrayList<String> agendaNames = storage.getAgendaNames();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, agendaNames);
        agendaSpinner.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}