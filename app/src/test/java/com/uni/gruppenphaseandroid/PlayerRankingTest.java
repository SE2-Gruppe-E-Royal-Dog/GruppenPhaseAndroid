package com.uni.gruppenphaseandroid;

import com.uni.gruppenphaseandroid.manager.GameManager;
import com.uni.gruppenphaseandroid.manager.PlayerRanking;
import com.uni.gruppenphaseandroid.playingfield.Color;
import com.uni.gruppenphaseandroid.playingfield.Field;
import com.uni.gruppenphaseandroid.playingfield.Figure;
import com.uni.gruppenphaseandroid.playingfield.GoalField;
import com.uni.gruppenphaseandroid.playingfield.PlayingField;
import com.uni.gruppenphaseandroid.playingfield.Typ;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.view.View;
import android.widget.ImageView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerRankingTest {

    View view;
    ImageView imageView;
    PlayingField playingField;
    GameManager gameManager;

    @Before
    public void setup() {
        view = mock(View.class);
        imageView = mock(ImageView.class);
        when(view.findViewWithTag(anyString())).thenReturn(imageView);

        playingField = new PlayingField(view);
        gameManager = GameManager.getInstance();
        gameManager.setPlayingField(playingField);
        Field startingAreaField = playingField.getRedStartingField().getPreviousStartingArea();
        gameManager.setPlayerNames(new String[]{"one","two", "three", "four"});
    }

    @Test
    public void testOnePlayer() {
        gameManager.setNumberOfPlayers(1);

        GoalField currentGoal = playingField.getGreenStartingField().getNextGoalField();

        Figure figure1 = new Figure(1, Color.GREEN, currentGoal, Typ.JERK, null);
        currentGoal = (GoalField) currentGoal.getNextField();
        Figure figure2 = new Figure(2, Color.GREEN, currentGoal, Typ.CITIZEN, null);
        currentGoal = (GoalField) currentGoal.getNextField();
        Figure figure3 = new Figure(3, Color.GREEN, currentGoal, Typ.KNIGHT, null);
        currentGoal = (GoalField) currentGoal.getNextField();
        Figure figure4 = new Figure(4, Color.GREEN, currentGoal, Typ.KING, null);

        PlayerRanking playerRanking = new PlayerRanking();

        Assert.assertEquals("1: one", playerRanking.getRankedNames()[0]);
    }

    @Test
    public void testFourPlayers_DifferentPoints(){
        gameManager.setNumberOfPlayers(4);

        GoalField currentGoal = playingField.getGreenStartingField().getNextGoalField();

        Figure figure1 = new Figure(1, Color.GREEN, currentGoal, Typ.JERK, null);
        currentGoal = (GoalField)currentGoal.getNextField();
        Figure figure2 = new Figure(2, Color.GREEN, currentGoal, Typ.CITIZEN, null);
        currentGoal = (GoalField)currentGoal.getNextField();
        Figure figure3 = new Figure(3, Color.GREEN, currentGoal, Typ.KNIGHT, null);
        currentGoal = (GoalField)currentGoal.getNextField();
        Figure figure4 = new Figure(4, Color.GREEN, currentGoal, Typ.KING, null);

        currentGoal = (GoalField)playingField.getYellowStartingField().getNextGoalField().getNextField();
        Figure figure5 = new Figure(5, Color.YELLOW, currentGoal, Typ.KING, null);
        currentGoal = (GoalField)currentGoal.getNextField();
        Figure figure6 = new Figure(6, Color.YELLOW, currentGoal, Typ.KNIGHT, null);

        currentGoal = playingField.getRedStartingField().getNextGoalField();
        Figure figure7 = new Figure(7, Color.RED, currentGoal, Typ.JERK, null);
        currentGoal = (GoalField)currentGoal.getNextField();
        Figure figure8 = new Figure(8, Color.RED, currentGoal, Typ.CITIZEN, null);
        currentGoal = (GoalField)currentGoal.getNextField();
        Figure figure9 = new Figure(9, Color.RED, currentGoal, Typ.KNIGHT, null);

        PlayerRanking playerRanking = new PlayerRanking();

        Assert.assertEquals("1: one", playerRanking.getRankedNames()[0]);
        Assert.assertEquals("2: three", playerRanking.getRankedNames()[1]);
        Assert.assertEquals("3: two", playerRanking.getRankedNames()[2]);
        Assert.assertEquals("4: four", playerRanking.getRankedNames()[3]);
    }

    @Test
    public void testEqualPoints(){
        gameManager.setNumberOfPlayers(4);

        GoalField currentGoal = playingField.getGreenStartingField().getNextGoalField();
        Figure figure1 = new Figure(1, Color.GREEN, currentGoal, Typ.CITIZEN, null);
        currentGoal = (GoalField)currentGoal.getNextField();
        Figure figure2 = new Figure(2, Color.GREEN, currentGoal, Typ.KNIGHT, null);
        currentGoal = (GoalField)currentGoal.getNextField();
        Figure figure3 = new Figure(3, Color.GREEN, currentGoal, Typ.KING, null);

        currentGoal = (GoalField)playingField.getYellowStartingField().getNextGoalField().getNextField();
        Figure figure4 = new Figure(4, Color.YELLOW, currentGoal, Typ.KING, null);
        currentGoal = (GoalField)currentGoal.getNextField();
        Figure figure5 = new Figure(5, Color.YELLOW, currentGoal, Typ.KNIGHT, null);
        currentGoal = (GoalField)currentGoal.getNextField();
        Figure figure6 = new Figure(6, Color.YELLOW, currentGoal, Typ.KNIGHT, null);

        currentGoal = playingField.getRedStartingField().getNextGoalField();
        Figure figure7 = new Figure(7, Color.RED, currentGoal, Typ.JERK, null);
        currentGoal = (GoalField)currentGoal.getNextField();
        Figure figure8 = new Figure(8, Color.RED, currentGoal, Typ.CITIZEN, null);
        currentGoal = (GoalField)currentGoal.getNextField();
        Figure figure9 = new Figure(9, Color.RED, currentGoal, Typ.KNIGHT, null);
        currentGoal = (GoalField)currentGoal.getNextField();
        Figure figure10 = new Figure(10, Color.RED, currentGoal, Typ.KING, null);

        currentGoal = playingField.getBlueStartingField().getNextGoalField();
        Figure figure11 = new Figure(11, Color.BLUE, currentGoal, Typ.KING, null);

        PlayerRanking playerRanking = new PlayerRanking();

        Assert.assertEquals("1: three", playerRanking.getRankedNames()[0]);
        Assert.assertEquals("2: two", playerRanking.getRankedNames()[1]);
        Assert.assertEquals("2: one", playerRanking.getRankedNames()[2]);
        Assert.assertEquals("3: four", playerRanking.getRankedNames()[3]);
    }


}