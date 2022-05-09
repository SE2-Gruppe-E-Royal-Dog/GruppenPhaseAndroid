package com.se2.communication.dto;

import java.util.LinkedList;

public class SendCardsPayload{
    private String lobbyID;
    private String playerID;
    private LinkedList cards;

    public SendCardsPayload(LinkedList cards) {
        this.cards = cards;
    }

    public LinkedList getCards() {
        return cards;
    }

    public String getLobbyID() {
        return lobbyID;
    }

    public String getPlayerID() {
        return playerID;
    }
}