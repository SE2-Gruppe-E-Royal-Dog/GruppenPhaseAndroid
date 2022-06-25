package com.uni.gruppenphaseandroid.cheating;

import androidx.fragment.app.Fragment;

import com.uni.gruppenphaseandroid.manager.GameManager;

import java.util.ArrayList;
import java.util.List;

public class Cheater extends Fragment {

    private int roundIndex;
    private int playerID;                    //for now - String evtl in int ändern

    /**
     *liste ist static, dass alle die selbe Liste mit allen Cheaters habe --> man hätte diese dann noch verweden können fürs leader board um eine statistik auf zustellen, wie oft jemand geschummelt hat
     */
    private static List<Cheater> cheaters = new ArrayList<>();



    public Cheater(int playerID, int roundIndex) {
        this.playerID = playerID;
        this.roundIndex = roundIndex;                         //merkt sich die Runde, in der geschummelt wurde
    }


    /**
     * checks if cheating is permitted --> the player hasn't cheated within 5 rounds
     */
    public boolean cheatingAllowed(String playerId) {
        boolean cheatingAllowed = true;
        cheatingAllowed = !GameManager.getInstance().getHasCheated();
        return cheatingAllowed;
        }

        public boolean cheatingAllowedForThisClient(){
        return !GameManager.getInstance().getHasCheated();
        }

    /**
     * cheating ruft noteCheating auf weil um ein objekt der statischen Listen hinzuzufügen muss die methode auch static sein
     */
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


    public static void makeAccusation(int playerID, int currentRoundIndex, int numberOfPlayer) {
        for (int i=0; i<cheaters.size(); i++)
            if (cheaters.get(i).playerID == playerID && (currentRoundIndex - (cheaters.get(i).getRoundIndex()) <= numberOfPlayer - 1)) {
                GameManager.getInstance().punishPlayer(playerID);
                return;
            }
        GameManager.getInstance().punishPlayer(GameManager.getInstance().getCurrentTurnPlayerNumber());
    }


    public int getRoundIndex() {
        return roundIndex;
    }

    public int getPlayerID() {
        return playerID;
    }

    public static List<Cheater> getCheaters() {
        return cheaters;
    }




}
