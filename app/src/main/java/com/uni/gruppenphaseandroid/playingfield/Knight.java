package com.uni.gruppenphaseandroid.playingfield;

public class Knight extends Figure {

    public Knight(int id, Color color, Field currentField, Typ typ, FigureUI figureUI) {
        super(id, color, currentField, typ, figureUI);
        this.typ = Typ.KNIGHT;
    }

    public Knight() {
    }

    /**
     * Degree has to be considered.
     * this figure - figure who moves
     * figure 2 - figure to be overtaken
     * @return true if overtaking possible
     */
    @Override
    public boolean checkOvertaking() {
        Field newPosition = getCurrentField().getNextField();
        Figure figure2 = newPosition.getCurrentFigure();

        if(super.checkOvertaking()) {
            switch (figure2.getTyp()) {
                case JERK:
                case CITIZEN:
                case KNIGHT:
                    return true;
                case KING:
                    return false;
            }
        }
        return false;
    }

    /**
     * King can only be beaten by another king.
     * this figure - figure who moves
     * figure 2 - figure to be beaten
     * @return true if beating is possible
     */
    @Override
    public boolean checkBeaten() {
        Field newPosition = getCurrentField().getNextField();
        Figure figure2 = newPosition.getCurrentFigure();

        if((super.checkBeaten() && figure2.getTyp() == Typ.KING) || !super.checkBeaten()) {
            return false;
        } return true;
    }
}
