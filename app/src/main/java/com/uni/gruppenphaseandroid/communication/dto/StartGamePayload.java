package com.uni.gruppenphaseandroid.communication.dto;

import java.util.LinkedList;

public class StartGamePayload {
    String lobbyID;
    int numberOfPlayers;
    int clientPlayerNumber;
    LinkedList<String> playerNames;

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

    public LinkedList<String> getPlayerNames() {
        return playerNames;
    }
}
