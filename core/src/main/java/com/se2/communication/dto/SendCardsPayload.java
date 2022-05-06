package com.se2.communication.dto;

import java.util.LinkedList;

public class SendCardsPayload extends Payload {
    private LinkedList cards;

    public SendCardsPayload(String lobbyID, String playerID, LinkedList cards) {
        super(lobbyID, playerID);
        this.cards = cards;
    }

    public LinkedList getCards() {
        return cards;
    }
}