package com.se2.communication.dto;

public class NewPlayerJoinedLobbyPayload {
    String playerName;

    public NewPlayerJoinedLobbyPayload(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }
}
