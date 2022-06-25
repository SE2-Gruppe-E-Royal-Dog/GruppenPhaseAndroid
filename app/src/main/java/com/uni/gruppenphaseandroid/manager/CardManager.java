package com.uni.gruppenphaseandroid.manager;

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
        GameManager.getInstance().setSelectedCard(card);//set this temporary for integration hell reasons
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
        GameManager.getInstance().setSelectedCard(null);
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


    //HANDCARDS

    private List<Card> myHandCards;

    public List<Card> getMyHandCards() {
        return myHandCards;
    }

    public void addCardToHand(List<Card> cards) {
        myHandCards.addAll(cards);
    }


    /**
     *
     * @param index index der Karte im Cardholder
     *
     */
    public void discardHandcard(int index){

        GameManager.getInstance().getVisualEffectsManager().setInitialStackImage();                             //eigentlich unnötige zeile, weil sie eh überschrieben wird. würde den ablagestapel auf das erste bild setzten
        Card toBeRemoved = myHandCards.get(index);                                                              //speichert die karte
        GameManager.getInstance().getVisualEffectsManager().setStackImageAfterMyMove(toBeRemoved);              //"wirft die Karte ab", Image wird auf den Kartenstablet angepasst
        if(GameManager.getInstance().getLastTurn() == null){                                                    //servermessage braucht einen last turn
            Figure someFigure = GameManager.getInstance().getFigureManager().getFigureWithID(1); //just get any figure to prevent nullpointer
            LastTurn lastTurn = new LastTurn(someFigure, null, someFigure.getCurrentField(), null);
            GameManager.getInstance().setLastTurn(lastTurn);
        }
        GameManager.getInstance().setSelectedCard(toBeRemoved);                                                   //selectedCard wird im Gamemanager gesetzt und der Prozess (serverbenachrichtigung, next turn etc) läuft weiter
        myHandCards.remove(index);                                                                                //entfernt die Karte aus myHandCards, dass sie auch im Cardholder nicht mehr sind
    }

    public void setMyHandCards(List<Card> myHandCards) {
        this.myHandCards = myHandCards;
    }
}
