package com.se2.communication.dto;

public class RequestCardsPayload {
    int numOfRequestedCards;

    public RequestCardsPayload(int numOfRequestedCards) {
        this.numOfRequestedCards = numOfRequestedCards;
    }

    public int getNumOfRequestedCards() {
        return numOfRequestedCards;
    }
}
