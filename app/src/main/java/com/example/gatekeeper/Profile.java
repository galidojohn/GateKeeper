package com.example.gatekeeper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity {

    FirebaseAuth mAuth;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("Users");
    private StorageReference reference = FirebaseStorage.getInstance().getReference();

    ImageView profile;
    private MaterialButton updatebtn;
    private String name, email, password;
    private TextView editname;
    private TextView editemail;
    private TextView editpassword;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profile = findViewById(R.id.profile);
        updatebtn = findViewById(R.id.updatebtn);
        editname = findViewById(R.id.editname);
        editemail = findViewById(R.id.editemail);
        editpassword = findViewById(R.id.editpassword);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();

        if (firebaseUser == null){
            Toast.makeText(Profile.this, "Something went wrong",Toast.LENGTH_SHORT).show();
        }else {
            showProfile(firebaseUser);
        }
    }


    private void showProfile(FirebaseUser firebaseUser) {
        String userId = firebaseUser.getUid();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UsersHelper helper = snapshot.getValue(UsersHelper.class);
                if (helper != null){
                    name = firebaseUser.getDisplayName();
                    email = firebaseUser.getEmail();

                    name = helper.name;
                    email = helper.email;
                    password = helper.password;

                    editname.setText(name);
                    editemail.setText(email);
                    editpassword.setText(password);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Profile.this, "Something went wrong",Toast.LENGTH_SHORT).show();
            }
        });{

        }
    }

}