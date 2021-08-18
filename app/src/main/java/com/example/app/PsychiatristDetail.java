package com.example.app;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class PsychiatristDetail extends AppCompatActivity {
    EditText mTitleTv, mDetailTv;
    ImageButton back,save;
    private DatabaseReference ref;
    private Button del;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psychiatrist_detail);

        // handle back button
        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        // handle save button
        save = findViewById(R.id.saveBtn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
                Toast.makeText(PsychiatristDetail.this, "Saved", Toast.LENGTH_SHORT).show();
            }
        });

        
        // handle delete button
        del = findViewById(R.id.deleteBtn);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref = FirebaseDatabase.getInstance().getReference("Psychiatrists");
                ref.child(getIntent().getExtras().get("index").toString()).removeValue();
                Toast.makeText(PsychiatristDetail.this, "Deleted", Toast.LENGTH_SHORT).show();
                mTitleTv.setText("");
                mDetailTv.setText("");
            }
        });


        mDetailTv = findViewById(R.id.dDescriptionTv);

        mTitleTv = findViewById(R.id.dTitleTv);

        String title =  (String) getIntent().getStringExtra("title");
        String detail = ((String) getIntent().getStringExtra("detail"));

        mTitleTv.setText(title);
        mDetailTv.setText(detail);
        mDetailTv.setMovementMethod(new ScrollingMovementMethod());
    }

    void update(){
        ref = FirebaseDatabase.getInstance().getReference("Psychiatrists");
        ref.child(getIntent().getExtras().get("index").toString()).child("description").setValue(mDetailTv.getText().toString());
        ref.child(getIntent().getExtras().get("index").toString()).child("title").setValue(mTitleTv.getText().toString());
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mDetailTv.getWindowToken(),
                InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }

}