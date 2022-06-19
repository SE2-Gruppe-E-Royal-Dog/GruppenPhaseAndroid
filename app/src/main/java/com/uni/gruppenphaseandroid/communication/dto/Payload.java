package com.uni.gruppenphaseandroid.communication.dto;

public abstract class Payload {
    private String lobbyID;
    private String playerID;

    protected Payload(String lobbyID, String playerID) {
        this.lobbyID = lobbyID;
        this.playerID = playerID;
    }

    public String getLobbyID() {
        return lobbyID;
    }

    public String getPlayerID() {
        return playerID;
    }
}
