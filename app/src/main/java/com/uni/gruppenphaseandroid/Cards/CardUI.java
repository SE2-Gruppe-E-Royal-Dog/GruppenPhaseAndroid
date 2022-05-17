package com.uni.gruppenphaseandroid.Cards;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.uni.gruppenphaseandroid.CardViewFragment;
import com.uni.gruppenphaseandroid.R;
import com.uni.gruppenphaseandroid.manager.Handcards;

import java.util.LinkedList;

public class CardUI {

    private LinearLayout linearLayout;
    private View view;
    private static CardUI cardUI;
    int tag;

    public static CardUI getInstance() {
        if (cardUI == null) {
            cardUI = new CardUI();
        }
        return cardUI;
    }


    public CardUI() {
    }

    public void setView (View view){
        this.view = view;
    }


    public void addCardToHand() {
        LinkedList<Card> cards = Handcards.getInstance().getMyCards();
        linearLayout.findViewById(R.id.linlayout_cardHolder);

        tag = 0;
        for (Card c : cards){
            linearLayout.addView(findImageView(c, tag));
            tag++;
        }
    }

    private ImageView findImageView (Card card, int tag) {
        ImageView cardIV;
        cardIV = new ImageView(view.getContext());


        try {
            switch (card.getCardtype()) {
                case TWO:
                    cardIV.setBackgroundResource(R.drawable.ic_card_2);
                    break;
                case THREE:
                    cardIV.setBackgroundResource(R.drawable.ic_card_3);
                    break;
                case FIVE:
                    cardIV.setBackgroundResource(R.drawable.ic_card_5);
                    break;
                case SIX:
                    cardIV.setBackgroundResource(R.drawable.ic_card_6);
                    break;
                case EIGTH:
                    cardIV.setBackgroundResource(R.drawable.ic_card_8);
                    break;
                case NINE:
                    cardIV.setBackgroundResource(R.drawable.ic_card_9);
                    break;
                case TEN:
                    cardIV.setBackgroundResource(R.drawable.ic_card_10);
                    break;
                case TWELVE:
                    cardIV.setBackgroundResource(R.drawable.ic_card_12);
                    break;
                case EQUAL:
                    cardIV.setBackgroundResource(R.drawable.ic_card_copy);
                    break;
                case FOUR_PLUSMINUS:
                    cardIV.setBackgroundResource(R.drawable.ic_card_4);
                    break;
                case ONETOSEVEN:
                    cardIV.setBackgroundResource(R.drawable.ic_card_7);
                    break;
                case ONEORELEVEN_START:
                    cardIV.setBackgroundResource(R.drawable.ic_card_11);
                    break;
                case THIRTEEN_START:
                    cardIV.setBackgroundResource(R.drawable.ic_card_13);
                    break;
                case MAGNET:
                    cardIV.setBackgroundResource(R.drawable.ic_card_magnet);
                    break;
                case SWITCH:
                    cardIV.setBackgroundResource(R.drawable.ic_card_switch);
                    break;
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        cardIV.setTag(tag);
        return cardIV;
    }

    public void removeFromHand (ImageView view){
        linearLayout.removeView(view);
    }

    public void removeAllFromHand (){
        linearLayout.removeAllViews();
    }

}


