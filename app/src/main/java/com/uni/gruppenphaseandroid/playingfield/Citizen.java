package com.uni.gruppenphaseandroid.playingfield;

public class Citizen extends Figure {

    public Citizen(int id, Color color, Field currentField, Typ typ, FigureUI figureUI) {
        super(id, color, currentField, typ, figureUI);
    }

    public Citizen() {
    }

    /**
     * Degree has to be considered.
     * @param figure1 - figure who moves
     * @return true if overtaking possible
     */
    @Override
    public boolean checkOvertaking(Figure figure1) {
        Field newPosition = figure1.getCurrentField().getNextField();
        Figure figure2 = newPosition.getCurrentFigure();
        if (figure2.getTyp() != Typ.JERK || figure2.getTyp() != Typ.CITIZEN) {
            return false;
        }
        return true;
    }

    /**
     * If moving to a special field clockwise and taking a card,
     * citizen is allowed to jump to the next special field -
     * no matter of blocked starting fields or higher degree.
     * @param figure1 - figure who moves
     * @param fieldsToMove
     * @return true if moving possible
     */
    @Override
    public boolean checkMoving(Figure figure1, int fieldsToMove) { // TODO: Switch Methode einbauen


        return true;
    }
}
