package com.uni.gruppenphaseandroid.playingfield;

import com.uni.gruppenphaseandroid.Cards.Card;

public class King extends Figure {

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
    public boolean checkOvertaking(Figure figure1) {
        Field newPosition = figure1.getCurrentField().getNextField();
        Figure figure2 = newPosition.getCurrentFigure();

        if(super.checkOvertaking(figure1) == true) {
            if (figure2.getTyp() == Typ.JERK || figure2.getTyp() != Typ.CITIZEN || figure2.getTyp() != Typ.KNIGHT || figure2.getTyp() != Typ.KING) { // all 4 cases covered => but default value set to false
                return false;
            } else {
                return true;
            }
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
    public boolean checkBeaten(Figure figure1) {
        Field newPosition = figure1.getCurrentField().getNextField();
        Figure figure2 = newPosition.getCurrentFigure();

        if (newPosition instanceof StartingField && ((StartingField) newPosition).getColor() != figure2.getColor() || figure1.getTyp() == Typ.KING) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * King can only move 1-7 fields and use starting card, magnet card and changer card.
     * Copy card is only allowed, if previous move was also allowed for the king.
     * Starting is allowed with 13 or 1/11.
     * @param figure1 - figure who moves
     * @param card which is played
     * @return true if moving possible
     */
    @Override
    public boolean checkMoving(Figure figure1, Card card) { // TODO: Verknüpfung mit Karten und Effekt


        return true;
    }
}
