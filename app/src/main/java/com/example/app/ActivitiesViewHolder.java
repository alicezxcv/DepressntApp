package com.example.app;

import android.content.Context;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class ActivitiesViewHolder extends RecyclerView.ViewHolder {
    View mView;

    public ActivitiesViewHolder(View itemView) {
        super(itemView);
        mView = itemView;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(v, getAdapterPosition());
            }
        });
    }

    //Set details to recycler view row
    public void setDetails(Context ctx, String title, String description, String image, String content){
        TextView mTitleTv = mView.findViewById(R.id.rTitleTv);
        TextView mContentTv = mView.findViewById(R.id.rContentTv);
        TextView mDetailTv = mView.findViewById(R.id.rDescriptionTv);
        ImageView mImageTv = mView.findViewById(R.id.rImageView);

        mDetailTv.setText(description);
        mContentTv.setText(content);
        mTitleTv.setText(title);
        Picasso.with(ctx).load(image).into(mImageTv);

    }

    private ActivitiesViewHolder.ClickListener mClickListener;
    public interface ClickListener{
        void onItemClick(View view, int position);

    }
    public void setOnClickListener(ActivitiesViewHolder.ClickListener clickListener){
        mClickListener = clickListener;
    }
}
