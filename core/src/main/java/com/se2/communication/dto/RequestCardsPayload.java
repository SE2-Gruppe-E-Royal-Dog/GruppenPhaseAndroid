package com.se2.communication.dto;

public class RequestCardsPayload {
    int lobbyID;
    int playerID;
    boolean sendAll;
    int numOfRequestedCards;

    public RequestCardsPayload(int lobbyID, int playerID, boolean sendAll, int numOfRequestedCards) {
        this.lobbyID = lobbyID;
        this.playerID = playerID;
        this.sendAll = sendAll;
        this.numOfRequestedCards = numOfRequestedCards;
    }

    public int getLobbyID() {
        return lobbyID;
    }

    public int getPlayerID() {
        return playerID;
    }

    public boolean isSendAll() {
        return sendAll;
    }

    public int getNumOfRequestedCards() {
        return numOfRequestedCards;
    }
}
