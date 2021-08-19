package com.example.app;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

public class MedicineViewHolder extends RecyclerView.ViewHolder {

    View mView;

    public MedicineViewHolder(View itemView) {
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
    public void setDetails(Context ctx, String image, String interaction, String missed, String name,
                           String note, String overdose, String overview, String precaution, String side,
                           String storage, String use, String warning){
        TextView mnameTv = mView.findViewById(R.id.rnameTvMed);
        TextView moverviewTv = mView.findViewById(R.id.roverviewTvMed);
        TextView minteractionTv = mView.findViewById(R.id.rinteractionTvMed);
        TextView mmissedTv = mView.findViewById(R.id.rmissedTvMed);
        TextView mnoteTv = mView.findViewById(R.id.rnoteTvMed);
        TextView moverdoseTv = mView.findViewById(R.id.roverdoseTvMed);
        TextView mprecautionTv = mView.findViewById(R.id.rprecautionTvMed);
        TextView msideTv = mView.findViewById(R.id.rsideTvMed);
        TextView mstorageTv = mView.findViewById(R.id.rstorageTvMed);
        TextView museTv = mView.findViewById(R.id.ruseTvMed);
        TextView mwarningTv = mView.findViewById(R.id.rwarningTvMed);
        ImageView mImageTv = mView.findViewById(R.id.rImageViewMed);

        mnameTv.setText(name);
        moverviewTv.setText(overview);
        minteractionTv.setText(interaction);
        mmissedTv.setText(missed);
        mnoteTv.setText(note);
        minteractionTv.setText(interaction);
        moverdoseTv.setText(overdose);
        mprecautionTv.setText(precaution);
        msideTv.setText(side);
        mstorageTv.setText(storage);
        museTv.setText(use);
        mwarningTv.setText(warning);
        Picasso.with(ctx).load(image).into(mImageTv);
    }


    private MedicineViewHolder.ClickListener mClickListener;
    public interface ClickListener{
        void onItemClick(View view, int position);

    }
    public void setOnClickListener(MedicineViewHolder.ClickListener clickListener){
        mClickListener = clickListener;
    }
}
