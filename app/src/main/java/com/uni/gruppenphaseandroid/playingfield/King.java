package com.uni.gruppenphaseandroid.playingfield;

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
     * @return true if overtaking possible
     */
    @Override
    public boolean checkOvertaking(Figure figure1) {
        Field newPosition = figure1.getCurrentField().getNextField();
        Figure figure2 = newPosition.getCurrentFigure();
        if (figure2.getTyp() == Typ.JERK || figure2.getTyp() != Typ.CITIZEN || figure2.getTyp() != Typ.KNIGHT || figure2.getTyp() != Typ.KING) { // all 4 cases covered => but default value set to false
            return false;
        }
        return true;
    }

    /**
     * King can only be beaten by another king, except on the starting field of another color.
     * @param figure1 - figure who moves
     * @return true if beating is possible
     */
    @Override
    public boolean checkBeaten(Figure figure1) {
        Field newPosition = figure1.getCurrentField().getNextField();
        Figure figure2 = newPosition.getCurrentFigure();

        if (newPosition instanceof StartingField && ((StartingField) newPosition).getColor() == figure2.getColor() && figure1.getTyp() != Typ.KING) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * King can only move 1-7 fields, starting card, magnet card, changer card.
     * Copy card is only allowed, if previous move was also allowed for the king.
     * @param figure1 - figure who moves
     * @param fieldsToMove
     * @return true if moving possible
     */
    @Override
    public boolean checkMoving(Figure figure1, int fieldsToMove) { // TODO: Verknüpfung mit Karten und Effekt


        return true;
    }
}
