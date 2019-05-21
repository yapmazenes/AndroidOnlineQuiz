package com.example.pc.onlinequizapp.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.onlinequizapp.Interface.ItemClickListener;
import com.example.pc.onlinequizapp.R;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView category_name;
    public ImageView category_image;
    public com.github.clans.fab.FloatingActionButton fabImage;
    public com.github.clans.fab.FloatingActionButton fabSound;
    public com.github.clans.fab.FloatingActionButton fabText;
    public com.github.clans.fab.FloatingActionMenu fabAddQuestion;

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public CategoryViewHolder(View itemView) {
        super(itemView);
        category_image = itemView.findViewById(R.id.category_image);
        category_name = itemView.findViewById(R.id.category_name);
        fabAddQuestion = itemView.findViewById(R.id.menu);
        fabImage = itemView.findViewById(R.id.menu_itemImage);
        fabSound = itemView.findViewById(R.id.menu_itemSound);
        fabText = itemView.findViewById(R.id.menu_itemText);
        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        itemClickListener.Onclick(v, getAdapterPosition(), false);
    }
}
