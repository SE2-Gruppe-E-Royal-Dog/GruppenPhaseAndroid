package com.uni.gruppenphaseandroid.communication.dto;

public class UpdateBoardPayload {
    int figure1ID;
    int figure2ID;
    int newField1ID;
    int newField2ID;
    int cardType;
    int cheatModifier;
    String lobbyID;
    String playerID;

    public UpdateBoardPayload(int figure1ID, int figure2ID, int newField1ID, int newField2ID, int cardType, int cheatModifier, String lobbyID, String playerID) {
        this.figure1ID = figure1ID;
        this.figure2ID = figure2ID;
        this.newField1ID = newField1ID;
        this.newField2ID = newField2ID;
        this.cardType = cardType;
        this.cheatModifier = cheatModifier;
        this.lobbyID = lobbyID;
        this.playerID = playerID;
    }

    public int getFigure1ID() {
        return figure1ID;
    }

    public int getFigure2ID() {
        return figure2ID;
    }

    public int getNewField1ID() {
        return newField1ID;
    }

    public int getNewField2ID() {
        return newField2ID;
    }

    public int getCardType() {
        return cardType;
    }

    public int getCheatModifier(){
        return cheatModifier;
    }

    public String getLobbyID() {
        return lobbyID;
    }

    public void setLobbyID(String lobbyID) {
        this.lobbyID = lobbyID;
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }
}
