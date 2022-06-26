package com.uni.gruppenphaseandroid.manager;

import com.uni.gruppenphaseandroid.playingfield.GoalField;
import com.uni.gruppenphaseandroid.playingfield.PlayingField;

public class PlayerRanking {

    private String[] names;
    private int[] points;

    public PlayerRanking() {
        GameManager gameManager = GameManager.getInstance();
        PlayingField playingField = gameManager.getPlayingField();

        //Namen aller Spieler
        names = gameManager.getModifiedPlayerNamesArray();

        /*
        Punkte aller Spieler werden berechnet. Die Arrays mit den Namen und Punkten sind gleich sortiert.
        dh. Index 0: Spieler1 Name
                     Spieler1 Punkte

        Punkte sind die Anzahl der Figuren im Zielbereich (min = 0, max = 4).
         */
        points = new int[4];
        points[0] = calcPoints(playingField.getGreenStartingField().getNextGoalField());
        if (names[1] != null) {
            points[1] = calcPoints(playingField.getYellowStartingField().getNextGoalField());
        }else{
            points[1] = -1;
        }
        if (names[2] != null) {
            points[2] = calcPoints(playingField.getRedStartingField().getNextGoalField());
        }else{
            points[2] = -1;
        }
        if (names[3] != null) {
            points[3] = calcPoints(playingField.getBlueStartingField().getNextGoalField());
        }else{
            points[3] = -1;
        }

        sortLists();
        addRanksToNames();

    }

    /*
    Zählt wie viele Zielfelder eines Spielers besetzt sind. Gibt eine Ganzzahl zwischen 0 und 4 zurück.
     */
    private int calcPoints(GoalField goalField) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            if (goalField.getCurrentFigure() != null) {
                count++;
            }
            goalField = (GoalField)goalField.getNextField();
        }
        return count;
    }

    /*
    Sortiert beide Arrays von der höchsten zur niedrigsten Punktezahl.
     */
    private void sortLists() {
        for (int i = 0; i < names.length; i++) {
            for (int j = i + 1; j < names.length; j++) {
                if (points[i] < points[j]) {
                    int tmpNum = points[i];
                    String tmpName = names[i];
                    points[i] = points[j];
                    names[i] = names[j];
                    points[j] = tmpNum;
                    names[j] = tmpName;
                }
            }
        }
    }


    /*
    Fügt Ränge zu den Namen hinzu. Wenn die Punktezahl mehrerer Spieler gleich ist bekommen sie den selben
    Rang.
    Bsp. 1: Spieler1
         2: Spieler2
         2: Spieler3
         3: Spieler4
     */
    private void addRanksToNames() {
        String connector = ": ";
        int rank = 1;
        int prev = 4;
        for (int j = 0; j < names.length; j++) {
            if (points[j] < prev) {
                rank++;
            }
            if(names[j]==null){
                names[j] = "";
            }else {
                StringBuilder sb = new StringBuilder();
                sb.append(rank);
                sb.append(connector);
                sb.append(names[j]);
                names[j] = sb.toString();
            }
            prev = points[j];
        }
    }

    public String[] getRankedNames() {
        return names;
    }
}
