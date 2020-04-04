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
import com.example.joy.cancerhub.adapters.HelpAdapter;
import com.example.joy.cancerhub.adapters.ProtectAdapter;
import com.example.joy.cancerhub.models.HelpModel;
import com.example.joy.cancerhub.models.Protect;

import java.util.ArrayList;
import java.util.List;

public class protect extends AppCompatActivity {

    private List<Protect> helpList = new ArrayList<>();
    private RecyclerView recyclerH;
    private ProtectAdapter hAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protect);

        Toolbar toolbar = findViewById(R.id.toolbarHelp);
        toolbar.setTitle("Protect Yourself");
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

        hAdapter = new ProtectAdapter(helpList);
        recyclerH.setAdapter(hAdapter);

        outputHelpData();

    }

    private void outputHelpData() {

        @SuppressLint("ResourceType") View view = (View)findViewById(R.layout.layout_protect);

        Protect help = new Protect("Wash your Hands with soap for 20 seconds", (R.drawable.wash_hands));
        helpList.add(help);
        help = new Protect("Avoid touching eyes, nose and mouth", (R.drawable.avoid_touch));
        helpList.add(help);
        help = new Protect("Cover your nose and mouth", (R.drawable.elbow));
        helpList.add(help);
        help = new Protect("Social Distance - 1 meter", (R.drawable.social_distance));
        helpList.add(help);
        help = new Protect("Cover mouth with mask", (R.drawable.mask));
        helpList.add(help);
        hAdapter.notifyDataSetChanged();

    }
}

