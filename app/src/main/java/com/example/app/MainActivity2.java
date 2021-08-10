package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.app.databinding.ActivityMainScreenBinding;

public class MainActivity2 extends AppCompatActivity {

    private ActivityMainScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // handle click video image button
        binding.videoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /// start activity to youtube player

                startActivity(new Intent(MainActivity2.this, ViewVideo.class));
                

            }
        });
    }




    @Override
    public void onBackPressed(){
        startActivity(new Intent(MainActivity2.this, MainActivity.class));
        finish();
    }
}