package com.example.gatekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

public class Control extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView name, email, password, conpassword;
        TextView register = findViewById(R.id.registerbtn);
        TextView verify = findViewById(R.id.registerbtn);

        name = findViewById(R.id.inputname);
        email = findViewById(R.id.inputemail);
        password = findViewById(R.id.inputpassword);
        conpassword = findViewById(R.id.repassword);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Control.this, MainActivity.class));


            }
        });
    }
}