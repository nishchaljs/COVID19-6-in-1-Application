package com.example.joy.cancerhub.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

import com.example.joy.cancerhub.R;

public class Coping extends AppCompatActivity {
    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coping);

        Toolbar toolbar = findViewById(R.id.toolbarCoping);
        toolbar.setTitle("Coping");
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


        webView = findViewById(R.id.webViewCope);
        webView.getSettings().setJavaScriptEnabled(true);

        String htmlAsString = getString(R.string.html);
        webView.loadDataWithBaseURL(null, htmlAsString, "text/html", "utf-8", null);

        // webView.loadData(cancerType.getSigns_symptoms(), "text/html", "UTF-8");


    }
}
