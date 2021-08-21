package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app.databinding.ActivityDiagnosisResultBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DiagnosisResult extends AppCompatActivity {

    private ActivityDiagnosisResultBinding binding;

    private int points;
    private ProgressBar points_circular;
    private TextView display_result, display_conclude, display_advice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDiagnosisResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.check.setVisibility(View.GONE);
        display_result = findViewById(R.id.result);
        display_conclude = findViewById(R.id.conclude);
        display_advice = findViewById(R.id.advice);
        points_circular = findViewById(R.id.points_circular);

        points = getIntent().getIntExtra("points",points);

        // PHQ-9 score would range from 0 - 27 pts
        // 27 == 100% => Max == 27
        points_circular.setProgress(0);
        points_circular.setMax(27);
        points_circular.setProgress(points);

        // Display result
        // Lower severity
        if (points <= 9) {
            // None
            if (points == 0) {
                binding.check.setVisibility(View.VISIBLE);
                display_result.setText(R.string.diagnosis_result_none);
                display_conclude.setText(R.string.diagnosis_result_none_conclusion);
            }
            // Minimum
            else if (points > 0 & points <= 4) {
                display_result.setText(R.string.diagnosis_result_minimum);
                display_conclude.setText(R.string.diagnosis_result_none_conclusion);
            }
            // Mild
            else if (points > 4 & points <= 9) {
                display_result.setText(R.string.diagnosis_result_mild);
                display_conclude.setText(R.string.diagnosis_result_mild_conclusion);
            }
            display_advice.setText(R.string.diagnosis_result_low_priority_advice);
        }
        // Considerable severity
        else
        {
            // Moderate
            if (points > 9 && points <= 14) {
                display_result.setText(R.string.diagnosis_result_moderate);
                display_conclude.setText(R.string.diagnosis_result_moderate_conclusion);
                display_advice.setText(R.string.diagnosis_result_mid_priority_advice);
            }
            // Moderately severe
            else if (points > 14 && points <= 19) {
                display_result.setText(R.string.diagnosis_result_moderately_severe);
                display_conclude.setText(R.string.diagnosis_result_moderately_severe_conclusion);
                display_advice.setText(R.string.diagnosis_result_above_mid_advice);
            }
            // Omae wa mou shindeiru
            else if (points > 19 && points <= 27) {
                display_result.setText(R.string.diagnosis_result_severe);
                display_conclude.setText(R.string.diagnosis_result_severe_conclusion);
                display_advice.setText(R.string.diagnosis_result_high_priority_advice);
            }
        }

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(DiagnosisResult.this, MainActivity2.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed(){
        //startActivity(new Intent(DiagnosisResult.this, MainActivity2.class));
        finish();
    }
}
