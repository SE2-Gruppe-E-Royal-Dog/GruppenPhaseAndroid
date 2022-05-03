package com.uni.gruppenphaseandroid.playingfield;

public class King extends Figure {

    public King(int id, Color color, Field currentField, Typ typ, FigureUI figureUI) {
        super(id, color, currentField, typ, figureUI);
    }

    public King() {
    }

    @Override
    public boolean checkOvertaking() {
        Field newPosition = getCurrentField().getNextField();
        Figure occupied = newPosition.getCurrentFigure();
        if (occupied.getTyp() == Typ.JERK || occupied.getTyp() != Typ.CITIZEN || occupied.getTyp() != Typ.KNIGHT || occupied.getTyp() != Typ.KING) { // all 4 cases covered => but default value set to false
            return false;
        }
        return true;
    }
}
