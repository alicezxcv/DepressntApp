package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Psychiatrist extends AppCompatActivity {
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psychiatrist);

        back = (ImageButton) findViewById(R.id.back);
//
        //RecyclerView
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        //set layout as linear
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //send query to firebase
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("Psychiatrists"); //*psychiatrists


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Psychiatrist.this, MainActivity2.class));
                finish();
            }
        });
    }


    //load data into recyclerView

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Model, ActivitiesViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Model, ActivitiesViewHolder>(
                        Model.class,
                        R.layout.row_activities,
                        ActivitiesViewHolder.class,
                        mRef
                ) {
                    @Override
                    protected void populateViewHolder(ActivitiesViewHolder viewHolder, Model model, int position) {
                        viewHolder.setDetails(getApplicationContext(), model.getTitle(), model.getDescription(), model.getImage(), model.getContent());
                    }

                    @Override
                    public ActivitiesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        ActivitiesViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                        viewHolder.setOnClickListener(new ActivitiesViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                            }
                        });
                        return viewHolder;
                    }
                };
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}
