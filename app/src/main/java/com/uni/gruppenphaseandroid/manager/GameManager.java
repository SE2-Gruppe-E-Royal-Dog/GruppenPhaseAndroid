package com.uni.gruppenphaseandroid.manager;

import android.view.View;

import com.google.gson.Gson;
import com.uni.gruppenphaseandroid.Cards.Card;
import com.uni.gruppenphaseandroid.Cards.Cardtype;
import com.uni.gruppenphaseandroid.R;
import com.uni.gruppenphaseandroid.communication.Client;
import com.uni.gruppenphaseandroid.communication.dto.Message;
import com.uni.gruppenphaseandroid.communication.dto.MessageType;
import com.uni.gruppenphaseandroid.communication.dto.UpdateBoardPayload;
import com.uni.gruppenphaseandroid.communication.dto.WormholeSwitchPayload;
import com.uni.gruppenphaseandroid.playingfield.Color;
import com.uni.gruppenphaseandroid.playingfield.Field;
import com.uni.gruppenphaseandroid.playingfield.Figure;
import com.uni.gruppenphaseandroid.playingfield.FigureManager;
import com.uni.gruppenphaseandroid.playingfield.PlayingField;
import com.uni.gruppenphaseandroid.playingfield.Wormhole;

import org.java_websocket.client.WebSocketClient;

import java.util.List;

public class GameManager {

    private static GameManager instance;

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    private int currentTurnPlayerNumber;
    private TurnPhase currentTurnPhase;
    private PlayingField playingField;
    private int numberOfPlayers;
    private int myTurnNumber;
    private Client webSocketClient;
    private LastTurn lastTurn;
    //cardmanager
    private FigureManager figuremanager;
    private Card selectedCard;


    public void startGame(int numberOfPlayers, int playerTurnNumber) {
        //deactivate start game button
        playingField.getView().findViewById(R.id.start_game_button).setVisibility(View.INVISIBLE);

        this.numberOfPlayers = numberOfPlayers;
        this.myTurnNumber = playerTurnNumber;
        figuremanager = new FigureManager();
        for (int i = 0; i < numberOfPlayers; i++) {
            createFigureSet(Color.values()[i]);
        }
        currentTurnPlayerNumber = numberOfPlayers - 1;
        nextTurn();
    }


    void createFigureSet(Color color) {
        figuremanager.createFigureSetOfColor(color, playingField, playingField.getView().findViewById(R.id.playingFieldRelativeLayout));
    }

    void nextTurn() {

        currentTurnPlayerNumber += 1 % numberOfPlayers;

        if (!doesAnyoneHaveCardsLeftInHand()) {
            everyOneDraws5Cards();
        }
        currentTurnPhase = TurnPhase.CHOOSECARD;
    }

    public void cardGotPlayed(Card card) {
        if (currentTurnPhase == TurnPhase.CHOOSECARD && isItMyTurn()) {
            currentTurnPhase = TurnPhase.CHOOSEFIGURE;
            selectedCard = card;
        }
    }

    public void figureGotSelected(Figure figure) throws Exception {
        if (currentTurnPhase == TurnPhase.CHOOSEFIGURE && isItMyTurn()) {

            if (!checkIfMoveIsPossible(figure, selectedCard)) {
                //show feedback
                currentTurnPhase = TurnPhase.CHOOSECARD;
                return;
            }
            currentTurnPhase = TurnPhase.CURRENTLYMOVING;
            int effect = 1;//TODO: set effect
            selectedCard.playCard(figure, effect, null);
            //send message to server
            lastTurn.setCardtype(selectedCard.getCardtype());
            webSocketClient.send(lastTurn.generateServerMessage());
        }
    }

    public void updateBoard(UpdateBoardPayload updateBoardPayload) {
        if (currentTurnPhase == TurnPhase.CURRENTLYMOVING) {
            Figure figure1 = figuremanager.getFigureWithID(updateBoardPayload.getFigure1ID());
            Figure figure2 = (updateBoardPayload.getFigure2ID() == -1)?null:figuremanager.getFigureWithID(updateBoardPayload.getFigure2ID());
            Field figure1newField = playingField.getFieldWithID(updateBoardPayload.getNewField1ID());
            Field figure2newField = (updateBoardPayload.getNewField2ID() == -1)?null:playingField.getFieldWithID(updateBoardPayload.getNewField2ID());;
            lastTurn = new LastTurn(figure1, figure2,figure1newField , figure2newField, 0);

            if (!isItMyTurn()) { //for the turnplayer, the update took place already
                playingField.moveFigureToField(figure1, figure1newField);
                if(figure2 != null && figure2newField != null){
                    playingField.moveFigureToField(figure2, figure2newField);
                }
            }
            //play the card
            lastTurn.setCardtype(Cardtype.values()[updateBoardPayload.getCardType()]);
            //TODO: update card UI
            nextTurn();
        }

    }

    public boolean doesAnyoneHaveCardsLeftInHand() {
        return true;
    }

    private void everyOneDraws5Cards() {

    }

    private boolean checkIfMoveIsPossible(Figure figure, Card card) {

        switch (card.getCardtype()) {
            case TWO:
                return playingField.checkMovingPossible(figure, 2);
            //... other cases
        }
        return false;
    }

    private boolean isItMyTurn() {
        return (currentTurnPlayerNumber == myTurnNumber);
    }


    public PlayingField getPlayingField() {
        return playingField;
    }

    public void setPlayingField(PlayingField playingField) {
        this.playingField = playingField;
    }

    public WebSocketClient getWebSocketClient() {
        return webSocketClient;
    }

    public void setWebSocketClient(Client webSocketClient) {
        this.webSocketClient = webSocketClient;
    }

    public LastTurn getLastTurn() {
        return lastTurn;
    }

    public void setLastTurn(LastTurn lastTurn) {
        this.lastTurn = lastTurn;
    }

    public void moveWormholes() {
        if (isItMyTurn() == true || currentTurnPhase == TurnPhase.CURRENTLYMOVING) {
            return;
        }

        playingField.moveAllWormholesRandomly();
        List<Wormhole> wormholeList = playingField.getWormholeList();

        var payload = new WormholeSwitchPayload(wormholeList.get(0).getFieldID(), wormholeList.get(1).getFieldID(), wormholeList.get(2).getFieldID(), wormholeList.get(3).getFieldID());
        var message = new Message();
        message.setType(MessageType.WORMHOLE_MOVE);
        message.setPayload(new Gson().toJson(payload));
        webSocketClient.send(message);
    }


    public void moveFigureShowcase(int figureID, int distance) {
        try {
            playingField.move(figuremanager.getFigureWithID(figureID), distance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
