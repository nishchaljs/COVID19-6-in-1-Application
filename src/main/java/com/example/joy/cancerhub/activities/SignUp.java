//package com.example.joy.cancerhub.activities;
//
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.joy.cancerhub.R;
//import com.example.joy.cancerhub.models.User;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.FirebaseDatabase;
//
//public class SignUp extends AppCompatActivity implements View.OnClickListener {
//
//    private EditText edit_fname, edit_username, edit_email, edit_password, c_password;
//    private Button buttonSignup;
//    private TextView textViewSignin;
//    private ProgressDialog progressDialog;
//
//    private FirebaseAuth firebaseAuth;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sign_up);
//
//        firebaseAuth = FirebaseAuth.getInstance();
//
//        edit_fname = findViewById(R.id.input_fname);
//        edit_email = findViewById(R.id.input_email2);
//        edit_username = findViewById(R.id.input_Username);
//        edit_password = findViewById(R.id.input_password2);
//        c_password = findViewById(R.id.input_cpassword);
//        buttonSignup = findViewById(R.id.btn_signup);
//        textViewSignin = findViewById(R.id.link_signin);
//
//        progressDialog = new ProgressDialog(this);
//
//        //attaching listener to button
//        buttonSignup.setOnClickListener(this);
//        textViewSignin.setOnClickListener(this);
//
//    }
//
//
//    private void registerUser() {
//        final String fname = edit_fname.getText().toString().trim();
//        final String username = edit_username.getText().toString().trim();
//        final String email = edit_email.getText().toString().trim();
//        String password = edit_password.getText().toString().trim();
//        String cpassword = c_password.getText().toString().trim();
//
//
//        if (TextUtils.isEmpty(fname)) {
//            Toast.makeText(this, "Please enter fullname", Toast.LENGTH_LONG).show();
//            return;
//        }
//        if (TextUtils.isEmpty(username)) {
//            Toast.makeText(this, "Please enter username", Toast.LENGTH_LONG).show();
//            return;
//        }
//        if (TextUtils.isEmpty(email)) {
//            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
//            return;
//        }
//        if (TextUtils.isEmpty(password)) {
//            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
//            return;
//        }
//        if (TextUtils.isEmpty(cpassword)) {
//            Toast.makeText(this, "Please confirm password", Toast.LENGTH_LONG).show();
//            return;
//        }
//        if (password.length() < 6) {
//            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (!cpassword.equals(password)) {
//            Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        progressDialog.setMessage("Registering Please Wait...");
//        progressDialog.show();
//
//        firebaseAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            User user = new User(fname, username, email);
//                            FirebaseDatabase.getInstance().getReference("Users")
//                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if (task.isSuccessful()) {
//                                        Toast.makeText(SignUp.this, "Registration Successful",
//                                                Toast.LENGTH_LONG).show();
//                                        finish();
//                                        startActivity(new Intent(getApplicationContext(), SignIn.class));
//                                    } else {
//                                        Toast.makeText(SignUp.this, "Registration Error",
//                                                Toast.LENGTH_LONG).show();
//                                    }
//                                }
//                            });
//                        } else {
//                            Toast.makeText(SignUp.this, task.getException().getMessage(),
//                                    Toast.LENGTH_LONG).show();
//                        }
//                        progressDialog.dismiss();
//                    }
//                });
//    }
//
//    @Override
//    public void onClick(View view) {
//
//        if (view == buttonSignup) {
//            registerUser();
//        }
//
//        if (view == textViewSignin) {
//            finish();
//            startActivity(new Intent(this, SignIn.class));
//        }
//    }
//}
