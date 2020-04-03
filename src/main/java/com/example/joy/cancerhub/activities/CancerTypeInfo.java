package com.example.joy.cancerhub.activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joy.cancerhub.R;
import com.example.joy.cancerhub.models.CancerType;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class CancerTypeInfo extends AppCompatActivity {

    FirebaseFirestore Dbs;
    private WebView webView;
    private String cancerTypeId;
    private String cancerTypeName;
    private TextView info;
    String overview = "Overview";
    String illustration = "Medical Illustration";
    String risk = "Risk Factors";
    String signs = "Signs and Symptoms";
    String diagnosis = "Diagnosis";
    String treatment = "Treatment and Management";
    //String stages = "Stages and Grades";
    //String research = "Latest Research";

    ProgressDialog progressDialog;


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancertype_info);

        Dbs = FirebaseFirestore.getInstance();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            cancerTypeId = bundle.getString("cancer_type_id");
            cancerTypeName = bundle.getString("cancer_name");

        }

        Toolbar toolbar = findViewById(R.id.toolbarLungCancer);
        toolbar.setTitle(cancerTypeName);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), cancer_types.class));
                finish();
            }
        });

        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        info = findViewById(R.id.info);
        webView = findViewById(R.id.webViewLung);
        webView.getSettings().setJavaScriptEnabled(true);

        Dbs.collection("diseases")
                .document(cancerTypeId)
                .get()
                .addOnCompleteListener(CancerTypeInfo.this, new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            CancerType cancerType = task.getResult().toObject(CancerType.class);
                            if (cancerType != null) {
                                info.setText(overview);
                                webView.loadUrl(cancerType.getOverview());
                            }
                        } else {
                            Toast.makeText(CancerTypeInfo.this, "Internet Connection Error", Toast.LENGTH_LONG).show();
                        }
                    }
                });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cancer_info, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        progressDialog.show();
        switch (item.getItemId()) {
            case R.id.action_information:
                Dbs.collection("diseases")
                        .document(cancerTypeId)
                        .get()
                        .addOnCompleteListener(CancerTypeInfo.this, new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                progressDialog.dismiss();
                                if (task.isSuccessful()) {
                                    CancerType cancerType = task.getResult().toObject(CancerType.class);
                                    if (cancerType != null) {
                                        info.setText(overview);
                                        webView.loadUrl(cancerType.getOverview());
                                    }
                                } else {
                                    Toast.makeText(CancerTypeInfo.this, "Internet Connection Error", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                break;
            case R.id.action_information1:
                Dbs.collection("diseases")
                        .document(cancerTypeId)
                        .get()
                        .addOnCompleteListener(CancerTypeInfo.this, new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                progressDialog.dismiss();
                                if (task.isSuccessful()) {
                                    CancerType cancerType = task.getResult().toObject(CancerType.class);
                                    if (cancerType != null) {
                                        info.setText(illustration);
                                        webView.loadUrl(cancerType.getMedical_illustrations());
                                    }
                                } else {
                                    Toast.makeText(CancerTypeInfo.this, "Internet Connection Error", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                break;
            case R.id.action_information2:
                Dbs.collection("diseases")
                        .document(cancerTypeId)
                        .get()
                        .addOnCompleteListener(CancerTypeInfo.this, new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                progressDialog.dismiss();
                                if (task.isSuccessful()) {
                                    CancerType cancerType = task.getResult().toObject(CancerType.class);
                                    if (cancerType != null) {
                                        info.setText(risk);
                                        webView.loadUrl(cancerType.getRisk_factors());
                                    }
                                } else {
                                    Toast.makeText(CancerTypeInfo.this, "Internet Connection Error", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                break;
            case R.id.action_information3:
                Dbs.collection("diseases")
                        .document(cancerTypeId)
                        .get()
                        .addOnCompleteListener(CancerTypeInfo.this, new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                progressDialog.dismiss();
                                if (task.isSuccessful()) {
                                    CancerType cancerType = task.getResult().toObject(CancerType.class);
                                    if (cancerType != null) {
                                        info.setText(signs);
                                        // webView.loadData(cancerType.getSigns_symptoms(), "text/html", "UTF-8");
                                        webView.loadUrl(cancerType.getSigns_symptoms());
                                    }
                                } else {
                                    Toast.makeText(CancerTypeInfo.this, "Internet Connection Error", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                break;
            case R.id.action_information4:
                Dbs.collection("diseases")
                        .document(cancerTypeId)
                        .get()
                        .addOnCompleteListener(CancerTypeInfo.this, new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                progressDialog.dismiss();
                                if (task.isSuccessful()) {
                                    CancerType cancerType = task.getResult().toObject(CancerType.class);
                                    if (cancerType != null) {
                                        info.setText(diagnosis);
                                        webView.loadUrl(cancerType.getDiagnosis());
                                    }
                                } else {
                                    Toast.makeText(CancerTypeInfo.this, "Internet Connection Error", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

            case R.id.action_information5:
                Dbs.collection("diseases")
                        .document(cancerTypeId)
                        .get()
                        .addOnCompleteListener(CancerTypeInfo.this, new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                progressDialog.dismiss();
                                if (task.isSuccessful()) {
                                    CancerType cancerType = task.getResult().toObject(CancerType.class);
                                    if (cancerType != null) {
                                        info.setText(treatment);
                                        webView.loadUrl(cancerType.getTreatement());
                                    }
                                } else {
                                    Toast.makeText(CancerTypeInfo.this, "Internet Connection Error", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void fetchRealTimeData() {
//                    DBRef.child("liver_cancer/overview").addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        String liver = dataSnapshot.getValue(String.class);
//                        // textView.setText(Html.fromHtml(liver));
//                        webView.loadData(liver, "text/html", "UTF-8");
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                        System.out.println("The read failed: " + databaseError.getCode());
//                    }
//                });
    }
}
