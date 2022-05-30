package com.uni.gruppenphaseandroid.cards;

import android.util.Log;

import com.uni.gruppenphaseandroid.R;
import com.uni.gruppenphaseandroid.manager.Handcards;

import java.util.LinkedList;

public class CardUI  {

    private static CardUI cardUI;
    LinkedList<Integer> imageCardList;

    public static CardUI getInstance() {
        if (cardUI == null) {
            cardUI = new CardUI();
        }
        return cardUI;
    }

    public CardUI() {
    }

    public void addCardToHand() {

        imageCardList = new LinkedList<>();
        LinkedList<Card> cards = Handcards.getInstance().getMyCards();

       for (Card c : cards){
           findImageView(c);
           Log.e("code", imageCardList.getFirst().toString());
        }
    }

    private void findImageView (Card card) {

        //cardIV = new ImageView(view.getContext());


            switch (card.getCardtype()) {
                case TWO:
                    //cardIV.setBackgroundResource(R.drawable.ic_card_2);
                    imageCardList.add(R.drawable.ic_card_2);
                    break;
                case THREE:
                    //cardIV.setBackgroundResource(R.drawable.ic_card_3);
                    imageCardList.add(R.drawable.ic_card_3);
                    break;
                case FIVE:
                    //cardIV.setBackgroundResource(R.drawable.ic_card_5);
                    imageCardList.add(R.drawable.ic_card_5);
                    break;
                case SIX:
                    //cardIV.setBackgroundResource(R.drawable.ic_card_6);
                    imageCardList.add(R.drawable.ic_card_6);
                    break;
                case EIGTH:
                    //cardIV.setBackgroundResource(R.drawable.ic_card_8);
                    imageCardList.add(R.drawable.ic_card_8);
                    break;
                case NINE:
                    //cardIV.setBackgroundResource(R.drawable.ic_card_9);
                    imageCardList.add(R.drawable.ic_card_9);
                    break;
                case TEN:
                    //cardIV.setBackgroundResource(R.drawable.ic_card_10);
                    imageCardList.add(R.drawable.ic_card_10);
                    break;
                case TWELVE:
                    //cardIV.setBackgroundResource(R.drawable.ic_card_12);
                    imageCardList.add(R.drawable.ic_card_12);
                    break;
                case EQUAL:
                    //cardIV.setBackgroundResource(R.drawable.ic_card_copy);
                    imageCardList.add(R.drawable.ic_card_copy);
                    break;
                case FOUR_PLUSMINUS:
                    //cardIV.setBackgroundResource(R.drawable.ic_card_4);
                    imageCardList.add(R.drawable.ic_card_4);
                    break;
                case ONETOSEVEN:
                    //cardIV.setBackgroundResource(R.drawable.ic_card_7);
                    imageCardList.add(R.drawable.ic_card_7);
                    break;
                case ONEORELEVEN_START:
                    //cardIV.setBackgroundResource(R.drawable.ic_card_11);
                    imageCardList.add(R.drawable.ic_card_11);
                    break;
                case THIRTEEN_START:
                    //cardIV.setBackgroundResource(R.drawable.ic_card_13);
                    imageCardList.add(R.drawable.ic_card_13);
                    break;
                case MAGNET:
                    //cardIV.setBackgroundResource(R.drawable.ic_card_magnet);
                    imageCardList.add(R.drawable.ic_card_magnet);
                    break;
                case SWITCH:
                    //cardIV.setBackgroundResource(R.drawable.ic_card_switch);
                    imageCardList.add(R.drawable.ic_card_switch);
                    break;
            }

        //return cardIV;
    }

    public LinkedList<Integer> cardsForRecyclerView (){

        addCardToHand();
        return imageCardList;
    }

}


