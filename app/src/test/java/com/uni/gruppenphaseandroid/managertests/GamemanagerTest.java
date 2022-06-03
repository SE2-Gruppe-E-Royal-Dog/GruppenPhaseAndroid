package com.uni.gruppenphaseandroid.managertests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.view.View;

import com.google.gson.Gson;
import com.uni.gruppenphaseandroid.cards.Card;
import com.uni.gruppenphaseandroid.cards.Cardtype;
import com.uni.gruppenphaseandroid.communication.Client;
import com.uni.gruppenphaseandroid.communication.dto.Message;
import com.uni.gruppenphaseandroid.communication.dto.MessageType;
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
import org.mockito.ArgumentCaptor;

public class GamemanagerTest {

    PlayingField playingField;
    View view;
    FigureManager figureManager;
    Card cardEight;
    Card cardSwitch;

    Figure figure1;
    Figure figure2;
    Field field1;
    Field field2;
    UpdateBoardPayload updateBoardPayloadSingleFigure;
    UpdateBoardPayload updateBoardPayloadTwoFigures;
    Client socketClient;
    Message message1;
    Message message2;

    @Before
    public void setUp() {
        view = mock(View.class);
        playingField = mock(PlayingField.class);
        GameManager.getInstance().setPlayingField(playingField);
        figureManager = mock(FigureManager.class);
        cardEight = mock(Card.class);
        cardSwitch = mock(Card.class);
        when(cardEight.getCardtype()).thenReturn(Cardtype.EIGTH);
        when(cardSwitch.getCardtype()).thenReturn(Cardtype.SWITCH);
        socketClient = mock(Client.class);

        figure1 = new Figure();
        figure1.setId(1);
        figure1.setColor(Color.GREEN);
        figure2 = new Figure();
        figure2.setId(9);
        field1 = new Field();
        field1.setFieldID(3);
        field2 = new Field();
        field2.setFieldID(12);

        updateBoardPayloadSingleFigure =  new UpdateBoardPayload(1, -1, 3, -1, 6, 0, "id", "id");
        updateBoardPayloadTwoFigures = new UpdateBoardPayload(1, 9, 3, 12, 14, 0, "id", "id");

        when(figureManager.getFigureWithID(figure1.getId())).thenReturn(figure1);
        when(playingField.getFieldWithID(field1.getFieldID())).thenReturn(field1);
        when(figureManager.getFigureWithID(figure2.getId())).thenReturn(figure2);
        when(playingField.getFieldWithID(field2.getFieldID())).thenReturn(field2);

        GameManager.getInstance().setWebSocketClient(socketClient);
        message1 = new Message();
        message1.setType(MessageType.UPDATE_BOARD);
        message1.setPayload(new Gson().toJson(updateBoardPayloadSingleFigure));
        message2 = new Message();
        message2.setType(MessageType.UPDATE_BOARD);
        message2.setPayload(new Gson().toJson(updateBoardPayloadTwoFigures));

        GameManager.getInstance().startGame(4, 0, "id", "id", figureManager);
    }

    @After
    public void tearDown() {
        playingField = null;
        view = null;
        updateBoardPayloadTwoFigures = null;
        updateBoardPayloadSingleFigure = null;
        field1 = null;
        field2 = null;
        figure1 = null;
        figure2 = null;
        cardEight = null;
        cardSwitch = null;
        GameManager.getInstance().setCurrentEffect(0);
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
        GameManager.getInstance().cardGotPlayed(cardEight);
        Assert.assertEquals(TurnPhase.CHOOSEFIGURE, GameManager.getInstance().getCurrentTurnPhase());
        Assert.assertEquals(cardEight, GameManager.getInstance().getSelectedCard());
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

    @Test
    public void figureGotSelectedTest(){
        GameManager.getInstance().setCurrentEffect(0);
        var argumentCaptor = ArgumentCaptor.forClass(Message.class);
        when(cardEight.checkIfCardIsPlayable(figure1, 0, null)).thenReturn(true);
        LastTurn lastTurn = new LastTurn(figure1, null, field1, null);
        GameManager.getInstance().setLastTurn(lastTurn);

        GameManager.getInstance().cardGotPlayed(cardEight);
        GameManager.getInstance().figureGotSelected(figure1);

        verify(socketClient,times(1)).send(argumentCaptor.capture());
        Assert.assertEquals(message1.getPayload(), argumentCaptor.getValue().getPayload());
        verify(socketClient, times(1)).send(argumentCaptor.capture());
        verify(cardEight, times(1)).playCard(figure1, 0, null);
        verify(cardEight, times(1)).checkIfCardIsPlayable(figure1, 0, null);
    }

    @Test
    public void figureGotSelectedSwitchCase(){
        GameManager.getInstance().setCurrentEffect(-1);
        var argumentCaptor = ArgumentCaptor.forClass(Message.class);
        when(cardSwitch.checkIfCardIsPlayable(figure1, -1, figure2)).thenReturn(true);
        LastTurn lastTurn = new LastTurn(figure1, figure2, field1, field2);
        GameManager.getInstance().setLastTurn(lastTurn);

        GameManager.getInstance().cardGotPlayed(cardSwitch);
        GameManager.getInstance().figureGotSelected(figure1);
        GameManager.getInstance().figureGotSelected(figure2);

        verify(socketClient,times(1)).send(argumentCaptor.capture());
        Assert.assertEquals(message2.getPayload(), argumentCaptor.getValue().getPayload());
        verify(socketClient, times(1)).send(argumentCaptor.capture());
        verify(cardSwitch, times(1)).playCard(figure1, -1, figure2);
        verify(cardSwitch, times(1)).checkIfCardIsPlayable(figure1, -1, figure2);
    }

}
