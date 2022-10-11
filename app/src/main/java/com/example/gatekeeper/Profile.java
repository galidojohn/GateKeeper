package com.example.gatekeeper;


import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Profile extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    TextView titleemail, titlepassword, nametitle;
    Button updatebtn;
    EditText regname1;
    EditText regemail1;
    EditText regpassword1;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() == null){
            return;
        }
        FirebaseUser user = firebaseAuth.getCurrentUser();
        regemail1 = (EditText) findViewById(R.id.regemail1);
        regemail1.setText(""+user.getEmail());

        regname1 = (EditText) findViewById(R.id.regname1);
        regname1.setText(""+user.getEmail());

        regpassword1 = (EditText) findViewById(R.id.regpassword1);
        regname1.setText(""+user.getEmail());

        databaseReference = FirebaseDatabase.getInstance().getReference();
        updatebtn = (Button)findViewById(R.id.updatebtn);
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInfo();
                startActivity(new Intent(Profile.this,Control.class));
            }
        });
    }
    private void saveUserInfo(){
        String name = regname1.getText().toString().trim();
        String email = regemail1.getText().toString().trim();
        String password = regpassword1.getText().toString();

        UsersHelper helper = new UsersHelper(name,email, password);
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).setValue(helper);
        Toast.makeText(this, "INFO SAVED...",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(),Control.class));
    }
}