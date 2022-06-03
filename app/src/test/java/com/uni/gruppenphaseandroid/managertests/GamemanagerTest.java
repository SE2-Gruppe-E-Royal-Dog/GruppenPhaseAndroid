package com.uni.gruppenphaseandroid.managertests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.view.View;

import com.uni.gruppenphaseandroid.cards.Card;
import com.uni.gruppenphaseandroid.cards.Cardtype;
import com.uni.gruppenphaseandroid.communication.dto.UpdateBoardPayload;
import com.uni.gruppenphaseandroid.manager.GameManager;
import com.uni.gruppenphaseandroid.manager.LastTurn;
import com.uni.gruppenphaseandroid.manager.TurnPhase;
import com.uni.gruppenphaseandroid.playingfield.Color;
import com.uni.gruppenphaseandroid.playingfield.Field;
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
    public void setUp() {
        view = mock(View.class);
        playingField = mock(PlayingField.class);
        GameManager.getInstance().setPlayingField(playingField);
        figureManager = mock(FigureManager.class);
        card = new Card(Cardtype.EIGTH);
    }

    @After
    public void tearDown() {
        playingField = null;
        view = null;
    }

    @Test
    public void startGameTest() {
        GameManager.getInstance().startGame(4, 0, "id", "id", figureManager);
        Assert.assertEquals(TurnPhase.CHOOSECARD, GameManager.getInstance().getCurrentTurnPhase());
        Assert.assertEquals(0, GameManager.getInstance().getMyTurnNumber());
        Assert.assertEquals(0, GameManager.getInstance().getCurrentTurnPlayerNumber());
        Assert.assertEquals(4, GameManager.getInstance().getNumberOfPlayers());
    }

    @Test
    public void nextTurnTestOnce() {
        GameManager.getInstance().startGame(4, 0, "id","id", figureManager);
        GameManager.getInstance().nextTurn();
        Assert.assertEquals(1, GameManager.getInstance().getCurrentTurnPlayerNumber());
        Assert.assertEquals(TurnPhase.CHOOSECARD, GameManager.getInstance().getCurrentTurnPhase());
    }

    @Test
    public void nextTurnTestTwice() {
        GameManager.getInstance().startGame(4, 0, "id","id", figureManager);
        GameManager.getInstance().nextTurn();
        GameManager.getInstance().nextTurn();
        Assert.assertEquals(2, GameManager.getInstance().getCurrentTurnPlayerNumber());
        Assert.assertEquals(TurnPhase.CHOOSECARD, GameManager.getInstance().getCurrentTurnPhase());
    }

    @Test
    public void nextTurnTestWholeRound() {
        GameManager.getInstance().startGame(4, 0, "id","id", figureManager);
        GameManager.getInstance().nextTurn();
        GameManager.getInstance().nextTurn();
        GameManager.getInstance().nextTurn();
        GameManager.getInstance().nextTurn();
        Assert.assertEquals(0, GameManager.getInstance().getCurrentTurnPlayerNumber());
        Assert.assertEquals(TurnPhase.CHOOSECARD, GameManager.getInstance().getCurrentTurnPhase());
    }

    @Test
    public void isItMyTurnTestTrue() {
        GameManager.getInstance().startGame(4, 0, "id","id", figureManager);
        Assert.assertEquals(true, GameManager.getInstance().isItMyTurn());
    }

    @Test
    public void isItMyTurnTestFalse() {
        GameManager.getInstance().startGame(4, 0, "id","id", figureManager);
        GameManager.getInstance().nextTurn();
        Assert.assertEquals(false, GameManager.getInstance().isItMyTurn());
    }

    @Test
    public void cardGotPlayedTest() {
        GameManager.getInstance().startGame(4, 0, "id","id", figureManager);
        GameManager.getInstance().cardGotPlayed(card);
        Assert.assertEquals(TurnPhase.CHOOSEFIGURE, GameManager.getInstance().getCurrentTurnPhase());
        Assert.assertEquals(card, GameManager.getInstance().getSelectedCard());
    }

    @Test
    public void testUpdateBoardSingleFigure(){
        GameManager.getInstance().startGame(4, 0, "id", "id", figureManager);
        GameManager.getInstance().nextTurn();
        Figure figure1 = new Figure();
        Field field1 = new Field();
        UpdateBoardPayload updateBoardPayload = new UpdateBoardPayload(1, -1, 3, -1, 4, 0, "id", "id");
        LastTurn expected = new LastTurn(figure1, null, field1, null);
        when(figureManager.getFigureWithID(1)).thenReturn(figure1);
        when(playingField.getFieldWithID(3)).thenReturn(field1);

        GameManager.getInstance().updateBoard(updateBoardPayload);

        verify(figureManager, times(1)).getFigureWithID(1);
        verify(playingField, times(1)).getFieldWithID(3);
        verify(playingField, times(1)).moveFigureToField(figure1, field1);
        LastTurn actualLastTurn = GameManager.getInstance().getLastTurn();
        Assert.assertEquals(expected.getFigure1(), actualLastTurn.getFigure1());
        Assert.assertEquals(expected.getFigure2(), actualLastTurn.getFigure2());

    }

    @Test
    public void testUpdateBoardTwoFigures(){
        GameManager.getInstance().startGame(4, 0, "id", "id", figureManager);
        GameManager.getInstance().nextTurn();
        Figure figure1 = new Figure();
        Figure figure2 = new Figure();
        Field field1 = new Field();
        Field field2 = new Field();
        UpdateBoardPayload updateBoardPayload = new UpdateBoardPayload(1, 9, 3, 12, 4, 0, "id", "id");
        LastTurn expected = new LastTurn(figure1, figure2, field1, field2);
        when(figureManager.getFigureWithID(1)).thenReturn(figure1);
        when(playingField.getFieldWithID(3)).thenReturn(field1);
        when(figureManager.getFigureWithID(9)).thenReturn(figure2);
        when(playingField.getFieldWithID(12)).thenReturn(field2);

        GameManager.getInstance().updateBoard(updateBoardPayload);

        verify(figureManager, times(1)).getFigureWithID(1);
        verify(playingField, times(1)).getFieldWithID(3);
        verify(figureManager, times(1)).getFigureWithID(9);
        verify(playingField, times(1)).getFieldWithID(12);
        verify(playingField, times(1)).moveFigureToField(figure1, field1);
        verify(playingField, times(1)).moveFigureToField(figure2, field2);
        LastTurn actualLastTurn = GameManager.getInstance().getLastTurn();
        Assert.assertEquals(expected.getFigure1(), actualLastTurn.getFigure1());
        Assert.assertEquals(expected.getFigure2(), actualLastTurn.getFigure2());

    }

}
