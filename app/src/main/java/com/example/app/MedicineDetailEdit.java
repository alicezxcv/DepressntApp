package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MedicineDetailEdit extends AppCompatActivity implements View.OnClickListener {

    EditText mNameEd;
    CardView mOverviewTv, mInteractionTv, mMissedTv, mnoteTv, moverdoseTv,
            mprecautionTv, msideTv, mstorageTv, museTv, mwarningTv;

    ImageButton back;

    String name, overview, interaction, missed, note, overdose, precaution, side, storage, use, warning;
    String index;

    Button updateBtn,deleteBtn;
    private DatabaseReference ref;
    int LAUNCH_SECOND_ACTIVITY = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_detail_edit);
        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(MedicineDetail.this, Medicine.class));
                finish();
            }
        });
        updateBtn = findViewById(R.id.updateBtnMed);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateMed();
            }
        });

        // handle delete button pressed
        deleteBtn = findViewById(R.id.deleteBtnMed);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref = FirebaseDatabase.getInstance().getReference("Medicine");
                ref.child(index).removeValue();
                Toast.makeText(MedicineDetailEdit.this, "Deleted", Toast.LENGTH_SHORT).show();
                mNameEd.setText("");
                name =  "";
                overview = "";
                interaction =  "";
                missed = "";
                note =  "";
                overdose = "";
                precaution =  "";
                side = "";
                storage =  "";
                use = "";
                warning = "";

            }
        });
//
        mNameEd = findViewById(R.id.dNameEd);
//
        mOverviewTv = findViewById(R.id.dOverviewTv);
        mInteractionTv = findViewById(R.id.dInteractionsTv);
        mMissedTv = findViewById(R.id.dMissedTv);
        mnoteTv = findViewById(R.id.dNotesTv);
        moverdoseTv = findViewById(R.id.dOverdoseTv);
        mprecautionTv = findViewById(R.id.dPrecautionsTv);
        msideTv = findViewById(R.id.dSideTv);
        mstorageTv = findViewById(R.id.dStorageTv);
        museTv = findViewById(R.id.dUseTv);
        mwarningTv = findViewById(R.id.dWarningTv);
//
        name =  (String) getIntent().getStringExtra("name");
        overview = (String) getIntent().getStringExtra("overview");
        interaction =  (String) getIntent().getStringExtra("interaction");
        missed = (String) getIntent().getStringExtra("missed");
        note =  (String) getIntent().getStringExtra("note");
        overdose = (String) getIntent().getStringExtra("overdose");
        precaution =  (String) getIntent().getStringExtra("precaution");
        side = (String) getIntent().getStringExtra("side");
        storage =  (String) getIntent().getStringExtra("storage");
        use = (String) getIntent().getStringExtra("use");
        warning = (String) getIntent().getStringExtra("warning");

        index = (String) getIntent().getStringExtra("index");
////
        mOverviewTv.setOnClickListener((View.OnClickListener) this);
        mInteractionTv.setOnClickListener((View.OnClickListener) this);
        mMissedTv.setOnClickListener((View.OnClickListener) this);
        mnoteTv.setOnClickListener((View.OnClickListener) this);
        moverdoseTv.setOnClickListener((View.OnClickListener) this);
        mprecautionTv.setOnClickListener((View.OnClickListener) this);
        mstorageTv.setOnClickListener((View.OnClickListener) this);
        museTv.setOnClickListener((View.OnClickListener) this);
        mwarningTv.setOnClickListener((View.OnClickListener) this);
        msideTv.setOnClickListener((View.OnClickListener) this);
