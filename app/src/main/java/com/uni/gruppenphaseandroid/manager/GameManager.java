package com.uni.gruppenphaseandroid.manager;

import android.util.Log;

import com.google.gson.Gson;
import com.uni.gruppenphaseandroid.cards.Card;
import com.uni.gruppenphaseandroid.cards.CardUI;
import com.uni.gruppenphaseandroid.cards.Cardtype;
import com.uni.gruppenphaseandroid.communication.Client;
import com.uni.gruppenphaseandroid.communication.dto.Message;
import com.uni.gruppenphaseandroid.communication.dto.MessageType;
import com.uni.gruppenphaseandroid.communication.dto.PunishPayload;
import com.uni.gruppenphaseandroid.communication.dto.UpdateBoardPayload;
import com.uni.gruppenphaseandroid.communication.dto.WormholeSwitchPayload;
import com.uni.gruppenphaseandroid.playingfield.Color;
import com.uni.gruppenphaseandroid.playingfield.Figure;
import com.uni.gruppenphaseandroid.playingfield.FigureManager;
import com.uni.gruppenphaseandroid.playingfield.PlayingField;
import com.uni.gruppenphaseandroid.playingfield.Wormhole;

import org.java_websocket.client.WebSocketClient;

import java.util.LinkedList;
import java.util.List;

public class GameManager {

    private static GameManager instance;

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    private int currentTurnPlayerNumber; //welcher Spieler ist grad dran? Zahl zwischen 0...numberOfPlayers-1
    private TurnPhase currentTurnPhase;//Phase des Spielzugs: CHOOSECARD->CHOOSEFIGURE->(CHOOSESECONDFIGURE->)CURRENTLYMOVING
    private int myTurnNumber;//welcher Spieler bin ich/dieser Client? Zahl von 0...numberOfPlayers-1
    private int numberOfPlayers;//wie viele Spieler gibt es in diesem Spiel? Zahl zwischen 1...4

    private PlayingField playingField;
    private Client webSocketClient;
    private FigureManager figuremanager;
    private VisualEffectsManager visualEffectsManager;
    private CardManager cardManager;
    private CommunicationManager communicationManager;
    private CardUI cardUI;
    private LastTurn lastTurn;
    private Card selectedCard;
    private int currentEffect;
    private int selectCardIndex;
    private Figure currentlySelectedFigure; //muss zwischengespeichert werden wegen der Switch-Card wo 2 Figuren gewählt werden müssen
    private int cheatModifier = 0;
    private boolean hasMovedWormholes = false;
    private boolean hasCheated = false;
    private String[] playerNames = {"Player1", "Player2", "Player3", "Player4"}; //redundant?


    int roundIndex;

    /**
     * Setzt alle Abhängigkeiten des GameManagers, setzt die Anzahl an Spieler, erstellt die Figuren,
     * initialisiert das Ablagestapel-UI,
     * und startet die erste Runde (1. Runde hat dann roundIndex von 1)
     *
     * Die Äbhängigkeiten werden über die Parameter gesetzt damit die Klasse leichter testbar ist (Dependency Inversion Principle)
     * @param numberOfPlayers
     * @param playerTurnNumber
     * @param figureManager
     * @param visualEffectsManager
     * @param cardManager
     * @param communicationManager
     * @param cardUI
     */
    public void startGame(int numberOfPlayers, int playerTurnNumber, FigureManager figureManager, VisualEffectsManager visualEffectsManager, CardManager cardManager, CommunicationManager communicationManager, CardUI cardUI) {
        this.roundIndex = 0;

        this.numberOfPlayers = numberOfPlayers;
        this.myTurnNumber = playerTurnNumber;
        this.figuremanager = figureManager;
        this.visualEffectsManager = visualEffectsManager;
        this.cardManager = cardManager;
        this.communicationManager = communicationManager;
        this.cardUI = cardUI;

        for (int i = 0; i < numberOfPlayers; i++) {
            figureManager.createFigureSetOfColor(Color.values()[i], playingField);
        }
        currentTurnPlayerNumber = numberOfPlayers - 1;
        visualEffectsManager.setInitialStackImage();
        nextTurn();
    }

