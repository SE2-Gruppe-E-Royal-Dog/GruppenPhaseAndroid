package com.uni.gruppenphaseandroid;


import java.util.ArrayList;
import java.util.List;

public class Cheater {

    private int roundIndex;
    private int currentRoundIndex;
    private String playerID;                    //for now - String evtl in int ändern
    private boolean cheatingAllowed;
    private static List<Cheater> cheaters = new ArrayList<>();


    public Cheater() {
    }

    public Cheater(String playerID, int roundIndex){
        this.playerID = playerID;                             //bis jetzt nur am Server --> von gameManager?
        this.roundIndex = roundIndex;                         //merkt sich die Runde, in der geschummelt wurde
        setCheatingAllowed(false);                            //constructor --> erstes mal schummeln --> daher für nächsten runden false
    }


    /**
     * checks if cheating is permitted --> the player hasn't cheated within 5 rounds
     */
    public boolean cheatingAllowed(String playerID){
        if (getlastCheat(playerID) == 0){
            setCheatingAllowed(true);
        }else {
            if ((getlastCheat(playerID) - getRoundIndex()) >= 5) {
                setCheatingAllowed(true);
            }else {
                setCheatingAllowed(false);
            }
        }


        return isCheatingAllowed();
    }

    public void cheating (Cheater c){
        //TODO koppel with move methode
        noteCheating(c);
    }

    public static void noteCheating (Cheater cheater){
        cheaters.add(cheater);
        cheater.setCheatingAllowed(false);

    }

    public int getlastCheat (String playerID){

        int round = 0;
        if (!cheaters.isEmpty()) {
            for (Cheater c : cheaters) {
                if (c.getPlayerID().equals(playerID)) {
                    round = c.getRoundIndex();
                }
            }
        }
        return round;
    }


    public static void emptyCheaters(){
        cheaters.clear();
    }



    //getter setter
    public int getRoundIndex() {
        return roundIndex;
    }

    public void setRoundIndex(int roundIndex) {
        this.roundIndex = roundIndex;
    }

    public int getCurrentRoundIndex() {
        return currentRoundIndex;
    }

    public void setCurrentRoundIndex(int currentRoundIndex) {
        this.currentRoundIndex = currentRoundIndex;
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public boolean isCheatingAllowed() {
        return cheatingAllowed;
    }

    public void setCheatingAllowed(boolean cheatingAllowed) {
        this.cheatingAllowed = cheatingAllowed;
    }

    public static List<Cheater> getCheaters() {
        return cheaters;
    }


}
