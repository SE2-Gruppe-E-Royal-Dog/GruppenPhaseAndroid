package com.uni.gruppenphaseandroid.manager;

import android.util.Log;

import com.uni.gruppenphaseandroid.cards.Card;
import com.uni.gruppenphaseandroid.cards.Cardtype;
import com.uni.gruppenphaseandroid.playingfield.Color;
import com.uni.gruppenphaseandroid.playingfield.Figure;
import com.uni.gruppenphaseandroid.playingfield.FigureManager;

import java.util.LinkedList;
import java.util.List;

public class CardManager {

    FigureManager figureManager;

    public CardManager() {
        this.myHandCards = new LinkedList<>();
    }

    public boolean isThereAnyPossibleMove(int turnPlayerID, LastTurn lastTurn){
        boolean flag = false;
        for(Card card : myHandCards){
            for(Figure figure : figureManager.getFiguresOfColour(Color.values()[turnPlayerID])){
                if(card.getCardtype() == Cardtype.EQUAL){
                    flag = flag ||checkIfMoveIsPossibleEqualCard(figure, lastTurn);
                }
                else {
                    flag = flag || checkIfMoveIsPossibleForSingleCard(card, figure);
                }
            }
            if(flag) break; //early break for performance reasons
        }
        return flag;
    }

    private boolean checkIfMoveIsPossibleEqualCard(Figure figure, LastTurn lastTurn){
        if(lastTurn == null){
            return  false;
        }
        Card tempCard = new Card(lastTurn.getCardtype());
        return checkIfMoveIsPossibleForSingleCard(tempCard, figure);
    }

    private boolean checkIfMoveIsPossibleForSingleCard(Card card, Figure figure){
        boolean flag;
        switch (card.getCardtype()){
            case ONETOSEVEN:
                flag = checkOneToSeven(card, figure);
                break;
            case FOUR_PLUSMINUS:
                flag = checkFourPlusMinus(card, figure);
                break;
            case ONEORELEVEN_START:
                flag = checkOneOrElevenStart(card, figure);
                break;
            case THIRTEEN_START:
                flag = checkThirteenStart(card, figure);
                break;
            case SWITCH:
                flag = checkSwitch(card, figure);
                break;
            default:
                flag =  card.checkIfCardIsPlayable(figure, -1, null);
        }
        return flag;
    }

    private boolean checkOneToSeven(Card card, Figure figure){
        boolean flag = false;
        for(int i = 1; i <=7;i++ ){
            flag = flag || card.checkIfCardIsPlayable(figure, i, null);
        }
        return flag;
    }
    private  boolean checkFourPlusMinus(Card card, Figure figure){
        boolean flag;
        flag = card.checkIfCardIsPlayable(figure, 1, null);
        flag = flag || card.checkIfCardIsPlayable(figure, 2, null);
        return flag;
    }
    private boolean checkOneOrElevenStart(Card  card, Figure figure){
        boolean flag;
        flag = card.checkIfCardIsPlayable(figure, 0, null);
        flag = flag || card.checkIfCardIsPlayable(figure, 1, null);
        flag = flag || card.checkIfCardIsPlayable(figure, 11, null);
        return flag;
    }
    private boolean checkThirteenStart(Card card, Figure figure){
        boolean flag;
        flag = card.checkIfCardIsPlayable(figure, 0, null);
        flag = flag || card.checkIfCardIsPlayable(figure, 13, null);
        return flag;
    }
    private boolean checkSwitch(Card card, Figure figure){
        boolean flag = false;
        for(int i = 1;i<= figureManager.getNumberOfTotalFigures();i++){
            if(i == figure.getId()){
                continue;
            }
            Figure targetFigure = figureManager.getFigureWithID(i);
            flag = flag || card.checkIfCardIsPlayable(figure, -1, targetFigure);
        }
        return  flag;
    }

    public void setFigureManager(FigureManager figureManager){
        this.figureManager = figureManager;
    }

    public void turnPlayerDiscardsCard(){       //TODO still needed??
        /*
        TODO: Select Card to Discard
        index = cardIndexInHandcards
        Handcards.getInstance().discardHandcard(index);
        InGameFragment.setStackImage();
         */
    }

    boolean discardCardIfNecessary(int turnPlayerID, LastTurn lastTurn){        //TODO needed?
        if(!isThereAnyPossibleMove(turnPlayerID, lastTurn)){
            turnPlayerDiscardsCard();
            //int index = selectCardToDiscard();
            //Handcards.getInstance().discardHandcard(index);
            return true;
        }
        return false;
    }

    //HANDCARDS

    private List<Card> myHandCards;

    public List<Card> getMyHandCards() {
        return myHandCards;
    }

    public void addCardToHand(List<Card> cards) {
        myHandCards.addAll(cards);
    }

    public void discardHandcard(int index){

        GameManager.getInstance().getVisualEffectsManager().setInitialStackImage();
        Card toBeRemoved = myHandCards.get(index);
        GameManager.getInstance().getVisualEffectsManager().setStackImageAfterMyMove(toBeRemoved);
        if(GameManager.getInstance().getLastTurn() == null){
            Figure someFigure = GameManager.getInstance().getFiguremanager().getFigureWithID(1); //just get any figure to prevent nullpointer
            LastTurn lastTurn = new LastTurn(someFigure, null, someFigure.getCurrentField(), null);
            GameManager.getInstance().setLastTurn(lastTurn);
        }
        //GameManager.getInstance().setSelectedCard(toBeRemoved);
        myHandCards.remove(index);
    }

    public void setMyHandCards(List<Card> myHandCards) {
        this.myHandCards = myHandCards;
    }
}
