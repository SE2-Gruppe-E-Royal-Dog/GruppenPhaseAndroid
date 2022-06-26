package com.uni.gruppenphaseandroid.playingfield;

public class King extends Figure {

    public King(int id, Color color, Field currentField, Typ typ, FigureUI figureUI) {
        super(id, color, currentField, typ, figureUI);
        this.typ = Typ.KING;
    }

    public King() {
    }

    /**
     * Degree has to be considered.
     * King can only be overtaken by another king.
     * this figure - figure who moves
     * figure 2 - figure to be overtaken
     * @return true if overtaking possible
     */
    @Override
    public boolean isOvertaking() {
        Field newPosition = getCurrentField().getNextField();
        Figure figure2 = newPosition.getCurrentFigure();

        if(super.isOvertaking()) {
            switch (figure2.getTyp()) { // King is allowed to overtake any figure (exception blocked starting fields)
                case JERK:
                case CITIZEN:
                case KNIGHT:
                case KING:
                    return true;
            }
        } return false;
    }

    /**
     * King can only move 1 to 7 fields and use starting card, magnet card and switch card.
     * Equal card is only allowed, if previous move was also allowed for the king.
     * Starting is allowed with 13-start or 1/11_start.
     * this figure - figure who moves
     * @param fieldsToMove - fields to move
     * @return true if moving possible
     */
    @Override
    public boolean isMoving(int fieldsToMove) {
        if(!(super.isMoving(fieldsToMove)) || fieldsToMove > 7) { // cannot move more than 7 fields
            return false;
        } else {
            return true;
        }
    }
}
