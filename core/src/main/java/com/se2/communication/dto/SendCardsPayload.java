package com.se2.communication.dto;

import java.util.LinkedList;

public class SendCardsPayload{
    private LinkedList cards;

    public SendCardsPayload(LinkedList cards) {
        this.cards = cards;
    }

    public LinkedList getCards() {
        return cards;
    }
}