package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Medicine extends AppCompatActivity {

    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);
        back = (ImageButton) findViewById(R.id.back);
//
        //RecyclerView
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        //set layout as linear
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //send query to firebase
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("Medicine"); //*activities

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(Medicine.this, MainActivity2.class));
                finish();
            }
        });

    }

    //load data into recyclerView

    @Override
    protected void onStart() {
        super.onStart();
//        FirebaseRecyclerOptions<Model> options = new FirebaseRecyclerOptions.Builder<Model>()
        FirebaseRecyclerAdapter<ModelMedicine, MedicineViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<ModelMedicine, MedicineViewHolder>(
                        ModelMedicine.class,
                        R.layout.row_medicine,
                        MedicineViewHolder.class,
                        mRef
                ) {
                    @Override
                    protected void populateViewHolder(MedicineViewHolder viewHolder, ModelMedicine model, int position) {
                        viewHolder.setDetails(getApplicationContext(), model.getImage(), model.getInteraction(),
                                model.getMissed(), model.getName(),model.getNote(),model.getOverdose(),
                                model.getOverview(),model.getPrecaution(),model.getSide(),
                                model.getStorage(),model.getUse(),model.getWarning());
                    }

                    @Override
                    public MedicineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        MedicineViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                        viewHolder.setOnClickListener(new MedicineViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                TextView mnameTv = view.findViewById(R.id.rnameTvMed);
                                TextView moverviewTv = view.findViewById(R.id.roverviewTvMed);
                                TextView minteractionTv = view.findViewById(R.id.rinteractionTvMed);
                                TextView mmissedTv = view.findViewById(R.id.rmissedTvMed);
                                TextView mnoteTv = view.findViewById(R.id.rnoteTvMed);
                                TextView moverdoseTv = view.findViewById(R.id.roverdoseTvMed);
                                TextView mprecautionTv = view.findViewById(R.id.rprecautionTvMed);
                                TextView msideTv = view.findViewById(R.id.rsideTvMed);
                                TextView mstorageTv = view.findViewById(R.id.rstorageTvMed);
                                TextView museTv = view.findViewById(R.id.ruseTvMed);
                                TextView mwarningTv = view.findViewById(R.id.rwarningTvMed);
                                ImageView mImageTv = view.findViewById(R.id.rImageViewMed);

                                String mname = mnameTv.getText().toString();
                                String moverview = moverviewTv.getText().toString();
                                String minteraction = minteractionTv.getText().toString();
                                String mmissed = mmissedTv.getText().toString();
                                String mnote = mnoteTv.getText().toString();
                                String moverdose = moverdoseTv.getText().toString();
                                String mprecaution = mprecautionTv.getText().toString();
                                String mside = msideTv.getText().toString();
                                String mstorage = mstorageTv.getText().toString();
                                String muse = museTv.getText().toString();
                                String mwarning = mwarningTv.getText().toString();
//                        Drawable mDrawable = mImageTv.getDrawable();
//                        Bitmap mBitmap = ((BitmapDrawable)mDrawable).getBitmap();


                                Intent intent = new Intent(Medicine.this, MedicineDetail.class);
//                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                        mBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

//                        intent.putExtra("image", stream.toByteArray());
                                intent.putExtra("name", mname);
                                intent.putExtra("overview", moverview);
                                intent.putExtra("interaction", minteraction);
                                intent.putExtra("missed", mmissed);
                                intent.putExtra("note", mnote);
                                intent.putExtra("overdose", moverdose);
                                intent.putExtra("precaution", mprecaution);
                                intent.putExtra("side", mside);
                                intent.putExtra("storage", mstorage);
                                intent.putExtra("use", muse);
                                intent.putExtra("warning", mwarning);


                                startActivity(intent);

                            }
                        });
                        return viewHolder;
                    }
                };
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}
