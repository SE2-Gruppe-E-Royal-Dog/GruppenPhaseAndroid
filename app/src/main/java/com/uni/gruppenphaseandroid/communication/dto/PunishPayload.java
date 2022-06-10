package com.uni.gruppenphaseandroid.communication.dto;

public class PunishPayload {
    String LobbyID;
    int figureID;




    public PunishPayload(String lobbyID, int figureID) {
        LobbyID = lobbyID;
        this.figureID = figureID;
    }

    public String getLobbyID() {
        return LobbyID;
    }


    public int getFigureID() {
        return figureID;
    }


}
