package com.se2.communication.dto;

public class PlayerLeftLobbyPayload {
    String playerName;

    public PlayerLeftLobbyPayload(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }
}
