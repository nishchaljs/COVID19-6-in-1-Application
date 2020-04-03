package com.example.joy.cancerhub.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.joy.cancerhub.R;
import com.example.joy.cancerhub.adapters.F_DoctorAdapter;
import com.example.joy.cancerhub.models.Doctor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FindDoctor extends AppCompatActivity {

    FirebaseFirestore dbFirestore;
    private List<Doctor> docs_List = new ArrayList<>();
    private F_DoctorAdapter dAdapter;
    EditText editSearchDoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);

        dbFirestore = FirebaseFirestore.getInstance();
        Toolbar toolbar = findViewById(R.id.toolbarFindDoc);
        toolbar.setTitle("Find a Doctor");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Home.class));
                finish();

            }
        });

        editSearchDoc = findViewById(R.id.editSearchDoc);
        RecyclerView recyclerD = findViewById(R.id.recyclerViewD);
        recyclerD.setLayoutManager(new LinearLayoutManager(this));
        recyclerD.setItemAnimator(new DefaultItemAnimator());

        docs_List = new ArrayList<>();
        dAdapter = new F_DoctorAdapter(getApplicationContext(), docs_List);
        recyclerD.setAdapter(dAdapter);
        prepareDocsData();

        editSearchDoc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // dAdapter.getFilter().filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
    }

    private void prepareDocsData() {

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        dbFirestore.collection("Doctors")
                .get()
                .addOnCompleteListener(FindDoctor.this, new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                docs_List.add(document.toObject(Doctor.class));
                            }
                            dAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(FindDoctor.this, "Error connecting to Internet", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void filter(String text) {
        List<Doctor> filteredList = new ArrayList<>();
        for (Doctor doctor : docs_List) {
            if (doctor.getDescription().toLowerCase().contains(text.toLowerCase())|| doctor.getExactPlace().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(doctor);
            }
        }
        dAdapter.filterList(filteredList);

    }
}
