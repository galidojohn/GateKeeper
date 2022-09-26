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

public class MainActivity extends AppCompatActivity {

    private MaterialButton loginbtn;
    private MaterialButton registerbtn;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //textView
        TextView logemail = (TextView) findViewById(R.id.inputemail);

        //buttons
        loginbtn = (MaterialButton) findViewById(R.id.loginbtn);
        registerbtn = (MaterialButton) findViewById(R.id.registerbtn);
        mAuth = FirebaseAuth.getInstance();

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
            }
        });
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String em = logemail.getText().toString();
                String pass = logemail.getText().toString();

                if (TextUtils.isEmpty(em)) {
                    logemail.setError("Email cannot be empty");
                    logemail.requestFocus();
                } else if (TextUtils.isEmpty(pass)) {
                    TextView inputpassword = (TextView) findViewById(R.id.inputpassword);
                    inputpassword.setError("Password cannot be empty");
                    inputpassword.requestFocus();
                } else {
                    mAuth.signInWithEmailAndPassword(em, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, Control.class));
                            } else {
                                Toast.makeText(MainActivity.this, "Registration error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }
}
