package com.uni.gruppenphaseandroid.cheating;


import androidx.fragment.app.Fragment;

import com.uni.gruppenphaseandroid.MainActivity;
import com.uni.gruppenphaseandroid.communication.Client;
import com.uni.gruppenphaseandroid.communication.dto.Message;
import com.uni.gruppenphaseandroid.communication.dto.MessageType;

import java.util.ArrayList;
import java.util.List;

public class Cheater extends Fragment {

    private int roundIndex;
    private int currentRoundIndex;
    private String playerID;                    //for now - String evtl in int ändern
    private boolean cheatingAllowed;
    private static List<Cheater> cheaters = new ArrayList<>();
    private Client websocketClient;


    public Cheater() {
    }

    public Cheater(String playerID, int roundIndex) {
        this.playerID = playerID;                             //bis jetzt nur am Server --> von gameManager?
        this.roundIndex = roundIndex;                         //merkt sich die Runde, in der geschummelt wurde
    }


    /**
     * checks if cheating is permitted --> the player hasn't cheated within 5 rounds
     */
    public boolean cheatingAllowed(String playerID) {
        this.cheatingAllowed = (( getRoundIndex() - getLastCheat(playerID)) >= 5);
        return cheatingAllowed;
        }

    public void cheating(Cheater cheater) {
        noteCheating(cheater);
    }

    public static void noteCheating(Cheater cheater) {
        cheaters.add(cheater);
    }

    public int getLastCheat(String playerID) {

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

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public boolean isCheatingAllowed() {
        return cheatingAllowed;
    }


    public static List<Cheater> getCheaters() {
        return cheaters;
    }


}
