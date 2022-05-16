package com.uni.gruppenphaseandroid.Cards;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.uni.gruppenphaseandroid.R;
import com.uni.gruppenphaseandroid.manager.Handcards;

import java.util.LinkedList;

public class CardUI {

    private LinearLayout linearLayout;
    private View view;
    private static CardUI cardUI;

    public static CardUI getInstance() {
        if (cardUI == null) {
            cardUI = new CardUI();
        }
        return cardUI;
    }


    public CardUI(View view) {
        this.view = view;
        linearLayout = view.findViewById(R.id.linlayout_cardHolder);
    }

    public CardUI() {
    }


    public void addCardToHand() {
        LinkedList<Card> cards = Handcards.getInstance().getMyCards();

        for (Card c : cards){
        linearLayout.addView(findImageView(c));
        }
    }

    private ImageView findImageView (Card card) {
        ImageView cardIV;
        cardIV = new ImageView(view.getContext());


        try {
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
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return null;
    }

    public void removeFromHand (ImageView view){
        linearLayout.removeView(view);
    }

    public void removeAllFromHand (){
        linearLayout.removeAllViews();
    }


}


