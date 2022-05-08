package com.uni.gruppenphaseandroid.communication.dto;

public class JoinedLobbyPayload {
    String lobbyId;
    String playerId;

    public JoinedLobbyPayload(String lobbyId, String playerId) {
        this.lobbyId = lobbyId;
        this.playerId = playerId;
    }

    public String getLobbyId() {
        return lobbyId;
    }

    public String getPlayerId() {
        return playerId;
    }
}
