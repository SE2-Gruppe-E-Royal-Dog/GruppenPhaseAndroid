package com.uni.gruppenphaseandroid.manager;

import com.uni.gruppenphaseandroid.cards.Card;
import com.uni.gruppenphaseandroid.cards.Cardtype;
import com.uni.gruppenphaseandroid.playingfield.Color;
import com.uni.gruppenphaseandroid.playingfield.Figure;
import com.uni.gruppenphaseandroid.playingfield.FigureManager;

public class CardManager {

    FigureManager figureManager;

    public boolean isThereAnyPossibleMove(int turnPlayerID, LastTurn lastTurn){
        boolean flag = false;
        for(Card card : Handcards.getInstance().getMyCards()){
            for(Figure figure : figureManager.getFiguresOfColour(Color.values()[turnPlayerID])){

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
                    Figure targetFigure = figureManager.getFigureWithID(i);
                    flag = flag || card.checkIfCardIsPlayable(figure, -1, targetFigure);
                }
                break;
            default:
                flag =  card.checkIfCardIsPlayable(figure, -1, null);
        }
        return flag;
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
