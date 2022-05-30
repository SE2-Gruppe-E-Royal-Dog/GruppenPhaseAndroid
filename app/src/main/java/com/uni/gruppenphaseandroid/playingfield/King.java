package com.uni.gruppenphaseandroid.playingfield;

import com.uni.gruppenphaseandroid.cards.Card;
import com.uni.gruppenphaseandroid.cards.Cardtype;
import com.uni.gruppenphaseandroid.manager.GameManager;

public class King extends Figure { // TODO: Copy Card

    public King(int id, Color color, Field currentField, Typ typ, FigureUI figureUI) {
        super(id, color, currentField, typ, figureUI);
    }

    public King() {
    }

    /**
     * Degree has to be considered.
     * King can only be overtaken by another king.
     * @param figure1 - figure who moves
     * figure 2 - figure to be overtaken
     * @return true if overtaking possible
     */
    @Override
    protected boolean checkOvertaking(Figure figure1) {
        Field newPosition = figure1.getCurrentField().getNextField();
        Figure figure2 = newPosition.getCurrentFigure();

        if(super.checkOvertaking(figure1)) {
            // all 4 cases covered => but default value set to false
            return figure2.getTyp() != Typ.JERK && figure2.getTyp() == Typ.CITIZEN && figure2.getTyp() == Typ.KNIGHT && figure2.getTyp() == Typ.KING;
        } else {
            return false;
        }
    }

    /**
     * King can only be beaten by another king, except on the starting field of another color.
     * @param figure1 - figure who moves
     * figure 2 - figure to be beaten
     * @return true if beating is possible
     */
    @Override
    protected boolean checkBeaten(Figure figure1) {
        Field newPosition = figure1.getCurrentField().getNextField();
        Figure figure2 = newPosition.getCurrentFigure();

        return newPosition instanceof StartingField && ((StartingField) newPosition).getColor() != figure2.getColor() || figure1.getTyp() == Typ.KING;
    }

    /**
     * King can only move 1-7 fields and use starting card, magnet card and switch card.
     * Copy card is only allowed, if previous move was also allowed for the king.
     * Starting is allowed with 13 or 1/11.
     * @param figure1 - figure who moves
     * @param fieldsToMove - fields to move
     * @return true if moving possible
     */
    @Override
    public boolean checkMoving(Figure figure1, int fieldsToMove) { // TODO: Copy Card für Zug des vorherigen Spielers
        Card card = GameManager.getInstance().getSelectedCard();
        return super.checkMoving(figure1, fieldsToMove) == true && (card.getCardtype() == Cardtype.ONETOSEVEN || card.getCardtype() == Cardtype.ONEORELEVEN_START || card.getCardtype() == Cardtype.THIRTEEN_START || card.getCardtype() == Cardtype.MAGNET || card.getCardtype() == Cardtype.SWITCH);
    }
}
