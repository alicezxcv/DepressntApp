package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;

public class HealthyActivities extends AppCompatActivity {
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthy_activities);

        back = (ImageButton) findViewById(R.id.back);
//
        //RecyclerView
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        //set layout as linear
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //send query to firebase
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("Activites"); //*activities

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HealthyActivities.this, MainActivity2.class));
                finish();
            }
        });

    }

    //load data into recyclerView

    @Override
    protected void onStart() {
        super.onStart();
//        FirebaseRecyclerOptions<Model> options = new FirebaseRecyclerOptions.Builder<Model>()
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
                        TextView mTitleTv = view.findViewById(R.id.rTitleTv);
                        TextView mDetailTv = view.findViewById(R.id.rContentTv);
                        ImageView mImageTv = view.findViewById(R.id.rImageView);

                        String mTitle = mTitleTv.getText().toString();
                        String mDetail = mDetailTv.getText().toString();
//                        Drawable mDrawable = mImageTv.getDrawable();
//                        Bitmap mBitmap = ((BitmapDrawable)mDrawable).getBitmap();


                        Intent intent = new Intent(HealthyActivities.this, ActivitiesDetail.class);
//                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                        mBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

//                        intent.putExtra("image", stream.toByteArray());
                        intent.putExtra("title", mTitle);
                        intent.putExtra("detail", mDetail);
                        startActivity(intent);

                    }
                });
                return viewHolder;
            }
        };
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}