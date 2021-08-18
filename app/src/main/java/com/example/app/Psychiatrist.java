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
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Psychiatrist extends AppCompatActivity {
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    ImageButton back,add;
    private boolean isClickable = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psychiatrist);

        back = (ImageButton) findViewById(R.id.back);

        // handle add button
        add= findViewById(R.id.addBtn);
        add.bringToFront();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Psychiatrist.this, PsychiatristDetail.class);
                intent.putExtra("title", "");
                intent.putExtra("detail", "");
                intent.putExtra("index",String.valueOf(mRecyclerView.getChildCount()));
                startActivity(intent);
            }
        });


        //RecyclerView
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        //set layout as linear
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (getIntent().getExtras().get("type").toString().equals("user")){
            isClickable = false;
            add.setVisibility(View.GONE);
        }



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
                        R.layout.row_psychiatrist,
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
                                if (isClickable) {
                                    // clickable content is available for admin only
                                    TextView mTitleTv = view.findViewById(R.id.rTitleTv);
                                    TextView mDetailTv = view.findViewById(R.id.rDescriptionTv);
                                    ImageView mImageTv = view.findViewById(R.id.rImageView);

                                    String mTitle = mTitleTv.getText().toString();
                                    String mDetail = mDetailTv.getText().toString();
//                        Drawable mDrawable = mImageTv.getDrawable();
//                        Bitmap mBitmap = ((BitmapDrawable)mDrawable).getBitmap();

                                    Intent intent = new Intent(Psychiatrist.this, PsychiatristDetail.class);
//                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                        mBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

//                        intent.putExtra("image", stream.toByteArray());
                                    intent.putExtra("title", mTitle);
                                    intent.putExtra("detail", mDetail);
                                    intent.putExtra("index", String.valueOf(position));
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
