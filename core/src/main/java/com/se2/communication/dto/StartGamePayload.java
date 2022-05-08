package com.se2.communication.dto;

public class StartGamePayload {
    String lobbyID;
    int numberOfPlayers;
    int clientPlayerNumber;

    public StartGamePayload(String lobbyID, int numberOfPlayers, int clientPlayerNumber) {
        this.lobbyID = lobbyID;
        this.numberOfPlayers = numberOfPlayers;
        this.clientPlayerNumber = clientPlayerNumber;
    }

    public String getLobbyID() {
        return lobbyID;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public int getClientPlayerNumber() {
        return clientPlayerNumber;
    }
}
