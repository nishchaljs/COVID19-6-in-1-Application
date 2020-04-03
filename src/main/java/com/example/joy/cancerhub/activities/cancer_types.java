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
import android.view.View;

import com.example.joy.cancerhub.R;
import com.example.joy.cancerhub.adapters.CancerTAdapter;
import com.example.joy.cancerhub.models.CancerType;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class cancer_types extends AppCompatActivity {

    FirebaseFirestore dbfirestore;
    private List<CancerType> cancer_List = new ArrayList<>();
    private RecyclerView recyclerC;
    private CancerTAdapter cAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancer_types);

        dbfirestore = FirebaseFirestore.getInstance();

        Toolbar toolbar = findViewById(R.id.toolbarCancerT);
        toolbar.setTitle("Types of Cancer");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Information.class));
                finish();
            }
        });

        recyclerC = findViewById(R.id.recyclerCancerT);
        recyclerC.setLayoutManager(new LinearLayoutManager(this));
        recyclerC.setItemAnimator(new DefaultItemAnimator());


        cAdapter = new CancerTAdapter(cancer_types.this, cancer_List);
        recyclerC.setAdapter(cAdapter);
        OutputCancerTypeList();
    }

    private void OutputCancerTypeList() {

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Cancer types...");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        dbfirestore.collection("diseases").orderBy("name", Query.Direction.ASCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                CancerType cancerType = document.toObject(CancerType.class);
                                cancerType.setId(document.getId());
                                cancer_List.add(cancerType);
                            }
                            cAdapter.notifyDataSetChanged();
                        }
                    }
                });

    }
}
