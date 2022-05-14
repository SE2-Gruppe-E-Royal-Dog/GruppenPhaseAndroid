package com.uni.gruppenphaseandroid.communication.dto;

public class PlayerLeftLobbyPayload {
    String playerName;

    public PlayerLeftLobbyPayload(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }
}
