package com.example.gatekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class Control extends AppCompatActivity {

    private MaterialButton unlockbtn;
    private MaterialButton lockbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        unlockbtn = (MaterialButton) findViewById(R.id.unlockbtn);
        lockbtn = (MaterialButton) findViewById(R.id.lockbtn);

    }
}