    /**
     * Ein neuer Zug startet(roundIndex++).
     * Es wird gecheckt ob es einen Sieger gibt.
     * Alle 5 Runden werden die Cheat/Wormhole Berechtigungen wieder erlaubt.
     */
    public void nextTurn() {
        for(int i = 0; i < numberOfPlayers;i++){
            if(figuremanager.isWinner(Color.values()[i])){
                visualEffectsManager.showWinningScreen();
                return;
            }
        }
        currentTurnPlayerNumber = (currentTurnPlayerNumber + 1) % numberOfPlayers;
        visualEffectsManager.showNextTurnMessage(playerNames[currentTurnPlayerNumber]);

        currentTurnPhase = TurnPhase.CHOOSECARD;
        roundIndex++;
        if(roundIndex % ((5*numberOfPlayers)+1) == 0){
            hasMovedWormholes = false;
            hasCheated = false;
        }
    }

    /**
     * Wenn eine Karte zum richtigen Zeitpunkt gespielt wird, wird sie hier im GameManager gesetzt.
     * @param card
     */
    public void cardGotPlayed(Card card) {
        if (currentTurnPhase == TurnPhase.CHOOSECARD && isItMyTurn()) {
            currentTurnPhase = TurnPhase.CHOOSEFIGURE;
            selectedCard = card;
        }
    }

    /**
     * Wenn eine Figur zum richtigen Zeitpunkt ausgewählt wird, dann muss diese Methode aufgerufen werden.
     * Hierbei gibt es 2 Fälle:
     * 1) normaler Fall
     * 2) Fall wo die 2. Figur für die switch-Karte ausgewählt wird
     * @param figure
     */
    public void figureGotSelected(Figure figure){
        if (currentTurnPhase == TurnPhase.CHOOSEFIGURE && isItMyTurn() && figure.getColor() == Color.values()[myTurnNumber]) {
            figureSelectedNormalCase(figure);
        }
        else if(currentTurnPhase == TurnPhase.CHOOSESECONDFIGURE && isItMyTurn()){
            figureSelectedSwitchCase(figure);
        }
    }

    /**
     * Normaler Fall wo eine Figur ausgewählt wird.
     * Falls die Switch Karte gespielt wurde, wird der GameManager so eingestellt dass man noch eine zweite Figur wählen kann (außerdem wird hier dann returnt)
     *
     * Ansonsten wird der Check gemacht ob es ein legaler Move ist.
     * Bei Erfolg wird die Karte gespielt, abgeworfen und es wird eine Nachricht an den Server geschickt.
     * @param figure
     */
    private void figureSelectedNormalCase(Figure figure){
        if(selectedCard.getCardtype() == Cardtype.SWITCH || (selectedCard.getCardtype() == Cardtype.EQUAL && lastTurn != null && lastTurn.getCardtype() == Cardtype.SWITCH)){
            currentTurnPhase = TurnPhase.CHOOSESECONDFIGURE;
            currentlySelectedFigure = figure;
            return;
        }
        else if(!doCheckAndShowFeedback(figure, null)){
            return;
        }
        currentTurnPhase = TurnPhase.CURRENTLYMOVING;
        selectedCard.playCard(figure, currentEffect, null);
        cardManager.discardHandcard(selectCardIndex);

        //send message to server
        sendLastTurnServerMessage();
    }

    /**
     * Falls eine 2. Figur für die Switch-Karte ausgwählt wird.
     *
     * Zuerst wird überprüft ob es ein legaler Zug ist.
     * Bei Erfolg wird die Karte ausgespielt, abgworfen und eine Nachricht an den Server gesendet.
     * @param figure
     */
    private void figureSelectedSwitchCase(Figure figure){
        if(!doCheckAndShowFeedback(currentlySelectedFigure, figure)){
            return;
        }
        currentTurnPhase = TurnPhase.CURRENTLYMOVING;
        selectedCard.playCard(currentlySelectedFigure, -1, figure);
        currentlySelectedFigure = null;
        cardManager.discardHandcard(selectCardIndex);
        //send message to server
        sendLastTurnServerMessage();
    }

    /**
     * Macht den Check ob eine Karte mit den entsprechenden ausgewählten Figuren möglich ist.
     * Wenn nicht, wird false returnt und dem Spieler visuelles Feedback gegeben dass der Zug illegal ist.
     * @param figure1
     * @param figure2
     * @return
     */
    private boolean doCheckAndShowFeedback(Figure figure1, Figure figure2){
        if (!selectedCard.checkIfCardIsPlayable(figure1, currentEffect, figure2)) {
            visualEffectsManager.showIllegalMoveMessage();
            currentTurnPhase = TurnPhase.CHOOSECARD;
            return false;
        }
        return true;
    }

