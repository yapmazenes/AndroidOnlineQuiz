package com.example.pc.onlinequizapp.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.pc.onlinequizapp.R;

public class ScoreDetailViewHolder extends RecyclerView.ViewHolder {
    public TextView txt_CategoryName,txt_Score;


    public ScoreDetailViewHolder(View itemView) {
        super(itemView);
        txt_CategoryName=itemView.findViewById(R.id.txt_CategoryName);
        txt_Score=itemView.findViewById(R.id.txt_score);
    }
}
