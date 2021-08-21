package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MedicineViewEdit extends AppCompatActivity {
    ImageButton back;
    TextView mTitleTv;
    EditText mDetailEd;
    String title, index, content;
    Button updateBtn;
    private DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_view_edit);
        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        updateBtn = findViewById(R.id.updateMedEdBtn);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateMedView();
            }
        });
        title =  (String) getIntent().getStringExtra("title");
        content =  (String) getIntent().getStringExtra("content");
        index =  (String) getIntent().getStringExtra("index");

        mDetailEd = findViewById(R.id.medDescriptionEd);
        mTitleTv = findViewById(R.id.medTitle);

        mTitleTv.setText(title);
        mDetailEd.setText(content);
        mDetailEd.setMovementMethod(new ScrollingMovementMethod());
    }

    public void updateMedView(){
        ref = FirebaseDatabase.getInstance().getReference("Medicine");
        switch (title){
            case "Overview":
                ref.child(index).child("overview").setValue(mDetailEd.getText().toString());
                break;
            case "Interactions":
                ref.child(index).child("interaction").setValue(mDetailEd.getText().toString());
                break;
            case "Missed":
                ref.child(index).child("missed").setValue(mDetailEd.getText().toString());
                break;
            case "Notes":
                ref.child(index).child("note").setValue(mDetailEd.getText().toString());
                break;
            case "Overdose":
                ref.child(index).child("overdose").setValue(mDetailEd.getText().toString());
                break;
            case "Precautions":
                ref.child(index).child("precaution").setValue(mDetailEd.getText().toString());
                break;
            case "Storage":
                ref.child(index).child("storage").setValue(mDetailEd.getText().toString());
                break;
            case "How To Use":
                ref.child(index).child("use").setValue(mDetailEd.getText().toString());
                break;
            case "Warnings":
                ref.child(index).child("warning").setValue(mDetailEd.getText().toString());
                break;
            case "Side Effects":
                ref.child(index).child("side").setValue(mDetailEd.getText().toString());
                break;
        }

        Toast.makeText(MedicineViewEdit.this, "Updated", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed()
        {
        Intent intent = new Intent();
        switch (title){
            case "Overview":
                intent.putExtra("result","Overview");
                intent.putExtra("detail", mDetailEd.getText().toString());
                break;
            case "Interactions":
                intent.putExtra("result","Interactions");
                intent.putExtra("detail", mDetailEd.getText().toString());
                break;
            case "Missed":
                intent.putExtra("result","Missed");
                intent.putExtra("detail", mDetailEd.getText().toString());
                break;
            case "Notes":
                intent.putExtra("result","Notes");
                intent.putExtra("detail", mDetailEd.getText().toString());
                break;
            case "Overdose":
                intent.putExtra("result","Overdose");
                intent.putExtra("detail", mDetailEd.getText().toString());
                break;
            case "Precautions":
                intent.putExtra("result","Precautions");
                intent.putExtra("detail", mDetailEd.getText().toString());
                break;
            case "Storage":
                intent.putExtra("result","Storage");
                intent.putExtra("detail", mDetailEd.getText().toString());
                break;
            case "How To Use":
                intent.putExtra("result","How To Use");
                intent.putExtra("detail", mDetailEd.getText().toString());
                break;
            case "Warnings":
                intent.putExtra("result","Warnings");
                intent.putExtra("detail", mDetailEd.getText().toString());
                break;
            case "Side Effects":
                intent.putExtra("result","Side Effects");
                intent.putExtra("detail", mDetailEd.getText().toString());
                break;
        }

        setResult(MedicineViewEdit.RESULT_OK, intent);
        finish();
    }
}