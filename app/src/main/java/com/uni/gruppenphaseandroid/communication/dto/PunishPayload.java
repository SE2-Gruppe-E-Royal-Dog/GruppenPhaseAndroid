package com.uni.gruppenphaseandroid.communication.dto;

/**
 * hier übergeben wir die lobby ID und die Figuren ID, damit dann ein Abgleich
 * auf die anderen Clients gemacht werden kann
 */
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
