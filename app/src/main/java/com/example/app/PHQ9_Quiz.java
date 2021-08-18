package com.example.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.databinding.ActivityQuizBinding;

public class PHQ9_Quiz extends AppCompatActivity {

    private ActivityQuizBinding binding;
    private PHQ9_Questionnaire questionnaire = new PHQ9_Questionnaire();

    private ImageButton back;
    private TextView question;
    private Button ans0, ans1, ans2, ans3;

    private int points = 0;
    private int question_index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        back = (ImageButton) findViewById(R.id.back);
        question = (TextView) findViewById(R.id.question);
        ans0 = (Button) findViewById(R.id.btn_0);
        ans1 = (Button) findViewById(R.id.btn_1);
        ans2 = (Button) findViewById(R.id.btn_2);
        ans3 = (Button) findViewById(R.id.btn_3);

        updateQuestion();

        //Bind answer's button listener here
        ans0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                points += 0;
                isLastQuestion();
            }
        });

        ans1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                points += 1;
                isLastQuestion();
            }
        });

        ans2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                points += 2;
                isLastQuestion();
            }
        });

        ans3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                points += 3;
                isLastQuestion();
            }
        });

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(PHQ9_Quiz.this);
                builder1.setMessage("This will cancel your current progress\nDo you want to stop taking the report?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // perform delete entry
                                startActivity(new Intent(PHQ9_Quiz.this, MainActivity2.class));
                                finish();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });
    }

    private void updateQuestion() {
        question.setText(questionnaire.getQuestion(question_index++));
        binding.header.setText(getString(R.string.question_index_display,question_index));
    }

    private void isLastQuestion() {
        if (question_index == PHQ9_Questionnaire.questionnaire.length) {
            Intent intent = new Intent(PHQ9_Quiz.this, DiagnosisResult.class);
            intent.putExtra("points", points);
            PHQ9_Quiz.this.finish();
            startActivity(intent);
        }
        else updateQuestion();
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(PHQ9_Quiz.this, MainActivity.class));
        finish();
    }
}
