package com.uni.gruppenphaseandroid.manager;

import android.util.Log;

import com.google.gson.Gson;
import com.uni.gruppenphaseandroid.InGameFragment;
import com.uni.gruppenphaseandroid.cards.Card;
import com.uni.gruppenphaseandroid.cards.Cardtype;
import com.uni.gruppenphaseandroid.communication.Client;
import com.uni.gruppenphaseandroid.communication.dto.Message;
import com.uni.gruppenphaseandroid.communication.dto.MessageType;
import com.uni.gruppenphaseandroid.communication.dto.Payload;
import com.uni.gruppenphaseandroid.communication.dto.PunishPayload;
import com.uni.gruppenphaseandroid.communication.dto.UpdateBoardPayload;
import com.uni.gruppenphaseandroid.communication.dto.WormholeSwitchPayload;
import com.uni.gruppenphaseandroid.playingfield.Color;
import com.uni.gruppenphaseandroid.playingfield.Field;
import com.uni.gruppenphaseandroid.playingfield.Figure;
import com.uni.gruppenphaseandroid.playingfield.FigureManager;
import com.uni.gruppenphaseandroid.playingfield.PlayingField;
import com.uni.gruppenphaseandroid.playingfield.Wormhole;

import org.java_websocket.client.WebSocketClient;

import java.util.ArrayList;
import java.util.List;

public class GameManager {

    private static GameManager instance;

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    private String lobbyID;
    private String playerID;

    private int currentTurnPlayerNumber;
    private TurnPhase currentTurnPhase;
    private int myTurnNumber;
    private int numberOfPlayers;

    private PlayingField playingField;
    private Client webSocketClient;
    private FigureManager figuremanager;
    private VisualEffectsManager visualEffectsManager;
    private LastTurn lastTurn;
    private Card selectedCard;
    private int currentEffect;                          //int for special cards
    private Figure currentlySelectedFigure;
    private int cheatModifier = 0;
    private boolean hasMovedWormholes = false;
    private boolean hasCheated = false;



    int roundIndex;

    public void startGame(int numberOfPlayers, int playerTurnNumber, String lobbyID,String playerID, FigureManager figureManager, VisualEffectsManager visualEffectsManager) {
        this.lobbyID = lobbyID;
        this.playerID = playerID;
        this.roundIndex = 0;

        this.numberOfPlayers = numberOfPlayers;
        this.myTurnNumber = playerTurnNumber;
        this.figuremanager = figureManager;
        this.visualEffectsManager = visualEffectsManager;
        for (int i = 0; i < numberOfPlayers; i++) {
            createFigureSet(Color.values()[i]);
        }
        currentTurnPlayerNumber = numberOfPlayers - 1;
        visualEffectsManager.setInitialStackImage();
        nextTurn();

    }


    void createFigureSet(Color color) {
        figuremanager.createFigureSetOfColor(color, playingField);
    }

    public void nextTurn() {

        currentTurnPlayerNumber = (currentTurnPlayerNumber + 1) % numberOfPlayers;

        currentTurnPhase = TurnPhase.CHOOSECARD;
        roundIndex++;
        if(isItMyTurn()){
            checkForDiscarding();
        }
    }

    private void checkForDiscarding(){
        if(!isThereAnyPossibleMove()){
            turnPlayerDiscardsCard();
            visualEffectsManager.showNoPossibleMoveMessage();
            //int index = selectCardToDiscard();
            //Handcards.getInstance().discardHandcard(index);
            //nextTurn();
        }
    }

    public void cardGotPlayed(Card card) {
        if (currentTurnPhase == TurnPhase.CHOOSECARD && isItMyTurn()) {
            currentTurnPhase = TurnPhase.CHOOSEFIGURE;
            selectedCard = card;
        }
    }

