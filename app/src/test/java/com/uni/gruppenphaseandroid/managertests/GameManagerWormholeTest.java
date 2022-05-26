package com.uni.gruppenphaseandroid.managertests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.view.View;

import com.google.gson.Gson;
import com.uni.gruppenphaseandroid.cards.Card;
import com.uni.gruppenphaseandroid.communication.Client;
import com.uni.gruppenphaseandroid.communication.dto.Message;
import com.uni.gruppenphaseandroid.communication.dto.MessageType;
import com.uni.gruppenphaseandroid.communication.dto.WormholeSwitchPayload;
import com.uni.gruppenphaseandroid.manager.GameManager;
import com.uni.gruppenphaseandroid.manager.TurnPhase;
import com.uni.gruppenphaseandroid.playingfield.FigureManager;
import com.uni.gruppenphaseandroid.playingfield.PlayingField;
import com.uni.gruppenphaseandroid.playingfield.Wormhole;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;

public class GameManagerWormholeTest {

    PlayingField playingField;
    View view;
    FigureManager figureManager;
    Card card;
    Client socketClient;
    ArrayList<Wormhole> wormholeList;
    String lobbyID;
    Message message;

    @Before
    public void setUp() {
        lobbyID = "id";
        view = mock(View.class);
        playingField = mock(PlayingField.class);
        GameManager.getInstance().setPlayingField(playingField);
        socketClient = mock(Client.class);
        GameManager.getInstance().setWebSocketClient(socketClient);
        figureManager = mock(FigureManager.class);
        wormholeList = new ArrayList<>();
        when(playingField.getWormholeList()).thenReturn(wormholeList);

        for ( int i = 0; i<4; i++){
            wormholeList.add(new Wormhole(i));
          }
        var payload = new WormholeSwitchPayload(wormholeList.get(0).getFieldID(), wormholeList.get(1).getFieldID(), wormholeList.get(2).getFieldID(), wormholeList.get(3).getFieldID(), lobbyID);
        message = new Message();
        message.setType(MessageType.WORMHOLE_MOVE);
        message.setPayload(new Gson().toJson(payload));
    }

    @After
    public void tearDown() {
        playingField = null;
        view = null;
    }

    @Test
    public void testWormholeMove(){
        var argumentCaptor = ArgumentCaptor.forClass(Message.class);
        GameManager.getInstance().startGame(4, 2, lobbyID, figureManager);
       // GameManager.getInstance().setCurrentTurnPhase(TurnPhase.CURRENTLYMOVING);
        Assert.assertFalse(GameManager.getInstance().hasCheated());
        GameManager.getInstance().moveWormholes();
        verify(playingField,times(1)).moveAllWormholesRandomly();
        verify(playingField,times(1)).getWormholeList();
        Assert.assertTrue(GameManager.getInstance().hasCheated());
        verify(socketClient,times(1)).send(argumentCaptor.capture());
        Assert.assertEquals(message.getPayload(), argumentCaptor.getValue().getPayload());
    }

}
