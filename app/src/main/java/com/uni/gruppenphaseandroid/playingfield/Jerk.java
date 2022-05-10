package com.uni.gruppenphaseandroid.playingfield;

public class Jerk extends Figure {

    public Jerk(int id, Color color, Field currentField, Typ typ, FigureUI figureUI) {
        super(id, color, currentField, typ, figureUI);
    }

    public Jerk() {
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
        if (figure2.getTyp() != Typ.JERK) {
            return false;
        }
        return true;
    }

    /**
     * Jerk is allowed to move up to 2 fields less than displayed on the card,
     * if he is moving into the goal area.
     * @param figure1 - figure who moves
     * @param fieldsToMove
     * @return true if moving possible
     */
    @Override
    public boolean checkMoving(Figure figure1, int fieldsToMove) { // TODO: Button implementieren für wieviele Punkte verfallen lassen


        return true;
    }
}
