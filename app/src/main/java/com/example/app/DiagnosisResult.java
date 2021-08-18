package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.databinding.ActivityDiagnosisResultBinding;

public class DiagnosisResult extends AppCompatActivity {

    private ActivityDiagnosisResultBinding binding;

    private int points;
    private TextView point_display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDiagnosisResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        point_display = (TextView) findViewById(R.id.points_display);
        points = getIntent().getIntExtra("points",points);
        point_display.setText(String.valueOf(points));

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DiagnosisResult.this, MainActivity2.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(DiagnosisResult.this, MainActivity2.class));
        finish();
    }
}
