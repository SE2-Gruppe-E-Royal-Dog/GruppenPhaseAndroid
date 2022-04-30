package com.uni.gruppenphaseandroid.playingfield;

public class Jerk extends Figure {

    public Jerk(int id, Color color, Field currentField, Typ typ, FigureUI figureUI) {
        super(id, color, currentField, typ, figureUI);
    }

    public Jerk() {
    }

    @Override
    public boolean checkOvertaking() {
        Field newPosition = getCurrentField().getNextField();
        Figure occupied = newPosition.getCurrentFigure();
        if (occupied.getTyp() != Typ.JERK) {
            return false;
        }
        return true;
    }
}
