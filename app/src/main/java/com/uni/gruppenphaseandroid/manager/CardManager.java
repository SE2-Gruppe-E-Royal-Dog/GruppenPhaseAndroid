package com.uni.gruppenphaseandroid.manager;

import android.util.Log;

import com.uni.gruppenphaseandroid.cards.Card;
import com.uni.gruppenphaseandroid.cards.Cardtype;
import com.uni.gruppenphaseandroid.playingfield.Color;
import com.uni.gruppenphaseandroid.playingfield.Figure;
import com.uni.gruppenphaseandroid.playingfield.FigureManager;

public class CardManager {

    FigureManager figureManager;

    public boolean isThereAnyPossibleMove(int turnPlayerID, LastTurn lastTurn){
        //Log.e("card", Handcards.getInstance().getMyCards().toString());
        boolean flag = false;
        for(Card card : Handcards.getInstance().getMyCards()){
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

    public void turnPlayerDiscardsCard(){
        /*
        TODO: Select Card to Discard
        index = cardIndexInHandcards
        Handcards.getInstance().discardHandcard(index);
        InGameFragment.setStackImage();
         */
    }

    boolean discardCardIfNecessary(int turnPlayerID, LastTurn lastTurn){
        if(!isThereAnyPossibleMove(turnPlayerID, lastTurn)){
            turnPlayerDiscardsCard();
            //int index = selectCardToDiscard();
            //Handcards.getInstance().discardHandcard(index);
            return true;
        }
        return false;
    }

}
