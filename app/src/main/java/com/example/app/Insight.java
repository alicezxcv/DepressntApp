package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.app.databinding.ActivityInsightBinding;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Insight extends AppCompatActivity {
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    ImageButton back,add;
    private Boolean isUser = false;
    private FirebaseRecyclerAdapter<Model, ActivitiesViewHolder> firebaseRecyclerAdapter;
    private ActivityInsightBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsightBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        add = findViewById(R.id.addBtn);
        add.bringToFront();

        back = (ImageButton) findViewById(R.id.back);
//
        //RecyclerView
        mRecyclerView = findViewById(R.id.recyclerViewI);
        mRecyclerView.setHasFixedSize(true);

        //set layout as linear
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //send query to firebase
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("Insight");

        if (getIntent().getExtras().get("type").toString().equals("user")){
            isUser = true;
            add.setVisibility(View.GONE);
        }
        else
        {
            LinearLayout layout = binding.getRoot();
            layout.setBackgroundColor(getResources().getColor(R.color.white));
            back.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.black)));
            binding.title.setTextColor(getResources().getColor(R.color.black));
            binding.recyclerViewI.setBackgroundColor(getResources().getColor(R.color.white));
            binding.subtitle.setVisibility(View.GONE);
        }

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Insight.this, InsightEdit.class);
                intent.putExtra("title", "");
                intent.putExtra("detail", "");
                intent.putExtra("content","");
                intent.putExtra("index",String.valueOf(firebaseRecyclerAdapter.getItemCount()));
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // startActivity(new Intent(Insight.this, MainActivity2.class));
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
//        FirebaseRecyclerOptions<Model> options = new FirebaseRecyclerOptions.Builder<Model>()
        firebaseRecyclerAdapter =
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
                                TextView mContent = view.findViewById(R.id.rContentTv);
                                //ImageView mImageTv = view.findViewById(R.id.rImageView);
                                TextView mDescriptionTv = view.findViewById(R.id.rDescriptionTv);

                                //String mTitle = mTitleTv.getText().toString();
                                //String mDetail = mContent.getText().toString();
//                        Drawable mDrawable = mImageTv.getDrawable();
//                        Bitmap mBitmap = ((BitmapDrawable)mDrawable).getBitmap();
                                if (isUser) {
                                    Intent intent = new Intent(Insight.this, InsightEdit.class);
//                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                        mBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                        intent.putExtra("image", stream.toByteArray());
                                    intent.putExtra("title", mTitleTv.getText().toString());
                                    intent.putExtra("detail", mContent.getText().toString());
                                    startActivity(intent);
                                }
                                else{
                                    Intent intent = new Intent(Insight.this, InsightEdit.class);
//                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                        mBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                        intent.putExtra("image", stream.toByteArray());
                                    intent.putExtra("title", mTitleTv.getText().toString());
                                    intent.putExtra("description", mDescriptionTv.getText().toString());
                                    intent.putExtra("content",mContent.getText().toString());
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