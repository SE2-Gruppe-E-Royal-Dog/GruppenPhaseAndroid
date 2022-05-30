package com.uni.gruppenphaseandroid.manager;

import com.google.gson.Gson;
import com.uni.gruppenphaseandroid.Cards.Card;
import com.uni.gruppenphaseandroid.Cards.Cardtype;
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
    private FigureManager figuremanager;
    private Card selectedCard;
    private int currentEffect;                          //int for special cards
    private String lobbyID;
    private boolean hasCheated = false;
    private Figure currentlySelectedFigure;

    public void startGame(int numberOfPlayers, int playerTurnNumber, String lobbyID, FigureManager figureManager) {
        this.lobbyID = lobbyID;

        this.numberOfPlayers = numberOfPlayers;
        this.myTurnNumber = playerTurnNumber;
        this.figuremanager = figureManager;
        for (int i = 0; i < numberOfPlayers; i++) {
            createFigureSet(Color.values()[i]);
        }
        currentTurnPlayerNumber = numberOfPlayers - 1;
        nextTurn();
    }


    void createFigureSet(Color color) {
        figuremanager.createFigureSetOfColor(color, playingField);
    }

    public void nextTurn() {

        currentTurnPlayerNumber = (currentTurnPlayerNumber + 1) % numberOfPlayers;

        currentTurnPhase = TurnPhase.CHOOSECARD;

        if(!isThereAnyPossibleMove()){
            turnPlayerDiscardsCard();
            //nextTurn();
        }
    }

    public void cardGotPlayed(Card card) {
        if (currentTurnPhase == TurnPhase.CHOOSECARD && isItMyTurn()) {
            currentTurnPhase = TurnPhase.CHOOSEFIGURE;
            selectedCard = card;
        }
    }

    public void figureGotSelected(Figure figure) throws Exception {
        if (currentTurnPhase == TurnPhase.CHOOSEFIGURE && isItMyTurn()) {
            figureSelectedNormalCase(figure);
        }
        else if(currentTurnPhase == TurnPhase.CHOOSESECONDFIGURE && isItMyTurn()){
            figureSelectedSwitchCase(figure);
        }
        selectedCard = null;
    }

    private void figureSelectedNormalCase(Figure figure){
        if(selectedCard.getCardtype() == Cardtype.SWITCH){
            currentTurnPhase = TurnPhase.CHOOSESECONDFIGURE;
            currentlySelectedFigure = figure;
            return;
        }
        else if(!doCheckAndShowFeedback(figure, null)){
            return;
        }
        currentTurnPhase = TurnPhase.CURRENTLYMOVING;
        selectedCard.playCard(figure, currentEffect, null);
        //send message to server
        sendLastTurnServerMessage();
    }

    private void figureSelectedSwitchCase(Figure figure){
        if(!doCheckAndShowFeedback(currentlySelectedFigure, figure)){
            return;
        }
        currentTurnPhase = TurnPhase.CURRENTLYMOVING;
        selectedCard.playCard(currentlySelectedFigure, -1, figure);
        currentlySelectedFigure = null;
        //send message to server
        sendLastTurnServerMessage();
    }

    private boolean doCheckAndShowFeedback(Figure figure1, Figure figure2){
        if (!selectedCard.checkIfCardIsPlayable(figure1, currentEffect, figure2)) {
            //show feedback
            currentTurnPhase = TurnPhase.CHOOSECARD;
            return false;
        }
        return true;
    }

    private void sendLastTurnServerMessage(){
        lastTurn.setCardtype(selectedCard.getCardtype());
        webSocketClient.send(lastTurn.generateServerMessage());
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
    hasCheated = false;
    }

    public boolean isItMyTurn() {
        return (currentTurnPlayerNumber == myTurnNumber);
    }

    public boolean isThereAnyPossibleMove(){

        boolean flag = false;
        for(Card card : Handcards.getInstance().getMyCards()){
            for(Figure figure : figuremanager.getFiguresOfColour(Color.values()[myTurnNumber])){
                switch (card.getCardtype()){
                    //TODO: equal card?
                    case ONETOSEVEN:
                        for(int i = 1; i <=7;i++ ){
                            flag = flag | card.checkIfCardIsPlayable(figure, i, null);
                        }
                        break;
                    case FOUR_PLUSMINUS:
                        flag = flag | card.checkIfCardIsPlayable(figure, 1, null);
                        //TODO: which effect nr is -4?
                        break;
                    case ONEORELEVEN_START:
                        flag = flag | card.checkIfCardIsPlayable(figure, 0, null);
                        flag = flag | card.checkIfCardIsPlayable(figure, 1, null);
                        flag = flag | card.checkIfCardIsPlayable(figure, 11, null);
                        break;
                    case THIRTEEN_START:
                        flag = flag | card.checkIfCardIsPlayable(figure, 0, null);
                        flag = flag | card.checkIfCardIsPlayable(figure, 13, null);
                        break;
                    case SWITCH:
                        for(int i = 1;i<=16;i++){
                            if(i == figure.getId()){
                                continue;
                            }
                            Figure targetFigure = figuremanager.getFigureWithID(i);
                            flag = flag | card.checkIfCardIsPlayable(figure, -1, targetFigure);
                        }
                        break;
                    default:
                        flag = flag | card.checkIfCardIsPlayable(figure, -1, null);
                }
                if(flag) break; //early break for performance reasons
            }
            if(flag) break; //early break for performance reasons
        }
        return flag;
    }

    public void turnPlayerDiscardsCard(){

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

        hasCheated = true;

        playingField.moveAllWormholesRandomly();
        List<Wormhole> wormholeList = playingField.getWormholeList();

        var payload = new WormholeSwitchPayload(wormholeList.get(0).getFieldID(), wormholeList.get(1).getFieldID(), wormholeList.get(2).getFieldID(), wormholeList.get(3).getFieldID(),  lobbyID);
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
    public String getLobbyID() {
        return lobbyID;
    }

    public void moveWormholes(int [] newFieldIDs){
        for(int i = 0; i<4; i++){
            playingField.getWormholeList().get(i).switchField(playingField.getFieldWithID(newFieldIDs[i]));
            playingField.repairRootField();
        }
        playingField.repairWormholeVisuals();
    }

    public boolean isHasCheated() {
        return hasCheated;
    }

    public int getCurrentTurnPlayerNumber() {
        return currentTurnPlayerNumber;
    }

    public TurnPhase getCurrentTurnPhase() {
        return currentTurnPhase;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public int getMyTurnNumber() {
        return myTurnNumber;
    }
 
    public Card getSelectedCard() {
        return selectedCard;
    }

    public int getCurrentEffect() {
        return currentEffect;
    }

    public void setCurrentEffect(int currentEffect) {
        this.currentEffect = currentEffect;
    }
}
