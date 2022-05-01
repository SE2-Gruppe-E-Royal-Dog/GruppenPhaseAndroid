package com.uni.gruppenphaseandroid.Cards;

import com.uni.gruppenphaseandroid.playingfield.Figure;

public class Card {
    private Cardtype cardtype;
    private int cardID;

    public Card(Cardtype cardtype, int cardID) {
        this.cardtype = cardtype;
        this.cardID = cardID;
    }

    public Cardtype getCardtype() {
        return cardtype;
    }

    public int getCardID() {
        return cardID;
    }

    public void playCard(int effect, Figure myFigure, Figure targetFigure) {
        /*
        if (getCardtype() == null) throw new IllegalArgumentException("Card cannot be null");
        if (effect < 0 || effect < 0 || (effect > 7 && effect < 11) || effect == 12 || effect > 13)
            throw new IllegalArgumentException("Illegal value for effect");
        if (myFigure == null) throw new IllegalArgumentException("myFigure cannot be null");

        int myFigureID = myFigure.getId();

        if (effect == 0 && targetFigure == null) {
            switch (getCardtype()) {
                case TWO:
                    myFigure.move(2, false);
                    return;
                case THREE:
                    myFigure.move(3, false);
                    return;
                case FIVE:
                    myFigure.move(5, false);
                    return;
                case SIX:
                    myFigure.move(6, false);
                    return;
                case EIGTH:
                    myFigure.move(8, false);
                    return;
                case NINE:
                    myFigure.move(9, false);
                    return;
                case TEN:
                    myFigure.move(10, false);
                    return;
                case TWELVE:
                    myFigure.move(12, false);
                    return;
                case EQUAL:
                    myFigureID.move(getLastMove(), false);
                    return;
            }
        } else if (targetFigure == null) {
            switch (getCardtype()) {
                case FOUR_PLUSMINUS:
                    if (effect == 1) myFigureID.move(4, false);
                    else myFigureID.move(-4, false);
                    return;
                case ONETOSEVEN:
                    myFigureID.move(effect, false);
                    return;
                case ONEORELEVEN_START:
                    if (effect == 0) myFigureID.move(0, false);
                    else if (effect == 1) myFigureID.move(1, false);
                    else myFigureID.move(11, false);
                    return;
                case THIRTEEN_START:
                    if (effect == 0) myFigureID.move(0, false);
                    else myFigureID.move(13, false);
            }
        } else {
            switch (getCardtype()) {
                case Cardtype.MAGNET:
                    myFigureID.move(targetFigure.getCurrentField().getFieldID() - myFigure.getCurrentField().getFieldID() - 1, false);
                    return;
                case Cardtype.SWITCH:
                    myFigureID.move(targetFigure.getCurrentfield().getFieldID() - myFigure.getCurrentField().getFieldID(), true);
                    targetFigure.move(myFigure.getCurrentField().getFieldID() - target.getCurrentfield().getFieldID(), true);
                    return;
            }
        }
        throw new IllegalArgumentException("Illegal Combination of values");
         */
    }
}
