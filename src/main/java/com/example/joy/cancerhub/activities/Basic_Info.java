package com.example.joy.cancerhub.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.joy.cancerhub.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Basic_Info extends AppCompatActivity {

    RadioButton radioAge1, radioAge2, radioAge3;
    RadioButton radioArea1, radioArea2;
    RadioButton radioAnaemic1, radioAnaemic2;
    RadioButton radioMale, radioFemale;
    RadioButton radioFCHY, radioFCHN;
    RadioButton radioPrevDiagnosis1, radioPrevDiagnosis2;
    Spinner spinHabits, spinHazards;
    RadioGroup radioGroup1, radioGroup2, radioGroup3, radioGroup4, radioGroup5, radioGroup6;
    Button btP;

    int AgeScore, AreaScore, HabitsSore, HazardsScore, AnaemiaScore, GenderScore, FHBGScore, HBGScore;

    FirebaseDatabase fDbs;
    DatabaseReference dRef;
    FirebaseUser fuser;

    public static float riskScore;
    public static String text;
    public static String advice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_info);

        fuser = FirebaseAuth.getInstance().getCurrentUser();

        radioAge1 = findViewById(R.id.radio_Age1);
        radioAge2 = findViewById(R.id.radio_Age2);
        radioAge3 = findViewById(R.id.radio_Age3);
        radioArea1 = findViewById(R.id.radio_Area1);
        radioArea2 = findViewById(R.id.radio_Area2);
        radioAnaemic1 = findViewById(R.id.radioAnaemia1);
        radioAnaemic2 = findViewById(R.id.radioAnaemia2);
        radioMale = findViewById(R.id.radioGender1);
        radioFemale = findViewById(R.id.radioGender2);
        radioFCHY = findViewById(R.id.radiFHBg1);
        radioFCHN = findViewById(R.id.radiFHBg2);
        radioPrevDiagnosis1 = findViewById(R.id.radiHBg1);
        radioPrevDiagnosis2 = findViewById(R.id.radiHBg2);
        spinHabits = findViewById(R.id.habitsSpin);
        spinHazards = findViewById(R.id.hazardsSpin);
        radioGroup1 = findViewById(R.id.radioG1);
        radioGroup2 = findViewById(R.id.radioG2);
        radioGroup3 = findViewById(R.id.radioG3);
        radioGroup4 = findViewById(R.id.radioG4);
        radioGroup5 = findViewById(R.id.radioG5);
        radioGroup6 = findViewById(R.id.radioG6);


        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.radio_Age1) {
                    AgeScore = 3;
                } else if (checkedId == R.id.radio_Age2) {
                    AgeScore = 5;
                } else if (checkedId == R.id.radio_Age3) {
                    AgeScore = 6;
                }
            }

        });
        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.radio_Area1) {
                    AreaScore = 6;
                } else if (checkedId == R.id.radio_Area2) {
                    AreaScore = 3;
                }
            }
        });
        spinHabits.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int checkedId, long l) {
                switch (checkedId) {
                    case 0:
                        HabitsSore = 0;
                        break;
                    case 1:
                        HabitsSore = 4;
                        break;
                    case 2:
                        HabitsSore = 4;
                        break;
                    case 3:
                        HabitsSore = 6;
                        break;
                    case 4:
                        HabitsSore = 3;
                        break;
                    case 5:
                        HabitsSore = 2;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinHazards.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int checkedId, long l) {
                switch (checkedId) {
                    case 0:
                        HazardsScore = 0;
                        break;
                    case 1:
                        HazardsScore = 4;
                        break;
                    case 2:
                        HazardsScore = 4;
                        break;
                    case 3:
                        HazardsScore = 3;
                        break;
                    case 4:
                        HazardsScore = 2;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        radioGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.radioAnaemia1) {
                    AnaemiaScore = 3;
                } else if (checkedId == R.id.radioAnaemia2) {
                    AnaemiaScore = 1;
                }
            }
        });
        radioGroup4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.radioGender1) {
                    GenderScore = 1;
                } else if (checkedId == R.id.radioGender2) {
                    GenderScore = 2;
                }

            }

        });
        radioGroup5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.radiFHBg1) {
                    FHBGScore = 5;
                } else if (checkedId == R.id.radiFHBg2) {
                    FHBGScore = 1;
                }
            }
        });
        radioGroup6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.radiHBg1) {
                    HBGScore = 6;
                } else if (checkedId == R.id.radiHBg2) {
                    HBGScore = 1;
                }
            }
        });
        btP = findViewById(R.id.btnPersonal);
        btP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getScore();
                radioGroup1.clearCheck();
                radioGroup2.clearCheck();
                radioGroup3.clearCheck();
                radioGroup4.clearCheck();
                radioGroup5.clearCheck();
                radioGroup6.clearCheck();
                spinHazards.setSelection(0);
                spinHabits.setSelection(0);
                riskScore = 0;
            }
        });
    }

    private void getScore() {
        if (radioGroup1.getCheckedRadioButtonId() != -1 && radioGroup2.getCheckedRadioButtonId() != -1 && radioGroup3.getCheckedRadioButtonId() != -1 && radioGroup4.getCheckedRadioButtonId() != -1 && radioGroup5.getCheckedRadioButtonId() != -1 && radioGroup6.getCheckedRadioButtonId() != -1) {

            riskScore = (float) ((AgeScore + AreaScore + HabitsSore + HazardsScore + AnaemiaScore + GenderScore + FHBGScore + HBGScore) * 2.6773);

            if (riskScore < 30.0) {
                text = "Low level";
                advice = "Eat more fruits , do some body workouts to keep fit and asses your health status more often";
            } else if (riskScore >= 30.0 && riskScore <= 65.0) {
                text = "Intermediate level";
                advice = "We would strongly advice you to watch your eating habits, keep fit, seek medical advice from other doctor, read more on cancer";
            } else {
                text = "High level";
                advice = "This is a critical level, you should see a doctor for scanning or more cancer tests for early treatment  ";
            }

            Intent intent = new Intent(this, MyHealth.class);
            Bundle bundle = new Bundle();
            bundle.putFloat("riskScore", riskScore);
            bundle.putString("prognosis", text);
            bundle.putString("advice", advice);
            intent.putExtra("RiskScore", bundle);
            startActivity(intent);

        } else {
            Toast.makeText(this, "Your Information is incomplete,\n Please answer all the questions!", Toast.LENGTH_SHORT).show();

        }
    }
}