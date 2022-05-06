package com.se2.communication.dto;

public abstract class Payload {
    private int lobbyID;
    private int playerID;

    public Payload(int lobbyID, int playerID) {
        this.lobbyID = lobbyID;
        this.playerID = playerID;
    }

    public int getLobbyID() {
        return lobbyID;
    }

    public int getPlayerID() {
        return playerID;
    }
}
