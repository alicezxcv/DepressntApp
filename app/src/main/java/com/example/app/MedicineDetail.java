package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MedicineDetail extends AppCompatActivity implements View.OnClickListener  {

    TextView mNameTv;
    CardView mOverviewTv, mInteractionTv, mMissedTv, mnoteTv, moverdoseTv,
            mprecautionTv, msideTv, mstorageTv, museTv, mwarningTv;

    ImageButton back;

    String name, overview, interaction, missed, note, overdose, precaution, side, storage, use, warning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_detail);
        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MedicineDetail.this, Medicine.class));
                finish();
            }
        });
        mNameTv = findViewById(R.id.dNameTv);

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
//
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

//        byte[] bytes = getIntent().getByteArrayExtra("image");
//        Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);



//
        mNameTv.setText(name);
//        mOverviewTv.setText(overview);
//        mInteractionTv.setText(interaction);
//        mMissedTv.setText(missed);
//        mnoteTv.setText(note);
//        moverdoseTv.setText(overdose);
//        mprecautionTv.setText(precaution);
//        msideTv.setText(side);
//        mstorageTv.setText(storage);
//        museTv.setText(use);
//        mwarningTv.setText(warning);


//        mImageIv.setImageBitmap(bmp);


    }
    @Override
    public void onClick(View v)
    {
        Intent intent;
        switch (v.getId()){
            case R.id.dOverviewTv:
                intent = new Intent(MedicineDetail.this, MedicineView.class);
                intent.putExtra("title", "Overview");
                intent.putExtra("content", overview);
                startActivity(intent);
                break;
            case R.id.dInteractionsTv:
                intent = new Intent(MedicineDetail.this, MedicineView.class);
                intent.putExtra("title", "Interactions");
                intent.putExtra("content", interaction);
                startActivity(intent);
                break;
            case R.id.dMissedTv:
                intent = new Intent(MedicineDetail.this, MedicineView.class);
                intent.putExtra("title", "Missed");
                intent.putExtra("content", missed);
                startActivity(intent);
                break;
            case R.id.dNotesTv:
                intent = new Intent(MedicineDetail.this, MedicineView.class);
                intent.putExtra("title", "Notes");
                intent.putExtra("content", note);
                startActivity(intent);
                break;
            case R.id.dOverdoseTv:
                intent = new Intent(MedicineDetail.this, MedicineView.class);
                intent.putExtra("title", "Overdose");
                intent.putExtra("content", overdose);
                startActivity(intent);
                break;
            case R.id.dPrecautionsTv:
                intent = new Intent(MedicineDetail.this, MedicineView.class);
                intent.putExtra("title", "Precautions");
                intent.putExtra("content", precaution);
                startActivity(intent);
                break;
            case R.id.dStorageTv:
                intent = new Intent(MedicineDetail.this, MedicineView.class);
                intent.putExtra("title", "Storage");
                intent.putExtra("content", storage);
                startActivity(intent);
                break;
            case R.id.dUseTv:
                intent = new Intent(MedicineDetail.this, MedicineView.class);
                intent.putExtra("title", "How To Use");
                intent.putExtra("content", use);
                startActivity(intent);
                break;
            case R.id.dWarningTv:
                intent = new Intent(MedicineDetail.this, MedicineView.class);
                intent.putExtra("title", "Warnings");
                intent.putExtra("content", warning);
                startActivity(intent);
                break;
            case R.id.dSideTv:
                intent = new Intent(MedicineDetail.this, MedicineView.class);
                intent.putExtra("title", "Side Effects");
                intent.putExtra("content", side);
                startActivity(intent);
                break;





        }
    }
}
