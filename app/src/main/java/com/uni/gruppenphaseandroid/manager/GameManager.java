package com.uni.gruppenphaseandroid.manager;

import com.uni.gruppenphaseandroid.playingfield.Color;
import com.uni.gruppenphaseandroid.playingfield.PlayingField;

public class GameManager {

    int currentTurnPlayerNumber;
    TurnPhase currentTurnPhase;
    PlayingField playingField;
    private int numberOfPlayers;
    private int myTurnNumber;


    public void startGame(int numberOfPlayers, int playerTurnNumber, PlayingField playingField){
        this.playingField = playingField;
        this.numberOfPlayers = numberOfPlayers;
        this.myTurnNumber = playerTurnNumber;
        //TODO: create figures
        for(int i = 0; i<numberOfPlayers; i++){
            createFigureSet(Color.values()[i]);
        }
        currentTurnPlayerNumber = 0;
        nextTurn();
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
}
