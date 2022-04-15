package com.se2.communication.dto;

public class NewPlayerPayload {
    String playerName;

    public NewPlayerPayload(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }
}
