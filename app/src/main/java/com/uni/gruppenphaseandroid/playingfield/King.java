package com.uni.gruppenphaseandroid.playingfield;

import com.uni.gruppenphaseandroid.cards.Card;
import com.uni.gruppenphaseandroid.cards.Cardtype;
import com.uni.gruppenphaseandroid.manager.GameManager;

public class King extends Figure {

    public King(int id, Color color, Field currentField, Typ typ, FigureUI figureUI) {
        super(id, color, currentField, typ, figureUI);
        typ = Typ.KING;
    }

    public King() {
    }

    /**
     * Degree has to be considered.
     * King can only be overtaken by another king.
     * this figure - figure who moves
     * figure 2 - figure to be overtaken
     * @return true if overtaking possible
     */
    @Override
    public boolean checkOvertaking() {
        Field newPosition = getCurrentField().getNextField();
        Figure figure2 = newPosition.getCurrentFigure();

        if(super.checkOvertaking()) {
            switch (figure2.getTyp()) {
                case JERK:
                case CITIZEN:
                case KNIGHT:
                case KING:
                    return true;
            }
        } return false;

    }

    /**
     * King can only move 1-7 fields and use starting card, magnet card and switch card.
     * Copy card is only allowed, if previous move was also allowed for the king.
     * Starting is allowed with 13 or 1/11.
     * this figure - figure who moves
     * @param fieldsToMove - fields to move
     * @return true if moving possible
     */
    @Override
    public boolean checkMoving(int fieldsToMove) {
        Card card = GameManager.getInstance().getSelectedCard();
        if(super.checkMoving(fieldsToMove) && (card.getCardtype() == Cardtype.ONETOSEVEN || card.getCardtype() == Cardtype.ONEORELEVEN_START || card.getCardtype() == Cardtype.THIRTEEN_START || card.getCardtype() == Cardtype.MAGNET || card.getCardtype() == Cardtype.SWITCH)) {
            return true;
        } else {
            return false;
        }
    }
}
