package com.uni.gruppenphaseandroid.Cards;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.content.res.ResourcesCompat;

import com.uni.gruppenphaseandroid.R;

import java.util.LinkedList;

public class CardUiIimpl implements CardUI {

    private ImageView cardImageView;
    private static LinearLayout linearLayout;
    private View view;
    private Context context;


    public CardUiIimpl(Context context, View view) {
        this.context = context;
        this.view = view;
        linearLayout = view.findViewById(R.id.linlayout_cardHolder);
    }

    public CardUiIimpl() {
    }


    public void sortCardOnHandUI(){

    }

    public void addCardToHand(ImageView card) {
        linearLayout.addView(card);
    }

    private ImageView findImageView (Card card) {
        ImageView cardIV;
        cardIV = new ImageView(view.getContext());


        switch (card.getCardtype()) {
            case TWO:
                cardIV.setBackgroundResource(R.drawable.ic_card_2);
                return cardIV;
            case THREE:
                cardIV.setBackgroundResource(R.drawable.ic_card_3);
                return cardIV;
            case FIVE:
                cardIV.setBackgroundResource(R.drawable.ic_card_5);
                return cardIV;
            case SIX:
                cardIV.setBackgroundResource(R.drawable.ic_card_6);
                return cardIV;
            case EIGTH:
                cardIV.setBackgroundResource(R.drawable.ic_card_8);
                return cardIV;
            case NINE:
                cardIV.setBackgroundResource(R.drawable.ic_card_9);
                return cardIV;
            case TEN:
                cardIV.setBackgroundResource(R.drawable.ic_card_10);
                return cardIV;
            case TWELVE:
                cardIV.setBackgroundResource(R.drawable.ic_card_12);
                return cardIV;
            case EQUAL:
                cardIV.setBackgroundResource(R.drawable.ic_card_copy);
                return cardIV;
            case FOUR_PLUSMINUS:
                cardIV.setBackgroundResource(R.drawable.ic_card_4);
                return cardIV;
            case ONETOSEVEN:
                cardIV.setBackgroundResource(R.drawable.ic_card_7);
                return cardIV;
            case ONEORELEVEN_START:
                cardIV.setBackgroundResource(R.drawable.ic_card_11);
                return cardIV;
            case THIRTEEN_START:
                cardIV.setBackgroundResource(R.drawable.ic_card_13);
                return cardIV;
            case MAGNET:
                cardIV.setBackgroundResource(R.drawable.ic_card_magnet);
                return cardIV;
            case SWITCH:
                cardIV.setBackgroundResource(R.drawable.ic_card_switch);
                return cardIV;
        }
        return null;
    }
}


