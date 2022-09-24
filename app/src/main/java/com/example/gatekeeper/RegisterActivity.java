package com.example.gatekeeper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class RegisterActivity extends AppCompatActivity {

    EditText regname = (EditText) findViewById(R.id.regname);
    EditText regemail = (EditText) findViewById(R.id.regemail);
    EditText regpassword = (EditText) findViewById(R.id.regpassword);
    EditText regrepassword = (EditText) findViewById(R.id.regrepassword);
    TextView register = findViewById(R.id.registerbtn);
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regname = findViewById(R.id.regname);
        regemail = findViewById(R.id.regemail);
        regpassword = findViewById(R.id.regpassword);
        regrepassword = findViewById(R.id.regrepassword);

        mAuth = FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });
    }
    private void createUser(){
        String em = regemail.getText().toString();
        String pass = regemail.getText().toString();

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