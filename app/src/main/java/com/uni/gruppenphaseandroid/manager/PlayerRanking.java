package com.uni.gruppenphaseandroid.manager;

import com.uni.gruppenphaseandroid.playingfield.GoalField;
import com.uni.gruppenphaseandroid.playingfield.PlayingField;

public class PlayerRanking {

    private String[] names;
    private int[] points;

    public PlayerRanking() {
        GameManager gameManager = GameManager.getInstance();
        PlayingField playingField = gameManager.getPlayingField();

        names = gameManager.getModifiedPlayerNamesArray();

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
