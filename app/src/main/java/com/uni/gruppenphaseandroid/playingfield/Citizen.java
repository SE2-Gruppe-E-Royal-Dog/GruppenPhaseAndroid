package com.uni.gruppenphaseandroid.playingfield;

public class Citizen extends Figure {
    private String citizenGreen;
    private String citizenRed;
    private String citizenYellow;
    private String citizenBlue;

    public Citizen(int id, Color color, Field currentField, Typ typ) {
        super(id, color, currentField, typ);
    }

    public boolean checkCitizen(Figure figure) {
        Field newPosition = figure.getCurrentField().getNextField();
        Figure occupied = newPosition.getCurrentFigure();
        if (occupied.getTyp() != Typ.JERK || occupied.getTyp() != Typ.CITIZEN) {
            return false;
        }
        return true;
    }
}