    /**
     * Macht eine letzte Vorbereitung für das LastTurn Objekt und sendet es dann als Server Nachricht.
     */
    public void sendLastTurnServerMessage(){
        lastTurn.setCardtype(selectedCard.getCardtype());
        selectedCard = null;
        communicationManager.sendUpdateBoardMessage(lastTurn);
    }

    /**
     * Diese Methode wir aufgerufen nachdem die Servernachricht für die Synchronisierung des Spielfelds bei den Clients ankommt.
     * Die erste Figur des updateBoardPayloads wird bewegt und die zweite Figur auch (falls vorhanden)
     * Dann wird ein neuer Zug gestartet.
     * @param updateBoardPayload
     */
    public void updateBoard(UpdateBoardPayload updateBoardPayload) {
        if (!isItMyTurn()) { //for the turnplayer, the update took place already
            lastTurn = LastTurn.generateLastTurnObject(updateBoardPayload, figuremanager, playingField);
            visualEffectsManager.setStackImage();
            playingField.moveFigureToField(lastTurn.getFigure1(), lastTurn.getNewFigure1Field());
            if (lastTurn.getFigure2() != null && lastTurn.getNewFigure2Field() != null) {
                playingField.moveFigureToField(lastTurn.getFigure2(), lastTurn.getNewFigure2Field());
            }

        }
        visualEffectsManager.setCardHolderUI();
        nextTurn();
    }

    public boolean isItMyTurn() {
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

    public void initiateMoveWormholes() {
        if (isItMyTurn() || currentTurnPhase == TurnPhase.CURRENTLYMOVING) {
            return;
        }
        hasMovedWormholes = true;

        playingField.moveAllWormholesRandomly();
        List<Wormhole> wormholeList = playingField.getWormholeList();

        var payload = new WormholeSwitchPayload(wormholeList.get(0).getFieldID(), wormholeList.get(1).getFieldID(), wormholeList.get(2).getFieldID(), wormholeList.get(3).getFieldID(), communicationManager.lobbyID);
        var message = new Message();
        message.setType(MessageType.WORMHOLE_MOVE);
        message.setPayload(new Gson().toJson(payload));
        webSocketClient.send(message);
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

    public void setSelectedCard(Card selectedCard) {
        this.selectedCard = selectedCard;
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

    public void setPlayerNames(LinkedList<String> playerNames) {
        for (int i = 0; i < playerNames.size(); i++){
            this.playerNames[i] = playerNames.get(i);
        }
    }

    public String getPlayerNameWithIndex(int index){
        return this.playerNames[index];
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
        var payload = new PunishPayload(communicationManager.lobbyID, figureID);
        var message = new Message();
        message.setType(MessageType.PUNISHMENT_MESSAGE);
        message.setPayload(new Gson().toJson(payload));
        webSocketClient.send(message);

    }

    public void executePunishment(int figureID){
        playingField.beat(figuremanager.getFigureWithID(figureID));
    }
    public int getSelectCardIndex() {
        return selectCardIndex;
    }

    public void setSelectCardIndex(int selectCardIndex) {
        this.selectCardIndex = selectCardIndex;
    }
    public boolean isThereAnyPossibleMove() {
        return cardManager.isThereAnyPossibleMove(myTurnNumber, lastTurn);
    }

    public String[] getModifiedPlayerNamesArray() {
        String[] names = new String[4];
        for(int i=0;i<4;i++){
            if(i>=numberOfPlayers){
                names[i] = null;
            }else{
                names[i] = playerNames[i];
            }
        }
        return names;
    }

    public void setPlayerNames(String[] playerNames) {
        this.playerNames = playerNames;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public CardManager getCardManager() {
        return cardManager;
    }

    public FigureManager getFigureManager(){
        return this.figuremanager;
    }

    public VisualEffectsManager getVisualEffectsManager() {
        return visualEffectsManager;
    }

    public CardUI getCardUI() {
        return cardUI;
    }

    public void setCardUI(CardUI cardUI) {
        this.cardUI = cardUI;
    }
}
