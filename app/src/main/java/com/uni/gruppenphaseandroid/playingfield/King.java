package com.uni.gruppenphaseandroid.playingfield;

public class King extends Figure {
    private String kingGreen;
    private String kingRed;
    private String kingYellow;
    private String kingBlue;

    public King(int id, Color color, Field currentField, Typ typ) {
        super(id, color, currentField, typ);
    }

    public boolean checkKing(Figure figure) {
        Field newPosition = figure.getCurrentField().getNextField();
        Figure occupied = newPosition.getCurrentFigure();
        if (occupied.getTyp() == Typ.JERK || occupied.getTyp() != Typ.CITIZEN || occupied.getTyp() != Typ.KNIGHT || occupied.getTyp() != Typ.KING) { // all 4 cases covered => but default value set to false
            return false;
        }
        return true;
    }
}
