package com.uni.gruppenphaseandroid.Cards;

import static com.uni.gruppenphaseandroid.Cards.Cardtype.EIGTH;
import static com.uni.gruppenphaseandroid.Cards.Cardtype.EQUAL;
import static com.uni.gruppenphaseandroid.Cards.Cardtype.FIVE;
import static com.uni.gruppenphaseandroid.Cards.Cardtype.FOUR_PLUSMINUS;
import static com.uni.gruppenphaseandroid.Cards.Cardtype.MAGNET;
import static com.uni.gruppenphaseandroid.Cards.Cardtype.NINE;
import static com.uni.gruppenphaseandroid.Cards.Cardtype.ONEORELEVEN_START;
import static com.uni.gruppenphaseandroid.Cards.Cardtype.ONETOSEVEN;
import static com.uni.gruppenphaseandroid.Cards.Cardtype.SIX;
import static com.uni.gruppenphaseandroid.Cards.Cardtype.SWITCH;
import static com.uni.gruppenphaseandroid.Cards.Cardtype.TEN;
import static com.uni.gruppenphaseandroid.Cards.Cardtype.THIRTEEN_START;
import static com.uni.gruppenphaseandroid.Cards.Cardtype.THREE;
import static com.uni.gruppenphaseandroid.Cards.Cardtype.TWELVE;
import static com.uni.gruppenphaseandroid.Cards.Cardtype.TWO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.style.LineHeightSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uni.gruppenphaseandroid.CardViewFragment;
import com.uni.gruppenphaseandroid.R;
import com.uni.gruppenphaseandroid.manager.Handcards;

import java.util.LinkedList;

public class CardUI  {


    private static CardUI cardUI;
    LinkedList<Integer> imageCardList;

    static LinkedList<Card> myCards;        //für test zwecke


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
        Card card2 = new Card(TWO);
        testCards.add(card2);
        Card card3 = new Card(Cardtype.NINE);
        testCards.add(card1);
        testCards.add(card2);
        testCards.add(card2);
        testCards.add(card3);
        testCards.add(card1);
//test cards end

       for (Card c : testCards){
           findImageView(c);
           Log.e("code", imageCardList.getFirst().toString());
        }
    }

    private void findImageView (Card card) {

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
    }

    public Cardtype idToCardType (int card) {
        switch (card) {
            case R.drawable.ic_card_2:
                return TWO;
            case R.drawable.ic_card_3:
                return THREE;
            case R.drawable.ic_card_5:
                return FIVE;
            case R.drawable.ic_card_6:
                return SIX;
            case R.drawable.ic_card_8:
                return EIGTH;
            case R.drawable.ic_card_9:
                return NINE;
            case R.drawable.ic_card_10:
                return TEN;
            case R.drawable.ic_card_12:
                return TWELVE;
            case R.drawable.ic_card_copy:
                return EQUAL;
            case R.drawable.ic_card_4:
                return FOUR_PLUSMINUS;
            case R.drawable.ic_card_7:
                return ONETOSEVEN;
            case R.drawable.ic_card_11:
                return ONEORELEVEN_START;
            case R.drawable.ic_card_13:
                return THIRTEEN_START;
            case R.drawable.ic_card_magnet:
                return MAGNET;
            case R.drawable.ic_card_switch:
                return SWITCH;
        }
        return null;
    }


    public LinkedList<Integer> cardsForRecyclerView (){
        addCardToHand();
        return imageCardList;
    }



}


