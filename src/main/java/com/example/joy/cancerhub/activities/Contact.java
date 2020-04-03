package com.example.joy.cancerhub.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joy.cancerhub.R;

public class Contact extends AppCompatActivity implements View.OnClickListener {

    TextView textContactEmail, textContactCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Toolbar toolbar = findViewById(R.id.toolbarContact);
        toolbar.setTitle("Contact us");
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

        textContactCall = findViewById(R.id.textContactCall);
        textContactEmail = findViewById(R.id.textContactEmail);
        textContactCall.setOnClickListener(this);
        textContactEmail.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.textContactCall){

            String phone = "0750443609";
            try {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                String temp = "tel:" + phone;
                intent.setData(Uri.parse(temp));
                startActivity(intent);
            }catch(Exception e) {
                Toast.makeText(getApplicationContext(),"Your call has failed...",
                        Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
        if (id == R.id.textContactEmail){
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"joyluseno2@gmail.com"});
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "");
            emailIntent.setType("plain/text");
            try {
                startActivity(Intent.createChooser(emailIntent, "Send mail using..."));
                Log.i("TAG", "Email received");
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(Contact.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
