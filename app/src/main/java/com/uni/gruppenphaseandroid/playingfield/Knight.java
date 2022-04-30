package com.uni.gruppenphaseandroid.playingfield;

public class Knight extends Figure {
    private String knightGreen; // Button
    private String knightRed;
    private String knightYellow;
    private String knigthBlue;

    public Knight(int id, Color color, Field currentField, Typ typ) {
        super(id, color, currentField, typ);
    }

    public boolean checkKnight(Figure figure) {
        Field newPosition = figure.getCurrentField().getNextField();
        Figure occupied = newPosition.getCurrentFigure();
        if (occupied.getTyp() != Typ.JERK || occupied.getTyp() != Typ.CITIZEN || occupied.getTyp() != Typ.KNIGHT) {
            return false;
        }
        return true;
    }

}
