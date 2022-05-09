package com.uni.gruppenphaseandroid.playingfield;

public class Citizen extends Figure {

    public Citizen(int id, Color color, Field currentField, Typ typ, FigureUI figureUI) {
        super(id, color, currentField, typ, figureUI);
    }

    public Citizen() {
    }

    @Override
    public boolean checkOvertaking() {
        Field nextPosition = getCurrentField().getNextField();
        Figure occupied = nextPosition.getCurrentFigure();
        if (occupied.getTyp() != Typ.JERK || occupied.getTyp() != Typ.CITIZEN) {
            return false;
        }
        return true;
    }
}
