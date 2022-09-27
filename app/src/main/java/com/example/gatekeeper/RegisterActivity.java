package com.example.gatekeeper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    EditText regname, regemail, regpassword, regrepassword;
    private MaterialButton registerbtn1;
    FirebaseAuth mAuth;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("Users");

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regname = findViewById(R.id.regname);
        regemail = findViewById(R.id.regemail);
        regpassword = findViewById(R.id.regpassword);
        regrepassword = findViewById(R.id.regrepassword);
        registerbtn1 = (MaterialButton) findViewById(R.id.registerbtn1);

        mAuth = FirebaseAuth.getInstance();
        registerbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });
        registerbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });
    }
    private void createUser(){
        String em = regemail.getText().toString();
        String pass = regpassword.getText().toString();

        //checks if entry is correct
        if (TextUtils.isEmpty(em)){
            regemail.setError("Email cannot be empty");
            regemail.requestFocus();
        }else if (TextUtils.isEmpty(pass)){
            regpassword.setError("Password cannot be empty");
            regpassword.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(em, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        //pushing data to realtime database
                        String name = regname.getText().toString();
                        root.child("Users").setValue(name);
                        String email = regemail.getText().toString();
                        root.setValue(email);
                        String password = regpassword.getText().toString();
                        root.setValue(password);
                        HashMap<String, String> userMap = new HashMap<>();
                        userMap.put("name", name);
                        userMap.put("email", email);
                        userMap.put("password", password);
                        root.push().setValue(userMap);
                        //popup message
                        Toast.makeText(RegisterActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    }else{
                        Toast.makeText(RegisterActivity.this, "Registration error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }
}