package com.uni.gruppenphaseandroid.manager;

import com.uni.gruppenphaseandroid.playingfield.GoalField;
import com.uni.gruppenphaseandroid.playingfield.PlayingField;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class PlayerRanking {

    private LinkedList<String> names;
    private LinkedList<Integer> points;

    public PlayerRanking() {
        GameManager gameManager = GameManager.getInstance();
        PlayingField playingField = gameManager.getPlayingField();

        names = gameManager.getPlayerNames();
        points = new LinkedList<>();
        points.addLast(calcPoints(playingField.getGreenStartingField().getNextGoalField()));
        if (names.get(1) != null) {
            points.addLast(calcPoints(playingField.getYellowStartingField().getNextGoalField()));
        }
        if (names.get(2) != null) {
            points.addLast(calcPoints(playingField.getRedStartingField().getNextGoalField()));
        }
        if (names.get(3) != null) {
            points.addLast(calcPoints(playingField.getBlueStartingField().getNextGoalField()));
        }

        sortLists();
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

    }

    private void addRanksToNames() {

    }

    public LinkedList<String> getRankedList() {
        return names;
    }
}
