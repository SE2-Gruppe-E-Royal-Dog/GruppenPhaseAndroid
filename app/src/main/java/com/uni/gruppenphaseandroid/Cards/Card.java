package com.uni.gruppenphaseandroid.Cards;

public class Card {
    private Cardtype cardtype;

    public Card(Cardtype cardtype){
        this.cardtype = cardtype;
    }

    public Cardtype getCardtype(){
        return cardtype;
    }
}
