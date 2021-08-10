package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app.databinding.ActivityAdminLoginBinding;
import com.example.app.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class admin_login extends AppCompatActivity {
    private ActivityAdminLoginBinding binding;
    private EditText mEmail, mPass;
    private Button signInbtn;
    private FirebaseAuth firebaseAuth;

    public void disableClipOnParents(View v) {
        if (v == null) {
            return;
        }
        if (v instanceof ViewGroup) {
            ((ViewGroup) v).setClipChildren(false);
        }
        disableClipOnParents((View) v.getParent());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mEmail = findViewById(R.id.user);
        mPass = findViewById(R.id.pass);
        signInbtn = findViewById(R.id.adminloginbtn);


        firebaseAuth = FirebaseAuth.getInstance();

        // admin signin button
        binding.adminloginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* begin admin sign in */
            }
        });
        signInbtn.setOnClickListener((v) -> {loginUser();});
    }

    public void onClick1(View v){
        startActivity(new Intent(admin_login.this,MainActivity.class));
        finish();
    }

    private void loginUser() {
        String email = mEmail.getText().toString();
        String password = mPass.getText().toString();

        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            if (!password.isEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email,password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(admin_login.this, "Login Successfully!!!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(admin_login.this ,ProfileActivity2.class));
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(admin_login.this, "Login failed", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
            else{
                mPass.setError("Empty fields are not allowed");
            }
        }
        else if (email.isEmpty()){
            mEmail.setError("Empty fields are not allowed");
        }
        else{
            mEmail.setError("Please enter correct email");
        }
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(admin_login.this, MainActivity.class));
        finish();
    }
}