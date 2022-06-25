package com.uni.gruppenphaseandroid.cards;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.uni.gruppenphaseandroid.R;
import com.uni.gruppenphaseandroid.manager.GameManager;

import java.util.LinkedList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    /**
     * CaqrdAdapter ist für den Recycler View notwendig --> Das ist der Scroll Container, in dem die Handkarten angezeigt werden
     * Grund warum ich genau Recyclerview verwendet habe --> man konnte einem OnItemClickListener implementieren
     */

    private LinkedList<Integer> imageCardList;
    private ItemClickListener mItemClickListener;
    public static int mPreviousIndex = -1;


    /**
     * Viewholder ist die Klasse, in der die Elemente "generiert werden"
     * imageView ist das "Single Item"; dafür gibt es ein eigenes .xml bei den res
     * für background gilt das selbe
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final ImageView background;

        public ViewHolder(View view) {
            super(view);

            // Define click listener for the ViewHolder's View.
            view.setOnClickListener(v -> Log.d("code", "Element " + getAdapterPosition() + " clicked."));               //reine Info
            imageView = (ImageView) view.findViewById(R.id.imageView);
            background = (ImageView) view.findViewById(R.id.imageView_selectedBackground);
            background.setColorFilter(Color.GRAY);
        }

        public ImageView getImageView() {
            return imageView;
        }


    }

    public CardAdapter(ItemClickListener itemClickListener) {
        this.imageCardList = GameManager.getInstance().getCardUI().cardsForRecyclerView();
        this.mItemClickListener = itemClickListener;
    }


    /**
     * tbh, nicht 100% sicher, was es macht, habs aus der anleitung übernommen
     *
     */
    @Override
    public CardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_single_item, parent, false);

        return new ViewHolder(view);
    }

    /**
     * setzt den hintergrund auf das nicht geclickte bild invisible, für das geklickte visible
     * setzt die Karten aus der liste
     * übergibt, wenn eine Karte geklickt wurde den Index der Karte in der liste und die Position im Cardholder (... sollte eigentlich der selbe wert sein...ich denke, dass eine neue variable übergeben damals schneller gib als es durchzudenken, ob es wirklich die selbe zahl ist ¯\_(ツ)_/¯
     * notifyDataSetChanged() war notwendig, dass sich der hintergrund wieder neu setzt
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (mPreviousIndex == position){
            holder.background.setVisibility(View.VISIBLE);
        }else{
            holder.background.setVisibility(View.INVISIBLE);
        }
        holder.imageView.setImageResource(imageCardList.get(position));
        holder.itemView.setOnClickListener((view -> {
            mItemClickListener.onItemClick(imageCardList.get(position),position);
            notifyDataSetChanged();
        }));
    }

    @Override
    public int getItemCount() {
        return imageCardList.size();
    }

    /**
     * das interface für den OnItemclickListener
     */
    public interface ItemClickListener{
        void onItemClick(int card, int position);
    }


}
