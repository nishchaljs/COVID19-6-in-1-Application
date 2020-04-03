package com.example.joy.cancerhub.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
//import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.joy.cancerhub.R;

/**
 * Created by Shreyansh Singh on 13-01-2017.
 */

public class SwitchActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    SwitchCompat mySwitch = null;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminder_detail);


        mySwitch = (SwitchCompat) findViewById(R.id.switch2);
        mySwitch.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            Spinner dropdown = (Spinner) findViewById(R.id.spinner);
            int a[]={1,2,3,4};
            ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                    .createFromResource(this, a[0], android.R.layout.simple_spinner_item);

            // Specify the layout to use when the list of choices appears
            staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // Apply the adapter to the spinner
            dropdown.setAdapter(staticAdapter);
        } else {
            TextView every = (TextView) findViewById(R.id.textView3);
            Spinner everytime =(Spinner) findViewById(R.id.spinner);
            EditText time =(EditText) findViewById(R.id.everytime);
            every.setVisibility(View.INVISIBLE);
            everytime.setVisibility(View.INVISIBLE);
            time.setVisibility(View.INVISIBLE);
        }
    }

}
