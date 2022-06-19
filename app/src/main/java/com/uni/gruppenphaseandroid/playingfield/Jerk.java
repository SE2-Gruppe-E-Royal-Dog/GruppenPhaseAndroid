package com.uni.gruppenphaseandroid.playingfield;

public class Jerk extends Figure {

    public Jerk(int id, Color color, Field currentField, Typ typ, FigureUI figureUI) {
        super(id, color, currentField, typ, figureUI);
        this.typ = Typ.JERK;
    }

    public Jerk() {
    }

    /**
     * Degree has to be considered.
     * this figure - figure who moves
     * figure 2 - figure to be overtaken
     * @return true if overtaking possible
     */
    @Override
    public boolean isOvertaking() {
        Field newPosition = getCurrentField().getNextField();
        Figure figure2 = newPosition.getCurrentFigure();

        if (super.isOvertaking()) {
            switch (figure2.getTyp()) {
                case JERK:
                    return true;
                case CITIZEN:
                case KNIGHT:
                case KING:
                    return false;
            }
        } return false;
    }

    /**
     * King can only be beaten by another king.
     * Exception: Beating King allowed on foreign StartingField
     * this figure - figure who moves
     * figure 2 - figure to be beaten
     * @return true if beating is possible
     */
    @Override
    public boolean isBeaten() {
        Field newPosition = getCurrentField().getNextField();
        Figure figure2 = newPosition.getCurrentFigure();

        if((super.isBeaten() && isKing(figure2, newPosition)) || !super.isBeaten()) {
            return false;
        } return true;
    }

    /**
     * Jerk is allowed to move up to 2 fields less than displayed on the card,
     * if he is moving into the goal area.
     * this gitfigure1 - figure who moves
     * @param fieldsToMove - fields to move
     * @return new Position of Jerk within Goal Area.
     */
    @Override
    public Field setNewPosition (int fieldsToMove){
        if (isMoving(fieldsToMove)) { //für Testzwecke

            Field positionMinus2 = getCurrentField().getFieldAtDistance(fieldsToMove - 2, getColor());
            Field positionMinus1 = getCurrentField().getFieldAtDistance(fieldsToMove - 1, getColor());
            Field positionNormal = getCurrentField().getFieldAtDistance(fieldsToMove, getColor());

            if (positionNormal instanceof GoalField) {
                return positionNormal;
            } else if (positionMinus1 instanceof GoalField) {
                return positionMinus1;
            } else if (positionMinus2 instanceof GoalField) {
                return positionMinus2;
            } else {
                return positionNormal;
            }
        } else {
            return null;
        }
    }
}
