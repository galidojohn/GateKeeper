package com.example.gatekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private MaterialButton loginbtn;
    private MaterialButton registerbtn;
    private MaterialButton verifybtn;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //textView
        TextView name = (TextView) findViewById(R.id.inputname);
        TextView password = (TextView) findViewById(R.id.inputpassword);
        TextView register = findViewById(R.id.registerbtn);

        //buttons
         loginbtn = (MaterialButton) findViewById(R.id.loginbtn);
         registerbtn = (MaterialButton) findViewById(R.id.registerbtn);
         verifybtn = (MaterialButton) findViewById(R.id.verifybtn);
         mAuth = FirebaseAuth.getInstance();

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                    Intent intent = new Intent(MainActivity.this, Control.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
            }

        });
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}