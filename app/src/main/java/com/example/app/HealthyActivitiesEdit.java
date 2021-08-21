package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HealthyActivitiesEdit extends AppCompatActivity {
    EditText mTitleEt, mDetailEt, mContentEt;
    ImageButton back;
    Button updateBtn,deleteBtn;
    private DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthy_activities_edit);

        // handle back button pressed
        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HealthyActivitiesEdit.super.onBackPressed();
            }
        });

        // handle update button pressed
        updateBtn = findViewById(R.id.updateBtn);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });

        // handle delete button pressed
        deleteBtn = findViewById(R.id.deleteBtn);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref = FirebaseDatabase.getInstance().getReference("Activites");
                ref.child(getIntent().getExtras().get("index").toString()).removeValue();
                Toast.makeText(HealthyActivitiesEdit.this, "Deleted", Toast.LENGTH_SHORT).show();
                mTitleEt.setText("");
                mDetailEt.setText("");
                mContentEt.setText("");
            }
        });


        mDetailEt = findViewById(R.id.dDescriptionEt);
        mTitleEt = findViewById(R.id.dTitleEt);
        mContentEt = findViewById(R.id.ContentEt);

        String title =  (String) getIntent().getStringExtra("title");
        String detail = (String) getIntent().getStringExtra("description");
        String content = getIntent().getExtras().get("content").toString();

        mTitleEt.setText(title);
        mDetailEt.setText(detail);
        mContentEt.setText(content);
        mDetailEt.setMovementMethod(new ScrollingMovementMethod());
        mContentEt.setMovementMethod(new ScrollingMovementMethod());


    }

    public void update(){
        ref = FirebaseDatabase.getInstance().getReference("Activites");
        ref.child(getIntent().getExtras().get("index").toString()).child("title").setValue(mTitleEt.getText().toString());
        ref.child(getIntent().getExtras().get("index").toString()).child("content").setValue(mContentEt.getText().toString());
        ref.child(getIntent().getExtras().get("index").toString()).child("description").setValue(mDetailEt.getText().toString());
        Toast.makeText(HealthyActivitiesEdit.this, "Updated", Toast.LENGTH_SHORT).show();
    }

}