package com.stylet.nutronx.Activities.ui.Patients;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.stylet.nutronx.Activities.AddNewPatient;
import com.stylet.nutronx.R;

import java.util.ArrayList;
import java.util.List;

public class PatientFragment extends Fragment {

    private PatientViewModel patientViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_patient, container, false);

        Toolbar toolbar = root.findViewById(R.id.all_patient_toolbar);


        final FloatingActionButton fab = root.findViewById(R.id.addpatientfab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toAddPatient = new Intent(getContext(), AddNewPatient.class);
                startActivity(toAddPatient);

            }
        });

        return root;
    }
}