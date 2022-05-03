package com.uni.gruppenphaseandroid.manager;

import com.uni.gruppenphaseandroid.MainActivity;
import com.uni.gruppenphaseandroid.playingfield.Color;
import com.uni.gruppenphaseandroid.playingfield.PlayingField;

import org.java_websocket.client.WebSocketClient;

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
    //figuremanager

    public void startGame(int numberOfPlayers, int playerTurnNumber){
        this.numberOfPlayers = numberOfPlayers;
        this.myTurnNumber = playerTurnNumber;
        //TODO: create figures
        for(int i = 0; i<numberOfPlayers; i++){
            createFigureSet(Color.values()[i]);
        }
        currentTurnPlayerNumber = 0;
        initializeCardDeck();
        nextTurn();
    }

    void initializeCardDeck(){

        //synchronize with server so that every client shuffles the same way
    }

    void createFigureSet(Color color){

    }

    void nextTurn(){

        currentTurnPlayerNumber += 1 % numberOfPlayers;
        currentTurnPhase = TurnPhase.CHOOSECARD;

        if(myTurnNumber == currentTurnPlayerNumber){
            //my turn, do stuff
        }
        else {
            //other's turn, wait
        }
    }

    public void cardGotPlayed(){
        if(currentTurnPhase == TurnPhase.CHOOSECARD && myTurnNumber == currentTurnPlayerNumber){
            currentTurnPhase = TurnPhase.CHOOSEFIGURE;
        }
    }

    public void figureGotSelected(){
        if(currentTurnPhase == TurnPhase.CHOOSEFIGURE && myTurnNumber == currentTurnPlayerNumber){
            currentTurnPhase = TurnPhase.CURRENTLYMOVING;
            //send message to server
            webSocketClient.send(lastTurn.generateServerMessage());

        }
    }

    public void updateBoard(String[] serverMessage){
        if(currentTurnPhase == TurnPhase.CURRENTLYMOVING){
            //update figures
            //update card UI
            //next turn
        }
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
}
