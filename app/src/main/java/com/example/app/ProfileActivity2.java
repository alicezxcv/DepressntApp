package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.app.databinding.ActivityProfile2Binding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity2 extends AppCompatActivity {

    //view binding
    private ActivityProfile2Binding binding;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfile2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //init firebase auth
        firebaseAuth = FirebaseAuth.getInstance();
        checkUser();

        //handle click, logout
        binding.logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                checkUser();
            }
        });

        //handle click chat button
        binding.chatBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent= new Intent(getApplicationContext(),Room.class);
                intent.putExtra("user_name","Admin");
                startActivity(intent);
            }
        });
    }

    private void checkUser() {
        // get current user
        FirebaseUser firebaseUser= firebaseAuth.getCurrentUser();
        if (firebaseUser == null){
            // user not logged in
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }
        else{
            // user logged in
            // get user info
            String email = firebaseUser.getEmail();
            // set email
            binding.emailTv.setText((email));
        }
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(ProfileActivity2.this, admin_login.class));
        finish();
    }
}