    public void figureGotSelected(Figure figure){
        if (currentTurnPhase == TurnPhase.CHOOSEFIGURE && isItMyTurn() && figure.getColor() == Color.values()[myTurnNumber]) {
            figureSelectedNormalCase(figure);
        }
        else if(currentTurnPhase == TurnPhase.CHOOSESECONDFIGURE && isItMyTurn()){
            figureSelectedSwitchCase(figure);
        }
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
            visualEffectsManager.showIllegalMoveMessage();
            currentTurnPhase = TurnPhase.CHOOSECARD;
            return false;
        }
        return true;
    }

    private void sendLastTurnServerMessage(){
        lastTurn.setCardtype(selectedCard.getCardtype());
        selectedCard = null;
        webSocketClient.send(lastTurn.generateServerMessage());
    }

    public void updateBoard(UpdateBoardPayload updateBoardPayload) {
        if (!isItMyTurn()) { //for the turnplayer, the update took place already
            lastTurn = LastTurn.generateLastTurnObject(updateBoardPayload, figuremanager, playingField);
            visualEffectsManager.setStackImage();
            playingField.moveFigureToField(lastTurn.getFigure1(), lastTurn.getNewFigure1Field());
            if (lastTurn.getFigure2() != null && lastTurn.getNewFigure2Field() != null) {
                playingField.moveFigureToField(lastTurn.getFigure2(), lastTurn.getNewFigure2Field());
            }

        }
        //TODO: update card UI
        nextTurn();
    }

    public boolean doesAnyoneHaveCardsLeftInHand() {
        return true;
    }

    private void everyOneDraws5Cards() {
        hasMovedWormholes = false;
        hasCheated = false;
    }

    public boolean isItMyTurn() {
        return (currentTurnPlayerNumber == myTurnNumber);
    }

    public boolean isThereAnyPossibleMove(){
        boolean flag = false;
        for(Card card : Handcards.getInstance().getMyCards()){
            for(Figure figure : figuremanager.getFiguresOfColour(Color.values()[myTurnNumber])){

                Card tempCard = card;
                if(card.getCardtype() == Cardtype.EQUAL){
                    if(lastTurn == null){
                        continue;
                    }
                    tempCard = new Card(lastTurn.getCardtype());
                }
                flag = flag || checkIfMoveIsPossibleForSingleCard(tempCard, figure);
            }
            if(flag) break; //early break for performance reasons
        }
        return flag;
    }

    private boolean checkIfMoveIsPossibleForSingleCard(Card card, Figure figure){
        boolean flag = false;
        switch (card.getCardtype()){
            case ONETOSEVEN:
                for(int i = 1; i <=7;i++ ){
                    flag = flag || card.checkIfCardIsPlayable(figure, i, null);
                }
                break;
            case FOUR_PLUSMINUS:
                flag = card.checkIfCardIsPlayable(figure, 1, null);
                flag = flag || card.checkIfCardIsPlayable(figure, 2, null);
                break;
            case ONEORELEVEN_START:
                flag = card.checkIfCardIsPlayable(figure, 0, null);
                flag = flag || card.checkIfCardIsPlayable(figure, 1, null);
                flag = flag || card.checkIfCardIsPlayable(figure, 11, null);
                break;
            case THIRTEEN_START:
                flag = card.checkIfCardIsPlayable(figure, 0, null);
                flag = flag || card.checkIfCardIsPlayable(figure, 13, null);
                break;
            case SWITCH:
                for(int i = 1;i<=16;i++){
                    if(i == figure.getId()){
                        continue;
                    }
                    Figure targetFigure = figuremanager.getFigureWithID(i);
                    flag = flag || card.checkIfCardIsPlayable(figure, -1, targetFigure);
                }
                break;
            default:
                flag =  card.checkIfCardIsPlayable(figure, -1, null);
        }
        return flag;
    }

    public void turnPlayerDiscardsCard(){
        /*
        TODO: Select Card to Discard
        index = cardIndexInHandcards
        Handcards.getInstance().discardHandcard(index);
        InGameFragment.setStackImage();
         */
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

    public void initiateMoveWormholes() {
        if (isItMyTurn() || currentTurnPhase == TurnPhase.CURRENTLYMOVING) {
            return;
        }
        hasMovedWormholes = true;

        playingField.moveAllWormholesRandomly();
        List<Wormhole> wormholeList = playingField.getWormholeList();

        var payload = new WormholeSwitchPayload(wormholeList.get(0).getFieldID(), wormholeList.get(1).getFieldID(), wormholeList.get(2).getFieldID(), wormholeList.get(3).getFieldID(), lobbyID);
        var message = new Message();
        message.setType(MessageType.WORMHOLE_MOVE);
        message.setPayload(new Gson().toJson(payload));
        webSocketClient.send(message);
    }

    public String getLobbyID() {
        return lobbyID;
    }

    public void moveWormholes(int[] newFieldIDs) {
        for (int i = 0; i < 4; i++) {
            playingField.getWormholeList().get(i).switchField(playingField.getFieldWithID(newFieldIDs[i]));
            playingField.repairRootField();
        }
        playingField.repairWormholeVisuals();
    }

    public boolean getHasMovedWormholes() {
        return hasMovedWormholes;
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

    public void setCurrentTurnPhase(TurnPhase currentTurnPhase) {
        this.currentTurnPhase = currentTurnPhase;
    }

    public int getCheatModifier() {
        return cheatModifier;
    }

    public void setCheatModifier(int cheatModifier) {
        this.cheatModifier = cheatModifier;
    }


    public int getCurrentEffect() {
        return currentEffect;
    }

    public void setCurrentEffect(int currentEffect) {
        this.currentEffect = currentEffect;
    }

    public String getPlayerID() {
        return playerID;
    }


    public Color getColorOfClient(int playerIndex){
        return Color.values()[playerIndex];
    }
    public Color getColorOfMyClient(){
        return getColorOfClient(myTurnNumber);
    }

    public boolean getHasCheated() {
        return hasCheated;
    }

    public void setHasCheated(boolean hasCheated) {
        this.hasCheated = hasCheated;
    }

    public int getRoundIndex() {
        return roundIndex;
    }

    public boolean hasThisClientFigureOnBoard(){
        return figuremanager.checkIfPlayerHasFigureOnBoard(getColorOfMyClient());

    }

    public void punishPlayer(int playerID){
        if (!(figuremanager.checkIfPlayerHasFigureOnBoard(getColorOfClient(playerID)))){
            visualEffectsManager.showCanNotAcccusePlayerMessage();
            return;
        }
        sendPunishmentMessage(figuremanager.getRandomFigureIdOfPlayerOnBoard(getColorOfClient(playerID)));
    }

    private void sendPunishmentMessage(int figureID){
        var payload = new PunishPayload(lobbyID, figureID);
        var message = new Message();
        message.setType(MessageType.PUNISHMENT_MESSAGE);
        message.setPayload(new Gson().toJson(payload));
        webSocketClient.send(message);

    }

    public void executePunishment(int figureID){
        playingField.overtake(figuremanager.getFigureWithID(figureID));
        if(isItMyTurn()){
            checkForDiscarding(); //since the field changed, there may be no playable card in hand
        }
    }

}
