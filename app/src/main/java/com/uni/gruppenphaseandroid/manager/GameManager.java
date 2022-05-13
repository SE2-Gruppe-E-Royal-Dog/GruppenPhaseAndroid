package com.uni.gruppenphaseandroid.manager;

import android.view.View;

import com.se2.communication.dto.UpdateBoardPayload;
import com.uni.gruppenphaseandroid.Cards.Card;
import com.uni.gruppenphaseandroid.Cards.Cardtype;
import com.uni.gruppenphaseandroid.R;
import com.google.gson.Gson;
import com.se2.communication.dto.LeaveLobbyPayload;
import com.se2.communication.dto.Message;
import com.se2.communication.dto.MessageType;
import com.se2.communication.dto.WormholeSwitchPayload;
import com.uni.gruppenphaseandroid.MainActivity;
import com.uni.gruppenphaseandroid.playingfield.Color;
import com.uni.gruppenphaseandroid.playingfield.Figure;
import com.uni.gruppenphaseandroid.playingfield.FigureManager;
import com.uni.gruppenphaseandroid.playingfield.PlayingField;
import com.uni.gruppenphaseandroid.playingfield.Wormhole;

import org.java_websocket.client.WebSocketClient;

import java.util.List;

public class GameManager {

    public static GameManager instance;
    public static GameManager getInstance(){
        if(instance == null){
            instance = new GameManager();
        }
        return instance;
    }

    private int currentTurnPlayerNumber;
    private TurnPhase currentTurnPhase;
    private PlayingField playingField;
    private int numberOfPlayers;
    private int myTurnNumber;
    private WebSocketClient webSocketClient;
    private LastTurn lastTurn;
    //cardmanager
    private FigureManager figuremanager;
    private Card selectedCard;


    public void startGame(int numberOfPlayers, int playerTurnNumber){
        //deactivate start game button
        playingField.getView().findViewById(R.id.start_game_button).setVisibility(View.INVISIBLE);

        this.numberOfPlayers = numberOfPlayers;
        this.myTurnNumber = playerTurnNumber;
        figuremanager = new FigureManager();
        for(int i = 0; i<numberOfPlayers; i++){
            createFigureSet(Color.values()[i]);
        }
        currentTurnPlayerNumber = numberOfPlayers-1;
        nextTurn();
    }


    void createFigureSet(Color color){
        figuremanager.createFigureSetOfColor(color, playingField, playingField.getView().findViewById(R.id.playingFieldRelativeLayout));
    }

    void nextTurn(){

        currentTurnPlayerNumber += 1 % numberOfPlayers;

        if(!doesAnyoneHaveCardsLeftInHand()){
            everyOneDraws5Cards();
        }
        currentTurnPhase = TurnPhase.CHOOSECARD;

        if(myTurnNumber == currentTurnPlayerNumber){
            //my turn, do stuff
        }
        else {
            //other's turn, wait
        }
    }

    public void cardGotPlayed(Card card){
        if(currentTurnPhase == TurnPhase.CHOOSECARD && isItMyTurn()){
            currentTurnPhase = TurnPhase.CHOOSEFIGURE;
            selectedCard = card;
        }
    }

    public void figureGotSelected(Figure figure){
        if(currentTurnPhase == TurnPhase.CHOOSEFIGURE && isItMyTurn()){

            if(!checkIfMoveIsPossible(figure, selectedCard)){
                //show feedback
                currentTurnPhase = TurnPhase.CHOOSECARD;
                return;
            }
            currentTurnPhase = TurnPhase.CURRENTLYMOVING;
            int effect = 1;//TODO: set effect
            selectedCard.playCard(effect, figure, null);
            //send message to server
            webSocketClient.send(lastTurn.generateServerMessage());

        }
    }

    public void updateBoard(UpdateBoardPayload updateBoardPayload){
        if(currentTurnPhase == TurnPhase.CURRENTLYMOVING){
            if(!isItMyTurn()){ //for the turnplayer, the update took place already
                Figure figure1 = figuremanager.getFigureWithID(updateBoardPayload.getFigure1ID());
                Figure figure2 = figuremanager.getFigureWithID(updateBoardPayload.getFigure2ID());
                lastTurn = new LastTurn(figure1, figure2, playingField.getFieldWithID(updateBoardPayload.getNewField1ID()), playingField.getFieldWithID(updateBoardPayload.getNewField2ID()), 0);
                //TODO: play the card
                //TODO: update card UI
            }
            nextTurn();
        }
    }

    public boolean doesAnyoneHaveCardsLeftInHand(){
        return true;
    }

    private void everyOneDraws5Cards(){

    }

    private boolean checkIfMoveIsPossible(Figure figure, Card card){

        switch (card.getCardtype()){
            case TWO: return playingField.checkMovingPossible(figure, 2);
            //... other cases
        }
        return false;
    }

    private boolean isItMyTurn(){
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

    public void setWebSocketClient(WebSocketClient webSocketClient) {
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
            webSocketClient.send(String.valueOf(message));


    }


    public void moveFigureShowcase(int figureID, int distance){
        try {
            playingField.move(figuremanager.getFigureWithID(figureID),distance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
