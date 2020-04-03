package com.example.joy.cancerhub.activities;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.joy.cancerhub.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class MakeAppointment extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth auth;
    private String emailaddress;
    EditText txtDate, txtTime, txtName;
    Button btnAppointment;
    private int mYear, mMonth, mDay, mHour, mMinute;

    String date, time, name;
    String recipient, subject, textMessage;
    ProgressDialog prog;

    @SuppressLint({"WrongViewCast", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_appointment);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            emailaddress = bundle.getString("doctor_email");
        }

        txtDate = findViewById(R.id.in_date);
        txtTime = findViewById(R.id.in_time);
        txtName = findViewById(R.id.in_name);
        btnAppointment = findViewById(R.id.btnSendAppointment);

        txtTime.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (MotionEvent.ACTION_UP == motionEvent.getAction()) {
                    // Get Current Time
                    final Calendar c = Calendar.getInstance();
                    mHour = c.get(Calendar.HOUR_OF_DAY);
                    mMinute = c.get(Calendar.MINUTE);
                    // Launch Time Picker Dialog
                    TimePickerDialog timePickerDialog = new TimePickerDialog(MakeAppointment.this,
                            new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay,
                                                      int minute) {
                                    txtTime.setText(hourOfDay + ":" + minute);
                                }
                            }, mHour, mMinute, false);
                    timePickerDialog.show();
                }
                return true;
            }
        });

        txtDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEvent.ACTION_UP == event.getAction()) {

                    // Get Current Date
                    final Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);
                    DatePickerDialog datePickerDialog = new DatePickerDialog(MakeAppointment.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {
                                    txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                }
                return true; // return is important...
            }
        });

        btnAppointment.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        date = txtDate.getText().toString();
        time = txtTime.getText().toString();
        name = txtName.getText().toString();

        recipient = emailaddress;
        subject = "Appointment Request";
        textMessage = "Request by: \n" + name + ",\nOn date: " + date + ",\nTime: " + time;

        Log.i("SendMailActivity", "Send Button Clicked.");

        String fromEmail = "cancerhub0@gmail.com";
        String fromPassword = "Manager@20";
        String toEmails = recipient;
        List<String> toEmailList = Arrays.asList(toEmails
                .split("\\s*,\\s*"));
        Log.i("SendMailActivity", "To List: " + toEmailList);
        String emailSubject = subject;
        String emailBody = textMessage;
        new SendMailTask(MakeAppointment.this).execute(fromEmail,
                fromPassword, toEmailList, emailSubject, emailBody);

    }

}