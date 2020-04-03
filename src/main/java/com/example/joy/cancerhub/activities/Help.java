package com.example.joy.cancerhub.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.joy.cancerhub.R;
import com.example.joy.cancerhub.adapters.HelpAdapter;
import com.example.joy.cancerhub.models.HelpModel;

import java.util.ArrayList;
import java.util.List;

public class Help extends AppCompatActivity {

    private List<HelpModel> helpList = new ArrayList<>();
    private RecyclerView recyclerH;
    private HelpAdapter hAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        Toolbar toolbar = findViewById(R.id.toolbarHelp);
        toolbar.setTitle("Help with App");
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

        recyclerH = findViewById(R.id.recyclerViewH);
        recyclerH.setLayoutManager(new LinearLayoutManager(this));
        recyclerH.setItemAnimator(new DefaultItemAnimator());

        hAdapter = new HelpAdapter(helpList);
        recyclerH.setAdapter(hAdapter);

        outputHelpData();

    }

    private void outputHelpData() {

        HelpModel help = new HelpModel("My Health", "Allows to enter some " +
                "user basic information which helps in determining the cancer risk level. After which one is able to check their cancer health status after selecting symptoms experienced" +
                " from a list provided, the test is analysed and the feedback is instantly given to user.");
        helpList.add(help);

        help = new HelpModel("Find Doctor", "Choose doctor and book appointment for " +
                "consultations, screening tests and checkups.");
        helpList.add(help);

        help = new HelpModel("Track my Health", "Keeps track and records of cancer test taken by user with detailed information");
        helpList.add(help);

        help = new HelpModel("News", "See videos, podcasts and blog posts Cancer.Hub RSS feed.\n\n" +
                "The feed will automatically update when internet connection is available");
        helpList.add(help);
        help = new HelpModel("Information", "Guides on the few types of cancer, with information about treating cancer, managing side effects , managing the cost of care , and living with cancer." +
                "\n\nThe Information in this section is taken from an cloud database. An internet connection is required to download content for the first time you view it, and this may take a few seconds.Once " +
                "the content is downloaded it is stored on your device so you can view it any time\n\nWhen an internet connection is present, the app will periodically check for the new information from the database" +
                " and automatically download it.This ensure that your device has the most up-to-date information.");
        helpList.add(help);
        hAdapter.notifyDataSetChanged();

    }
}
