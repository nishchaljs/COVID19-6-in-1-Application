package com.example.joy.cancerhub.activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joy.cancerhub.R;
import com.example.joy.cancerhub.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    FirebaseAuth mAuth;
    DatabaseReference dbRef;
    FirebaseUser fuser;

    private ProgressDialog progressDialog;
    public TextView textWelcomeMessage, emailprofile, profilefull;
    GridLayout gridLayout;
    CardView cardView1, cardView2, cardView3, cardView4, cardView5, cardView6, cardView7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //mAuth = FirebaseAuth.getInstance();

        //fuser = FirebaseAuth.getInstance().getCurrentUser();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        progressDialog = new ProgressDialog(this);

        gridLayout = findViewById(R.id.mainGrid);
        cardView1 = findViewById(R.id.card_view1);
        cardView2 = findViewById(R.id.card_view2);
        cardView3 = findViewById(R.id.card_view3);
        cardView4 = findViewById(R.id.card_view4);
        //cardView5 = findViewById(R.id.card_view5);
        cardView6 = findViewById(R.id.card_view6);
        cardView7 = findViewById(R.id.card_view7);


        cardView1.setOnClickListener(this);
        cardView2.setOnClickListener(this);
        cardView3.setOnClickListener(this);
        cardView4.setOnClickListener(this);
        //cardView5.setOnClickListener(this);
        cardView6.setOnClickListener(this);
        cardView7.setOnClickListener(this);

        textWelcomeMessage = findViewById(R.id.textWelcomeMessage);

        View navigationHeaderLayout = navigationView.getHeaderView(0);
        emailprofile = navigationHeaderLayout.findViewById(R.id.emailprofile);
        profilefull = navigationHeaderLayout.findViewById(R.id.profilefull);
       // dbRef = FirebaseDatabase.getInstance().getReference("Users").child(fuser.getUid());


    }

    @Override
    protected void onStart() {

        super.onStart();
//        dbRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                //User user = dataSnapshot.getValue(User.class);
//                textWelcomeMessage.setText("Welcome\t" + ",");
//                profilefull.setText("xyz");
//                emailprofile.setText("swe.com");
//            }

//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("IntentReset")
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_Health) {
            startActivity(new Intent(this, Basic_Info.class));
        } else if (id == R.id.nav_Appointment) {
            startActivity(new Intent(this, FindDoctor.class));
        } else if (id == R.id.nav_manage) {

            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"joyluseno2@gmail.com"});
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback CancerHub");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "");
            emailIntent.setType("plain/text");
            try {
                startActivity(Intent.createChooser(emailIntent, "Send mail using..."));
                Log.i("TAG", "We appreciate your Feedback");
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(Home.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.nav_help) {

            startActivity(new Intent(this, Help.class));

        } else if (id == R.id.nav_about) {

            startActivity(new Intent(this, About.class));

        } else if (id == R.id.nav_contact) {

            startActivity(new Intent(this, Contact.class));}

//        } else if (id == R.id.nav_logout) {
//            progressDialog.setMessage("Logging out...");
//            progressDialog.show();
//            Runnable progressRunnable = new Runnable() {
//                @Override
//                public void run() {
//                    progressDialog.dismiss();
//                    mAuth.signOut();
//                    finish();
//                    startActivity(new Intent(getApplicationContext(), SignIn.class));
//                }
//            };
//            Handler pdCanceller = new Handler();
//            pdCanceller.postDelayed(progressRunnable, 3000);
//        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        if (view == cardView1) {
            startActivity(new Intent(this, Basic_Info.class));
        }
        if (view == cardView2) {
            startActivity(new Intent(this, Help.class));
        }
        if (view == cardView3) {
            //finish();
            startActivity(new Intent(this, FindDoctor.class));
        }
        if (view == cardView4) {
            //finish();
            startActivity(new Intent(this, Main_Reminder.class));
        }
        if (view == cardView5) {
            //finish();
            startActivity(new Intent(this, NewsMain.class));
        }
        if (view == cardView6) {
            //finish();
            startActivity(new Intent(this, Information.class));
        }
        if (view == cardView7) {
            //finish();
            startActivity(new Intent(this, protect.class));
        }
    }
}