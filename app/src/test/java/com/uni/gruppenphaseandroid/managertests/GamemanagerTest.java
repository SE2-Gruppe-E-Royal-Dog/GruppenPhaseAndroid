package com.uni.gruppenphaseandroid.managertests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.view.View;
import android.widget.RelativeLayout;

import com.uni.gruppenphaseandroid.Cards.Card;
import com.uni.gruppenphaseandroid.Cards.Cardtype;
import com.uni.gruppenphaseandroid.R;
import com.uni.gruppenphaseandroid.manager.GameManager;
import com.uni.gruppenphaseandroid.manager.TurnPhase;
import com.uni.gruppenphaseandroid.playingfield.Color;
import com.uni.gruppenphaseandroid.playingfield.Figure;
import com.uni.gruppenphaseandroid.playingfield.FigureManager;
import com.uni.gruppenphaseandroid.playingfield.PlayingField;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GamemanagerTest {

    PlayingField playingField;
    View view;
    FigureManager figureManager;
    Card card;

    @Before
    public void setUp(){
        view = mock(View.class);
        playingField = mock(PlayingField.class);
        GameManager.getInstance().setPlayingField(playingField);
        figureManager = mock(FigureManager.class);
        card = new Card(Cardtype.EIGTH);
    }

    @After
    public void tearDown(){
        playingField = null;
        view = null;
    }

    @Test
    public void startGameTest(){
        GameManager.getInstance().startGame(4, 0, "id", figureManager);
        Assert.assertEquals(TurnPhase.CHOOSECARD, GameManager.getInstance().getCurrentTurnPhase());
        Assert.assertEquals(0, GameManager.getInstance().getMyTurnNumber());
        Assert.assertEquals(0, GameManager.getInstance().getCurrentTurnPlayerNumber());
        Assert.assertEquals(4, GameManager.getInstance().getNumberOfPlayers());
    }

    @Test
    public void nextTurnTestOnce(){
        GameManager.getInstance().startGame(4, 0, "id", figureManager);
        GameManager.getInstance().nextTurn();
        Assert.assertEquals(1, GameManager.getInstance().getCurrentTurnPlayerNumber());
        Assert.assertEquals(TurnPhase.CHOOSECARD, GameManager.getInstance().getCurrentTurnPhase());
    }

    @Test
    public void nextTurnTestTwice(){
        GameManager.getInstance().startGame(4, 0, "id", figureManager);
        GameManager.getInstance().nextTurn();
        GameManager.getInstance().nextTurn();
        Assert.assertEquals(2, GameManager.getInstance().getCurrentTurnPlayerNumber());
        Assert.assertEquals(TurnPhase.CHOOSECARD, GameManager.getInstance().getCurrentTurnPhase());
    }

    @Test
    public void nextTurnTestWholeRound(){
        GameManager.getInstance().startGame(4, 0, "id", figureManager);
        GameManager.getInstance().nextTurn();
        GameManager.getInstance().nextTurn();
        GameManager.getInstance().nextTurn();
        GameManager.getInstance().nextTurn();
        Assert.assertEquals(0, GameManager.getInstance().getCurrentTurnPlayerNumber());
        Assert.assertEquals(TurnPhase.CHOOSECARD, GameManager.getInstance().getCurrentTurnPhase());
    }

    @Test
    public void isItMyTurnTestTrue(){
        GameManager.getInstance().startGame(4, 0, "id", figureManager);
        Assert.assertEquals(true, GameManager.getInstance().isItMyTurn());
    }
    @Test
    public void isItMyTurnTestFalse(){
        GameManager.getInstance().startGame(4, 0, "id", figureManager);
        GameManager.getInstance().nextTurn();
        Assert.assertEquals(false, GameManager.getInstance().isItMyTurn());
    }

    @Test
    public void cardGotPlayedTest(){
        GameManager.getInstance().startGame(4, 0, "id", figureManager);
        GameManager.getInstance().cardGotPlayed(card);
        Assert.assertEquals(TurnPhase.CHOOSEFIGURE, GameManager.getInstance().getCurrentTurnPhase());
        Assert.assertEquals(card, GameManager.getInstance().getSelectedCard());
    }

}
