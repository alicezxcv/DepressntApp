package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MedicineView extends AppCompatActivity {
    ImageButton back;
    TextView mTitleTv, mDetailTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_view);
        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MedicineView.super.onBackPressed();
            }
        });
        String title =  (String) getIntent().getStringExtra("title");
        String content =  (String) getIntent().getStringExtra("content");

        mDetailTv = findViewById(R.id.medDescription);
        mTitleTv = findViewById(R.id.medTitle);

        mTitleTv.setText(title);
        mDetailTv.setText(content);
        mDetailTv.setMovementMethod(new ScrollingMovementMethod());
    }
}