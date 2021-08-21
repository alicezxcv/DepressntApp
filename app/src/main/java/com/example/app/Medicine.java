package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.app.databinding.ActivityHealthyActivitiesBinding;
import com.example.app.databinding.ActivityMedicineBinding;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Medicine extends AppCompatActivity {

    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    ImageButton back, add;
    private Boolean isUser = false;
    FirebaseRecyclerAdapter<ModelMedicine, MedicineViewHolder> firebaseRecyclerAdapter;
    private ActivityMedicineBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMedicineBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        back = (ImageButton) findViewById(R.id.back);
        add = findViewById(R.id.addBtnMed);
        add.bringToFront();
        if (getIntent().getExtras().get("type").toString().equals("user")){
            isUser = true;
            add.setVisibility(View.GONE);
        }
        else
        {
            LinearLayout layout = binding.getRoot();
            layout.setBackgroundColor(getResources().getColor(R.color.white));
            back.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.black)));
            binding.titleMed.setTextColor(getResources().getColor(R.color.black));
            binding.recyclerView.setBackgroundColor(getResources().getColor(R.color.white));
            binding.subtitleMed.setVisibility(View.GONE);
        }
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Medicine.this, MedicineDetailEdit.class);
                intent.putExtra("name", "");
                intent.putExtra("overview", "");
                intent.putExtra("interaction", "");
                intent.putExtra("missed", "");
                intent.putExtra("note", "");
                intent.putExtra("overdose", "");
                intent.putExtra("precaution", "");
                intent.putExtra("side", "");
                intent.putExtra("storage", "");
                intent.putExtra("use", "");
                intent.putExtra("warning", "");
                intent.putExtra("index",String.valueOf(firebaseRecyclerAdapter.getItemCount()));
                startActivity(intent);
            }
        });
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
                startActivity(new Intent(Medicine.this, MainActivity2.class));
                finish();
            }
        });

    }

    //load data into recyclerView

    @Override
    protected void onStart() {
        super.onStart();
//        FirebaseRecyclerOptions<Model> options = new FirebaseRecyclerOptions.Builder<Model>()
        firebaseRecyclerAdapter =
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


                                if (isUser) {
                                    Intent intent = new Intent(Medicine.this, MedicineDetail.class);

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
                                else
                                {
                                    Intent intent = new Intent(Medicine.this, MedicineDetailEdit.class);

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

                                    intent.putExtra("index",String.valueOf(position));
                                    startActivity(intent);
                                }

                            }
                        });
                        return viewHolder;
                    }
                };
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}
