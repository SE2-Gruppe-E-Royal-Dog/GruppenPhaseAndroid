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
import com.uni.gruppenphaseandroid.manager.VisualEffectsManager;
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
    String playerID;
    Message message;

    @Before
    public void setUp() {
        lobbyID = "id";
        playerID = "playerID";
        view = mock(View.class);
        playingField = mock(PlayingField.class);
        GameManager.getInstance().setPlayingField(playingField);
        socketClient = mock(Client.class);
        GameManager.getInstance().setWebSocketClient(socketClient);
        figureManager = mock(FigureManager.class);
        wormholeList = new ArrayList<>();
        when(playingField.getWormholeList()).thenReturn(wormholeList);

        for ( int i = 0; i<4; i++){
            Wormhole wormhole = mock(Wormhole.class);
            when(wormhole.getFieldID()).thenReturn(i);
            wormholeList.add(wormhole);
          }
        var payload = new WormholeSwitchPayload(wormholeList.get(0).getFieldID(), wormholeList.get(1).getFieldID(), wormholeList.get(2).getFieldID(), wormholeList.get(3).getFieldID(), lobbyID);
        message = new Message();
        message.setType(MessageType.WORMHOLE_MOVE);
        message.setPayload(new Gson().toJson(payload));

        VisualEffectsManager visualEffectsManager = new VisualEffectsManager() {
            @Override
            protected void setStackImage() {

            }

            @Override
            protected void setInitialStackImage() {

            }

            @Override
            protected void showIllegalMoveMessage() {

            }

            @Override
            protected void showWinningScreen() {

            }
        };
        GameManager.getInstance().startGame(4, 2, lobbyID, playerID, figureManager, visualEffectsManager);
      //  Assert.assertFalse(GameManager.getInstance().hasCheated());
    }

    @After
    public void tearDown() {
        playingField = null;
        view = null;
    }

    @Test
    public void testInitialWormholeMove(){
        var argumentCaptor = ArgumentCaptor.forClass(Message.class);
        GameManager.getInstance().initiateMoveWormholes();
        verify(playingField,times(1)).moveAllWormholesRandomly();
        verify(playingField,times(1)).getWormholeList();
        Assert.assertTrue(GameManager.getInstance().getHasMovedWormholes());
        verify(socketClient,times(1)).send(argumentCaptor.capture());
        Assert.assertEquals(message.getPayload(), argumentCaptor.getValue().getPayload());
    }

    @Test
    public void testWormholeMove(){
        int [] newFieldID = {6,22,38,54};
        for (int i = 0; i<4; i++){
            when(playingField.getFieldWithID(newFieldID[i])).thenReturn(null);
                   }

        GameManager.getInstance().moveWormholes(newFieldID);
        verify(playingField,times(1)).repairWormholeVisuals();
        verify(playingField,times(4)).repairRootField();
        verify(playingField,times(4)).getWormholeList();

        for (int i = 0; i<4; i++){
            verify(wormholeList.get(i), times(1)).switchField(null);
            verify(playingField, times(1)).getFieldWithID(newFieldID[i]);
        }

    }


}
