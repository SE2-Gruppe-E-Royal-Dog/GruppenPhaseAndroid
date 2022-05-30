package com.uni.gruppenphaseandroid.cards;

import com.uni.gruppenphaseandroid.cards.Cardtype;
import android.util.Log;


import com.uni.gruppenphaseandroid.R;
import com.uni.gruppenphaseandroid.manager.Handcards;

import java.util.LinkedList;

public class CardUI  {


    private static CardUI cardUI;
    LinkedList<Integer> imageCardList;


    static LinkedList<com.uni.gruppenphaseandroid.Cards.Card> myCards;        //für test zwecke

    //static LinkedList<Card> myCards;        //für test zwecke



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

//test cards

         LinkedList<Card> testCards = new LinkedList<>();

        Card card1 = new Card(Cardtype.EIGTH);
        testCards.add(card1);
        Card card2 = new Card(Cardtype.TWO);
        testCards.add(card2);
        Card card3 = new Card(Cardtype.NINE);
        testCards.add(card1);
        Card card4 = new Card(Cardtype.FOUR_PLUSMINUS);
        testCards.add(card4);
        Card card5 = new Card(Cardtype.ONETOSEVEN);
        testCards.add(card5);
        Card card6 = new Card(Cardtype.ONEORELEVEN_START);
        testCards.add(card6);
        testCards.add(card1);
//test cards end

       for (Card c : testCards){
           findImageView(c);

        }
    }

    private void findImageView (Card card) {

            switch (card.getCardtype()) {
                case TWO:
                    imageCardList.add(R.drawable.ic_card_2);
                    break;
                case THREE:
                    imageCardList.add(R.drawable.ic_card_3);
                    break;
                case FIVE:
                    imageCardList.add(R.drawable.ic_card_5);
                    break;
                case SIX:
                    imageCardList.add(R.drawable.ic_card_6);
                    break;
                case EIGTH:
                    imageCardList.add(R.drawable.ic_card_8);
                    break;
                case NINE:
                    imageCardList.add(R.drawable.ic_card_9);
                    break;
                case TEN:
                    imageCardList.add(R.drawable.ic_card_10);
                    break;
                case TWELVE:
                    imageCardList.add(R.drawable.ic_card_12);
                    break;
                case EQUAL:
                    imageCardList.add(R.drawable.ic_card_copy);
                    break;
                case FOUR_PLUSMINUS:
                    imageCardList.add(R.drawable.ic_card_4);
                    break;
                case ONETOSEVEN:
                    imageCardList.add(R.drawable.ic_card_7);
                    break;
                case ONEORELEVEN_START:
                    imageCardList.add(R.drawable.ic_card_11);
                    break;
                case THIRTEEN_START:
                    imageCardList.add(R.drawable.ic_card_13);
                    break;
                case MAGNET:
                    imageCardList.add(R.drawable.ic_card_magnet);
                    break;
                case SWITCH:
                    imageCardList.add(R.drawable.ic_card_switch);
                    break;
            }
    }

    public Cardtype idToCardType (int card) {
        switch (card) {
            case R.drawable.ic_card_2:
                return Cardtype.TWO;
            case R.drawable.ic_card_3:
                return Cardtype.THREE;
            case R.drawable.ic_card_5:
                return Cardtype.FIVE;
            case R.drawable.ic_card_6:
                return Cardtype.SIX;
            case R.drawable.ic_card_8:
                return Cardtype.EIGTH;
            case R.drawable.ic_card_9:
                return Cardtype.NINE;
            case R.drawable.ic_card_10:
                return Cardtype.TEN;
            case R.drawable.ic_card_12:
                return Cardtype.TWELVE;
            case R.drawable.ic_card_copy:
                return Cardtype.EQUAL;
            case R.drawable.ic_card_4:
                return Cardtype.FOUR_PLUSMINUS;
            case R.drawable.ic_card_7:
                return Cardtype.ONETOSEVEN;
            case R.drawable.ic_card_11:
                return Cardtype.ONEORELEVEN_START;
            case R.drawable.ic_card_13:
                return Cardtype.THIRTEEN_START;
            case R.drawable.ic_card_magnet:
                return Cardtype.MAGNET;
            case R.drawable.ic_card_switch:
                return Cardtype.SWITCH;
        }
        return null;
    }


    public LinkedList<Integer> cardsForRecyclerView (){

        addCardToHand();
        return imageCardList;
    }



}


