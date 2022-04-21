package com.se2.communication.dto;

public class JoinedLobbyPayload {
    String lobbyId;

    public JoinedLobbyPayload(String lobbyId) {
        this.lobbyId = lobbyId;
    }

    public String getLobbyId() {
        return lobbyId;
    }

}
