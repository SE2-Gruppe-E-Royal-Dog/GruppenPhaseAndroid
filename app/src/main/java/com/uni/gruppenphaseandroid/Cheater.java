package com.uni.gruppenphaseandroid;



public class Cheater {


    private int roundIndex;
    private String playerID;

    public Cheater() {
    }

    public Cheater(String playerID, int roundIndex){
        this.playerID = playerID;
        this.roundIndex = roundIndex;
    }


    public int getRound() {
        return roundIndex;
    }

    public void setRound(int roundIndex) {
        this.roundIndex = roundIndex;
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }
}