//
        mNameEd.setText(name);
    }
    @Override
    public void onClick(View v)
    {
        Intent intent;

        switch (v.getId()){
            case R.id.dOverviewTv:
                intent = new Intent(MedicineDetailEdit.this, MedicineViewEdit.class);
                intent.putExtra("title", "Overview");
                intent.putExtra("content", overview);
                intent.putExtra("index", index);
                startActivityForResult(intent,LAUNCH_SECOND_ACTIVITY);
                break;
            case R.id.dInteractionsTv:
                intent = new Intent(MedicineDetailEdit.this, MedicineViewEdit.class);
                intent.putExtra("title", "Interactions");
                intent.putExtra("content", interaction);
                intent.putExtra("index", index);
                startActivityForResult(intent,LAUNCH_SECOND_ACTIVITY);
                break;
            case R.id.dMissedTv:
                intent = new Intent(MedicineDetailEdit.this, MedicineViewEdit.class);
                intent.putExtra("title", "Missed");
                intent.putExtra("content", missed);
                intent.putExtra("index", index);
                startActivityForResult(intent,LAUNCH_SECOND_ACTIVITY);
                break;
            case R.id.dNotesTv:
                intent = new Intent(MedicineDetailEdit.this, MedicineViewEdit.class);
                intent.putExtra("title", "Notes");
                intent.putExtra("content", note);
                intent.putExtra("index", index);
                startActivityForResult(intent,LAUNCH_SECOND_ACTIVITY);
                break;
            case R.id.dOverdoseTv:
                intent = new Intent(MedicineDetailEdit.this, MedicineViewEdit.class);
                intent.putExtra("title", "Overdose");
                intent.putExtra("content", overdose);
                intent.putExtra("index", index);
                startActivityForResult(intent,LAUNCH_SECOND_ACTIVITY);
                break;
            case R.id.dPrecautionsTv:
                intent = new Intent(MedicineDetailEdit.this, MedicineViewEdit.class);
                intent.putExtra("title", "Precautions");
                intent.putExtra("content", precaution);
                intent.putExtra("index", index);
                startActivityForResult(intent,LAUNCH_SECOND_ACTIVITY);
                break;
            case R.id.dStorageTv:
                intent = new Intent(MedicineDetailEdit.this, MedicineViewEdit.class);
                intent.putExtra("title", "Storage");
                intent.putExtra("content", storage);
                intent.putExtra("index", index);
                startActivityForResult(intent,LAUNCH_SECOND_ACTIVITY);
                break;
            case R.id.dUseTv:
                intent = new Intent(MedicineDetailEdit.this, MedicineViewEdit.class);
                intent.putExtra("title", "How To Use");
                intent.putExtra("content", use);
                intent.putExtra("index", index);
                startActivityForResult(intent,LAUNCH_SECOND_ACTIVITY);
                break;
            case R.id.dWarningTv:
                intent = new Intent(MedicineDetailEdit.this, MedicineViewEdit.class);
                intent.putExtra("title", "Warnings");
                intent.putExtra("content", warning);
                intent.putExtra("index", index);
                startActivityForResult(intent,LAUNCH_SECOND_ACTIVITY);
                break;
            case R.id.dSideTv:
                intent = new Intent(MedicineDetailEdit.this, MedicineViewEdit.class);
                intent.putExtra("title", "Side Effects");
                intent.putExtra("content", side);
                intent.putExtra("index", index);
                startActivityForResult(intent,LAUNCH_SECOND_ACTIVITY);
                break;





        }
    }
//
    public void updateMed(){
        ref = FirebaseDatabase.getInstance().getReference("Medicine");
        ref.child(index).child("name").setValue(mNameEd.getText().toString());
        Toast.makeText(MedicineDetailEdit.this, "Updated", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LAUNCH_SECOND_ACTIVITY) {
            if(resultCode == MedicineDetailEdit.RESULT_OK){
                String result = data.getStringExtra("result");
                switch (result){
                    case "Overview":
                        overview = data.getStringExtra("detail");
                        break;
                    case "Interactions":
                        interaction = data.getStringExtra("detail");
                        break;
                    case "Missed":
                        missed = data.getStringExtra("detail");
                        break;
                    case "Notes":
                        note = data.getStringExtra("detail");
                        break;
                    case "Overdose":
                        overdose = data.getStringExtra("detail");
                        break;
                    case "Precautions":
                        precaution = data.getStringExtra("detail");
                        break;
                    case "Storage":
                        storage = data.getStringExtra("detail");
                        break;
                    case "How To Use":
                        use = data.getStringExtra("detail");
                        break;
                    case "Warnings":
                        warning = data.getStringExtra("detail");
                        break;
                    case "Side Effects":
                        side = data.getStringExtra("detail");
                        break;
                }

            }

        }
    }
}