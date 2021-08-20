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
    private EditText mTitleTv, mClinic, mLocation, mPhone, mMail;
    private ImageButton back;
    private DatabaseReference ref;
    private Button save, del;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psychiatrist_detail);

        mTitleTv = findViewById(R.id.dTitleTv);

        mClinic = findViewById(R.id.edit_clinic);
        mLocation = findViewById(R.id.edit_location);
        mPhone = findViewById(R.id.edit_phone);
        mMail = findViewById(R.id.edit_email);

        String title =  (String) getIntent().getStringExtra("title");
        String detail = (String) getIntent().getStringExtra("detail");

        if (!detail.equals("")) {
            String detailSplit[] = detail.split("\\n");

            mClinic.setText(detailSplit[0].replaceAll("\\bClinic: \\b", ""));
            mLocation.setText(detailSplit[1].replaceAll("\\bLocation: \\b", ""));
            mPhone.setText(detailSplit[2].replaceAll("\\bPhone\\s*number\\b.\\s*", ""));
            mMail.setText(detailSplit[3].replaceAll("\\bEmail: \\b", ""));
        }
        else{
            mClinic.setText("");
            mLocation.setText("");
            mPhone.setText("");
            mMail.setText("");
        }
        mTitleTv.setText(title);

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
                mClinic.setText("");
                mLocation.setText("");
                mPhone.setText("");
                mMail.setText("");
            }
        });

        // handle back button
        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    void update(){
        ref = FirebaseDatabase.getInstance().getReference("Psychiatrists");
        ref.child(getIntent().getExtras().get("index").toString()).child("title").setValue(mTitleTv.getText().toString());

        //Join EditText to string.psychiatrist_info_edit, see format in strings.xml
        String updateInfo = new String(getString(R.string.psychiatrist_info_edit,
                mClinic.getText().toString(),
                mLocation.getText().toString(),
                mPhone.getText().toString(),
                mMail.getText().toString()));
        ref.child(getIntent().getExtras().get("index").toString()).child("description").setValue(updateInfo);

        //I have no clue about this code block, but it contains mDetailTv which was removed
        //InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        //imm.hideSoftInputFromWindow(mDetailTv.getWindowToken(),
        //        InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }

}