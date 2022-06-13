package com.uni.gruppenphaseandroid.manager;

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
        Card toBeRemoved = myCards.remove(index);
        GameManager.getInstance().getLastTurn().setCardtype(toBeRemoved.getCardtype());
    }

    public void setMyCards(List<Card> myCards) {
        this.myCards = myCards;
    }
}