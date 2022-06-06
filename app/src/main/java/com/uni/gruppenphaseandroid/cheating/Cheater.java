package com.uni.gruppenphaseandroid.cheating;


import androidx.fragment.app.Fragment;

import com.uni.gruppenphaseandroid.manager.GameManager;

import java.util.ArrayList;
import java.util.List;

public class Cheater extends Fragment {

    private int roundIndex;
    private int currentRoundIndex;
    private int playerID;                    //for now - String evtl in int ändern
    private boolean cheatingAllowed;
    private static List<Cheater> cheaters = new ArrayList<>();



    public Cheater() {
    }

    public Cheater(int playerID, int roundIndex) {
        this.playerID = playerID;                             //bis jetzt nur am Server --> von gameManager?
        this.roundIndex = roundIndex;                         //merkt sich die Runde, in der geschummelt wurde
    }


    /**
     * checks if cheating is permitted --> the player hasn't cheated within 5 rounds
     */
    public boolean cheatingAllowed(String playerID) {
        this.cheatingAllowed = !GameManager.getInstance().getHasCheated(); /**(( getRoundIndex() - getLastCheat(playerID)) >= 5);**/
        return cheatingAllowed;
        }

        public boolean cheatingAllowedForThisClient(){
        return !GameManager.getInstance().getHasCheated();
        }

    public void cheating(Cheater cheater) {
        noteCheating(cheater);
    }

    public static void noteCheating(Cheater cheater) {
        cheaters.add(cheater);
    }

    public int getLastCheat(int playerID) {

        int round = 0;
        if (!cheaters.isEmpty()) {
            for (Cheater c : cheaters) {
                if (c.getPlayerID() == (playerID)) {
                    round = c.getRoundIndex();
                }
            }
        }
        return round;
    }


    public static void emptyCheaters() {
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

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public boolean isCheatingAllowed() {
        return cheatingAllowed;
    }


    public static List<Cheater> getCheaters() {
        return cheaters;
    }

    public static void makeAccusation(int playerID, int currentRoundIndex, int numberOfPlayer) {
        for (int i=0; i<cheaters.size(); i++){
            if (cheaters.get(i).playerID == playerID){
                if (currentRoundIndex - (cheaters.get(i).getRoundIndex()) <= numberOfPlayer-1) {
                    GameManager.getInstance().punishPlayer(playerID);
                    return;
                }
            }
        }
        GameManager.getInstance().punishPlayer(GameManager.getInstance().getCurrentTurnPlayerNumber());
    }


}
