package com.uni.gruppenphaseandroid.manager;

import com.uni.gruppenphaseandroid.cards.Card;

import java.util.LinkedList;

public class Handcards {
    private static Handcards hand;
    private LinkedList<Card> myCards;

    private Handcards() {
        myCards = new LinkedList<>();
    }

    public static Handcards getInstance() {
        if (hand == null) {
            hand = new Handcards();
        }
        return hand;
    }

    public LinkedList<Card> getMyCards() {
        return myCards;
    }

    public void addCardToHand(LinkedList<Card> cards) {
        myCards.addAll(cards);
    }

    public void discardHandcard(int index){
        myCards.remove(index);
    }
}