package com.uni.gruppenphaseandroid.Cards;

public class Card {
    private Cardtype cardtype;
    private int cardID;

    public Card(Cardtype cardtype, int cardID){
        this.cardtype = cardtype;
        this.cardID = cardID;
    }

    public Cardtype getCardtype(){
        return cardtype;
    }

    public int getCardID(){
        return cardID;
    }
}
