package com.example.joy.cancerhub.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.joy.cancerhub.R;
import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        firebaseAuth = FirebaseAuth.getInstance();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(firebaseAuth.getCurrentUser() != null){
                    startActivity(new Intent(getApplicationContext(), Home.class));
                    finish();
                }
                else{
                    startActivity(new Intent(getApplicationContext(), Home.class));
                    finish();
                }
            }
        }, 4 * 1000);
    }
}
