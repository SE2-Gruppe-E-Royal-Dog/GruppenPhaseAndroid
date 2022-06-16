package com.uni.gruppenphaseandroid.manager;

import android.util.Log;

import com.uni.gruppenphaseandroid.cards.Card;

import java.util.LinkedList;
import java.util.List;

public class Handcards {
    private static Handcards hand;
    private List<Card> myCards;

    private Handcards() {
        myCards = new LinkedList<>();
    }

    public static Handcards getInstance() {
        if (hand == null) {
            hand = new Handcards();
        }
        return hand;
    }

    public List<Card> getMyCards() {
        return myCards;
    }

    public void addCardToHand(List<Card> cards) {
        myCards.addAll(cards);
    }

    public void discardHandcard(int index){

        Card toBeRemoved = myCards.get(index);
        if(GameManager.getInstance().getLastTurn() == null){
            LastTurn lastTurn = new LastTurn(GameManager.getInstance().getFiguremanager().getFiguresOfColour(GameManager.getInstance().getColorOfMyClient()).get(0), GameManager.getInstance().getFiguremanager().getFiguresOfColour(GameManager.getInstance().getColorOfMyClient()).get(1), GameManager.getInstance().getFiguremanager().getFiguresOfColour(GameManager.getInstance().getColorOfMyClient()).get(0).getCurrentField(), GameManager.getInstance().getFiguremanager().getFiguresOfColour(GameManager.getInstance().getColorOfMyClient()).get(1).getCurrentField());
            GameManager.getInstance().setLastTurn(lastTurn);
            Log.e("handcards", "yo there?");
        }
            GameManager.getInstance().getLastTurn().setCardtype(toBeRemoved.getCardtype());
            GameManager.getInstance().nextTurn();
            GameManager.getInstance().sendLastTurnServerMessage();
            myCards.remove(index);
    }

    public void setMyCards(List<Card> myCards) {
        this.myCards = myCards;
    }
}