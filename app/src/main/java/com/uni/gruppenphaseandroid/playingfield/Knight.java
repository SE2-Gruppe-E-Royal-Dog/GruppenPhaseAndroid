package com.uni.gruppenphaseandroid.playingfield;

public class Knight extends Figure {

    public Knight(int id, Color color, Field currentField, Typ typ, FigureUI figureUI) {
        super(id, color, currentField, typ, figureUI);
    }

    public Knight() {
    }

    @Override
    public boolean checkOvertaking() {
        Field newPosition = getCurrentField().getNextField();
        Figure occupied = newPosition.getCurrentFigure();
        if (occupied.getTyp() != Typ.JERK || occupied.getTyp() != Typ.CITIZEN || occupied.getTyp() != Typ.KNIGHT) {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkBeaten() {


        return true;
    }

    @Override
    public boolean checkMoving() {


        return true;
    }

}
