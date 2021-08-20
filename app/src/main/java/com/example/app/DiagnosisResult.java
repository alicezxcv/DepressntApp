package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.databinding.ActivityDiagnosisResultBinding;

public class DiagnosisResult extends AppCompatActivity {

    private ActivityDiagnosisResultBinding binding;

    private int points;
    private TextView result_display, conclusion_display, advice_display;
    private ProgressBar points_circular;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDiagnosisResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        points = getIntent().getIntExtra("points",points);

        // PHQ-9 score would range from 0 - 27 pts
        // 27 == 100% => Max == 27
        points_circular = findViewById(R.id.points_circular);
        points_circular.setProgress(0);
        points_circular.setMax(27);
        points_circular.setProgress(points);

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
