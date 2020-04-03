package com.example.joy.cancerhub.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.joy.cancerhub.R;

public class Information extends AppCompatActivity implements View.OnClickListener {

    TextView textViewTOC, textCope, textViewNav, textViewAbout, textViewContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        Toolbar toolbar = findViewById(R.id.toolbarInformation);
        toolbar.setTitle("Information");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Home.class));
                finish();
            }
        });


        textViewTOC = findViewById(R.id.textViewTOC);
        textViewAbout = findViewById(R.id.textViewAbout);
        textViewContact = findViewById(R.id.textViewContact);
        textCope = findViewById(R.id.textViewCope);
        textViewNav = findViewById(R.id.textViewNav);

        textViewTOC.setOnClickListener(this);
        textViewAbout.setOnClickListener(this);
        textViewContact.setOnClickListener(this);
        textCope.setOnClickListener(this);
        textViewNav.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == textViewTOC) {
            startActivity(new Intent(Information.this, cancer_types.class));
            finish();
        }
        if(view == textViewAbout){
            startActivity(new Intent(Information.this, About.class));
            finish();
        }
        if(view == textViewContact){
            startActivity(new Intent(Information.this, Contact.class));
            finish();
        }
        if(view == textCope){
            startActivity(new Intent(Information.this, Coping.class));
            finish();
        }
        if(view == textViewNav){
            startActivity(new Intent(Information.this, Coping.class));
            finish();
        }    }

}
