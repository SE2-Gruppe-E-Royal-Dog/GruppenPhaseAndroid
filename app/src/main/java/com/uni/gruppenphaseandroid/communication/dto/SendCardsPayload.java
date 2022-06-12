package com.uni.gruppenphaseandroid.communication.dto;

import com.uni.gruppenphaseandroid.cards.Card;

import java.util.LinkedList;

public class SendCardsPayload {
    private String lobbyID;
    private String playerID;
    private LinkedList<Card> cards;

    public SendCardsPayload(LinkedList<Card> cards) {
        this.cards = cards;
    }

    public LinkedList<Card> getCards() {
        return cards;
    }

    public String getLobbyID() {
        return lobbyID;
    }

    public String getPlayerID() {
        return playerID;
    }
}