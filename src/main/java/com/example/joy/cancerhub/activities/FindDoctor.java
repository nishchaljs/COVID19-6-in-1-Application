package com.example.joy.cancerhub.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.joy.cancerhub.R;
import com.example.joy.cancerhub.adapters.F_DoctorAdapter;
import com.example.joy.cancerhub.adapters.ProtectAdapter;
import com.example.joy.cancerhub.models.Doctor;
import com.example.joy.cancerhub.models.Protect;

import java.util.ArrayList;
import java.util.List;

public class FindDoctor extends AppCompatActivity {

    private List<Doctor> helpList = new ArrayList<>();
    private RecyclerView recyclerH;
    private F_DoctorAdapter hAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);

        Toolbar toolbar = findViewById(R.id.toolbarFindDoc);
        toolbar.setTitle("News and Press");
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

        recyclerH = findViewById(R.id.recyclerViewD);
        recyclerH.setLayoutManager(new LinearLayoutManager(this));
        recyclerH.setItemAnimator(new DefaultItemAnimator());

        hAdapter = new F_DoctorAdapter(helpList, this);
        recyclerH.setAdapter(hAdapter);

        outputHelpData();

    }

    private void outputHelpData() {

        Doctor help = new Doctor("Situation Reports","Situation reports provide the latest updates on the novel coronavirus outbreak.",
                (R.drawable.news_situation_reports),"https://www.who.int/emergencies/diseases/novel-coronavirus-2019/situation-reports/");
        helpList.add(help);

        help = new Doctor("Rolling Updates","Rolling updates on coronavirus disease (COVID-19) sourced from across WHO media.",
                (R.drawable.rolling_updates),"https://www.who.int/emergencies/diseases/novel-coronavirus-2019/events-as-they-happen");
        helpList.add(help);

        help = new Doctor("News Articles","All news releases, statements, and notes for the media.",
                (R.drawable.news_articles),"https://www.who.int/emergencies/diseases/novel-coronavirus-2019/media-resources/news");
        helpList.add(help);

        help = new Doctor("Press Briefings","Coronavirus disease (COVID-2019) press briefings including videos, audio and transcripts.",
                (R.drawable.press_briefings),"https://www.who.int/emergencies/diseases/novel-coronavirus-2019/media-resources/press-briefings");
        helpList.add(help);

        hAdapter.notifyDataSetChanged();

    }
}

