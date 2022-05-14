package com.uni.gruppenphaseandroid.communication.dto;

public class NewPlayerPayload {
    String playerName;

    public NewPlayerPayload(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }
}
