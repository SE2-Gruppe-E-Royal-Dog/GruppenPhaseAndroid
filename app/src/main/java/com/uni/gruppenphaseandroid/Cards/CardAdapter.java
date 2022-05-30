package com.uni.gruppenphaseandroid.Cards;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uni.gruppenphaseandroid.R;

import java.util.LinkedList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private LinkedList<Integer> imageCardList;
    private ItemClickListener mItemClickListener;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;

        public ViewHolder(View view) {
            super(view);

            // Define click listener for the ViewHolder's View.
            view.setOnClickListener(v -> Log.d("code", "Element " + getAdapterPosition() + " clicked."));
            imageView = (ImageView) view.findViewById(R.id.imageView);
        }

        public ImageView getImageView() {
            return imageView;
        }
    }

    public CardAdapter(ItemClickListener itemClickListener) {
        this.imageCardList = CardUI.getInstance().cardsForRecyclerView();
        this.mItemClickListener = itemClickListener;
    }


    @Override
    public CardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_single_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(imageCardList.get(position));

        holder.itemView.setOnClickListener((view -> {
            mItemClickListener.onItemClick(imageCardList.get(position));
        }));
        }

    @Override
    public int getItemCount() {
        return imageCardList.size();
    }


    public interface ItemClickListener{
        void onItemClick(int card);
    }
}
