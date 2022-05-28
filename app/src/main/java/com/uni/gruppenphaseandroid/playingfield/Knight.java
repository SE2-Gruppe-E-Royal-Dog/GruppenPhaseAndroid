package com.uni.gruppenphaseandroid.playingfield;

public class Knight extends Figure {

    public Knight(int id, Color color, Field currentField, Typ typ, FigureUI figureUI) {
        super(id, color, currentField, typ, figureUI);
        typ = Typ.KNIGHT;
    }

    public Knight() {
    }

    /**
     * Degree has to be considered.
     * this figure - figure who moves
     * figure 2 - figure to be overtaken
     * @return true if overtaking possible
     */
    @Override
    public boolean checkOvertaking() {
        Field newPosition = getCurrentField().getNextField();
        Figure figure2 = newPosition.getCurrentFigure();

        if(super.checkOvertaking() == true) {
            if (figure2.getTyp() != Typ.JERK || figure2.getTyp() != Typ.CITIZEN || figure2.getTyp() != Typ.KNIGHT) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}
