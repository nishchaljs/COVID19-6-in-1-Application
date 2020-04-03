package com.example.joy.cancerhub.activities;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.joy.cancerhub.R;
import com.example.joy.cancerhub.adapters.MyHealthAdapter;
import com.example.joy.cancerhub.models.Disease;
import com.example.joy.cancerhub.models.Symptoms;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyHealth extends AppCompatActivity {


    public final static double RiskScore = 0.0;

    FirebaseUser fuser;
    FirebaseFirestore Dbs;
    private List<Symptoms> sympt_List = new ArrayList<>();
    private List<Disease> diseases = new ArrayList<>();
    private RecyclerView recyclerM;
    private MyHealthAdapter mAdapter;
    Toolbar toolbar;

    TextView textViewRisk, textViewCancer, textViewRecommend, textViewRecommend2;
    Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysymptoms);

        fuser = FirebaseAuth.getInstance().getCurrentUser();
        Dbs = FirebaseFirestore.getInstance();
        final ProgressDialog  progressDialog = new ProgressDialog(this);


        toolbar = findViewById(R.id.toolbarMyHealth);
        toolbar.setTitle("My Health");
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
        recyclerM = findViewById(R.id.symptom_list);
        recyclerM.setLayoutManager(new LinearLayoutManager(MyHealth.this));
        recyclerM.setItemAnimator(new DefaultItemAnimator());
        sympt_List = new ArrayList<>();
        mAdapter = new MyHealthAdapter(getApplicationContext(), sympt_List);
        recyclerM.setAdapter(mAdapter);

        prepareSymptomsData();
        fetchDiseases();

        FloatingActionButton fab = findViewById(R.id.floatBtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Disease> possibleDiseases = new ArrayList<>();
                for (Symptoms symptoms : sympt_List) {
                    if (symptoms.isSelected()) {
                        symptoms.setSelected(false);
                        for (Disease disease : diseases) {
                            if (disease.getSymptomids().contains(symptoms.getId())) {
                                if (!possibleDiseases.contains(disease))
                                    possibleDiseases.add(disease);
                                else {
                                    int index = possibleDiseases.indexOf(disease);
                                    Disease d = possibleDiseases.get(index);
                                    d.setCount(d.getCount() + 1);
                                    possibleDiseases.set(index, d);
                                }
                            }
                        }
                    }
                }
                if (possibleDiseases.size() > 0) {
                    Disease d = possibleDiseases.get(0);
                    for (Disease disease : possibleDiseases) {
                        if (disease.getCount() > d.getCount())
                            d = disease;
                    }


                    Intent intent = getIntent();
                    Bundle bundle = intent.getBundleExtra("RiskScore");
                    float risk = bundle.getFloat("riskScore");
                    final String riskLevel = bundle.getString("prognosis");
                    final String advice = bundle.getString("advice");
                    final String cancer = d.getName();


                    Map<String, Object> prognos = new HashMap<>();
//                    prognos.put("user", fuser.getUid());
                    prognos.put("timestamp", FieldValue.serverTimestamp());
                    prognos.put("cancerDisease", d.getName());
                    prognos.put("riskScore", risk);
                    prognos.put("riskLevel", riskLevel);
                    prognos.put("recommendation", advice);

                    Dbs.collection("user_prognosis")
                            .add(prognos)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d("TAG", "DocumentSnapshot written with ID: " + documentReference.getId());
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("TAG", "Error writing document", e);
                                }
                            });
                   final String text = "Limit alcohol consumption.\n" +
                            "Exercise for at least 30 minutes three or four days a week. \n" +
                            "A diet high in fat, processed foods and red meats increases cancer. Limit your consumption of these foods.";

                    progressDialog.setMessage("Analysing Results...");
                    progressDialog.show();
                    Runnable progressRunnable = new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.dismiss();
                            setContentView(R.layout.layout_result);
                            textViewCancer = findViewById(R.id.txtCancerType);
                            textViewRisk = findViewById(R.id.txtRiskLevel);
                            textViewRecommend = findViewById(R.id.txtRecommendations);
                            textViewRecommend2 = findViewById(R.id.txtRecommendations2);
                            btnExit = findViewById(R.id.btnExit);

                            textViewCancer.setText(cancer);
                            textViewRisk.setText(riskLevel);
                            textViewRecommend.setText(advice);
                            textViewRecommend2.setText(text);
                            btnExit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    finish();
                                    startActivity(new Intent(getApplicationContext(),Home.class));
                                }
                            });
                        }
                    };
                    Handler pdCanceller = new Handler();
                    pdCanceller.postDelayed(progressRunnable, 3000);

                }
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_symptom, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView mSearchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.symptom_search));
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        mSearchView.setQueryHint("Search");
        mSearchView.setSearchableInfo(searchManager != null ? searchManager.getSearchableInfo(getComponentName()) : null);
        mSearchView.setOnQueryTextListener(new OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mAdapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void prepareSymptomsData() {

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading symptoms");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        Dbs.collection("symptom").orderBy("description", Query.Direction.ASCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Symptoms symptom = document.toObject(Symptoms.class);
                                symptom.setId(document.getId());
                                sympt_List.add(symptom);
                            }
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                });

    }

    private void fetchDiseases() {
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading diseases");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        Dbs.collection("diseases")
                .get()
                .addOnCompleteListener(MyHealth.this, new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                diseases.add(document.toObject(Disease.class));
                            }
                        }
                    }
                });
    }
}
