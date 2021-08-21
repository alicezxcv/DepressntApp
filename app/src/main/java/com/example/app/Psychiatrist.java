package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.databinding.ActivityPsychiatristBinding;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Psychiatrist extends AppCompatActivity {

    ActivityPsychiatristBinding binding;

    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    ImageButton back,add;
    private boolean isClickable = true;
    private FirebaseRecyclerAdapter<Model, ActivitiesViewHolder> firebaseRecyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPsychiatristBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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
                intent.putExtra("index",String.valueOf(firebaseRecyclerAdapter.getItemCount()));
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
        else {
            LinearLayout layout = binding.getRoot();
            layout.setBackgroundColor(getResources().getColor(R.color.white));
            back.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.black)));
            binding.title.setTextColor(getResources().getColor(R.color.black));
            binding.recyclerView.setBackgroundColor(getResources().getColor(R.color.white));
            binding.subtitle.setVisibility(View.GONE);
        }

        //send query to firebase
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("Psychiatrists"); //*psychiatrists


        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //if (getIntent().getExtras().get("type").toString().equals("user")){
                    //startActivity(new Intent(Psychiatrist.this, MainActivity2.class));
                 //   finish();
               // }
               // else
               // {
                    //startActivity(new Intent(Psychiatrist.this, ProfileActivity2.class));
                    finish();
               // }
            }
        });
    }


    //load data into recyclerView

    @Override
    protected void onStart() {
        super.onStart();
        firebaseRecyclerAdapter =
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
