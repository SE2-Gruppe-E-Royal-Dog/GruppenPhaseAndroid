package com.uni.gruppenphaseandroid.playingfield;

public class Jerk extends Figure {
    private String jerkGreen;
    private String jerkRed;
    private String jerkYellow;
    private String jerkBlue;

    public Jerk(int id, Color color, Field currentField, Typ typ) {
        super(id, color, currentField, typ);
    }

    public boolean checkJerk(Figure figure) {
        Field newPosition = figure.getCurrentField().getNextField();
        Figure occupied = newPosition.getCurrentFigure();
        if (occupied.getTyp() != Typ.JERK) {
            return false;
        }
        return true;
    }
}
