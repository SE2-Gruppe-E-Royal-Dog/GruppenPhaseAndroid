package com.uni.gruppenphaseandroid.playingfield;

public class Knight extends Figure {

    public Knight(int id, Color color, Field currentField, Typ typ, FigureUI figureUI) {
        super(id, color, currentField, typ, figureUI);
    }

    public Knight() {
    }

    /**
     * Degree has to be considered.
     * @param figure1 - figure who moves
     * figure 2 - figure to be overtaken
     * @return true if overtaking possible
     */
    @Override
    public boolean checkOvertaking(Figure figure1) {
        Field newPosition = figure1.getCurrentField().getNextField();
        Figure figure2 = newPosition.getCurrentFigure();
// auf super8() zugreifen => wenn true dann weiter
        if (figure2.getTyp() != Typ.JERK || figure2.getTyp() != Typ.CITIZEN || figure2.getTyp() != Typ.KNIGHT) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param figure1 - figure who moves
     * @param fieldsToMove
     * @return true if moving possible
     */
    @Override
    public boolean checkMoving(Figure figure1, int fieldsToMove) { //TODO: Check Regelwerk


        return true;
    }

}
