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

    Figure figure1;
    Figure figure2;
    Field field1;
    Field field2;
    UpdateBoardPayload updateBoardPayloadSingleFigure;
    UpdateBoardPayload updateBoardPayloadTwoFigures;

    @Before
    public void setUp() {
        view = mock(View.class);
        playingField = mock(PlayingField.class);
        GameManager.getInstance().setPlayingField(playingField);
        figureManager = mock(FigureManager.class);
        card = new Card(Cardtype.EIGTH);

        figure1 = new Figure();
        figure1.setId(1);
        figure2 = new Figure();
        figure2.setId(9);
        field1 = new Field();
        field1.setFieldID(3);
        field2 = new Field();
        field2.setFieldID(12);

        updateBoardPayloadSingleFigure =  new UpdateBoardPayload(1, -1, 3, -1, 4, 0, "id", "id");
        updateBoardPayloadTwoFigures = new UpdateBoardPayload(1, 9, 3, 12, 4, 0, "id", "id");

        when(figureManager.getFigureWithID(figure1.getId())).thenReturn(figure1);
        when(playingField.getFieldWithID(field1.getFieldID())).thenReturn(field1);
        when(figureManager.getFigureWithID(figure2.getId())).thenReturn(figure2);
        when(playingField.getFieldWithID(field2.getFieldID())).thenReturn(field2);

        GameManager.getInstance().startGame(4, 0, "id", "id", figureManager);
    }

    @After
    public void tearDown() {
        playingField = null;
        view = null;
    }

    @Test
    public void startGameTest() {
        Assert.assertEquals(TurnPhase.CHOOSECARD, GameManager.getInstance().getCurrentTurnPhase());
        Assert.assertEquals(0, GameManager.getInstance().getMyTurnNumber());
        Assert.assertEquals(0, GameManager.getInstance().getCurrentTurnPlayerNumber());
        Assert.assertEquals(4, GameManager.getInstance().getNumberOfPlayers());
    }

    @Test
    public void nextTurnTestOnce() {
        GameManager.getInstance().nextTurn();
        Assert.assertEquals(1, GameManager.getInstance().getCurrentTurnPlayerNumber());
        Assert.assertEquals(TurnPhase.CHOOSECARD, GameManager.getInstance().getCurrentTurnPhase());
    }

    @Test
    public void nextTurnTestTwice() {
        GameManager.getInstance().nextTurn();
        GameManager.getInstance().nextTurn();
        Assert.assertEquals(2, GameManager.getInstance().getCurrentTurnPlayerNumber());
        Assert.assertEquals(TurnPhase.CHOOSECARD, GameManager.getInstance().getCurrentTurnPhase());
    }

    @Test
    public void nextTurnTestWholeRound() {
        GameManager.getInstance().nextTurn();
        GameManager.getInstance().nextTurn();
        GameManager.getInstance().nextTurn();
        GameManager.getInstance().nextTurn();
        Assert.assertEquals(0, GameManager.getInstance().getCurrentTurnPlayerNumber());
        Assert.assertEquals(TurnPhase.CHOOSECARD, GameManager.getInstance().getCurrentTurnPhase());
    }

    @Test
    public void isItMyTurnTestTrue() {
        Assert.assertEquals(true, GameManager.getInstance().isItMyTurn());
    }

    @Test
    public void isItMyTurnTestFalse() {
        GameManager.getInstance().nextTurn();
        Assert.assertEquals(false, GameManager.getInstance().isItMyTurn());
    }

    @Test
    public void cardGotPlayedTest() {
        GameManager.getInstance().cardGotPlayed(card);
        Assert.assertEquals(TurnPhase.CHOOSEFIGURE, GameManager.getInstance().getCurrentTurnPhase());
        Assert.assertEquals(card, GameManager.getInstance().getSelectedCard());
    }

    @Test
    public void testUpdateBoardSingleFigure(){

        GameManager.getInstance().nextTurn();
        LastTurn expected = new LastTurn(figure1, null, field1, null);


        GameManager.getInstance().updateBoard(updateBoardPayloadSingleFigure);

        verify(figureManager, times(1)).getFigureWithID(figure1.getId());
        verify(playingField, times(1)).getFieldWithID(field1.getFieldID());
        verify(playingField, times(1)).moveFigureToField(figure1, field1);
        LastTurn actualLastTurn = GameManager.getInstance().getLastTurn();
        Assert.assertEquals(expected.getFigure1(), actualLastTurn.getFigure1());
        Assert.assertEquals(expected.getFigure2(), actualLastTurn.getFigure2());

    }

    @Test
    public void testUpdateBoardTwoFigures(){
        GameManager.getInstance().startGame(4, 0, "id", "id", figureManager);
        GameManager.getInstance().nextTurn();
        LastTurn expected = new LastTurn(figure1, figure2, field1, field2);

        GameManager.getInstance().updateBoard(updateBoardPayloadTwoFigures);

        verify(figureManager, times(1)).getFigureWithID(figure1.getId());
        verify(playingField, times(1)).getFieldWithID(field1.getFieldID());
        verify(figureManager, times(1)).getFigureWithID(figure2.getId());
        verify(playingField, times(1)).getFieldWithID(field2.getFieldID());
        verify(playingField, times(1)).moveFigureToField(figure1, field1);
        verify(playingField, times(1)).moveFigureToField(figure2, field2);
        LastTurn actualLastTurn = GameManager.getInstance().getLastTurn();
        Assert.assertEquals(expected.getFigure1(), actualLastTurn.getFigure1());
        Assert.assertEquals(expected.getFigure2(), actualLastTurn.getFigure2());

    }

}
