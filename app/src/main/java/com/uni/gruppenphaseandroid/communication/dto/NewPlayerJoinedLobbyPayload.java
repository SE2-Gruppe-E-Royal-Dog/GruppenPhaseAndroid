package com.uni.gruppenphaseandroid.communication.dto;

public class NewPlayerJoinedLobbyPayload {
    String playerName;

    public NewPlayerJoinedLobbyPayload(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }
}
