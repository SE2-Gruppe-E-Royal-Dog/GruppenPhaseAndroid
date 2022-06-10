package com.uni.gruppenphaseandroid.communication.dto;

public class PunishPayload {
    String lobbyID;
    int figureID;

    public PunishPayload(String lobbyID, int figureID) {
        this.lobbyID = lobbyID;
        this.figureID = figureID;
    }

    public String getLobbyID() {
        return lobbyID;
    }


    public int getFigureID() {
        return figureID;
    }


}
