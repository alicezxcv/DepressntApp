package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivitiesDetail extends AppCompatActivity {
    TextView mTitleTv, mDetailTv;
    ImageView mImageIv;
    ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities_detail);
        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivitiesDetail.super.onBackPressed();
            }
        });
        mDetailTv = findViewById(R.id.dDescriptionTv);
        mImageIv = findViewById(R.id.dImageView);
        mTitleTv = findViewById(R.id.dTitleTv);

//        byte[] bytes = getIntent().getByteArrayExtra("image");
//        Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        String title =  getIntent().getStringExtra("title");
        String detail = getIntent().getStringExtra("detail");

//
        mTitleTv.setText(title);
        mDetailTv.setText(detail);
        mDetailTv.setMovementMethod(new ScrollingMovementMethod());
//        mImageIv.setImageBitmap(bmp);


    }
}